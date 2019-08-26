package arrow.meta.kt

import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtDeclaration
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtNamedDeclaration
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtTreeVisitorVoid
import org.jetbrains.kotlin.psi.psiUtil.blockExpressionsOrSingle
import org.jetbrains.kotlin.psi.psiUtil.getSuperNames
import org.jetbrains.kotlin.psi.psiUtil.getValueParameters
import org.jetbrains.kotlin.psi2ir.deparenthesize

fun KtClass.renderValueParameters(): String =
  if (getValueParameters().isEmpty()) ""
  else getValueParameters().joinToString(separator = ", ") { it.text }

fun KtClass.renderSuperTypes(): String =
  superTypeListEntries.joinToString(", ") { it.text }

fun KtClass.renderTypeParametersWithVariance(): String =
  typeParameters.joinToString(separator = ", ") { it.text }

fun KtNamedFunction.renderTypeParametersWithVariance(): String? =
  if (typeParameters.isNotEmpty()) typeParameters.joinToString(separator = ", ") { it.text }
  else null

fun KtNamedFunction.renderValueParameters(): String? =
  if (valueParameters.isEmpty()) null
  else valueParameters.joinToString(separator = ", ") { it.text }

fun KtClassOrObject.renderFunctions(): List<KtNamedDeclaration> =
  declarations.filterIsInstance<KtNamedFunction>()

fun KtClassOrObject.renderProperties(): List<KtNamedDeclaration> =
  declarations.filterIsInstance<KtProperty>()

fun KtClassOrObject.renderPrimaryParameters(): List<KtNamedDeclaration> =
  primaryConstructorParameters.filter { it.hasValOrVar() }.fold(emptyList())
  { acc, ktParameter -> acc + (ktParameter as KtNamedDeclaration) }


fun KtFunction.body(): KtExpression? =
  bodyExpression ?: bodyBlockExpression

fun KtFunction.bodyExpressionText(): String =
  bodyBlockExpression?.blockExpressionsOrSingle()
    ?.joinToString("\n") { it.deparenthesize().text }
    ?.trim()
    ?: bodyExpression?.text
    ?: ""

fun KtElement.dfs(f: (KtElement) -> Boolean): List<KtElement> {
  val found = arrayListOf<KtElement>()
  accept(object : KtTreeVisitorVoid() {
    override fun visitKtElement(element: KtElement) {
      val result = f(element)
      if (result) found.add(element)
      super.visitKtElement(element)
    }
  })
  return found
}

fun String.removeReturn(): String =
  replace("return ", "")

data class SealedSubclass(val simpleName: Name, val fqName: FqName?, val typeVariables: List<String>)

fun KtClass.sealedSubclasses(): List<SealedSubclass> =
  innerSealedSubclasses() + outerSealedSubclasses()

fun List<KtDeclaration>.sealedVariants(superKt: KtClass): List<SealedSubclass> =
  filter {
    (it is KtClassOrObject) && it.getSuperNames().contains(superKt.nameAsSafeName.identifier)
  }.map { it as KtClassOrObject }.map {
    SealedSubclass(
      simpleName = it.nameAsSafeName,
      fqName = it.fqName,
      typeVariables = if (it is KtClass) it.renderTypeParameters else emptyList()
    )
  }

private fun KtClass.innerSealedSubclasses(): List<SealedSubclass> =
  declarations.sealedVariants(this)

private fun KtClass.outerSealedSubclasses(): List<SealedSubclass> =
  containingKtFile.declarations.sealedVariants(this)

val KtClass.renderTypeParameters: List<String>
  get() = typeParameters.map { it.nameAsSafeName.identifier }.map {
    it.replace("out ", "")
      .replace("in ", "")
  }
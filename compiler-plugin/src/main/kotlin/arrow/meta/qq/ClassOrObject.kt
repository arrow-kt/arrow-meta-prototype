package arrow.meta.qq

import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtNamedDeclaration
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.psiUtil.findFunctionByName
import org.jetbrains.kotlin.psi.psiUtil.getValueParameters
import org.jetbrains.kotlin.psi.psiUtil.modalityModifierType
import org.jetbrains.kotlin.psi.psiUtil.visibilityModifierType

interface ClassOrObject : Quote<KtElement, KtClass, ClassOrObject.ClassScope> {

  class ClassScope(
    val modality: Name,
    val visibility: Name,
    val kind: Name,
    val name: Name,
    val typeParameters: Name,
    val valueParameters: Name,
    val supertypes: Name,
    //val sealedVariants: List<String>,
    val body: Name
    // val functions: List<Name>,
    //val primaryConstructorParameters: List<Name>,
    //val properties: List<Name>
  )

  override fun transform(ktElement: KtClass): ClassScope =
    ClassScope(
      modality = Name.identifier(ktElement.modalityModifierType()?.value.orEmpty()),
      visibility = Name.identifier(ktElement.visibilityModifierType()?.value.orEmpty()),
      kind = Name.identifier(ktElement.getClassOrInterfaceKeyword()?.text.orEmpty()),
      name = Name.identifier(ktElement.nameAsSafeName.identifier),
      typeParameters = Name.identifier(ktElement.renderTypeParametersWithVariance()),
      valueParameters = if (ktElement.isInterface()) Name.identifier("")
      else Name.identifier(ktElement.renderValueParameters()),
      supertypes = Name.identifier(ktElement.renderSuperTypes()),
      body = Name.identifier(ktElement.body?.text?.drop(1)?.dropLast(1).orEmpty())
      //functions = ktElement.renderFunctions().map { it.nameAsSafeName },
      //properties = ktElement.renderProperties().map { it.nameAsSafeName }
      //primaryConstructorParameters = ktElement.renderPrimaryParameters().map { it.nameAsSafeName }
      //sealedVariants = ktElement.sealedSubclasses()
    )


  override fun KtClass.cleanUserQuote(quoteDeclaration: String): String =
    quoteDeclaration.trimMargin().let {
      if (isInterface()) it.replace("interface (.*?)\\(\\)".toRegex(), "interface $1")
      else it
    }

  fun KtClass.sealedSubclasses(): List<String> = try {
    val con = fqName?.run { quasiQuoteContext.compilerContext.getStoredDescriptor(this) }?.sealedSubclasses?.filterIsInstance<ClassDescriptor>()
    listOf("${con?.isEmpty()}")
  } catch (e: IllegalStateException) {
    listOf("exception")
  } catch (e: RuntimeException) {
    listOf("runexc")
  } catch (e: Exception) {
    listOf("what")
  } finally {
    listOf("")
  }
/*
*  .filter { it in quasiQuoteContext.compilerContext.storedDescriptors() }*/
  /*containingKtFile.declarations
    .filter { it is KtClassOrObject && (it as KtClass).renderSuperTypes().contains((this as KtNamedDeclaration).nameAsSafeName.toString()) }
    .map { it as KtNamedDeclaration }*/
  // (this as KtClass).run { this::class.sealedSubclasses.filter { (it as KtClassOrObject).superTypeListEntries.contains(this@ClassOrObject as KtSuperTypeListEntry) } }

  override fun parse(template: String): KtClass =
    quasiQuoteContext.compilerContext.ktPsiElementFactory.createClass(template)

  fun KtClass.renderValueParameters(): String =
    if (getValueParameters().isEmpty()) ""
    else getValueParameters().joinToString(separator = ", ") { it.text }

  fun KtClass.renderSuperTypes(): String =
    superTypeListEntries.joinToString(", ") { it.name.orEmpty() }

  fun KtClass.renderTypeParametersWithVariance(): String =
    typeParameters.joinToString(separator = ", ") { it.text }

  fun KtClass.hasFunction(f: String): Boolean =
    findFunctionByName(f) != null

  fun KtClassOrObject.renderFunctions(): List<KtNamedDeclaration> =
    declarations.filter { it is KtNamedFunction }.map { it as KtNamedDeclaration }

  fun KtClassOrObject.renderProperties(): List<KtNamedDeclaration> =
    declarations.filter { it is KtProperty }.map { it as KtNamedDeclaration }

  fun KtClassOrObject.renderPrimaryParameters(): List<KtNamedDeclaration> =
    primaryConstructorParameters.filter { it.hasValOrVar() }.fold(emptyList())
    { acc, ktParameter -> acc + (ktParameter as KtNamedDeclaration) }

  companion object : Quote.Factory<KtElement, KtClass, ClassScope> {
    override operator fun invoke(
      quasiQuoteContext: QuasiQuoteContext,
      containingDeclaration: KtElement,
      match: KtClass.() -> Boolean,
      map: ClassScope.(quotedTemplate: KtClass) -> List<String>
    ): ClassOrObject =
      object : ClassOrObject {
        override val quasiQuoteContext: QuasiQuoteContext = quasiQuoteContext
        override fun KtClass.match(): Boolean = match(this)
        override fun ClassScope.map(quotedTemplate: KtClass): List<String> = map(quotedTemplate)
        override val containingDeclaration: KtElement = containingDeclaration
      }
  }
}
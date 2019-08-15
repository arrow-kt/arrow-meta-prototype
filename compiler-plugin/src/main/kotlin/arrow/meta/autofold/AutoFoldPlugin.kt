package arrow.meta.autofold

import arrow.meta.extensions.ExtensionPhase
import arrow.meta.extensions.MetaComponentRegistrar
import arrow.meta.higherkind.arity
import arrow.meta.higherkind.invariant
import arrow.meta.qq.classOrObject
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtDeclaration
import org.jetbrains.kotlin.psi.psiUtil.findFunctionByName
import org.jetbrains.kotlin.psi.psiUtil.getSuperNames

val MetaComponentRegistrar.autoFold: List<ExtensionPhase>
  get() =
    meta(
      classOrObject(::isAutoFoldable) { c ->
        println("Processing Sealed class: ${c.name}")
        val returnType = typeParameters.invariant.last().inc() // TODO: Needs to be extended to whole words for cases where TypeVariables look like this: AP, FF
        sealedVariants.forEach(::println)
        val typeInfo = Name.identifier(c.renderTypeParameters()).identifier
        listOfNotNull(
          if (sealedVariants.any { it.typeVariables.split(',').size > c.arity })
          // TODO: bail here or tell User this sealed Class can't have a fold function
            """
              |$visibility $modality $kind $name<$typeParameters>($valueParameters)${supertypes.identifier.doIf(String::isNotEmpty) { " : $it" }} {
              |  $body
              |}
            """.trimMargin()
          else
            """
              |$visibility $modality $kind $name<$typeParameters>($valueParameters)${supertypes.identifier.doIf(String::isNotEmpty) { " : $it" }} {
              |  ${body.asString().trimMargin()}
              |  inline fun <${typeInfo.doIf(String::isNotEmpty) { "$it, " }}$returnType> ${c.name}${typeInfo.doIf(String::isNotEmpty) { "<$it>" }}.fold(
              |  ${sealedVariants.params(returnType)}
              |  ): $returnType = when(val x = this) {
              |  ${sealedVariants.patternMatch()}
              |  }
              |}
            """.trimMargin()
        )
      }
    )

private fun KtClass.hasFoldFunction(): Boolean =
  findFunctionByName("fold") != null

private fun isAutoFoldable(ktClass: KtClass): Boolean =
  ktClass.isSealed() && !ktClass.isAnnotation() &&
    ktClass.fqName?.asString()?.startsWith("arrow.Kind") != true &&
    !ktClass.hasFoldFunction() && ktClass.sealedSubclasses().isNotEmpty()

data class SealedSubclass(val simpleName: Name, val fqName: FqName?, val typeVariables: String) // add typeVariable with <>

fun KtClass.sealedSubclasses(): List<SealedSubclass> =
  innerSealedSubclasses() + outerSealedSubclasses()

fun List<KtDeclaration>.sealedVariants(superKt: KtClass): List<SealedSubclass> =
  filter {
    (it is KtClassOrObject) && it.getSuperNames().contains(superKt.nameAsSafeName.identifier)
  }.map { it as KtClassOrObject }.map {
    SealedSubclass(
      simpleName = it.nameAsSafeName,
      fqName = it.fqName,
      typeVariables = if (it is KtClass) it.renderTypeParameters() else ""
    )
  }

fun KtClass.innerSealedSubclasses(): List<SealedSubclass> =
  declarations.sealedVariants(this)

fun KtClass.outerSealedSubclasses(): List<SealedSubclass> =
  containingKtFile.declarations.sealedVariants(this)

private fun KtClass.renderTypeParameters(): String =
  Name.identifier(typeParameters.joinToString(separator = ", ") { it.text }).invariant


inline fun <T> T.doIf(cond: T.() -> Boolean, doIt: (T) -> T): T =
  if (cond(this)) doIt(this) else this

private fun List<SealedSubclass>.patternMatch(): String = // works
  joinToString(
    transform = { "  is ${it.simpleName.identifier} -> ${it.simpleName.identifier.decapitalize()}(x)" },
    separator = "\n  ")

private fun List<SealedSubclass>.params(returns: Char): String =
  joinToString(
    transform = { "  crossinline ${it.simpleName.identifier.decapitalize()}: (${it.simpleName.identifier}${it.typeVariables.doIf(String::isNotEmpty) { "<$it>" }}) -> $returns" }
    , separator = ",\n  ")
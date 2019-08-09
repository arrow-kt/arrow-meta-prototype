package arrow.meta.autofold

import arrow.meta.extensions.ExtensionPhase
import arrow.meta.extensions.MetaComponentRegistrar
import arrow.meta.higherkind.arity
import arrow.meta.higherkind.invariant
import arrow.meta.qq.ClassOrObject
import arrow.meta.qq.classOrObject
import org.jetbrains.kotlin.backend.common.onlyIf
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtDeclaration
import org.jetbrains.kotlin.psi.KtNamedDeclaration
import org.jetbrains.kotlin.psi.psiUtil.findFunctionByName
import org.jetbrains.kotlin.psi.psiUtil.getSuperNames
import org.jetbrains.kotlin.psi.psiUtil.isObjectLiteral
import org.jetbrains.kotlin.resolve.descriptorUtil.fqNameSafe

val MetaComponentRegistrar.autoFold: List<ExtensionPhase>
  get() =
    meta(
      classOrObject(::isAutoFoldable) { c ->
        println("Processing Sealed class: ${c.name}")
        val returnType = typeParameters.invariant.last().inc() // TODO: Needs to be extended to whole words
        val variants = c.sealedSubclasses()
        val reflVeriants = c.reflectionBasedSealedSubclasses().map { (it as KtNamedDeclaration).nameAsSafeName.asString() }
        println("${c.name}'s reflectionBased Sealed Classes")
        println(reflVeriants)
        val typeInfo = Name.identifier(c.renderTypeParameters()).identifier
        val ss = classDescriptor.findSealedSubclasses()
        println("new SealedClasses: $ss")
        if (variants.any { it.typeVariables.split(' ').size > c.arity })
        // TODO: bail here
          listOfNotNull("BAILED!!!")
        else {
          listOfNotNull(
            """
            |inline fun <${typeInfo.doIf(String::isNotEmpty) { "$typeInfo, " }}$returnType> ${c.fqName}${typeInfo.doIf(String::isNotEmpty) { "<$it>" }}.fold(
            |${variants.params(returnType)}
            |): $returnType = when(this) {
            |${variants.patternMatch()}
            |}
          """.trimMargin()
          )
        }
      }
    )

private fun KtClass.hasFoldFunction(): Boolean =
  findFunctionByName("fold") != null

private fun isAutoFoldable(ktClass: KtClass): Boolean =
  ktClass.isSealed() && !ktClass.isAnnotation() &&
    ktClass.fqName?.asString()?.startsWith("arrow.Kind") != true &&
    !ktClass.hasFoldFunction() && ktClass.sealedSubclasses().isNotEmpty()

data class SealedSubclass(val simpleName: Name, val fqName: FqName?, val typeVariables: String) // add typeVariable with <>

/*
 AFAIk there are 2 ways to get all sealedSubclasses:
 - get the ClassDescriptor thorough the compilerContext and check it's property for [findSealedSubclasses]
 - directly get the ClassDescriptor through reflection... in most instances [::reflectionBasedSealedSubclasses]
 */
fun KtClass.sealedSubclasses(): List<SealedSubclass> {
  val s = reflectionBasedSealedSubclasses()
  println(s)
  return innerSealedSubclasses()
}

fun KtClass.reflectionBasedSealedSubclasses(): List<KtDeclaration> {
  val b = this::class.sealedSubclasses
    .map { it as KtDeclaration }
  println(b.map { it as KtNamedDeclaration }.map { it.nameAsSafeName.asString() })
  val c = b.map { (it as KtClassOrObject).getSuperNames() }
  println(c)
  return b.filter { it is KtClassOrObject && it.getSuperNames().contains<String>((this as KtNamedDeclaration).nameAsSafeName.toString()) }
}

fun KtClass.innerSealedSubclasses(): List<SealedSubclass> =
  declarations.filter {
    (it is KtClassOrObject) && it.getSuperNames().contains(this.nameAsSafeName.identifier)
  }.map { it as KtClassOrObject }.map {
    SealedSubclass(
      simpleName = it.nameAsSafeName,
      fqName = it.fqName,
      typeVariables = if (it is KtClass) it.renderTypeParameters() else ""
    )
  }

fun ClassDescriptor?.findSealedSubclasses()/*:  List<SealedSubclass> */ =
  if (this != null) {
    println(sealedSubclasses.size)
    sealedSubclasses.forEach { println("classDesc: ${it.typeConstructor}") }
    sealedSubclasses.map { SealedSubclass(it.name, it.fqNameSafe, if (it is KtClass) it.renderTypeParameters() else "") }
  } else emptyList()


private fun KtClass.renderTypeParameters(): String =
  Name.identifier(typeParameters.joinToString(separator = ", ") { it.text }).invariant


inline fun <T> T.doIf(cond: T.() -> Boolean, doIt: (T) -> T): T =
  if (cond(this)) doIt(this) else this

private fun List<SealedSubclass>.patternMatch(): String = // works
  joinToString(
    transform = { "  is ${it.fqName} -> ${it.simpleName.identifier.decapitalize()}(this)" },
    separator = "\n")

private fun List<SealedSubclass>.params(returns: Char): String =
  joinToString(
    transform = { "  crossinline ${it.simpleName.identifier.decapitalize()}: (${it.fqName}${it.typeVariables.doIf(String::isNotEmpty) { "<$it>" }}) -> $returns" }
    , separator = ",\n")
/*
inline fun <A, B> `higherkinds`.`Expr`<A>.fold(
  crossinline const: (`higherkinds`.`Const`) -> B,
  crossinline sum: (`higherkinds`.`Sum`<A>) -> B,
  crossinline notANumber: (`higherkinds`.`NotANumber`) -> B
): B = when (this) {
  is `higherkinds`.`Const` -> `const`(this)
  is `higherkinds`.`Sum` -> `sum`(this)
  is `higherkinds`.`NotANumber` -> `notANumber`(this)
}*/
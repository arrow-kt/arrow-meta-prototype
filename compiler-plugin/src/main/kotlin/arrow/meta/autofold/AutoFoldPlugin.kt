package arrow.meta.autofold

import arrow.meta.extensions.ExtensionPhase
import arrow.meta.extensions.MetaComponentRegistrar
import arrow.meta.qq.classOrObject
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.psiUtil.findFunctionByName
import org.jetbrains.kotlin.psi.psiUtil.getSuperNames

val MetaComponentRegistrar.autoFold: List<ExtensionPhase>
  get() =
    meta(
      classOrObject(::isAutoFoldable) { c ->
        println("Processing Sealed class: ${c.name}")
        //functions.forEach(::println)
        // println("${c.name}: modality = $modality")
        // sealedVariants.take(4).forEach(::println)
        // val returnType = typeParameters.invariant.last().inc() // supports currently arity of 24
        listOfNotNull(
          /*"""
            |inline fun <$returnType> fold(
            |  crossinline
            |): $returnType = when(this) {
            |
            |}
          """.trimIndent()*/
          "val for: Nothing = TODO()"
        )
      }
    )

/* Prototype for arity >  24
val String.nextTypeParameter : String
  get() = substringAfterLast(' ')
    .apply { "${dropLast(1)}${last().inc()}" }*/

private fun KtClass.hasFoldFunction(): Boolean =
  findFunctionByName("fold") != null

private fun isAutoFoldable(ktClass: KtClass): Boolean =
  ktClass.isSealed() && !ktClass.isAnnotation() &&
    ktClass.fqName?.asString()?.startsWith("arrow.Kind") != true &&
    ktClass.getSuperNames().isNotEmpty() && !ktClass.hasFoldFunction()

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
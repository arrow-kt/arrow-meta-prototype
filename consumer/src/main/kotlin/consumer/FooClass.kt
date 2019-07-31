package consumer

import arrow.Kind
import arrow.`*`

/** HigherKinds **/
sealed class Option<out A> {
  object None : Option<Nothing>()
  data class Some<out A>(val value: A) : Option<A>()
  fun <B> map(f: (A) -> B): Option<B> =
    when (this) {
      None -> None
      is Some -> Some(f(value))
    }
}

sealed class Either<out A, out B> {
  class Left<out A> : Either<A, Nothing>()
  class Right<out B>(val value: B) : Either<Nothing, B>()
  // Out of the box Autofold for sealedSubClasses within the sealed Class
  /*inline fun <A, B, C> consumer.Either<A, B>.fold(
    crossinline left: (consumer.Either.Left<A>) -> C,
    crossinline right: (consumer.Either.Right<B>) -> C
  ): C = when(this) {
    is consumer.Either.Left -> left(this)
    is consumer.Either.Right -> right(this)
  }*/
}

open class Kleisli<out F, out D, out A>

/** Type Classes **/

interface Functor<F> {
  fun <A, B> Kind<F, A>.map(f: (A) -> B): Kind<F, B>
}

//@extension
//class OptionFunctor : Functor<ForOption> {
//  override fun <A, B> OptionOf<A>.map(f: (A) -> B): Option<B> =
//    TODO()
//}

sealed class Expr<A> {
  fun done() = 3
  inline fun <B> fold( // still WIP
    const: (Const) -> B,
    sum: (Sum<A>) -> B,
    notANumber: (NotANumber) -> B
  ): B = when (this) {
    is Const -> const(this)
    is Sum -> sum(this)
    is NotANumber -> notANumber(this)
  }
}

fun <A> Expr<A>.fold(): Nothing = TODO()
data class Const(val number: Double) : Expr<Int>() // Not included Subclass
data class Sum<A>(val e1: Expr<A>, val e2: Expr<A>) : Expr<A>() // Not included Subclass
object NotANumber : Expr<Nothing>() // Not included Subclass
/*
fun functorForOption(): Functor<ForOption> = object : Functor<ForOption> {
  override fun <A, B> OptionOf<A>.map(f: (A) -> B): Option<B> =
    (this as Option<A>).map(f)
}

fun <F> Kind<F, Int>.addOne(FF: Functor<F> = `*`): Kind<F, Int> =
  map { it + 1 }

class Service {
  fun <F> Kind<F, Int>.addOne(FF: Functor<F> = `*`): Kind<F, Int> =
    map { it + 1 }
}

fun testConversion(): Any =
  Option.Some(1).addOne()*/
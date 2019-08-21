package consumer

import arrow.Kind
import arrow.`*`

/** HigherKinds **/
/** AutoFold **/
sealed class Option<out A> {
  object None : Option<Nothing>()
  data class Some<out A>(val value: A) : Option<A>()

  fun <B> map(f: (A) -> B): Option<B> =
    when (this) {
      None -> None
      is Some -> Some(f(value))
    }

  fun <B> flatMap(f: (A) -> Option<B>): Option<B> =
    when (this) {
      None -> None
      is Some -> f(value)
    }
}

sealed class Either<out A, out B> {
  class Left<out A> : Either<A, Nothing>()
  class Right<out B>(val value: B) : Either<Nothing, B>()
}

sealed class Expr<out A, B>
data class Const(val number: Double) : Expr<Int, Int>()
data class Sum<C>(val e1: Expr<Int, C>, val e2: Expr<Int, C>) : Expr<Int, C>()
object NotANumber : Expr<Nothing, Nothing>()

class Kleisli<out F, out D, out A>

/** Type Classes **/

interface Functor<F> {
  fun <A, B> Kind<F, A>.map(f: (A) -> B): Kind<F, B>
  fun <A, B> Kind<F, A>.flatMap(f: (A) -> Kind<F, B>): Kind<F, B>
}

//fun functorForOption(): Functor<ForOption> = object : Functor<ForOption> {
//  override fun <A, B> OptionOf<A>.map(f: (A) -> B): Option<B> =
//    (this as Option<A>).map(f)
//}

//val FunctorForOption: Functor<ForOption> = object : Functor<ForOption> {
//  override fun <A, B> OptionOf<A>.map(f: (A) -> B): Option<B> =
//    (this as Option<A>).map(f)
//}

//class FunctorForOption: Functor<ForOption> {
//  override fun <A, B> OptionOf<A>.map(f: (A) -> B): Option<B> =
//    (this as Option<A>).map(f)
//}

object FunctorForOption: Functor<ForOption> {
  override fun <A, B> OptionOf<A>.map(f: (A) -> B): Option<B> =
    (this as Option<A>).map(f)
}

fun <F> Kind<F, Int>.addOne(FF: Functor<F> = `*`): Kind<F, Int> =
  map { it + 1 }
//
//class Service {
//  fun <F> Kind<F, Int>.addOne(FF: Functor<F> = `*`): Kind<F, Int> =
//    map { it + 1 }
//} TODO support nested functions that are not top level in injections through arrow meta

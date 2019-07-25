package consumer

import arrow.Kind
import arrow.extension

/** HigherKinds **/
sealed class Option<out A> {
  object None : Option<Nothing>()
  data class Some<out A>(val value: A) : Option<A>()

  fun goT() = 4f
}

sealed class Either<out A, out B> {
  class Left<out A> : Either<A, Nothing>()
  class Right<out B>(val value: B) : Either<Nothing, B>()
}

class Kleisli<out F, out D, out A>

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
  inline fun <B> fold(
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
data class Const(val number: Double) : Expr<Int>()
data class Sum<A>(val e1: Expr<A>, val e2: Expr<A>) : Expr<A>()
object NotANumber : Expr<Nothing>()
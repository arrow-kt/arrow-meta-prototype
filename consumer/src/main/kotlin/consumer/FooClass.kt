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

class Kleisli<out F, out D, out A>

/** Type Classes **/

interface Functor<F> {
  fun <A, B> Kind<F, A>.map(f: (A) -> B): Kind<F, B>
  fun <A, B> Kind<F, A>.flatMap(f: (A) -> Kind<F, B>): Kind<F, B>
}

sealed class Expr<out A, B>
data class Const(val number: Double) : Expr<Int, Int>()
data class Sum<C>(val e1: Expr<Int, C>, val e2: Expr<Int, C>) : Expr<Int, C>()
object NotANumber : Expr<Nothing, Nothing>()


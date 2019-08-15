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
}

data class Const(val number: Double) : Expr<Int>()
data class Sum<D>(val e1: Expr<Int>, val e2: Expr<Int>) : Expr<Int>() // TODO: add parametertypes to fold
object NotANumber : Expr<Nothing>()
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
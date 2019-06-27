package arrow.sample

import arrow.extension
import arrow.sample.Contained.add
import arrow.with

interface Semigroup<A> {
  fun A.combine(other: A): A
  fun foo(): Unit = TODO() // adding new functions
}

class MyNumber(val value: Int) {
  companion object {
    @extension
    interface MyNumberSemigroup : Semigroup<MyNumber> {
      override fun MyNumber.combine(other: MyNumber): MyNumber =
        MyNumber(value + other.value)
    }
  }
}


val extension: Nothing
  get() = throw RuntimeException("Call not processed by arrow meta typeclass support")

object Contained {

  fun <A> add(a: A, b: A, @with S: Semigroup<A>): A {
    foo() // <- this function is added to the Function Body Resolvers scope at compile time
    a.combine(b)
  }

}

object Invocation {
  @JvmStatic
  fun main(args: Array<String>) {
    add(MyNumber(1), MyNumber(2))
  }
}
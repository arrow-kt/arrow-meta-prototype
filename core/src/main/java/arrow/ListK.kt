/*
package arrow.core

interface Kind<out F, out A>
interface Kind2<out F, out A, out B>
class ForTuple2 private constructor() {
  companion object
}
typealias Tuple2Of<A, B> = Kind2<ForTuple2, A, B>
typealias Tuple2PartialOf<A> = Kind<ForTuple2, A>

@Suppress("UNCHECKED_CAST", "NOTHING_TO_INLINE")
inline fun <A, B> Tuple2Of<A, B>.fix(): Tuple2<A, B> =
  this as Tuple2<A, B>

infix fun <A, B> A.toT(b: B): Tuple2<A, B> = Tuple2(this, b)

class ForListK private constructor() {
  companion object
}
typealias ListKOf<A> = Kind<ForListK, A>

@Suppress("UNCHECKED_CAST", "NOTHING_TO_INLINE")
inline fun <A> ListKOf<A>.fix(): ListK<A> =
  this as ListK<A>

*/
/**
 * A wrapper data type also considered by the @extension mechanisms to forward type class
 * instance methods into both the wrapper and the wrapped data type. Ex. List<A>#foldMap(M: Monoid<A>)
 *
 * A data type is considered a wrapper if
 * - It contains a single constructor with one parameter whose type is a type constructor with one type parameter
 * - It's a sub type of the type it wraps
 *
 * The type class @extension mechanism will project then all syntax generated for the Wrapper also into the Wrapped
 * type constructor as extension functions.
 *//*

data class ListK<out A>(private val list: List<A>) : ListKOf<A>, List<A> by list {

  fun <B> flatMap(f: (A) -> ListKOf<B>): ListK<B> = list.flatMap { f(it).fix().list }.k()

  fun <B> map(f: (A) -> B): ListK<B> = list.map(f).k()

  fun <B> foldLeft(b: B, f: (B, A) -> B): B = fold(b, f)

  override fun equals(other: Any?): Boolean =
    when (other) {
      is ListK<*> -> this.list == other.list
      is List<*> -> this.list == other
      else -> false
    }

  fun <B, Z> map2(fb: ListKOf<B>, f: (Tuple2<A, B>) -> Z): ListK<Z> =
    flatMap { a ->
      fb.fix().map { b ->
        f(Tuple2(a, b))
      }
    }

  override fun hashCode(): Int = list.hashCode()

  companion object {

    fun <A> just(a: A): ListK<A> = listOf(a).k()

    fun <A> empty(): ListK<A> = emptyList<A>().k()
  }
}

fun <A> ListKOf<A>.combineK(y: ListKOf<A>): ListK<A> =
  (fix() + y.fix()).k()

fun <A> List<A>.k(): ListK<A> = ListK(this)
data class Tuple2<out A, out B>(val a: A, val b: B) : Tuple2Of<A, B> {
  fun <C> map(f: (B) -> C) =
    a toT f(b)

  fun <C, D> bimap(fl: (A) -> C, fr: (B) -> D) =
    fl(a) toT fr(b)

  fun <C> ap(f: Tuple2Of<*, (B) -> C>) =
    map(f.fix().b)

  fun <C> flatMap(f: (B) -> Tuple2Of<@UnsafeVariance A, C>) =
    f(b).fix()

  fun <C> coflatMap(f: (Tuple2Of<A, B>) -> C) =
    a toT f(this)

  fun extract() =
    b

  fun <C> foldL(b: C, f: (C, B) -> C) =
    f(b, this.b)

  fun reverse(): Tuple2<B, A> = Tuple2(b, a)

  companion object
}
*/

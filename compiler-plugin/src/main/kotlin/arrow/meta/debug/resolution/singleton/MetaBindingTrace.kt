package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.diagnostics.Diagnostic
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.util.slicedMap.ReadOnlySlice
import org.jetbrains.kotlin.util.slicedMap.WritableSlice

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaBindingTrace(val delegate: BindingTrace) : BindingTrace by delegate {
  override fun report(p0: Diagnostic) {
    println("MetaBindingTrace.report: $p0")
    delegate.report(p0)
  }

  override fun <K : Any?, V : Any?> getKeys(p0: WritableSlice<K, V>?): MutableCollection<K> {
    println("MetaBindingTrace.getKeys: $p0")
    return delegate.getKeys(p0)
  }

  override fun getBindingContext(): BindingContext {
    println("MetaBindingTrace.getBindingContext:")
    return delegate.bindingContext
  }

  override fun <K : Any?, V : Any?> record(p0: WritableSlice<K, V>?, p1: K, p2: V) {
    println("MetaBindingTrace.record: $p0, $p1, $p2")
    delegate.record(p0, p1, p2)
  }

  override fun <K : Any?> record(p0: WritableSlice<K, Boolean>?, p1: K) {
    println("MetaBindingTrace.record: $p0, $p1")
    delegate.record(p0, p1)
  }

  override fun getType(p0: KtExpression): KotlinType? {
    println("MetaBindingTrace.getType:")
    return delegate.getType(p0)
  }

  override fun wantsDiagnostics(): Boolean {
    println("MetaBindingTrace.wantsDiagnostics:")
    return delegate.wantsDiagnostics()
  }

  override fun <K : Any?, V : Any?> get(p0: ReadOnlySlice<K, V>?, p1: K): V? {
    println("MetaBindingTrace.get: $p0, $p1")
    return delegate.get(p0, p1)
  }

  override fun recordType(p0: KtExpression, p1: KotlinType?) {
    println("MetaBindingTrace.recordType: $p0, $p1")
    delegate.recordType(p0, p1)
  }
}
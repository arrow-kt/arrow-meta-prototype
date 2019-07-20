package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.storage.CacheWithNotNullValues
import org.jetbrains.kotlin.storage.CacheWithNullableValues
import org.jetbrains.kotlin.storage.LockBasedStorageManager
import org.jetbrains.kotlin.storage.MemoizedFunctionToNotNull
import org.jetbrains.kotlin.storage.MemoizedFunctionToNullable
import org.jetbrains.kotlin.storage.NotNullLazyValue
import org.jetbrains.kotlin.storage.NullableLazyValue
import java.util.concurrent.ConcurrentMap

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaLockBasedStorageManager(debugText: String) : LockBasedStorageManager(debugText) {
  override fun <T : Any> createNullableLazyValueWithPostCompute(computable: () -> T?, postCompute: (T?) -> Unit): NullableLazyValue<T> {
    println("MetaLockBasedStorageManager.createNullableLazyValueWithPostCompute: $computable, $postCompute")
    return super.createNullableLazyValueWithPostCompute(computable, postCompute)
  }

  override fun <K : Any, V : Any> createMemoizedFunctionWithNullableValues(compute: (K) -> V?): MemoizedFunctionToNullable<K, V> {
    println("MetaLockBasedStorageManager.createMemoizedFunctionWithNullableValues: $compute")
    return super.createMemoizedFunctionWithNullableValues(compute)
  }

  override fun <K : Any, V : Any> createMemoizedFunctionWithNullableValues(compute: (K) -> V, map: ConcurrentMap<K, Any>): MemoizedFunctionToNullable<K, V> {
    println("MetaLockBasedStorageManager.createMemoizedFunctionWithNullableValues: $compute, $map")
    return super.createMemoizedFunctionWithNullableValues(compute, map)
  }

  override fun <T : Any> createNullableLazyValue(computable: () -> T?): NullableLazyValue<T> {
    println("MetaLockBasedStorageManager.createNullableLazyValue: $computable")
    return super.createNullableLazyValue(computable)
  }

  override fun <T : Any> createRecursionTolerantLazyValue(computable: () -> T, onRecursiveCall: T): NotNullLazyValue<T> {
    println("MetaLockBasedStorageManager.createRecursionTolerantLazyValue: $computable, $onRecursiveCall")
    return super.createRecursionTolerantLazyValue(computable, onRecursiveCall)
  }

  override fun <T : Any> compute(computable: () -> T): T {
    println("MetaLockBasedStorageManager.compute: $computable")
    return super.compute(computable)
  }

  override fun <K : Any, V : Any> createMemoizedFunction(compute: (K) -> V): MemoizedFunctionToNotNull<K, V> {
    println("MetaLockBasedStorageManager.createMemoizedFunction: $compute")
    return super.createMemoizedFunction(compute)
  }

  override fun <K : Any, V : Any> createMemoizedFunction(compute: (K) -> V, map: ConcurrentMap<K, Any>): MemoizedFunctionToNotNull<K, V> {
    println("MetaLockBasedStorageManager.createMemoizedFunction: $compute, $map")
    return super.createMemoizedFunction(compute, map)
  }

  override fun <K : Any, V : Any> createCacheWithNotNullValues(): CacheWithNotNullValues<K, V> {
    println("MetaLockBasedStorageManager.createCacheWithNotNullValues:")
    return super.createCacheWithNotNullValues()
  }

  /* override fun <T : Any> recursionDetectedDefault(): RecursionDetectedResult<T> {
     println("MetaLockBasedStorageManager.")
     return super.recursionDetectedDefault()
   }*/

  override fun <K : Any, V : Any> createCacheWithNullableValues(): CacheWithNullableValues<K, V> {
    println("MetaLockBasedStorageManager.createCacheWithNullableValues:")
    return super.createCacheWithNullableValues()
  }

  override fun <T : Any> createLazyValueWithPostCompute(computable: () -> T, onRecursiveCall: ((Boolean) -> T)?, postCompute: (T) -> Unit): NotNullLazyValue<T> {
    println("MetaLockBasedStorageManager.createLazyValueWithPostCompute: $computable, $onRecursiveCall, $postCompute")
    return super.createLazyValueWithPostCompute(computable, onRecursiveCall, postCompute)
  }

  override fun <T : Any> createLazyValue(computable: () -> T): NotNullLazyValue<T> {
    println("MetaLockBasedStorageManager.createLazyValue:")
    return super.createLazyValue(computable)
  }

  override fun <T : Any> createRecursionTolerantNullableLazyValue(computable: () -> T?, onRecursiveCall: T?): NullableLazyValue<T> {
    println("MetaLockBasedStorageManager.createRecursionTolerantNullableLazyValue: $computable, $onRecursiveCall")
    return super.createRecursionTolerantNullableLazyValue(computable, onRecursiveCall)
  }
}

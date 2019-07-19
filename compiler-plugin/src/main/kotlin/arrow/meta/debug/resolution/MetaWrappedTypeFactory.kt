package arrow.meta.debug.resolution

import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.storage.StorageManager
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.WrappedTypeFactory

/**
 * check out setupAdHocResolve in [org.jetbrains.kotlin.idea.caches.lightClasses.IDELightClassContexts]
 */
class MetaWrappedTypeFactory(val storageManager: StorageManager) : WrappedTypeFactory(storageManager) {
  override fun createDeferredType(trace: BindingTrace, computation: () -> KotlinType): KotlinType {
    println("MetaWrappedTypeFactory.createDeferredType: $trace, $computation")
    return super.createDeferredType(trace, computation)
  }

  override fun createRecursionIntolerantDeferredType(trace: BindingTrace, computation: () -> KotlinType): KotlinType {
    println("MetaWrappedTypeFactory.createRecursionIntolerantDeferredType: $trace, $computation")
    return super.createRecursionIntolerantDeferredType(trace, computation)
  }
}
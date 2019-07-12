package arrow.meta.debug.unusedServices

import org.jetbrains.kotlin.context.GlobalContext
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.incremental.components.LookupLocation
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.resolve.lazy.AbsentDescriptorHandler
import org.jetbrains.kotlin.resolve.lazy.LazyDeclarationResolver
import org.jetbrains.kotlin.resolve.lazy.TopLevelDescriptorProvider

/**
 * @UnusedService Currently we need to investigate how our costume functions can be executed.
 * Meaning we need to hook into the compiler earlier than Analysis. Either with Reflection or else
 */
class MetaLazyDeclarationResolver(
  globalContext: GlobalContext,
  trace: BindingTrace,
  topLevelDescriptorProvider: TopLevelDescriptorProvider,
  absentDescriptorHandler: AbsentDescriptorHandler
) :
  LazyDeclarationResolver(
    globalContext,
    trace,
    topLevelDescriptorProvider,
    absentDescriptorHandler) {
  override fun getClassDescriptor(classOrObject: KtClassOrObject, location: LookupLocation): ClassDescriptor {
    println("MetaLazyDeclarationResolver.getClassDescriptor: $classOrObject, $location")
    return super.getClassDescriptor(classOrObject, location)
  }

  override fun getClassDescriptorIfAny(classOrObject: KtClassOrObject, location: LookupLocation): ClassDescriptor? {
    println("MetaLazyDeclarationResolver.getClassDescriptorIfAny: $classOrObject, $location")
    return super.getClassDescriptorIfAny(classOrObject, location)
  }
}
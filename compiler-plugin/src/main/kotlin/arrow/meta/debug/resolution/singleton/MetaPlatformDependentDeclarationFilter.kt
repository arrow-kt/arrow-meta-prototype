package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.SimpleFunctionDescriptor
import org.jetbrains.kotlin.descriptors.deserialization.PlatformDependentDeclarationFilter
import org.jetbrains.kotlin.name.FqName

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaPlatformDependentDeclarationFilter(val delegate: PlatformDependentDeclarationFilter) : PlatformDependentDeclarationFilter {
  override fun isFunctionAvailable(classDescriptor: ClassDescriptor, functionDescriptor: SimpleFunctionDescriptor): Boolean {
    println("MetaPlatformDependentDeclarationFilter.isFunctionAvailable: $classDescriptor, $functionDescriptor")
    return delegate.isFunctionAvailable(classDescriptor, functionDescriptor)
  }
}

val METAPLATFORM_DEPENDENT_ANNOTATION_FQ_NAME = FqName("arrow.meta.utils.MetaPlatformDependent")

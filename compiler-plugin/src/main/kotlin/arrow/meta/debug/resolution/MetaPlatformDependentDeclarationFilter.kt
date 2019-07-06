package arrow.meta.utils

import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.SimpleFunctionDescriptor
import org.jetbrains.kotlin.descriptors.deserialization.PlatformDependentDeclarationFilter
import org.jetbrains.kotlin.name.FqName

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaPlatformDependentDeclarationFilter : PlatformDependentDeclarationFilter {
  override fun isFunctionAvailable(classDescriptor: ClassDescriptor, functionDescriptor: SimpleFunctionDescriptor): Boolean {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}

val METAPLATFORM_DEPENDENT_ANNOTATION_FQ_NAME = FqName("arrow.meta.utils.MetaPlatformDependent")

package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.load.java.components.SamConversionResolver
import org.jetbrains.kotlin.load.java.descriptors.JavaClassDescriptor
import org.jetbrains.kotlin.types.SimpleType

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaSamConversionResolver(val delegate: SamConversionResolver = SamConversionResolver.Empty) : SamConversionResolver by delegate {
  override fun resolveFunctionTypeIfSamInterface(classDescriptor: JavaClassDescriptor): SimpleType? {
    println(" MetaSamConversionResolver.resolveFunctionTypeIfSamInterface: $classDescriptor")
    return delegate.resolveFunctionTypeIfSamInterface(classDescriptor)
  }
}
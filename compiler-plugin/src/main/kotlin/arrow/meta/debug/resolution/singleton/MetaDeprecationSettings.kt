package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.descriptors.annotations.AnnotationDescriptor
import org.jetbrains.kotlin.resolve.deprecation.DeprecationSettings

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaDeprecationSettings(val delegate: DeprecationSettings) : DeprecationSettings by delegate {
  override fun propagatedToOverrides(deprecationAnnotation: AnnotationDescriptor): Boolean {
    println("MetaDeprecationSettings.propagatedToOverrides: $deprecationAnnotation")
    return delegate.propagatedToOverrides(deprecationAnnotation)
  }
}
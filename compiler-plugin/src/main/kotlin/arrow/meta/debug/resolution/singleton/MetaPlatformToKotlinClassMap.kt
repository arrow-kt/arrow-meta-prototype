package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.builtins.PlatformToKotlinClassMap
import org.jetbrains.kotlin.descriptors.ClassDescriptor

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaPlatformToKotlinClassMap(val delegate: PlatformToKotlinClassMap): PlatformToKotlinClassMap by delegate {
  override fun mapPlatformClass(p0: ClassDescriptor): MutableCollection<ClassDescriptor> {
    println("MetaPlatformToKotlinClassMap.mapPlatformClass: $p0")
    return delegate.mapPlatformClass(p0)
  }
}
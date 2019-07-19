package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.load.java.lazy.JavaResolverSettings

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaJavaResolverSettings(val delegate: JavaResolverSettings) : JavaResolverSettings by delegate {
  override val isReleaseCoroutines: Boolean = delegate.isReleaseCoroutines
}
package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.load.java.JavaClassesTracker
import org.jetbrains.kotlin.load.java.descriptors.JavaClassDescriptor

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaJavaClassesTracker(val delegate: JavaClassesTracker) : JavaClassesTracker by delegate {
  override fun onCompletedAnalysis(module: ModuleDescriptor) {
    println("MetaJavaClassesTracker.onCompletedAnalysis: $module")
    delegate.onCompletedAnalysis(module)
  }

  override fun reportClass(classDescriptor: JavaClassDescriptor) {
    println("MetaJavaClassesTracker.reportClass: $classDescriptor")
    delegate.reportClass(classDescriptor)
  }
}
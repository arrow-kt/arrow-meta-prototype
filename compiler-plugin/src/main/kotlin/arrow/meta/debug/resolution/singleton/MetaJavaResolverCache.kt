package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.ConstructorDescriptor
import org.jetbrains.kotlin.descriptors.PropertyDescriptor
import org.jetbrains.kotlin.descriptors.SimpleFunctionDescriptor
import org.jetbrains.kotlin.load.java.components.JavaResolverCache
import org.jetbrains.kotlin.load.java.structure.JavaClass
import org.jetbrains.kotlin.load.java.structure.JavaElement
import org.jetbrains.kotlin.load.java.structure.JavaField
import org.jetbrains.kotlin.load.java.structure.JavaMethod
import org.jetbrains.kotlin.name.FqName

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaJavaResolverCache(delegate: JavaResolverCache) : JavaResolverCache by delegate {
  override fun getClassResolvedFromSource(p0: FqName): ClassDescriptor? {
    println("MetaJavaResolverCache.getClassResolvedFromSource: $p0")
    TODO("Add delegate")
  }

  override fun recordMethod(p0: JavaMethod, p1: SimpleFunctionDescriptor) {
    println("MetaJavaResolverCache.recordMethod: $p0, $p1")
    TODO("Add delegate")
  }

  override fun recordConstructor(p0: JavaElement, p1: ConstructorDescriptor) {
    println("MetaJavaResolverCache.recordConstructor: $p0, $p1")
    TODO("Add delegate")
  }

  override fun recordClass(p0: JavaClass, p1: ClassDescriptor) {
    println("MetaJavaResolverCache.recordClass: $p0, $p1")
    TODO("Add delegate")
  }

  override fun recordField(p0: JavaField, p1: PropertyDescriptor) {
    println("MetaJavaResolverCache.recordField: $p0, $p1")
    TODO("Add delegate")
  }
}
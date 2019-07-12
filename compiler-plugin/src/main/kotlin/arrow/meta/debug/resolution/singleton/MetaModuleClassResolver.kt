package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.load.java.lazy.ModuleClassResolver
import org.jetbrains.kotlin.load.java.structure.JavaClass
import org.jetbrains.kotlin.psi.delegatedSuperTypeEntry

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaModuleClassResolver(delegate :  ModuleClassResolver) : ModuleClassResolver by delegate {
  override fun resolveClass(javaClass: JavaClass): ClassDescriptor? {
    println("MetaModuleClassResolver.resolveClass: $javaClass")
    TODO("Add delegate")
  }
}
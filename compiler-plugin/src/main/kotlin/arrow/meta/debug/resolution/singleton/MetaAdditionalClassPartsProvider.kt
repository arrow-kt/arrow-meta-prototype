package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.descriptors.ClassConstructorDescriptor
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.SimpleFunctionDescriptor
import org.jetbrains.kotlin.descriptors.deserialization.AdditionalClassPartsProvider
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.types.KotlinType

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaAdditionalClassPartsProvider(val delegate: AdditionalClassPartsProvider) : AdditionalClassPartsProvider by delegate {
  override fun getConstructors(classDescriptor: ClassDescriptor): Collection<ClassConstructorDescriptor> {
    println("MetaAdditionalClassPartsProvider.getConstructors: $classDescriptor")
    return delegate.getConstructors(classDescriptor)
  }

  override fun getFunctions(name: Name, classDescriptor: ClassDescriptor): Collection<SimpleFunctionDescriptor> {
    println("MetaAdditionalClassPartsProvider.getFunctions: $name, $classDescriptor")
    return delegate.getFunctions(name, classDescriptor)
  }

  override fun getFunctionsNames(classDescriptor: ClassDescriptor): Collection<Name> {
    println("MetaAdditionalClassPartsProvider.getFunctionsNames: $classDescriptor")
    return delegate.getFunctionsNames(classDescriptor)
  }

  override fun getSupertypes(classDescriptor: ClassDescriptor): Collection<KotlinType> {
    println("MetaAdditionalClassPartsProvider.getSupertypes: $classDescriptor")
    return delegate.getSupertypes(classDescriptor)
  }
}
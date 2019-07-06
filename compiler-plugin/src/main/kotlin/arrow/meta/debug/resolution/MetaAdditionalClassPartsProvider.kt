package arrow.meta.utils

import org.jetbrains.kotlin.descriptors.ClassConstructorDescriptor
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.SimpleFunctionDescriptor
import org.jetbrains.kotlin.descriptors.deserialization.AdditionalClassPartsProvider
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.types.KotlinType

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaAdditionalClassPartsProvider : AdditionalClassPartsProvider {
  override fun getConstructors(classDescriptor: ClassDescriptor): Collection<ClassConstructorDescriptor> {
    println("MetaAdditionalClassPartsProvider.getConstructors: $classDescriptor")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getFunctions(name: Name, classDescriptor: ClassDescriptor): Collection<SimpleFunctionDescriptor> {
    println("MetaAdditionalClassPartsProvider.getFunctions: $name")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getFunctionsNames(classDescriptor: ClassDescriptor): Collection<Name> {
    println("MetaAdditionalClassPartsProvider.getFunctionsNames: $classDescriptor")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getSupertypes(classDescriptor: ClassDescriptor): Collection<KotlinType> {
    println("MetaAdditionalClassPartsProvider.getSupertypes: $classDescriptor")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}
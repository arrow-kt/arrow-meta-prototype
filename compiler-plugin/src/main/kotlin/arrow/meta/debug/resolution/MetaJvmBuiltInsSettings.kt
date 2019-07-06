package arrow.meta.utils

import org.jetbrains.kotlin.descriptors.ClassConstructorDescriptor
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.descriptors.SimpleFunctionDescriptor
import org.jetbrains.kotlin.descriptors.deserialization.AdditionalClassPartsProvider
import org.jetbrains.kotlin.descriptors.deserialization.PlatformDependentDeclarationFilter
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.storage.StorageManager
import org.jetbrains.kotlin.types.KotlinType

/**
 * @throws org.jetbrains.kotlin.container.UnresolvedDependenciesException: Dependencies for `public arrow.meta.utils.MetaJvmBuiltInsSettings(org.jetbrains.kotlin.descriptors.ModuleDescriptor,org.jetbrains.kotlin.storage.StorageManager,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0)` cannot be satisfied:
 */
class MetaJvmBuiltInsSettings(
  private val moduleDescriptor: ModuleDescriptor,
  storageManager: StorageManager,
  deferredOwnerModuleDescriptor: () -> ModuleDescriptor,
  isAdditionalBuiltInsFeatureSupported: () -> Boolean
) : AdditionalClassPartsProvider, PlatformDependentDeclarationFilter {
  override fun getConstructors(classDescriptor: ClassDescriptor): Collection<ClassConstructorDescriptor> {
    println("MetaJvmBuiltInsSettings.getConstructors: $classDescriptor")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getFunctions(name: Name, classDescriptor: ClassDescriptor): Collection<SimpleFunctionDescriptor> {
    println("MetaJvmBuiltInsSettings.getFunctions: $name")
    TODO("not implemnted") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getFunctionsNames(classDescriptor: ClassDescriptor): Collection<Name> {
    println("MetaJvmBuiltInsSettings.getFunctionsNames: $classDescriptor")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getSupertypes(classDescriptor: ClassDescriptor): Collection<KotlinType> {
    println("MetaJvmBuiltInsSettings.getSupertypes: $classDescriptor")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun isFunctionAvailable(classDescriptor: ClassDescriptor, functionDescriptor: SimpleFunctionDescriptor): Boolean {
    println("MetaJvmBuiltInsSettings.isFunctionAvailable: $classDescriptor")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}
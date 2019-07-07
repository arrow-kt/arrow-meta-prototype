package arrow.meta.debug.resolution

import org.jetbrains.kotlin.descriptors.ClassConstructorDescriptor
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.descriptors.SimpleFunctionDescriptor
import org.jetbrains.kotlin.descriptors.deserialization.AdditionalClassPartsProvider
import org.jetbrains.kotlin.descriptors.deserialization.PlatformDependentDeclarationFilter
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.storage.StorageManager
import org.jetbrains.kotlin.types.KotlinType

interface BuiltInsSettings : AdditionalClassPartsProvider, PlatformDependentDeclarationFilter

/**
 * @throws org.jetbrains.kotlin.container.UnresolvedDependenciesException: Dependencies for `public arrow.meta.debug.resolution.MetaJvmBuiltInsSettings(org.jetbrains.kotlin.descriptors.ModuleDescriptor,org.jetbrains.kotlin.storage.StorageManager,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0)` cannot be satisfied:
 */
class MetaJvmBuiltInsSettings(
  val moduleDescriptor: ModuleDescriptor,
  val storageManager: StorageManager,
  val deferredOwnerModuleDescriptor: () -> ModuleDescriptor,
  val isAdditionalBuiltInsFeatureSupported: () -> Boolean,
  val delegate: BuiltInsSettings
) : BuiltInsSettings by delegate {
  override fun getConstructors(classDescriptor: ClassDescriptor): Collection<ClassConstructorDescriptor> {
    println("MetaJvmBuiltInsSettings.getConstructors: $classDescriptor")
    return delegate.getConstructors(classDescriptor)
  }

  override fun getFunctions(name: Name, classDescriptor: ClassDescriptor): Collection<SimpleFunctionDescriptor> {
    println("MetaJvmBuiltInsSettings.getFunctions: $name, $classDescriptor")
    return delegate.getFunctions(name, classDescriptor)
  }

  override fun getFunctionsNames(classDescriptor: ClassDescriptor): Collection<Name> {
    println("MetaJvmBuiltInsSettings.getFunctionsNames: $classDescriptor")
    return delegate.getFunctionsNames(classDescriptor)
  }

  override fun getSupertypes(classDescriptor: ClassDescriptor): Collection<KotlinType> {
    println("MetaJvmBuiltInsSettings.getSupertypes: $classDescriptor")
    return delegate.getSupertypes(classDescriptor)
  }

  override fun isFunctionAvailable(classDescriptor: ClassDescriptor, functionDescriptor: SimpleFunctionDescriptor): Boolean {
    println("MetaJvmBuiltInsSettings.isFunctionAvailable: $classDescriptor, $functionDescriptor")
    return delegate.isFunctionAvailable(classDescriptor, functionDescriptor)
  }
}
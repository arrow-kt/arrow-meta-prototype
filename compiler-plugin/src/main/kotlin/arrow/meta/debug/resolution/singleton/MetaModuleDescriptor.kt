package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.builtins.KotlinBuiltIns
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor
import org.jetbrains.kotlin.descriptors.DeclarationDescriptorVisitor
import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.descriptors.PackageViewDescriptor
import org.jetbrains.kotlin.descriptors.annotations.Annotations
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaModuleDescriptor(val delegate: ModuleDescriptor) : ModuleDescriptor by delegate {
  override val allDependencyModules: List<ModuleDescriptor>
    get() = delegate.allDependencyModules
  override val annotations: Annotations
    get() = delegate.annotations
  override val builtIns: KotlinBuiltIns
    get() = delegate.builtIns
  override val expectedByModules: List<ModuleDescriptor>
    get() = delegate.expectedByModules
  override val isValid: Boolean
    get() = delegate.isValid
  override val stableName: Name?
    get() = delegate.stableName

  override fun acceptVoid(p0: DeclarationDescriptorVisitor<Void, Void>?) {
    println("MetaModuleDescriptor.acceptVoid: $p0")
    return delegate.acceptVoid(p0)
  }

  override fun assertValid() {
    println("MetaModuleDescriptor.assertValid:")
    return delegate.assertValid()
  }

  override fun <T> getCapability(capability: ModuleDescriptor.Capability<T>): T? {
    println("MetaModuleDescriptor.getCapability: $capability")
    return delegate.getCapability(capability)
  }

  override fun getName(): Name {
    println("MetaModuleDescriptor.getName:")
    return delegate.name
  }

  override fun getOriginal(): DeclarationDescriptor {
    println("MetaModuleDescriptor.getOrigina:")
    return delegate.original
  }

  override fun getPackage(fqName: FqName): PackageViewDescriptor {
    println("MetaModuleDescriptor.getPackage: $fqName")
    return delegate.getPackage(fqName)
  }

  override fun getSubPackagesOf(fqName: FqName, nameFilter: (Name) -> Boolean): Collection<FqName> {
    println("MetaModuleDescriptor.getSubPackagesOf: $fqName, $nameFilter")
    return delegate.getSubPackagesOf(fqName, nameFilter)
  }

  override fun shouldSeeInternalsOf(targetModule: ModuleDescriptor): Boolean {
    println("MetaModuleDescriptor.shouldSeeInternalsOf: $targetModule")
    return delegate.shouldSeeInternalsOf(targetModule)
  }
}
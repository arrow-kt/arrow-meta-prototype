package arrow.meta.plugin.idea

import org.jetbrains.kotlin.descriptors.ClassifierDescriptor
import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.descriptors.PackageFragmentDescriptor
import org.jetbrains.kotlin.descriptors.PropertyDescriptor
import org.jetbrains.kotlin.descriptors.SimpleFunctionDescriptor
import org.jetbrains.kotlin.descriptors.impl.PackageFragmentDescriptorImpl
import org.jetbrains.kotlin.incremental.components.LookupLocation
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.resolve.scopes.MemberScope
import org.jetbrains.kotlin.resolve.scopes.MemberScopeImpl
import org.jetbrains.kotlin.utils.Printer

class MetaPackageFramentDescriptor(
  module: ModuleDescriptor,
  cache: SyntheticDescriptorCache,
  packageName: FqName
) : PackageFragmentDescriptorImpl(module, packageName) {
  private val scope = MetaExtensionPropertiesScope(packageName, cache)
  override fun getMemberScope(): MemberScope = scope
  private inner class MetaExtensionPropertiesScope(val packageName: FqName, val cache: SyntheticDescriptorCache) : MemberScopeImpl() {
    override fun getClassifierNames(): Set<Name>? =
      cache.file.declarations
        .mapNotNull { it.name }
        .map(Name::identifier).toSet()

    override fun getContributedClassifier(name: Name, location: LookupLocation): ClassifierDescriptor? =
      cache.descriptorCache.values.filterIsInstance<ClassifierDescriptor>().find { classifier ->
        val container = classifier.containingDeclaration
        container is PackageFragmentDescriptor && container.fqName == packageName
      }

    override fun getContributedFunctions(name: Name, location: LookupLocation): Collection<SimpleFunctionDescriptor> =
      cache.descriptorCache.values.filterIsInstance<SimpleFunctionDescriptor>().filter { classifier ->
        val container = classifier.containingDeclaration
        container is PackageFragmentDescriptor && container.fqName == packageName
      }

    override fun getContributedVariables(name: Name, location: LookupLocation): Collection<PropertyDescriptor> =
      cache.descriptorCache.values.filterIsInstance<PropertyDescriptor>().filter { classifier ->
        val container = classifier.containingDeclaration
        container is PackageFragmentDescriptor && container.fqName == packageName
      }

    override fun getFunctionNames(): Set<Name> =
      cache.file.declarations
        .filterIsInstance<KtNamedFunction>()
        .mapNotNull { it.name }
        .map(Name::identifier).toSet()

    override fun getVariableNames(): Set<Name> =
      cache.file.declarations
        .filterIsInstance<KtProperty>()
        .mapNotNull { it.name }
        .map(Name::identifier).toSet()

    override fun printScopeStructure(p: Printer) {
    }
  }
}

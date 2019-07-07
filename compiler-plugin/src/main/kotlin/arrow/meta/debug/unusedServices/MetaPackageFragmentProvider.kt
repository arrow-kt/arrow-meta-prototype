package arrow.meta.debug.unusedServices

import org.jetbrains.kotlin.descriptors.PackageFragmentDescriptor
import org.jetbrains.kotlin.descriptors.PackageFragmentProvider
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name

/**
 * @UnknownPhase Not subscribable in Analysis Phase:
 */
class MetaPackageFragmentProvider(val delegate: PackageFragmentProvider) : PackageFragmentProvider by delegate {
  override fun getPackageFragments(fqName: FqName): List<PackageFragmentDescriptor> {
    println("MetaPackageFragmentProvider.getPackageFragments: $fqName")
    return delegate.getPackageFragments(fqName)
  }

  override fun getSubPackagesOf(fqName: FqName, nameFilter: (Name) -> Boolean): Collection<FqName> {
    println("MetaPackageFragmentProvider.getSubPackagesOf: $fqName, $nameFilter")
    return delegate.getSubPackagesOf(fqName, nameFilter)
  }
}
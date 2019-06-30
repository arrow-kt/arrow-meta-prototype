package arrow.meta.utils

import org.jetbrains.kotlin.descriptors.PackageFragmentDescriptor
import org.jetbrains.kotlin.descriptors.PackageFragmentProvider
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name

/**
 * @UnknownPhase Not subscribable in Analysis Phase:
 */
class MetaPackageFragmentProvider : PackageFragmentProvider{
  override fun getPackageFragments(fqName: FqName): List<PackageFragmentDescriptor> {
    println("MetaPackageFragmentProvider.getPackageFragments: $fqName")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getSubPackagesOf(fqName: FqName, nameFilter: (Name) -> Boolean): Collection<FqName> {
    println("MetaPackageFragmentProvider.getSubPackagesOf: $fqName")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}
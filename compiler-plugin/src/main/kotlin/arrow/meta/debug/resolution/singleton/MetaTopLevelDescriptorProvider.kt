package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.descriptors.ClassifierDescriptor
import org.jetbrains.kotlin.incremental.components.LookupLocation
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.resolve.lazy.TopLevelDescriptorProvider
import org.jetbrains.kotlin.resolve.lazy.descriptors.LazyPackageDescriptor

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaTopLevelDescriptorProvider(val delegate: TopLevelDescriptorProvider): TopLevelDescriptorProvider by delegate {
  override fun assertValid() {
    println("MetaTopLevelDescriptorProvider.assertValid:")
    delegate.assertValid()
  }

  override fun getPackageFragment(fqName: FqName): LazyPackageDescriptor? {
    println("MetaTopLevelDescriptorProvider.getPackageFragment: $fqName")
    return delegate.getPackageFragment(fqName)
  }

  override fun getPackageFragmentOrDiagnoseFailure(fqName: FqName, from: KtFile?): LazyPackageDescriptor {
    println("MetaTopLevelDescriptorProvider.getPackageFragmentOrDiagnoseFailure: $fqName, $from")
    return delegate.getPackageFragmentOrDiagnoseFailure(fqName, from)
  }

  override fun getTopLevelClassifierDescriptors(fqName: FqName, location: LookupLocation): Collection<ClassifierDescriptor> {
    println("MetaTopLevelDescriptorProvider.getTopLevelClassifierDescriptors: $fqName, $location")
    return delegate.getTopLevelClassifierDescriptors(fqName, location)
  }
}
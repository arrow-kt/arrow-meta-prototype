package arrow.meta.debug.unusedServices

import org.jetbrains.kotlin.descriptors.PackageFragmentDescriptor
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.psi.KtImportDirective
import org.jetbrains.kotlin.psi.KtImportInfo
import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.resolve.lazy.ImportForceResolver
import org.jetbrains.kotlin.resolve.lazy.ImportResolutionComponents
import org.jetbrains.kotlin.resolve.lazy.IndexedImports
import org.jetbrains.kotlin.resolve.lazy.LazyImportResolver

/**
 * @UnusedService throws [UnresolvedDependenciesException]
 * several impl in [org.jetbrains.kotlin.resolve.lazy.LazyImportResolver] file
 */
class MetaLazyImportResolverForKtImportDirective<I : KtImportInfo>(
  components: ImportResolutionComponents,
  indexedImports: IndexedImports<I>,
  excludedImportNames: Collection<FqName>,
  traceForImportResolve: BindingTrace,
  packageFragment: PackageFragmentDescriptor?,
  delegate: ImportForceResolver
) : LazyImportResolver<I> (
  components,
  indexedImports,
  excludedImportNames,
  traceForImportResolve,
  packageFragment
), ImportForceResolver by delegate {
  override fun forceResolveImport(importDirective: KtImportDirective) {
    println("MetaLazyImportResolverForKtImportDirective.forceResolveImport: $importDirective")
    TODO("not implemented")
  }

  override fun forceResolveNonDefaultImports() {
    println("MetaLazyImportResolverForKtImportDirective.forceResolveNonDefaultImports:")
    TODO("not implemented")
  }
}
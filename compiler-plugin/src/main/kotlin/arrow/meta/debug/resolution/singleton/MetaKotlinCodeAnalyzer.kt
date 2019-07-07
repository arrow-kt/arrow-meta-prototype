package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.ClassifierDescriptor
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor
import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.descriptors.PackageFragmentProvider
import org.jetbrains.kotlin.incremental.components.LookupLocation
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtDeclaration
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.resolve.CodeAnalyzerInitializer
import org.jetbrains.kotlin.resolve.lazy.DeclarationScopeProvider
import org.jetbrains.kotlin.resolve.lazy.FileScopeProvider
import org.jetbrains.kotlin.resolve.lazy.KotlinCodeAnalyzer
import org.jetbrains.kotlin.resolve.lazy.descriptors.LazyPackageDescriptor

/**
 * if primitively registered it
 * @throws ContainerConsistencyException: Could not create the component Singleton: MetaCodeAnalyzerInitializer because it is being initialized. Do we have undetected circular dependency?
 * but check [MetaComponentRegistrar.registerProjectComponents] for correct registration
 */
class MetaCodeAnalyzerInitializer(val delegate: CodeAnalyzerInitializer): CodeAnalyzerInitializer by delegate {
  override fun createTrace(): BindingTrace =
    delegate.createTrace()


  override fun initialize(trace: BindingTrace, module: ModuleDescriptor, codeAnalyzer: KotlinCodeAnalyzer) {
    delegate.initialize(trace, module, MetaKotlinCodeAnalyzer(codeAnalyzer))
  }
}
/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaKotlinCodeAnalyzer(val delegate: KotlinCodeAnalyzer) : KotlinCodeAnalyzer by delegate{
  override fun resolveToDescriptor(p0: KtDeclaration): DeclarationDescriptor {
    println("MetaKotlinCodeAnalyzer.resolveToDescriptor: $p0")
    return delegate.resolveToDescriptor(p0)
  }

  override fun assertValid() {
    println("MetaKotlinCodeAnalyzer.assertValid:")
    delegate.assertValid()
  }

  override fun getTopLevelClassifierDescriptors(fqName: FqName, location: LookupLocation): Collection<ClassifierDescriptor> {
    println("MetaKotlinCodeAnalyzer.getTopLevelClassifierDescriptors: $fqName, $location")
    return delegate.getTopLevelClassifierDescriptors(fqName, location)
  }

  override fun getClassDescriptor(p0: KtClassOrObject, p1: LookupLocation): ClassDescriptor {
    println("MetaKotlinCodeAnalyzer.getClassDescriptor: $p0, $p1")
    return delegate.getClassDescriptor(p0, p1)
  }

  override fun forceResolveAll() {
    println("MetaKotlinCodeAnalyzer.forceResolveAll:")
    return delegate.forceResolveAll()
  }

  override fun getPackageFragment(fqName: FqName): LazyPackageDescriptor? {
    println("MetaKotlinCodeAnalyzer.getPackageFragment: $fqName")
    return delegate.getPackageFragment(fqName)
  }

  override fun getDeclarationScopeProvider(): DeclarationScopeProvider {
    println("MetaKotlinCodeAnalyzer.getDeclarationScopeProvider:")
    return delegate.declarationScopeProvider
  }

  override fun getPackageFragmentProvider(): PackageFragmentProvider {
    println("MetaKotlinCodeAnalyzer.getPackageFragmentProvider:")
    return delegate.packageFragmentProvider
  }

  override fun getPackageFragmentOrDiagnoseFailure(fqName: FqName, from: KtFile?): LazyPackageDescriptor {
    println("MetaKotlinCodeAnalyzer.getPackageFragmentOrDiagnoseFailure: $fqName, $from")
    return delegate.getPackageFragmentOrDiagnoseFailure(fqName, from)
  }

  override fun getBindingContext(): BindingContext {
    println("MetaKotlinCodeAnalyzer.getBindingContext:")
    return delegate.bindingContext
  }

  override fun getModuleDescriptor(): ModuleDescriptor {
    println("MetaKotlinCodeAnalyzer.getModuleDescriptor:")
    return delegate.moduleDescriptor
  }

  override fun getFileScopeProvider(): FileScopeProvider {
    println("MetaKotlinCodeAnalyzer.getFileScopeProvider:")
    return delegate.fileScopeProvider
  }
}
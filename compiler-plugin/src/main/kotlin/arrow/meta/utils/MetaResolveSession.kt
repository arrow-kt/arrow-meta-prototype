package arrow.meta.utils

// TODO: still WIP
import arrow.meta.higherkind.MetaKotlinCodeAnalyzer
import org.jetbrains.kotlin.config.LanguageVersionSettings
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.ClassifierDescriptor
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor
import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.descriptors.PackageFragmentProvider
import org.jetbrains.kotlin.descriptors.SupertypeLoopChecker
import org.jetbrains.kotlin.incremental.components.LookupLocation
import org.jetbrains.kotlin.incremental.components.LookupTracker
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtDeclaration
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.resolve.AnnotationResolver
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.resolve.DescriptorResolver
import org.jetbrains.kotlin.resolve.FunctionDescriptorResolver
import org.jetbrains.kotlin.resolve.TypeResolver
import org.jetbrains.kotlin.resolve.extensions.SyntheticResolveExtension
import org.jetbrains.kotlin.resolve.lazy.DeclarationScopeProvider
import org.jetbrains.kotlin.resolve.lazy.DelegationFilter
import org.jetbrains.kotlin.resolve.lazy.FileScopeProvider
import org.jetbrains.kotlin.resolve.lazy.KotlinCodeAnalyzer
import org.jetbrains.kotlin.resolve.lazy.LazyClassContext
import org.jetbrains.kotlin.resolve.lazy.declarations.DeclarationProviderFactory
import org.jetbrains.kotlin.resolve.lazy.descriptors.LazyPackageDescriptor
import org.jetbrains.kotlin.storage.StorageManager
import org.jetbrains.kotlin.types.WrappedTypeFactory
/*

class MetaResolveSession : KotlinCodeAnalyzer, LazyClassContext { // current;y has a Platform declaration Clash ..
  override val declarationScopeProvider: DeclarationScopeProvider
    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

  override fun getDeclarationScopeProvider(): DeclarationScopeProvider {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getModuleDescriptor(): ModuleDescriptor {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun resolveToDescriptor(p0: KtDeclaration): DeclarationDescriptor {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun assertValid() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getTopLevelClassifierDescriptors(fqName: FqName, location: LookupLocation): Collection<ClassifierDescriptor> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getClassDescriptor(p0: KtClassOrObject, p1: LookupLocation): ClassDescriptor {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun forceResolveAll() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getPackageFragment(fqName: FqName): LazyPackageDescriptor? {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }


  override fun getPackageFragmentProvider(): PackageFragmentProvider {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getPackageFragmentOrDiagnoseFailure(fqName: FqName, from: KtFile?): LazyPackageDescriptor {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getBindingContext(): BindingContext {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }


  override fun getFileScopeProvider(): FileScopeProvider {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override val annotationResolver: AnnotationResolver
    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
  override val declarationProviderFactory: DeclarationProviderFactory
    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
  override val delegationFilter: DelegationFilter
    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
  override val descriptorResolver: DescriptorResolver
    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
  override val functionDescriptorResolver: FunctionDescriptorResolver
    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
  override val languageVersionSettings: LanguageVersionSettings
    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
  override val lookupTracker: LookupTracker
    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
  override val storageManager: StorageManager
    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
  override val supertypeLoopChecker: SupertypeLoopChecker
    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
  override val syntheticResolveExtension: SyntheticResolveExtension
    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
  override val trace: BindingTrace
    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
  override val typeResolver: TypeResolver
    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
  override val wrappedTypeFactory: WrappedTypeFactory
    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
}*/

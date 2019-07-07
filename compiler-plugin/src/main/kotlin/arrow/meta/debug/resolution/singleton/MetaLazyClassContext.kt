package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.config.LanguageVersionSettings
import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.descriptors.SupertypeLoopChecker
import org.jetbrains.kotlin.incremental.components.LookupTracker
import org.jetbrains.kotlin.resolve.AnnotationResolver
import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.resolve.DescriptorResolver
import org.jetbrains.kotlin.resolve.FunctionDescriptorResolver
import org.jetbrains.kotlin.resolve.TypeResolver
import org.jetbrains.kotlin.resolve.extensions.SyntheticResolveExtension
import org.jetbrains.kotlin.resolve.lazy.DeclarationScopeProvider
import org.jetbrains.kotlin.resolve.lazy.DelegationFilter
import org.jetbrains.kotlin.resolve.lazy.LazyClassContext
import org.jetbrains.kotlin.resolve.lazy.declarations.DeclarationProviderFactory
import org.jetbrains.kotlin.storage.StorageManager
import org.jetbrains.kotlin.types.WrappedTypeFactory

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaLazyClassContext(val delegate: LazyClassContext): LazyClassContext by delegate{
  override val annotationResolver: AnnotationResolver
    get() = delegate.annotationResolver
  override val declarationProviderFactory: DeclarationProviderFactory
    get() = delegate.declarationProviderFactory
  override val declarationScopeProvider: DeclarationScopeProvider
    get() = delegate.declarationScopeProvider
  override val delegationFilter: DelegationFilter
    get() = delegate.delegationFilter
  override val descriptorResolver: DescriptorResolver
    get() = delegate.descriptorResolver
  override val functionDescriptorResolver: FunctionDescriptorResolver
    get() = delegate.functionDescriptorResolver
  override val languageVersionSettings: LanguageVersionSettings
    get() = delegate.languageVersionSettings
  override val lookupTracker: LookupTracker
    get() = delegate.lookupTracker
  override val moduleDescriptor: ModuleDescriptor
    get() = delegate.moduleDescriptor
  override val storageManager: StorageManager
    get() = delegate.storageManager
  override val supertypeLoopChecker: SupertypeLoopChecker
    get() = delegate.supertypeLoopChecker
  override val syntheticResolveExtension: SyntheticResolveExtension
    get() = delegate.syntheticResolveExtension
  override val trace: BindingTrace
    get() = delegate.trace
  override val typeResolver: TypeResolver
    get() = delegate.typeResolver
  override val wrappedTypeFactory: WrappedTypeFactory
    get() = delegate.wrappedTypeFactory
}
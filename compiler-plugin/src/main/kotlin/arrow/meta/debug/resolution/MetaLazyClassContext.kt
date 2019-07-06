package arrow.meta.utils

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

class MetaLazyClassContext: LazyClassContext{
  override val annotationResolver: AnnotationResolver
    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
  override val declarationProviderFactory: DeclarationProviderFactory
    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
  override val declarationScopeProvider: DeclarationScopeProvider
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
  override val moduleDescriptor: ModuleDescriptor
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
}
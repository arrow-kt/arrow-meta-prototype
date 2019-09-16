package arrow.meta.extensions

import arrow.meta.qq.MetaAnalyzer
import org.jetbrains.kotlin.analyzer.AnalysisResult
import org.jetbrains.kotlin.analyzer.ModuleInfo
import org.jetbrains.kotlin.backend.common.BackendContext
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.codegen.ClassBuilderFactory
import org.jetbrains.kotlin.codegen.ImplementationBodyCodegen
import org.jetbrains.kotlin.codegen.StackValue
import org.jetbrains.kotlin.codegen.extensions.ExpressionCodegenExtension
import org.jetbrains.kotlin.com.intellij.openapi.project.Project
import org.jetbrains.kotlin.com.intellij.openapi.vfs.VirtualFile
import org.jetbrains.kotlin.com.intellij.testFramework.LightVirtualFile
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.container.ComponentProvider
import org.jetbrains.kotlin.container.get
import org.jetbrains.kotlin.context.ProjectContext
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.ConstructorDescriptor
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.descriptors.PackageFragmentDescriptor
import org.jetbrains.kotlin.descriptors.PackageFragmentProvider
import org.jetbrains.kotlin.descriptors.PropertyDescriptor
import org.jetbrains.kotlin.descriptors.SimpleFunctionDescriptor
import org.jetbrains.kotlin.diagnostics.Diagnostic
import org.jetbrains.kotlin.diagnostics.DiagnosticSink
import org.jetbrains.kotlin.incremental.components.LookupLocation
import org.jetbrains.kotlin.incremental.components.LookupTracker
import org.jetbrains.kotlin.ir.declarations.IrFile
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.psi.KtDeclaration
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtImportInfo
import org.jetbrains.kotlin.psi.KtModifierListOwner
import org.jetbrains.kotlin.psi.KtPsiFactory
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCall
import org.jetbrains.kotlin.resolve.checkers.DeclarationCheckerContext
import org.jetbrains.kotlin.resolve.lazy.LazyClassContext
import org.jetbrains.kotlin.resolve.lazy.declarations.ClassMemberDeclarationProvider
import org.jetbrains.kotlin.resolve.lazy.declarations.PackageMemberDeclarationProvider
import org.jetbrains.kotlin.resolve.scopes.ResolutionScope
import org.jetbrains.kotlin.resolve.scopes.SyntheticScope
import org.jetbrains.kotlin.storage.StorageManager
import org.jetbrains.kotlin.synthetic.JavaSyntheticPropertiesScope
import org.jetbrains.kotlin.synthetic.SyntheticScopeProviderExtension
import org.jetbrains.kotlin.types.KotlinType
import java.util.*

interface ExtensionPhase {

  data class CompositePhase(val phases: List<ExtensionPhase>): ExtensionPhase {
    companion object {
      operator fun invoke(vararg phases: ExtensionPhase): ExtensionPhase =
        CompositePhase(phases.toList())
    }
  }
  
  interface Config : ExtensionPhase {
    fun CompilerContext.updateConfiguration(configuration: CompilerConfiguration): Unit
  }

  object Empty : ExtensionPhase

  interface ExtraImports : ExtensionPhase {
    fun CompilerContext.extraImports(ktFile: KtFile): Collection<KtImportInfo>
  }

  interface PackageProvider : ExtensionPhase {
    fun CompilerContext.getPackageFragmentProvider(
      project: Project,
      module: ModuleDescriptor,
      storageManager: StorageManager,
      trace: BindingTrace,
      moduleInfo: ModuleInfo?,
      lookupTracker: LookupTracker
    ): PackageFragmentProvider?
  }

  interface CollectAdditionalSources : ExtensionPhase {
    fun CompilerContext.collectAdditionalSourcesAndUpdateConfiguration(
      knownSources: Collection<KtFile>,
      configuration: CompilerConfiguration,
      project: Project
    ): Collection<KtFile>
  }

  interface AnalysisHandler : ExtensionPhase {
    fun CompilerContext.doAnalysis(
      project: Project,
      module: ModuleDescriptor,
      projectContext: ProjectContext,
      files: Collection<KtFile>,
      bindingTrace: BindingTrace,
      componentProvider: ComponentProvider
    ): AnalysisResult?

    fun CompilerContext.analysisCompleted(
      project: Project,
      module: ModuleDescriptor,
      bindingTrace: BindingTrace,
      files: Collection<KtFile>
    ): AnalysisResult?
  }


  interface SyntheticResolver : ExtensionPhase {
    fun CompilerContext.addSyntheticSupertypes(
      thisDescriptor: ClassDescriptor,
      supertypes: MutableList<KotlinType>
    ): Unit

    fun CompilerContext.generateSyntheticClasses(
      thisDescriptor: ClassDescriptor,
      name: Name,
      ctx: LazyClassContext,
      declarationProvider: ClassMemberDeclarationProvider,
      result: MutableSet<ClassDescriptor>
    ): Unit

    fun CompilerContext.generatePackageSyntheticClasses(
      thisDescriptor: PackageFragmentDescriptor,
      name: Name,
      ctx: LazyClassContext,
      declarationProvider: PackageMemberDeclarationProvider,
      result: MutableSet<ClassDescriptor>
    ): Unit

    fun CompilerContext.generateSyntheticMethods(
      thisDescriptor: ClassDescriptor,
      name: Name,
      bindingContext: BindingContext,
      fromSupertypes: List<SimpleFunctionDescriptor>,
      result: MutableCollection<SimpleFunctionDescriptor>
    ): Unit

    fun CompilerContext.generateSyntheticProperties(
      thisDescriptor: ClassDescriptor,
      name: Name,
      bindingContext: BindingContext,
      fromSupertypes: ArrayList<PropertyDescriptor>,
      result: MutableSet<PropertyDescriptor>
    ): Unit

    fun CompilerContext.getSyntheticCompanionObjectNameIfNeeded(thisDescriptor: ClassDescriptor): Name?

    fun CompilerContext.getSyntheticFunctionNames(thisDescriptor: ClassDescriptor): List<Name>

    fun CompilerContext.getSyntheticNestedClassNames(thisDescriptor: ClassDescriptor): List<Name>
  }

  interface DeclarationAttributeAlterer : ExtensionPhase {
    fun CompilerContext.refineDeclarationModality(
      modifierListOwner: KtModifierListOwner,
      declaration: DeclarationDescriptor?,
      containingDeclaration: DeclarationDescriptor?,
      currentModality: Modality,
      bindingContext: BindingContext,
      isImplicitModality: Boolean
    ): Modality?
  }

  interface StorageComponentContainer : ExtensionPhase {
    fun CompilerContext.registerModuleComponents(
      container: org.jetbrains.kotlin.container.StorageComponentContainer,
      moduleDescriptor: ModuleDescriptor
    ): Unit

    fun CompilerContext.check(
      declaration: KtDeclaration,
      descriptor: DeclarationDescriptor,
      context: DeclarationCheckerContext
    ): Unit
  }

  interface ClassBuilder : ExtensionPhase {
    fun CompilerContext.interceptClassBuilder(
      interceptedFactory: ClassBuilderFactory,
      bindingContext: BindingContext,
      diagnostics: DiagnosticSink
    ): ClassBuilderFactory
  }

  interface Codegen : ExtensionPhase {
    fun CompilerContext.applyFunction(
      receiver: StackValue,
      resolvedCall: ResolvedCall<*>,
      c: ExpressionCodegenExtension.Context
    ): StackValue?

    fun CompilerContext.applyProperty(
      receiver: StackValue,
      resolvedCall: ResolvedCall<*>,
      c: ExpressionCodegenExtension.Context
    ): StackValue?

    fun CompilerContext.generateClassSyntheticParts(codegen: ImplementationBodyCodegen): Unit
  }

  interface IRGeneration : ExtensionPhase {

    fun CompilerContext.generate(
      file: IrFile,
      backendContext: BackendContext,
      bindingContext: BindingContext
    )

  }

  interface SyntheticScopeProvider : ExtensionPhase {
    fun CompilerContext.syntheticConstructor(constructor: ConstructorDescriptor): ConstructorDescriptor?
    fun CompilerContext.syntheticConstructors(scope: ResolutionScope): Collection<FunctionDescriptor>
    fun CompilerContext.syntheticConstructors(scope: ResolutionScope, name: Name, location: LookupLocation): Collection<FunctionDescriptor>
    fun CompilerContext.syntheticExtensionProperties(receiverTypes: Collection<KotlinType>, location: LookupLocation): Collection<PropertyDescriptor>
    fun CompilerContext.syntheticExtensionProperties(receiverTypes: Collection<KotlinType>, name: Name, location: LookupLocation): Collection<PropertyDescriptor>
    fun CompilerContext.syntheticMemberFunctions(receiverTypes: Collection<KotlinType>): Collection<FunctionDescriptor>
    fun CompilerContext.syntheticMemberFunctions(receiverTypes: Collection<KotlinType>, name: Name, location: LookupLocation): Collection<FunctionDescriptor>
    fun CompilerContext.syntheticStaticFunctions(scope: ResolutionScope): Collection<FunctionDescriptor>
    fun CompilerContext.syntheticStaticFunctions(scope: ResolutionScope, name: Name, location: LookupLocation): Collection<FunctionDescriptor>
  }

  interface DiagnosticsSuppressor : ExtensionPhase {
    fun CompilerContext.isSuppressed(diagnostic: Diagnostic): Boolean
  }

  interface PreprocessedVirtualFileFactory : ExtensionPhase {
    fun CompilerContext.isPassThrough(): Boolean
    fun CompilerContext.createPreprocessedFile(file: VirtualFile?): VirtualFile?
    fun CompilerContext.createPreprocessedLightFile(file: LightVirtualFile?): LightVirtualFile?
  }
}

class CompilerContext(
  val project: Project,
  val messageCollector: MessageCollector?
) {

  val ktPsiElementFactory: KtPsiFactory = KtPsiFactory(project, false)
  val ctx: CompilerContext = this
  lateinit var module: ModuleDescriptor
  lateinit var files: Collection<KtFile>
  lateinit var componentProvider: ComponentProvider
  private lateinit var metaAnalyzerField: MetaAnalyzer

  val analyzer: MetaAnalyzer?
    get() = when {
      ::metaAnalyzerField.isInitialized -> metaAnalyzerField
      ::componentProvider.isInitialized -> {
        //TODO sometimes we get in here before the DI container has finished composing and it blows up
        metaAnalyzerField = componentProvider.get()
        metaAnalyzerField
      }
      else -> null
    }
}

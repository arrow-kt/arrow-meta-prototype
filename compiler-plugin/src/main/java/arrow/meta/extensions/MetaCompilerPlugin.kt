package arrow.meta.extensions

import org.jetbrains.kotlin.analyzer.AnalysisResult
import org.jetbrains.kotlin.analyzer.ModuleInfo
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeys
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.codegen.ClassBuilder
import org.jetbrains.kotlin.codegen.ClassBuilderFactory
import org.jetbrains.kotlin.codegen.DelegatingClassBuilder
import org.jetbrains.kotlin.codegen.ImplementationBodyCodegen
import org.jetbrains.kotlin.codegen.StackValue
import org.jetbrains.kotlin.codegen.extensions.ClassBuilderInterceptorExtension
import org.jetbrains.kotlin.codegen.extensions.ExpressionCodegenExtension
import org.jetbrains.kotlin.com.intellij.mock.MockProject
import org.jetbrains.kotlin.com.intellij.openapi.project.Project
import org.jetbrains.kotlin.compiler.plugin.ComponentRegistrar
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.container.ComponentProvider
import org.jetbrains.kotlin.context.ProjectContext
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.descriptors.PackageFragmentDescriptor
import org.jetbrains.kotlin.descriptors.PackageFragmentProvider
import org.jetbrains.kotlin.descriptors.PropertyDescriptor
import org.jetbrains.kotlin.descriptors.SimpleFunctionDescriptor
import org.jetbrains.kotlin.diagnostics.DiagnosticSink
import org.jetbrains.kotlin.extensions.CompilerConfigurationExtension
import org.jetbrains.kotlin.extensions.DeclarationAttributeAltererExtension
import org.jetbrains.kotlin.extensions.StorageComponentContainerContributor
import org.jetbrains.kotlin.incremental.components.LookupTracker
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.psi.KtDeclaration
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtModifierListOwner
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCall
import org.jetbrains.kotlin.resolve.checkers.DeclarationChecker
import org.jetbrains.kotlin.resolve.checkers.DeclarationCheckerContext
import org.jetbrains.kotlin.resolve.extensions.SyntheticResolveExtension
import org.jetbrains.kotlin.resolve.jvm.diagnostics.JvmDeclarationOrigin
import org.jetbrains.kotlin.resolve.jvm.extensions.AnalysisHandlerExtension
import org.jetbrains.kotlin.resolve.jvm.extensions.PackageFragmentProviderExtension
import org.jetbrains.kotlin.resolve.lazy.LazyClassContext
import org.jetbrains.kotlin.resolve.lazy.declarations.ClassMemberDeclarationProvider
import org.jetbrains.kotlin.resolve.lazy.declarations.PackageMemberDeclarationProvider
import org.jetbrains.kotlin.storage.StorageManager
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.org.objectweb.asm.AnnotationVisitor
import org.jetbrains.org.objectweb.asm.FieldVisitor
import org.jetbrains.org.objectweb.asm.MethodVisitor
import java.util.*

interface MetaCompilerPlugin : ComponentRegistrar {

  fun intercept(): List<ExtensionPhase>

  fun meta(vararg phases: ExtensionPhase): List<ExtensionPhase> =
    phases.toList()

  fun updateConfig(updateConfiguration: CompilerContext.(configuration: CompilerConfiguration) -> Unit): ExtensionPhase.Config =
    object : ExtensionPhase.Config {
      override fun CompilerContext.updateConfiguration(configuration: CompilerConfiguration) {
        updateConfiguration(configuration)
      }
    }

  fun analysys(
    doAnalysis: CompilerContext.(
      project: Project,
      module: ModuleDescriptor,
      projectContext: ProjectContext,
      files: Collection<KtFile>,
      bindingTrace: BindingTrace,
      componentProvider: ComponentProvider
    ) -> AnalysisResult?,
    analysisCompleted: (
      project: Project,
      module: ModuleDescriptor,
      bindingTrace: BindingTrace,
      files: Collection<KtFile>
    ) -> AnalysisResult?): ExtensionPhase.AnalysisHandler =
    object : ExtensionPhase.AnalysisHandler {
      override fun CompilerContext.doAnalysis(project: Project, module: ModuleDescriptor, projectContext: ProjectContext, files: Collection<KtFile>, bindingTrace: BindingTrace, componentProvider: ComponentProvider): AnalysisResult? =
        doAnalysis(project, module, projectContext, files, bindingTrace, componentProvider)

      override fun CompilerContext.analysisCompleted(project: Project, module: ModuleDescriptor, bindingTrace: BindingTrace, files: Collection<KtFile>): AnalysisResult? =
        analysisCompleted(project, module, bindingTrace, files)
    }

  fun classBuilderFactory(interceptClassBuilderFactory: CompilerContext.(interceptedFactory: ClassBuilderFactory, bindingContext: BindingContext, diagnostics: DiagnosticSink) -> ClassBuilderFactory): ExtensionPhase.ClassBuilder =
    object : ExtensionPhase.ClassBuilder {
      override fun CompilerContext.interceptClassBuilder(interceptedFactory: ClassBuilderFactory, bindingContext: BindingContext, diagnostics: DiagnosticSink): ClassBuilderFactory =
        interceptClassBuilderFactory(this, interceptedFactory, bindingContext, diagnostics)
    }


  fun newMethod(f: (origin: JvmDeclarationOrigin, access: Int, name: String, desc: String, signature: String?, exceptions: Array<out String>?) -> Unit): ExtensionPhase.ClassBuilder =
    object : ExtensionPhase.ClassBuilder {
      override fun CompilerContext.interceptClassBuilder(interceptedFactory: ClassBuilderFactory, bindingContext: BindingContext, diagnostics: DiagnosticSink): ClassBuilderFactory =
        object : ClassBuilderFactory by interceptedFactory {
          override fun newClassBuilder(origin: JvmDeclarationOrigin): ClassBuilder =
            NewMethodClassBuilder(interceptedFactory.newClassBuilder(origin), f)
        }
    }

  fun newField(f: (origin: JvmDeclarationOrigin, access: Int, name: String, desc: String, signature: String?, value: Any?) -> Unit): ExtensionPhase.ClassBuilder =
    object : ExtensionPhase.ClassBuilder {
      override fun CompilerContext.interceptClassBuilder(interceptedFactory: ClassBuilderFactory, bindingContext: BindingContext, diagnostics: DiagnosticSink): ClassBuilderFactory =
        object : ClassBuilderFactory by interceptedFactory {
          override fun newClassBuilder(origin: JvmDeclarationOrigin): ClassBuilder =
            NewFieldClassBuilder(interceptedFactory.newClassBuilder(origin), f)
        }
    }

  fun newAnnotation(f: (desc: String, visible: Boolean) -> Unit): ExtensionPhase.ClassBuilder =
    object : ExtensionPhase.ClassBuilder {
      override fun CompilerContext.interceptClassBuilder(interceptedFactory: ClassBuilderFactory, bindingContext: BindingContext, diagnostics: DiagnosticSink): ClassBuilderFactory =
        object : ClassBuilderFactory by interceptedFactory {
          override fun newClassBuilder(origin: JvmDeclarationOrigin): ClassBuilder =
            NewAnnotationClassBuilder(interceptedFactory.newClassBuilder(origin), f)
        }
    }

  private class DelegateClassBuilderFactory(val interceptedFactory: ClassBuilderFactory) : ClassBuilderFactory by interceptedFactory {
    override fun newClassBuilder(origin: JvmDeclarationOrigin): ClassBuilder {
      return object : DelegatingClassBuilder() {
        override fun getDelegate(): ClassBuilder =
          interceptedFactory.newClassBuilder(origin)
      }
    }
  }

  fun storageComponent(check: CompilerContext.(declaration: KtDeclaration, descriptor: DeclarationDescriptor, context: DeclarationCheckerContext) -> Unit): ExtensionPhase.StorageComponentContainer =
    object : ExtensionPhase.StorageComponentContainer {
      override fun CompilerContext.check(declaration: KtDeclaration, descriptor: DeclarationDescriptor, context: DeclarationCheckerContext) {
        check(declaration, descriptor, context)
      }
    }

  fun codegen(
    applyFunction: CompilerContext.(receiver: StackValue, resolvedCall: ResolvedCall<*>, c: ExpressionCodegenExtension.Context) -> StackValue?,
    applyProperty: CompilerContext.(receiver: StackValue, resolvedCall: ResolvedCall<*>, c: ExpressionCodegenExtension.Context) -> StackValue?,
    generateClassSyntheticParts: CompilerContext.(codegen: ImplementationBodyCodegen) -> Unit
  ): ExtensionPhase.Codegen =
    object : ExtensionPhase.Codegen {
      override fun CompilerContext.applyFunction(receiver: StackValue, resolvedCall: ResolvedCall<*>, c: ExpressionCodegenExtension.Context): StackValue? =
        applyFunction(receiver, resolvedCall, c)

      override fun CompilerContext.applyProperty(receiver: StackValue, resolvedCall: ResolvedCall<*>, c: ExpressionCodegenExtension.Context): StackValue? =
        applyProperty(receiver, resolvedCall, c)

      override fun CompilerContext.generateClassSyntheticParts(codegen: ImplementationBodyCodegen) =
        generateClassSyntheticParts(codegen)
    }

  fun declarationAttributeAlterer(
    refineDeclarationModality: CompilerContext.(
      modifierListOwner: KtModifierListOwner,
      declaration: DeclarationDescriptor?,
      containingDeclaration: DeclarationDescriptor?,
      currentModality: Modality,
      bindingContext: BindingContext,
      isImplicitModality: Boolean
    ) -> Modality?
  ): ExtensionPhase.DeclarationAttributeAlterer =
    object : ExtensionPhase.DeclarationAttributeAlterer {
      override fun CompilerContext.refineDeclarationModality(modifierListOwner: KtModifierListOwner, declaration: DeclarationDescriptor?, containingDeclaration: DeclarationDescriptor?, currentModality: Modality, bindingContext: BindingContext, isImplicitModality: Boolean): Modality? =
        refineDeclarationModality(modifierListOwner, declaration, containingDeclaration, currentModality, bindingContext, isImplicitModality)
    }

  fun packageProvider(getPackageFragmentProvider: CompilerContext.(project: Project, module: ModuleDescriptor, storageManager: StorageManager, trace: BindingTrace, moduleInfo: ModuleInfo?, lookupTracker: LookupTracker) -> PackageFragmentProvider?): ExtensionPhase.PackageProvider =
    object : ExtensionPhase.PackageProvider {
      override fun CompilerContext.getPackageFragmentProvider(project: Project, module: ModuleDescriptor, storageManager: StorageManager, trace: BindingTrace, moduleInfo: ModuleInfo?, lookupTracker: LookupTracker): PackageFragmentProvider? =
        getPackageFragmentProvider(project, module, storageManager, trace, moduleInfo, lookupTracker)
    }

  fun syntheticResolver(addSyntheticSupertypes: CompilerContext.(thisDescriptor: ClassDescriptor, supertypes: MutableList<KotlinType>) -> Unit,
                        generateSyntheticClasses: CompilerContext.(thisDescriptor: ClassDescriptor, name: Name, ctx: LazyClassContext, declarationProvider: ClassMemberDeclarationProvider, result: MutableSet<ClassDescriptor>) -> Unit,
                        generatePackageSyntheticClasses: CompilerContext.(thisDescriptor: PackageFragmentDescriptor, name: Name, ctx: LazyClassContext, declarationProvider: PackageMemberDeclarationProvider, result: MutableSet<ClassDescriptor>) -> Unit,
                        generateSyntheticMethods: CompilerContext.(thisDescriptor: ClassDescriptor, name: Name, bindingContext: BindingContext, fromSupertypes: List<SimpleFunctionDescriptor>, result: MutableCollection<SimpleFunctionDescriptor>) -> Unit,
                        generateSyntheticProperties: CompilerContext.(thisDescriptor: ClassDescriptor, name: Name, bindingContext: BindingContext, fromSupertypes: ArrayList<PropertyDescriptor>, result: MutableSet<PropertyDescriptor>) -> Unit,
                        getSyntheticCompanionObjectNameIfNeeded: CompilerContext.(thisDescriptor: ClassDescriptor) -> Name?,
                        getSyntheticFunctionNames: CompilerContext.(thisDescriptor: ClassDescriptor) -> List<Name>,
                        getSyntheticNestedClassNames: CompilerContext.(thisDescriptor: ClassDescriptor) -> List<Name>
  ): ExtensionPhase.SyntheticResolver =
    object : ExtensionPhase.SyntheticResolver {
      override fun CompilerContext.addSyntheticSupertypes(thisDescriptor: ClassDescriptor, supertypes: MutableList<KotlinType>) {
        addSyntheticSupertypes(thisDescriptor, supertypes)
      }

      override fun CompilerContext.generateSyntheticClasses(thisDescriptor: ClassDescriptor, name: Name, ctx: LazyClassContext, declarationProvider: ClassMemberDeclarationProvider, result: MutableSet<ClassDescriptor>) {
        generateSyntheticClasses(thisDescriptor, name, ctx, declarationProvider, result)
      }

      override fun CompilerContext.generatePackageSyntheticClasses(thisDescriptor: PackageFragmentDescriptor, name: Name, ctx: LazyClassContext, declarationProvider: PackageMemberDeclarationProvider, result: MutableSet<ClassDescriptor>) {
        generatePackageSyntheticClasses(thisDescriptor, name, ctx, declarationProvider, result)
      }

      override fun CompilerContext.generateSyntheticMethods(thisDescriptor: ClassDescriptor, name: Name, bindingContext: BindingContext, fromSupertypes: List<SimpleFunctionDescriptor>, result: MutableCollection<SimpleFunctionDescriptor>) {
        generateSyntheticMethods(thisDescriptor, name, bindingContext, fromSupertypes, result)
      }

      override fun CompilerContext.generateSyntheticProperties(thisDescriptor: ClassDescriptor, name: Name, bindingContext: BindingContext, fromSupertypes: ArrayList<PropertyDescriptor>, result: MutableSet<PropertyDescriptor>) {
        generateSyntheticProperties(thisDescriptor, name, bindingContext, fromSupertypes, result)
      }

      override fun CompilerContext.getSyntheticCompanionObjectNameIfNeeded(thisDescriptor: ClassDescriptor): Name? =
        getSyntheticCompanionObjectNameIfNeeded(thisDescriptor)

      override fun CompilerContext.getSyntheticFunctionNames(thisDescriptor: ClassDescriptor): List<Name> =
        getSyntheticFunctionNames(thisDescriptor)

      override fun CompilerContext.getSyntheticNestedClassNames(thisDescriptor: ClassDescriptor): List<Name> =
        getSyntheticNestedClassNames(thisDescriptor)
    }

  override fun registerProjectComponents(project: MockProject, configuration: CompilerConfiguration) {
    val messageCollector: MessageCollector = configuration.get(CLIConfigurationKeys.MESSAGE_COLLECTOR_KEY, MessageCollector.NONE)
    val ctx = CompilerContext(messageCollector)
    intercept().forEach { phase ->
      if (phase is ExtensionPhase.Config) registerCompilerConfiguration(project, phase, ctx)
      if (phase is ExtensionPhase.AnalysisHandler) registerAnalysisHandler(project, phase, ctx)
      if (phase is ExtensionPhase.ClassBuilder) registerClassBuilder(project, phase, ctx)
      if (phase is ExtensionPhase.StorageComponentContainer) registerStorageComponentContainer(project, phase, ctx)
      if (phase is ExtensionPhase.Codegen) registerCodegen(project, phase, ctx)
      if (phase is ExtensionPhase.DeclarationAttributeAlterer) registerDeclarationAttributeAlterer(project, phase, ctx)
      if (phase is ExtensionPhase.PackageProvider) registerPackageProvider(project, phase, ctx)
      if (phase is ExtensionPhase.SyntheticResolver) registerSyntheticResolver(project, phase, ctx)
    }
  }

  fun registerSyntheticResolver(project: MockProject, phase: ExtensionPhase.SyntheticResolver, compilerContext: CompilerContext) {
    SyntheticResolveExtension.registerExtension(project, object : SyntheticResolveExtension {
      override fun addSyntheticSupertypes(thisDescriptor: ClassDescriptor, supertypes: MutableList<KotlinType>) {
        phase.run { compilerContext.addSyntheticSupertypes(thisDescriptor, supertypes) }
      }

      override fun generateSyntheticClasses(thisDescriptor: ClassDescriptor, name: Name, ctx: LazyClassContext, declarationProvider: ClassMemberDeclarationProvider, result: MutableSet<ClassDescriptor>) {
        phase.run { compilerContext.generateSyntheticClasses(thisDescriptor, name, ctx, declarationProvider, result) }
      }

      override fun generateSyntheticClasses(thisDescriptor: PackageFragmentDescriptor, name: Name, ctx: LazyClassContext, declarationProvider: PackageMemberDeclarationProvider, result: MutableSet<ClassDescriptor>) {
        phase.run { compilerContext.generatePackageSyntheticClasses(thisDescriptor, name, ctx, declarationProvider, result) }
      }

      override fun generateSyntheticMethods(thisDescriptor: ClassDescriptor, name: Name, bindingContext: BindingContext, fromSupertypes: List<SimpleFunctionDescriptor>, result: MutableCollection<SimpleFunctionDescriptor>) {
        phase.run { compilerContext.generateSyntheticMethods(thisDescriptor, name, bindingContext, fromSupertypes, result) }
      }

      override fun generateSyntheticProperties(thisDescriptor: ClassDescriptor, name: Name, bindingContext: BindingContext, fromSupertypes: ArrayList<PropertyDescriptor>, result: MutableSet<PropertyDescriptor>) {
        phase.run { compilerContext.generateSyntheticProperties(thisDescriptor, name, bindingContext, fromSupertypes, result) }
      }

      override fun getSyntheticCompanionObjectNameIfNeeded(thisDescriptor: ClassDescriptor): Name? {
        return phase.run { compilerContext.getSyntheticCompanionObjectNameIfNeeded(thisDescriptor) }
      }

      override fun getSyntheticFunctionNames(thisDescriptor: ClassDescriptor): List<Name> {
        return phase.run { compilerContext.getSyntheticFunctionNames(thisDescriptor) }
      }

      override fun getSyntheticNestedClassNames(thisDescriptor: ClassDescriptor): List<Name> {
        return phase.run { compilerContext.getSyntheticNestedClassNames(thisDescriptor) }
      }
    })
  }

  fun registerPackageProvider(project: MockProject, phase: ExtensionPhase.PackageProvider, ctx: CompilerContext) {
    PackageFragmentProviderExtension.registerExtension(project, object : PackageFragmentProviderExtension {
      override fun getPackageFragmentProvider(
        project: Project,
        module: ModuleDescriptor,
        storageManager: StorageManager,
        trace: BindingTrace,
        moduleInfo: ModuleInfo?,
        lookupTracker: LookupTracker
      ): PackageFragmentProvider? {
        return phase.run { ctx.getPackageFragmentProvider(project, module, storageManager, trace, moduleInfo, lookupTracker) }
      }
    })
  }

  fun registerDeclarationAttributeAlterer(project: MockProject, phase: ExtensionPhase.DeclarationAttributeAlterer, ctx: CompilerContext) {
    DeclarationAttributeAltererExtension.registerExtension(project, object : DeclarationAttributeAltererExtension {
      override fun refineDeclarationModality(modifierListOwner: KtModifierListOwner, declaration: DeclarationDescriptor?, containingDeclaration: DeclarationDescriptor?, currentModality: Modality, bindingContext: BindingContext, isImplicitModality: Boolean): Modality? {
        return phase.run { ctx.refineDeclarationModality(modifierListOwner, declaration, containingDeclaration, currentModality, bindingContext, isImplicitModality) }
      }
    })
  }

  fun registerCodegen(project: MockProject, phase: ExtensionPhase.Codegen, ctx: CompilerContext) {
    ExpressionCodegenExtension.registerExtension(project, object : ExpressionCodegenExtension {
      override fun applyFunction(receiver: StackValue, resolvedCall: ResolvedCall<*>, c: ExpressionCodegenExtension.Context): StackValue? {
        return phase.run { ctx.applyFunction(receiver, resolvedCall, c) }
      }

      override fun applyProperty(receiver: StackValue, resolvedCall: ResolvedCall<*>, c: ExpressionCodegenExtension.Context): StackValue? {
        return phase.run { ctx.applyProperty(receiver, resolvedCall, c) }
      }

      override fun generateClassSyntheticParts(codegen: ImplementationBodyCodegen) {
        phase.run { ctx.generateClassSyntheticParts(codegen) }
      }
    })
  }

  class DelegatingContributorChecker(val phase: ExtensionPhase.StorageComponentContainer, val ctx: CompilerContext) : StorageComponentContainerContributor, DeclarationChecker {
    override fun check(declaration: KtDeclaration, descriptor: DeclarationDescriptor, context: DeclarationCheckerContext) {
      phase.run { ctx.check(declaration, descriptor, context) }
    }
  }

  fun registerStorageComponentContainer(project: MockProject, phase: ExtensionPhase.StorageComponentContainer, ctx: CompilerContext) {
    StorageComponentContainerContributor.registerExtension(project, DelegatingContributorChecker(phase, ctx))
  }

  fun registerAnalysisHandler(project: MockProject, phase: ExtensionPhase.AnalysisHandler, ctx: CompilerContext) {
    AnalysisHandlerExtension.registerExtension(project, object : AnalysisHandlerExtension {
      override fun analysisCompleted(project: Project, module: ModuleDescriptor, bindingTrace: BindingTrace, files: Collection<KtFile>): AnalysisResult? {
        return phase.run { ctx.analysisCompleted(project, module, bindingTrace, files) }
      }

      override fun doAnalysis(project: Project, module: ModuleDescriptor, projectContext: ProjectContext, files: Collection<KtFile>, bindingTrace: BindingTrace, componentProvider: ComponentProvider): AnalysisResult? {
        return phase.run { ctx.doAnalysis(project, module, projectContext, files, bindingTrace, componentProvider) }
      }
    })
  }

  fun registerClassBuilder(project: MockProject, phase: ExtensionPhase.ClassBuilder, ctx: CompilerContext) {
    ClassBuilderInterceptorExtension.registerExtension(project, object : ClassBuilderInterceptorExtension {
      override fun interceptClassBuilderFactory(interceptedFactory: ClassBuilderFactory, bindingContext: BindingContext, diagnostics: DiagnosticSink): ClassBuilderFactory =
        phase.run {
          ctx.interceptClassBuilder(interceptedFactory, bindingContext, diagnostics)
        }
    })
  }

  fun registerCompilerConfiguration(project: MockProject, phase: ExtensionPhase.Config, ctx: CompilerContext) {
    CompilerConfigurationExtension.registerExtension(project, object : CompilerConfigurationExtension {
      override fun updateConfiguration(configuration: CompilerConfiguration) {
        phase.run { ctx.updateConfiguration(configuration) }
      }
    })
  }

}

internal class NewMethodClassBuilder(
  private val builder: ClassBuilder,
  val f: (origin: JvmDeclarationOrigin, access: Int, name: String, desc: String, signature: String?, exceptions: Array<out String>?) -> Unit
) : DelegatingClassBuilder() {
  override fun getDelegate(): ClassBuilder = builder

  override fun newMethod(
    origin: JvmDeclarationOrigin,
    access: Int,
    name: String,
    desc: String,
    signature: String?,
    exceptions: Array<out String>?
  ): MethodVisitor {
    //delegate to the parent method visitor for construction
    val original: MethodVisitor = super.newMethod(origin, access, name, desc, signature, exceptions)
    f(origin, access, name, desc, signature, exceptions)
    return original
  }

}

internal class NewFieldClassBuilder(
  private val builder: ClassBuilder,
  val f: (origin: JvmDeclarationOrigin, access: Int, name: String, desc: String, signature: String?, value: Any?) -> Unit
) : DelegatingClassBuilder() {
  override fun getDelegate(): ClassBuilder = builder

  override fun newField(
    origin: JvmDeclarationOrigin,
    access: Int,
    name: String,
    desc: String,
    signature: String?,
    value: Any?
  ): FieldVisitor {
    //delegate to the parent method visitor for construction
    val original: FieldVisitor = super.newField(origin, access, name, desc, signature, value)
    f(origin, access, name, desc, signature, value)
    return original
  }

}

internal class NewAnnotationClassBuilder(
  private val builder: ClassBuilder,
  val f: (desc: String, visible: Boolean) -> Unit
) : DelegatingClassBuilder() {
  override fun getDelegate(): ClassBuilder = builder

  override fun newAnnotation(desc: String, visible: Boolean): AnnotationVisitor {
    val original: AnnotationVisitor = super.newAnnotation(desc, visible)
    f(desc, visible)
    return original
  }

}
package arrow.meta.plugin.idea

import arrow.meta.extensions.CompilerContext
import arrow.meta.qq.MetaAnalyzer
import arrow.meta.qq.Quote
import arrow.meta.qq.functionNames
import arrow.meta.qq.isMetaFile
import arrow.meta.qq.ktClassOrObject
import arrow.meta.qq.ktFile
import arrow.meta.qq.nestedClassNames
import com.intellij.AppTopics
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.compiler.CompilationStatusListener
import com.intellij.openapi.compiler.CompileContext
import com.intellij.openapi.compiler.CompilerTopics
import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.FileDocumentManagerListener
import com.intellij.openapi.fileEditor.FileEditor
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.FileEditorManagerEvent
import com.intellij.openapi.fileEditor.FileEditorManagerListener
import com.intellij.openapi.fileEditor.FileEditorProvider
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Key
import com.intellij.openapi.util.UserDataHolderBase
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.util.FileContentUtil
import com.intellij.util.keyFMap.KeyFMap
import org.jetbrains.kotlin.analyzer.AnalysisResult
import org.jetbrains.kotlin.analyzer.ModuleInfo
import org.jetbrains.kotlin.descriptors.CallableMemberDescriptor
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor
import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.descriptors.PackageFragmentDescriptor
import org.jetbrains.kotlin.descriptors.PackageViewDescriptor
import org.jetbrains.kotlin.descriptors.PropertyDescriptor
import org.jetbrains.kotlin.descriptors.SimpleFunctionDescriptor
import org.jetbrains.kotlin.descriptors.TypeAliasDescriptor
import org.jetbrains.kotlin.descriptors.Visibilities
import org.jetbrains.kotlin.descriptors.impl.DeclarationDescriptorVisitorEmptyBodies
import org.jetbrains.kotlin.idea.caches.project.forcedModuleInfo
import org.jetbrains.kotlin.idea.caches.resolve.analyzeWithAllCompilerChecks
import org.jetbrains.kotlin.idea.core.util.toPsiFile
import org.jetbrains.kotlin.js.resolve.diagnostics.findPsi
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtPureClassOrObject
import org.jetbrains.kotlin.psi.synthetics.SyntheticClassOrObjectDescriptor
import org.jetbrains.kotlin.resolve.descriptorUtil.fqNameSafe
import org.jetbrains.kotlin.resolve.descriptorUtil.module
import org.jetbrains.kotlin.resolve.lazy.LazyClassContext
import org.jetbrains.kotlin.resolve.lazy.declarations.ClassMemberDeclarationProvider
import org.jetbrains.kotlin.resolve.lazy.declarations.PackageMemberDeclarationProvider
import org.jetbrains.kotlin.resolve.lazy.descriptors.LazyClassDescriptor
import org.jetbrains.kotlin.storage.StorageManager
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.TypeUtils
import org.jetbrains.kotlin.utils.addToStdlib.safeAs
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.collections.set

val subscribedToEditorHooks: AtomicBoolean = AtomicBoolean(false)

private val blackList: Set<Name> =
  listOf("equals", "hashCode", "toString")
    .map(Name::identifier).toSet()

class SyntheticDescriptorCache(
  val module: ModuleDescriptor,
  val file: KtFile,
  val descriptorCache: ConcurrentHashMap<FqName, DeclarationDescriptor> = ConcurrentHashMap()
) {
  companion object {
    fun fromAnalysis(file: KtFile, analysis: AnalysisResult): SyntheticDescriptorCache {
      val moduleDescriptor = analysis.moduleDescriptor
      val packageViewDescriptor = moduleDescriptor.getPackage(file.packageFqName)
      val cache = SyntheticDescriptorCache(moduleDescriptor, file)
      packageViewDescriptor.accept(
        MetaRecursiveVisitor(object : DeclarationDescriptorVisitorEmptyBodies<Unit, Unit>() {
          override fun visitDeclarationDescriptor(descriptor: DeclarationDescriptor?, data: Unit?) {
            descriptor?.let {
              if (descriptor.name !in blackList) {
                if (descriptor is CallableMemberDescriptor) {
                  if (descriptor.ktFile()?.isMetaFile() == true && descriptor.kind != CallableMemberDescriptor.Kind.FAKE_OVERRIDE) {
                    println("Callable: Added to cache: ${descriptor.fqNameSafe}")
                    cache.descriptorCache[it.fqNameSafe] = it
                  }
                } else if (descriptor is ClassDescriptor) {
                  if (descriptor.ktFile()?.isMetaFile() == true) {
                    println("Class: Added to cache: ${descriptor.fqNameSafe}")
                    cache.descriptorCache[it.fqNameSafe] = it
                  }
                } else if (descriptor is TypeAliasDescriptor) {
                  if (descriptor.ktFile()?.isMetaFile() == true) {
                    println("Class: Added to cache: ${descriptor.fqNameSafe}")
                    cache.descriptorCache[it.fqNameSafe] = it
                  }
                } else if (descriptor is PackageViewDescriptor) {
                  if (descriptor.ktFile()?.isMetaFile() == true) {
                    println("Package: Added to cache: ${descriptor.fqNameSafe}")
                    cache.descriptorCache[it.fqNameSafe] = it
                  }
                } else {
                  println("skipped synthetic cache entry: $descriptor: ${descriptor.name}")
                }
              }
            }
          }
        }), Unit)
      return cache
    }

  }
}

private data class CacheId(val value: String)

private val VirtualFile.metaCacheId: CacheId
  get() = CacheId(path)

class MetaIdeAnalyzer : MetaAnalyzer {

  override fun ideProject(): Project? = currentProject()

  private val cache: ConcurrentHashMap<CacheId, SyntheticDescriptorCache> = ConcurrentHashMap()

  private val FILE_KEY = Key.create<VirtualFile>("FILE_KEY")

  override fun DeclarationDescriptor?.isGenerated(): Boolean =
    this?.findPsi()?.ktFile()?.name?.startsWith("_meta_") == true

  val DeclarationDescriptor?.syntheticCache: SyntheticDescriptorCache?
    get() = this?.let {
      if (!it.isGenerated()) {
        val file: VirtualFile? = it.ktFile()?.virtualFile
        file?.let { cache[it.metaCacheId] }
      } else null
    }

  override fun metaPackageFragments(
    storageManager: StorageManager,
    module: ModuleDescriptor,
    packageName: FqName
  ): List<PackageFragmentDescriptor> {
    val metaModule = module.cachedSyntheticModule()
    val moduleCache = module.cache()
    return if (metaModule != null && moduleCache != null && moduleCache.file.packageFqName == packageName) {
      listOf(MetaPackageFramentDescriptor(metaModule, moduleCache, packageName))
    } else emptyList()
  }

  private fun ModuleDescriptor.cachedSyntheticModule(): ModuleDescriptor? =
    cache.values.firstOrNull {
      it.module.name == name
    }?.module

  private fun ModuleDescriptor.cache(): SyntheticDescriptorCache? =
    cache.values.firstOrNull {
      it.module.name == name
    }

  override fun metaSubPackagesOf(
    storageManager: StorageManager,
    module: ModuleDescriptor,
    fqName: FqName,
    nameFilter: (Name) -> Boolean
  ): Collection<FqName> =
    cache.values.filter { it.file.packageFqName.asString().startsWith(fqName.asString()) && nameFilter(fqName.shortName()) }
      .map { it.file.packageFqName }.toSet()

  override fun metaSyntheticFunctionNames(thisDescriptor: ClassDescriptor): List<Name> =
    thisDescriptor.metaDescriptor()?.let { compiledDescriptor ->
      compiledDescriptor.functionNames() - thisDescriptor.functionNamesFromPsi() - blackList
    }?.toList() ?: emptyList()

  private fun ClassDescriptor.functionNamesFromPsi(): Set<Name> =
    findPsi().safeAs<KtClassOrObject>()?.functionNames()?.toSet() ?: emptySet()

  override fun metaSyntheticNestedClassNames(thisDescriptor: ClassDescriptor): List<Name> =
    thisDescriptor.metaDescriptor()?.let { compiledDescriptor ->
      thisDescriptor.nestedClassNamesFromPsi() - compiledDescriptor.nestedClassNamesFromPsi()
    } ?: emptyList()

  private fun ClassDescriptor.nestedClassNamesFromPsi(): List<Name> =
    ktClassOrObject()?.nestedClassNames()?.toSet()?.map(Name::identifier) ?: emptyList()

  override fun metaSyntheticMethods(name: Name, thisDescriptor: ClassDescriptor): List<SimpleFunctionDescriptor> =
    thisDescriptor.metaDescriptor()?.let { compiledDescriptor ->
      val compiledFunctions = compiledDescriptor.functions()
      val originalFunctions = thisDescriptor.functionNames()
      compiledFunctions.filter { cf ->
        cf.name == name && cf.name !in originalFunctions && cf.name !in blackList
      }.map { fn ->
        fn.copy(
          compiledDescriptor,
          fn.modality,
          fn.visibility,
          CallableMemberDescriptor.Kind.SYNTHESIZED,
          true
        )
      }
    } ?: emptyList()

  private fun ClassDescriptor.functionNames(): Set<Name> =
    ktClassOrObject()?.functionNames()?.toSet() ?: emptySet()

  private fun ClassDescriptor.functions(): List<SimpleFunctionDescriptor> =
    unsubstitutedMemberScope.getContributedDescriptors { true }.filterIsInstance<SimpleFunctionDescriptor>()

  override fun metaSyntheticProperties(name: Name, thisDescriptor: ClassDescriptor): List<PropertyDescriptor> =
    thisDescriptor.metaDescriptor()?.let { compiledDescriptor ->
      val compiledProperties = compiledDescriptor.properties()
      val originalProperties = thisDescriptor.propertyNames()
      compiledProperties.filter { cf ->
        cf.name == name && cf.name !in originalProperties && cf.name !in blackList
      }.mapNotNull { fn ->
        fn.copy(
          compiledDescriptor,
          fn.modality,
          fn.visibility,
          CallableMemberDescriptor.Kind.SYNTHESIZED,
          true
        ).safeAs<PropertyDescriptor>()
      }
    } ?: emptyList()

  private fun ClassDescriptor.propertyNames(): Set<Name> =
    unsubstitutedMemberScope.getVariableNames()

  private fun ClassDescriptor.properties(): List<PropertyDescriptor> =
    unsubstitutedMemberScope.getContributedDescriptors { true }.filterIsInstance<PropertyDescriptor>()

  override fun metaSyntheticSupertypes(classDescriptor: ClassDescriptor): List<KotlinType> =
    classDescriptor.metaDescriptor()?.let { compiled ->
      val superTypes = TypeUtils.getAllSupertypes(compiled.defaultType)
        .filter { tpe -> tpe != classDescriptor.module.builtIns.anyType }
      superTypes
    } ?: emptyList()

  private inline fun <reified A : DeclarationDescriptor> A.metaDescriptor(): A? =
    syntheticCache?.run { descriptorCache[fqNameSafe] }.safeAs()

  override fun metaCompanionObjectNameIfNeeded(classDescriptor: ClassDescriptor): Name? =
    classDescriptor.metaDescriptor()?.ktClassOrObject()?.companionObjects?.firstOrNull()?.nameAsSafeName

  override fun metaSyntheticPackageClasses(name: Name, packageDescriptor: PackageFragmentDescriptor, declarationProvider: PackageMemberDeclarationProvider): List<ClassDescriptor> =
    packageDescriptor.metaDescriptor()?.let { compiled ->
      val compiledClasses = compiled.classDescriptors()
      val originalClasses = packageDescriptor.classifierNames()
      compiledClasses.filter { cf ->
        cf.name == name && cf.name !in originalClasses
      }
    } ?: emptyList()

  private fun PackageFragmentDescriptor.classifierNames(): Set<Name> =
    getMemberScope().getClassifierNames() ?: emptySet()

  private fun PackageFragmentDescriptor.classDescriptors(): List<ClassDescriptor> =
    getMemberScope().getContributedDescriptors { true }.filterIsInstance<ClassDescriptor>()

  override fun metaSyntheticClasses(
    name: Name,
    classDescriptor: ClassDescriptor,
    declarationProvider: ClassMemberDeclarationProvider
  ): List<ClassDescriptor> =
    classDescriptor.metaDescriptor()?.let { compiled ->
      val compiledClasses = compiled.classDescriptors()
      val originalClasses = classDescriptor.classifierNames()
      compiledClasses.filter { cf ->
        cf.name == name && cf.name !in originalClasses
      }
    } ?: emptyList()

  private fun ClassDescriptor.classifierNames(): Set<Name> =
    unsubstitutedMemberScope.getClassifierNames() ?: emptySet()

  private fun ClassDescriptor.classDescriptors(): List<ClassDescriptor> =
    unsubstitutedMemberScope.getContributedDescriptors { true }.filterIsInstance<ClassDescriptor>()

  private fun Document.getFile(): VirtualFile? {
    val userMapField =
      UserDataHolderBase::class.java.getDeclaredField("myUserMap")
        .also { it.isAccessible = true }
    val userData: KeyFMap = userMapField.get(this) as KeyFMap
    return userData.keys.find { it.toString() == FILE_KEY.toString() }?.let {
      userData[it] as VirtualFile?
    }
  }

  override fun KtFile.metaAnalysys(moduleInfo: ModuleInfo?): AnalysisResult {
    moduleInfo?.let { forcedModuleInfo = it }
    return analyzeWithAllCompilerChecks().also {
      it.moduleDescriptor.allDependencyModules
    }
  }

  override fun <P : KtElement, K : KtElement, S> CompilerContext.subscribeToEditorHooks(
    quoteFactory: Quote.Factory<P, K, S>,
    match: K.() -> Boolean,
    map: S.(K) -> List<String>,
    transformation: (VirtualFile, Document) -> Pair<KtFile, AnalysisResult>?): Unit {
    if (!subscribedToEditorHooks.get()) {
      val application = ApplicationManager.getApplication()
      val connection = application.messageBus.connect()
      connection.subscribe<FileEditorManagerListener>(
        FileEditorManagerListener.FILE_EDITOR_MANAGER,
        object : FileEditorManagerListener {
          override fun selectionChanged(event: FileEditorManagerEvent) {
            println("FileEditorManagerListener.selectionChanged: ${this@MetaIdeAnalyzer} $event")
            super.selectionChanged(event)
          }

          override fun fileOpened(source: FileEditorManager, file: VirtualFile) {
            println("FileEditorManagerListener.fileOpened: ${this@MetaIdeAnalyzer} $file")
            file.document()?.let {
              println("FileEditorManagerListener.fileOpened: populateSyntheticCache $it")
              populateSyntheticCache(it, transformation)
              FileContentUtil.reparseFiles(file)
            }
            super.fileOpened(source, file)
          }

          override fun fileOpenedSync(source: FileEditorManager, file: VirtualFile, editors: com.intellij.openapi.util.Pair<Array<FileEditor>, Array<FileEditorProvider>>) {
            println("FileEditorManagerListener.fileOpenedSync: ${this@MetaIdeAnalyzer} $file")
            super.fileOpenedSync(source, file, editors)
          }

          override fun fileClosed(source: FileEditorManager, file: VirtualFile) {
            println("FileEditorManagerListener.fileClosed: ${this@MetaIdeAnalyzer}, removing cache for $file")
            cache.remove(file.metaCacheId)
            super.fileClosed(source, file)
          }
        }
      )
      connection.subscribe<CompilationStatusListener>(
        CompilerTopics.COMPILATION_STATUS,
        object : CompilationStatusListener {
          override fun compilationFinished(aborted: Boolean, errors: Int, warnings: Int, compileContext: CompileContext) {
            println("CompilationStatusListener.compilationFinished: ${this@MetaIdeAnalyzer} errors: $errors, context: $compileContext")
            super.compilationFinished(aborted, errors, warnings, compileContext)
          }

          override fun automakeCompilationFinished(errors: Int, warnings: Int, compileContext: CompileContext) {
            println("CompilationStatusListener.automakeCompilationFinished: ${this@MetaIdeAnalyzer} errors: $errors, context: $compileContext")
            super.automakeCompilationFinished(errors, warnings, compileContext)
          }

          override fun fileGenerated(outputRoot: String, relativePath: String) {
            println("CompilationStatusListener.fileGenerated: ${this@MetaIdeAnalyzer} $outputRoot $relativePath")
            super.fileGenerated(outputRoot, relativePath)
          }
        }
      )
      connection.subscribe<FileDocumentManagerListener>(
        AppTopics.FILE_DOCUMENT_SYNC,
        object : FileDocumentManagerListener {
          override fun fileContentReloaded(file: VirtualFile, document: Document) {
            println("MetaOnFileSaveComponent.fileContentReloaded: ${this@MetaIdeAnalyzer} $file")
            super.fileContentReloaded(file, document)
          }

          override fun fileContentLoaded(file: VirtualFile, document: Document) {
            println("MetaOnFileSaveComponent.fileContentLoaded: ${this@MetaIdeAnalyzer} $file")
            super.fileContentLoaded(file, document)
          }

          override fun beforeDocumentSaving(document: Document) {
            populateSyntheticCache(document, transformation)
            document.getFile()?.let { file ->
              file.refresh(true, true) {
                println("$file refreshed")
              }
            }
            println("MetaOnFileSaveComponent.beforeDocumentSaving: ${this@MetaIdeAnalyzer} $document")
          }
        })
      subscribedToEditorHooks.set(true)
    }
  }

  override fun populateSyntheticCache(document: Document, transformation: (VirtualFile, Document) -> Pair<KtFile, AnalysisResult>?) {
    document.getFile()?.let { file ->
      transformation(file, document)?.let { (ktFile, result) ->
        cache[file.metaCacheId] = SyntheticDescriptorCache.fromAnalysis(ktFile, result)
        println("Added cache transformation: cache[${file.name}] $result")
      }
    }
    dumpCacheInfo()
  }

  private fun dumpCacheInfo() {
    println(cache.toList().map { (cacheId, descriptor) ->
      "$cacheId : ${descriptor.descriptorCache.toList().joinToString { (fqName, descriptor) -> "\n$fqName: ${descriptor.name}" }}"
    })
  }

}

private fun ClassDescriptor.synthetic(
  editorDescriptor: ClassDescriptor,
  parent: KtPureClassOrObject,
  lazyClassContext: LazyClassContext,
  declarationProvider: ClassMemberDeclarationProvider): SyntheticClassOrObjectDescriptor? =
  when (this) {
    is SyntheticClassOrObjectDescriptor -> this
    is LazyClassDescriptor -> SyntheticClassOrObjectDescriptor(
      c = lazyClassContext,
      parentClassOrObject = parent,
      containingDeclaration = containingDeclaration,
      name = name,
      source = editorDescriptor.source,
      outerScope = declarationProvider.ownerInfo?.scopeAnchor?.let { anchor ->
        lazyClassContext.declarationScopeProvider.getResolutionScopeForDeclaration(anchor)
      }!!,
      modality = modality,
      visibility = visibility,
      annotations = annotations,
      constructorVisibility = constructors.firstOrNull()?.visibility ?: Visibilities.PUBLIC,
      kind = kind,
      isCompanionObject = isCompanionObject
    ).also {
      it.initialize(typeParameters = declaredTypeParameters)
    }
    else -> null
  }

fun VirtualFile.document(): Document? =
  currentProject()?.let { toPsiFile(it)?.viewProvider?.document }



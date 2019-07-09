package arrow.meta.debug.unusedServices

import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.resolve.lazy.FileScopeProvider
import org.jetbrains.kotlin.resolve.lazy.FileScopeProviderImpl
import org.jetbrains.kotlin.resolve.lazy.FileScopes
import org.jetbrains.kotlin.resolve.lazy.ImportForceResolver
import org.jetbrains.kotlin.resolve.scopes.LexicalScope
import org.jetbrains.kotlin.types.model.TypeSystemInferenceExtensionContextDelegate

/**
 * DefaultImpl is [FileScopeProviderImpl]
 * @throws ContainerConsistencyException if added primitively
 */
class MetaFileScopeProvider(val delegate: FileScopeProvider) : FileScopeProvider by delegate {
  override fun getFileScopes(file: KtFile): FileScopes {
    println("MetaFileScopeProvider.getFileScopes: $file")
    return delegate.getFileScopes(file)
  }

  override fun getFileResolutionScope(file: KtFile): LexicalScope {
    println("MetaFileScopeProvider.getFileResolutionScope: $file")
    return delegate.getFileResolutionScope(file)
  }

  override fun getImportResolver(file: KtFile): ImportForceResolver {
    println("MetaFileScopeProvider.getFileResolutionScope: $file")
    return delegate.getImportResolver(file)
  }
}
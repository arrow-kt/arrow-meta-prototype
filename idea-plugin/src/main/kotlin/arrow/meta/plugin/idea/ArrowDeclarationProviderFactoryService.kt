package arrow.meta.plugin.idea

import com.intellij.openapi.project.Project
import com.intellij.psi.search.GlobalSearchScope
import org.jetbrains.kotlin.analyzer.ModuleInfo
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.resolve.lazy.declarations.DeclarationProviderFactory
import org.jetbrains.kotlin.resolve.lazy.declarations.DeclarationProviderFactoryService
import org.jetbrains.kotlin.storage.StorageManager
/*
 * Singleton, Can't be registered
 */
class ArrowDeclarationProviderFactoryService : DeclarationProviderFactoryService(){
  override fun create(project: Project, storageManager: StorageManager, syntheticFiles: Collection<KtFile>, filesScope: GlobalSearchScope, moduleInfo: ModuleInfo): DeclarationProviderFactory {
    println("ArrowDeclarationProviderFactoryService.create: $project, $storageManager, $syntheticFiles, $filesScope, $moduleInfo")
    return DeclarationProviderFactory.EMPTY
  }
}
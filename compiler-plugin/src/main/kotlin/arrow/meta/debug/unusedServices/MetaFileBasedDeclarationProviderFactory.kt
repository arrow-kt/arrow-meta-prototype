package arrow.meta.debug.unusedServices

import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.resolve.lazy.data.KtClassLikeInfo
import org.jetbrains.kotlin.resolve.lazy.declarations.ClassMemberDeclarationProvider
import org.jetbrains.kotlin.resolve.lazy.declarations.FileBasedDeclarationProviderFactory
import org.jetbrains.kotlin.resolve.lazy.declarations.PackageMemberDeclarationProvider
import org.jetbrains.kotlin.storage.StorageManager

/**
 * @throws UnresolvedDependenciesException
 * @UnusedService Currently we need to investigate how our costume functions can be executed.
 * Meaning we need to hook into the compiler earlier than Analysis. Either with Reflection or else
 */
class MetaFileBasedDeclarationProviderFactory(
  storageManager: StorageManager,
  files: Collection<KtFile>
) :
  FileBasedDeclarationProviderFactory(storageManager, files) {

  override fun getClassMemberDeclarationProvider(classLikeInfo: KtClassLikeInfo): ClassMemberDeclarationProvider {
    println("MetaFileBasedDeclarationProviderFactory.getClassMemberDeclarationProvider: $classLikeInfo")
    return super.getClassMemberDeclarationProvider(classLikeInfo)
  }

  override fun getPackageMemberDeclarationProvider(packageFqName: FqName): PackageMemberDeclarationProvider? {
    println("MetaFileBasedDeclarationProviderFactory.getPackageMemberDeclarationProvider: $packageFqName")
    return super.getPackageMemberDeclarationProvider(packageFqName)
  }

  override fun diagnoseMissingPackageFragment(fqName: FqName, file: KtFile?) {
    println("MetaFileBasedDeclarationProviderFactory.diagnoseMissingPackageFragment: $fqName, $file")
    super.diagnoseMissingPackageFragment(fqName, file)
  }

  override fun packageExists(packageFqName: FqName): Boolean {
    println("MetaFileBasedDeclarationProviderFactory.packageExists: $packageFqName")
    return super.packageExists(packageFqName)
  }

  /*override fun getAllDeclaredSubPackagesOf(parent: FqName): MutableCollection<FqName> {
    println("MetaFileBasedDeclarationProviderFactory.getAllDeclaredSubPackagesOf: $parent")
    return super.getAllDeclaredSubPackagesOf(parent)
  }*/

  override fun createPackageMemberDeclarationProvider(packageFqName: FqName): PackageMemberDeclarationProvider? {
    println("MetaFileBasedDeclarationProviderFactory.createPackageMemberDeclarationProvider: $packageFqName")
    return super.createPackageMemberDeclarationProvider(packageFqName)
  }
}
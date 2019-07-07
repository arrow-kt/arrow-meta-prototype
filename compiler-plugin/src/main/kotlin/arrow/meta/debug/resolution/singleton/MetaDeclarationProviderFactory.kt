package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.resolve.lazy.data.KtClassLikeInfo
import org.jetbrains.kotlin.resolve.lazy.declarations.ClassMemberDeclarationProvider
import org.jetbrains.kotlin.resolve.lazy.declarations.DeclarationProviderFactory
import org.jetbrains.kotlin.resolve.lazy.declarations.PackageMemberDeclarationProvider

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaDeclarationProviderFactory(val delegate: DeclarationProviderFactory) : DeclarationProviderFactory by delegate {
  override fun diagnoseMissingPackageFragment(p0: FqName, p1: KtFile?) {
    println("MetaDeclarationProviderFactory.diagnoseMissingPackageFragment: $p0, $p1")
    delegate.diagnoseMissingPackageFragment(p0, p1)
  }

  override fun getClassMemberDeclarationProvider(p0: KtClassLikeInfo): ClassMemberDeclarationProvider {
    println("MetaDeclarationProviderFactory.getClassMemberDeclarationProvider: $p0")
    return delegate.getClassMemberDeclarationProvider(p0)
  }

  override fun getPackageMemberDeclarationProvider(p0: FqName): PackageMemberDeclarationProvider? {
    println("MetaDeclarationProviderFactory.getPackageMemberDeclarationProvider: $p0")
    return delegate.getPackageMemberDeclarationProvider(p0)
  }
}
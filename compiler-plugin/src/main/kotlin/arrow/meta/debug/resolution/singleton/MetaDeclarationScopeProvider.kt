package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowInfo
import org.jetbrains.kotlin.resolve.lazy.DeclarationScopeProvider
import org.jetbrains.kotlin.resolve.scopes.LexicalScope
/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaDeclarationScopeProvider(val delegate: DeclarationScopeProvider) : DeclarationScopeProvider by delegate {
  override fun getResolutionScopeForDeclaration(p0: PsiElement): LexicalScope {
    println("MetaDeclarationScopeProvider.getResolutionScopeForDeclaration: $p0")
    return delegate.getResolutionScopeForDeclaration(p0)
  }

  override fun getOuterDataFlowInfoForDeclaration(p0: PsiElement): DataFlowInfo {
    println("MetaDeclarationScopeProvider.getOuterDataFlowInfoForDeclaration: $p0")
    return delegate.getOuterDataFlowInfoForDeclaration(p0)
  }
}
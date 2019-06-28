package arrow.meta.utils

import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowInfo
import org.jetbrains.kotlin.resolve.lazy.DeclarationScopeProvider
import org.jetbrains.kotlin.resolve.scopes.LexicalScope
/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaDeclarationScopeProvider : DeclarationScopeProvider {
  override fun getResolutionScopeForDeclaration(p0: PsiElement): LexicalScope {
    println("MetaDeclarationScopeProvider.getResolutionScopeForDeclaration: $p0")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getOuterDataFlowInfoForDeclaration(p0: PsiElement): DataFlowInfo {
    println("MetaDeclarationScopeProvider.getOuterDataFlowInfoForDeclaration: $p0")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}
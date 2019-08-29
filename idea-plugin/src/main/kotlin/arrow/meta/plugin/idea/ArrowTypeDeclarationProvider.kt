package arrow.meta.plugin.idea

import com.intellij.codeInsight.navigation.actions.TypeDeclarationProvider
import com.intellij.psi.PsiElement

/*
 * Only one can be registered, and that is always the first one: depends on RunTIme
 */
class ArrowTypeDeclarationProvider : TypeDeclarationProvider {
  override fun getSymbolTypeDeclarations(symbol: PsiElement): Array<PsiElement>? {
    println("ArrowTypeDeclarationProvider.getSymbolTypeDeclarations: $symbol")
    return null
  }
}
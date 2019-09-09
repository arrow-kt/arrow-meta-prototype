package arrow.meta.qq

import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import javax.swing.Icon

interface MetaIconProvider {
  fun registerIconProvider(f: MetaIconProvider.(psiElement: PsiElement, flags: Int) -> Icon?): Unit
  fun iconFor(psiElement: PsiElement): Icon
}
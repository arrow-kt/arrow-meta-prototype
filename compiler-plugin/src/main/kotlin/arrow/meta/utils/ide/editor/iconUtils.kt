package arrow.meta.utils.ide.editor

import org.jetbrains.kotlin.com.intellij.ide.IconProvider
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import javax.swing.Icon

/**
 * extensionProvider(
 *  epName = IconProvider.EXTENSION_POINT_NAME,
 *   impl = addIcon(KotlinIcons.SUSPEND_CALL) { psiElement, flag ->
 *     psiElement.safeAs<KtNamedFunction>()?.name == "foo"
 *  }
 * )
 */
fun addIcon(icon: Icon, matchOn: (psiElement: PsiElement, flag: Int) -> Boolean) = object : IconProvider() {
  override fun getIcon(p0: PsiElement, p1: Int): Icon? =
    if (matchOn(p0, p1)) icon else null
}
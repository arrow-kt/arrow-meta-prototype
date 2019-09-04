package arrow.meta.plugin.idea.icon

import com.intellij.ide.IconProvider
import com.intellij.openapi.project.DumbAware
import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.idea.util.hasNoSideEffects
import org.jetbrains.kotlin.psi.KtFile
import javax.swing.Icon

/*
 * Only for Files without Icons.
 * TODO: Override old Icons
 */
class ArrowIconProvider : IconProvider(), DumbAware {
  override fun getIcon(element: PsiElement, flags: Int): Icon? =
    if (element is KtFile) {
      if (element.declarations.any { d -> !d.hasNoSideEffects() })
        ArrowIcons.IMPURE_LINEMARKER_ICON
      else
        null
    } else null
}
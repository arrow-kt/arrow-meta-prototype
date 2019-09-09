package arrow.meta.plugin.idea

import arrow.meta.qq.MetaIconProvider
import com.intellij.core.CoreApplicationEnvironment
import com.intellij.ide.IconProvider
import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.idea.KotlinIcons
import javax.swing.Icon

class MetaIdeIconProvider : MetaIconProvider {

  override fun registerIconProvider(f: MetaIconProvider.(psiElement: PsiElement, flags: Int) -> Icon?) {
    val project = currentProject()
    project?.getComponent(CoreApplicationEnvironment::class.java)?.addExtension(IconProvider.EXTENSION_POINT_NAME, object : IconProvider() {
      override fun getIcon(element: PsiElement, flags: Int): Icon? = f(element, flags)
    })
  }

  override fun iconFor(psiElement: PsiElement): Icon = KotlinIcons.EXTENSION_FUNCTION
}

package arrow.meta.plugin.idea

import com.intellij.psi.ElementDescriptionLocation
import com.intellij.psi.ElementDescriptionProvider
import com.intellij.psi.PsiElement

class ArrowElementDescriptionProvider : ElementDescriptionProvider {
  override fun getElementDescription(element: PsiElement, location: ElementDescriptionLocation): String? {
    println("ArrowElementDescriptionProvider.getElementDescription: $element, $location")
    return ""
  }
}
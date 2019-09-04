package arrow.meta.plugin.idea.icon

import com.intellij.codeInsight.daemon.LineMarkerInfo
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.editor.markup.GutterIconRenderer
import com.intellij.openapi.progress.ProgressManager
import com.intellij.psi.PsiElement
import javax.swing.Icon

fun arrowLineMarkerInfo(icon: Icon, element: PsiElement, message: String): LineMarkerInfo<PsiElement> =
  object : LineMarkerInfo<PsiElement>(
    element,
    element.textRange,
    icon,
    { message },
    null,
    GutterIconRenderer.Alignment.LEFT
  ) {
    override fun createGutterRenderer(): GutterIconRenderer =
      object : LineMarkerInfo.LineMarkerGutterIconRenderer<PsiElement>(this) {
        override fun getClickAction(): AnAction? = null // to place breakpoint on mouse click
      }
  }

fun addLineMarker(
  elements: MutableList<PsiElement>,
  result: MutableCollection<LineMarkerInfo<PsiElement>>,
  match: (PsiElement) -> Boolean,
  infoMarker: (PsiElement) -> LineMarkerInfo<PsiElement>?
) {
  for (element in elements) {
    ProgressManager.checkCanceled()
    if (match(element)) {
      infoMarker(element)?.let { result.add(it) }
    }
  }
}

/*interface ArrowConfigNotifer {
  companion object {
    val topic: Topic<ArrowConfigNotifer.Companion>
      get() = Topic.create("Arrow", ArrowConfigNotifer.Companion::class.java)
  }
}*/

/*
fun PsiElement.lineNumber(): Int =
  PsiDocumentManager.getInstance(project).getDocument(containingFile)!!.getLineNumber(textOffset)
*/

/*internal fun getElementForLineMark(callElement: PsiElement): PsiElement = // passes PsiIdentifier
  when (callElement) {
    is KtSimpleNameExpression -> callElement.getReferencedNameElement()
    is KtObjectDeclaration -> callElement.nameIdentifier ?: callElement
    else -> callElement
  }*/
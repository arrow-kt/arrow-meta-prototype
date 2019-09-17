package arrow.meta.utils.ide.editor

import com.intellij.codeInsight.daemon.LineMarkerInfo
import com.intellij.codeInsight.daemon.LineMarkerProvider
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.editor.markup.GutterIconRenderer
import com.intellij.openapi.progress.ProgressManager
import com.intellij.psi.PsiElement
import javax.swing.Icon

/**
 * This technique adds an LineMarker on the specified PsiElement similar to the Recursive Kotlin Icon or Suspended Icon
 * TODO: Add more Techniques such as the one from Elm
 */
fun addLineMarkerProvider(
  matchOn: (element: PsiElement) -> Boolean,
  slowLineMarker: (element: PsiElement) -> LineMarkerInfo<PsiElement>?,
  lineMarkerInfo: (element: PsiElement) -> LineMarkerInfo<PsiElement>? = { _ -> null }
): LineMarkerProvider =
  object : LineMarkerProvider {
    override fun getLineMarkerInfo(element: PsiElement): LineMarkerInfo<PsiElement>? =
      lineMarkerInfo(element)

    override fun collectSlowLineMarkers(elements: MutableList<PsiElement>, result: MutableCollection<LineMarkerInfo<PsiElement>>) {
      for (element in elements) {
        ProgressManager.checkCanceled()
        if (matchOn(element)) {
          slowLineMarker(element)?.let { result.add(it) }
        }
      }
    }
  }

fun lineMarkerInfo(
  icon: Icon,
  element: PsiElement,
  message: String,
  placed: GutterIconRenderer.Alignment = GutterIconRenderer.Alignment.LEFT
  // nav: GutterIconNavigationHandler<*>? = null TODO
): LineMarkerInfo<PsiElement> =
  object : LineMarkerInfo<PsiElement>(
    element,
    element.textRange,
    icon,
    { message },
    null,
    placed
  ) {
    override fun createGutterRenderer(): GutterIconRenderer =
      object : LineMarkerInfo.LineMarkerGutterIconRenderer<PsiElement>(this) {
        override fun getClickAction(): AnAction? = null // to place breakpoint on mouse click
      }
  }
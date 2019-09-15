package arrow.meta.utils.ide.editor

import com.intellij.codeInsight.template.TemplateContextType
import com.intellij.codeInsight.template.impl.DefaultLiveTemplatesProvider
import com.intellij.psi.PsiFile

fun addLiveTemplateContext(
  id: String,
  presentableName: String,
  isInContext: (file: PsiFile, offset: Int) -> Boolean
): TemplateContextType =
  object : TemplateContextType(id, presentableName) {
    override fun isInContext(file: PsiFile, offset: Int): Boolean =
      isInContext(file, offset)
  }

fun addLiveTemplateProvider(
  defaultLiveTemplateFiles: Array<String>,
  hiddenLiveTemplateFiles: Array<String>?
): DefaultLiveTemplatesProvider =
  object : DefaultLiveTemplatesProvider {
    override fun getDefaultLiveTemplateFiles(): Array<String> =
      defaultLiveTemplateFiles

    override fun getHiddenLiveTemplateFiles(): Array<String>? =
      hiddenLiveTemplateFiles
  }
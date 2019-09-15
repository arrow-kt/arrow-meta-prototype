package arrow.meta.utils.ide.editor

import com.intellij.lang.cacheBuilder.WordsScanner
import com.intellij.lang.findUsages.EmptyFindUsagesProvider
import com.intellij.lang.findUsages.FindUsagesProvider
import com.intellij.lang.findUsages.LanguageFindUsages
import com.intellij.psi.PsiElement

/**
 * Add it with [LanguageFindUsages]
 */
fun addFindUsageProvider(
  wordScanner: WordsScanner? =
    EmptyFindUsagesProvider().wordsScanner,
  nodeText: (element: PsiElement, useFullName: Boolean) -> String =
    { element, useFullName -> EmptyFindUsagesProvider().getNodeText(element, useFullName) },
  descriptorName: (element: PsiElement) -> String =
    { EmptyFindUsagesProvider().getDescriptiveName(it) },
  type: (element: PsiElement) -> String =
    { EmptyFindUsagesProvider().getType(it) },
  helpId: (element: PsiElement) -> String? =
    { EmptyFindUsagesProvider().getHelpId(it) },
  canFindUsagesFor: (psiElement: PsiElement) -> Boolean =
    { EmptyFindUsagesProvider().canFindUsagesFor(it) }
): FindUsagesProvider =
  object : FindUsagesProvider {
    override fun getWordsScanner(): WordsScanner? =
      wordScanner

    override fun getNodeText(element: PsiElement, useFullName: Boolean): String =
      nodeText(element, useFullName)

    override fun getDescriptiveName(element: PsiElement): String =
      descriptorName(element)

    override fun getType(element: PsiElement): String = type(element)

    override fun getHelpId(psiElement: PsiElement): String? =
      helpId(psiElement)

    override fun canFindUsagesFor(psiElement: PsiElement): Boolean =
      canFindUsagesFor(psiElement)
  }
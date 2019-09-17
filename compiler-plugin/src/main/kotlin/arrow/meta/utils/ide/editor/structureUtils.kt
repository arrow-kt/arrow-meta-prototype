package arrow.meta.utils.ide.editor

import com.intellij.ide.structureView.StructureViewBuilder
import com.intellij.ide.structureView.StructureViewModel
import com.intellij.ide.structureView.StructureViewModelBase
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.structureView.TreeBasedStructureViewBuilder
import com.intellij.ide.structureView.impl.common.PsiTreeElementBase
import com.intellij.lang.LanguageStructureViewBuilder
import com.intellij.lang.PsiStructureViewFactory
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.ui.Queryable
import com.intellij.psi.PsiFile
import org.jetbrains.kotlin.idea.structureView.KotlinStructureViewElement
import org.jetbrains.kotlin.idea.structureView.KotlinStructureViewModel
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtEnumEntry
import org.jetbrains.kotlin.psi.KtFile

/**
 * Use [LanguageStructureViewBuilder]
 * Factory(Builder(Model(TreeElementBase)))
 */
fun addStructureViewFactory(
  structureViewBuilder: (psiFile: PsiFile) -> StructureViewBuilder?
): PsiStructureViewFactory =
  PsiStructureViewFactory { psiFile -> structureViewBuilder(psiFile) }

/**
 * Standard impl for [StructureViewBuilder]
 */
fun addTreeBasedStructureViewBuilder(
  structureViewModel: (editor: Editor?) -> StructureViewModel
): StructureViewBuilder =
  object : TreeBasedStructureViewBuilder() {
    override fun createStructureViewModel(editor: Editor?): StructureViewModel =
      structureViewModel(editor)
  }

/**
 * Closest impl to Kotlin's StructureViewModel
 * Default's from [KotlinStructureViewModel]
 * Can also be abstracted to PsiFile's
 */
fun addStructureViewModel(
  ktFile: KtFile,
  editor: Editor?,
  treeElementBase: (KtFile) -> StructureViewTreeElement =
    { KotlinStructureViewElement(it, false) },
  isAlwaysShowsPlus: (element: StructureViewTreeElement?) -> Boolean =
    { it?.value.run { (this is KtClassOrObject && this !is KtEnumEntry) || this is KtFile } },
  isAlwaysLeaf: (element: StructureViewTreeElement?) -> Boolean =
    { false },
  putInfo: (info: MutableMap<String, String>) -> Unit
): StructureViewModel =
  object : StructureViewModelBase(ktFile, editor, treeElementBase(ktFile)), StructureViewModel.ElementInfoProvider, Queryable {
    override fun isAlwaysShowsPlus(element: StructureViewTreeElement?): Boolean =
      isAlwaysShowsPlus(element)

    override fun isAlwaysLeaf(element: StructureViewTreeElement?): Boolean =
      isAlwaysLeaf(element)

    override fun putInfo(info: MutableMap<String, String>) =
      putInfo(info)
  }

fun addStructureViewModel(
  ktFile: KtFile,
  treeElementBase: (KtFile) -> StructureViewTreeElement =
    { KotlinStructureViewElement(it, false) },
  isAlwaysShowsPlus: (element: StructureViewTreeElement?) -> Boolean,
  isAlwaysLeaf: (element: StructureViewTreeElement?) -> Boolean,
  putInfo: (info: MutableMap<String, String>) -> Unit
): StructureViewModel =
  object : StructureViewModelBase(ktFile, treeElementBase(ktFile)), StructureViewModel.ElementInfoProvider, Queryable {
    override fun isAlwaysShowsPlus(element: StructureViewTreeElement?): Boolean =
      isAlwaysShowsPlus(element)

    override fun isAlwaysLeaf(element: StructureViewTreeElement?): Boolean =
      isAlwaysLeaf(element)

    override fun putInfo(info: MutableMap<String, String>) =
      putInfo(info)
  }

fun addTreeElementBase(
  ktFile: KtFile,
  childrenBase: (KtFile) -> MutableCollection<StructureViewTreeElement> =
    { KotlinStructureViewElement(it).childrenBase.toMutableList() },
  presentableText: (KtFile) -> String? =
    { KotlinStructureViewElement(it).presentableText },
  putInfo: (info: MutableMap<String, String>) -> Unit
): StructureViewTreeElement =
  object : PsiTreeElementBase<KtFile>(ktFile), Queryable {
    override fun getChildrenBase(): MutableCollection<StructureViewTreeElement> =
      childrenBase(ktFile)

    override fun getPresentableText(): String? =
      presentableText(ktFile)

    override fun putInfo(info: MutableMap<String, String>) =
      putInfo(info)
  }
package arrow.meta.utils.ide.editor

import com.intellij.codeHighlighting.HighlightDisplayLevel
import com.intellij.codeInspection.InspectionEP
import com.intellij.codeInspection.InspectionSuppressor
import com.intellij.codeInspection.LanguageInspectionSuppressors
import com.intellij.codeInspection.LocalInspectionTool
import com.intellij.codeInspection.SuppressQuickFix
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.idea.inspections.AbstractApplicabilityBasedInspection
import org.jetbrains.kotlin.psi.KtElement

/**
 * [InspectionEP.GLOBAL_INSPECTION]
 * @param inspection can be used with #addApplicableInspection
 */
fun addInspection(
  inspection: LocalInspectionTool,
  shortName: String,
  defaultLevel: HighlightDisplayLevel = HighlightDisplayLevel(HighlightSeverity.INFORMATION),
  defaultShortName: String? = null,
  groupDisplayName: String? = null,
  defaultGroupDisplayName: String? = null,
  displayName: String? = null,
  defaultDisplayName: String? = null,
  groupPath: Array<String>? = null
): InspectionEP =
  object : InspectionEP() {
    override fun getDefaultLevel(): HighlightDisplayLevel =
      defaultLevel

    override fun getGroupPath(): Array<String>? =
      groupPath

    override fun getDisplayName(): String? =
      displayName

    override fun getDefaultDisplayName(): String? =
      defaultDisplayName

    override fun getInstance(): Any =
      inspection

    override fun getDefaultGroupDisplayName(): String? =
      defaultGroupDisplayName

    override fun getShortName(): String =
      shortName

    override fun getDefaultShortName(): String? =
      defaultShortName

    override fun getGroupDisplayName(): String? =
      groupDisplayName
  }

inline fun <reified K : KtElement> addApplicableInspection(
  defaultFixText: String,
  noinline highlightingRange: (element: K) -> TextRange? = { null },
  noinline inspectionText: (element: K) -> String,
  noinline applyTo: (element: K, project: Project, editor: Editor?) -> Unit,
  noinline isApplicable: (element: K) -> Boolean
): AbstractApplicabilityBasedInspection<K> =
  object : AbstractApplicabilityBasedInspection<K>(K::class.java) {
    override val defaultFixText: String
      get() = defaultFixText

    override fun applyTo(element: K, project: Project, editor: Editor?) =
      applyTo(element, project, editor)

    override fun inspectionText(element: K): String =
      inspectionText(element)

    override fun isApplicable(element: K): Boolean =
      isApplicable(element)

    override fun inspectionHighlightRangeInElement(element: K): TextRange? =
      highlightingRange(element)
  }

/**
 * Use [LanguageInspectionSuppressors]
 */
fun addInspectionSuppressor(
  suppressFor: (element: PsiElement, toolId: String) -> Boolean,
  suppressAction: (element: PsiElement?, toolId: String) -> Array<SuppressQuickFix>
): InspectionSuppressor =
  object : InspectionSuppressor {
    override fun getSuppressActions(element: PsiElement?, toolId: String): Array<SuppressQuickFix> =
      suppressAction(element, toolId)

    override fun isSuppressedFor(element: PsiElement, toolId: String): Boolean =
      suppressFor(element, toolId)
  }

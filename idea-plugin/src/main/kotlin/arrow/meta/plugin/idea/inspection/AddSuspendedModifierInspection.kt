package arrow.meta.plugin.idea.inspection

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import org.jetbrains.kotlin.idea.caches.resolve.resolveToDescriptorIfAny
import org.jetbrains.kotlin.idea.inspections.AbstractApplicabilityBasedInspection
import org.jetbrains.kotlin.idea.util.nameIdentifierTextRangeInThis
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.util.ReturnsCheck

/*
 * TODO: MetaJ2kPostProcessing is not necessary
 * TODO: SUPPRESS Unnecessary Suspended Modifier potentially with com.intellij.codeInspection.InspectionSuppressor, or extending SuspendingInspection with changes
 * https://github.com/JetBrains/kotlin/blob/90b0ea73dccf46a3ee44befaed97160fe9733845/idea/src/org/jetbrains/kotlin/idea/j2k/J2KPostProcessingRegistrarImpl.kt#L103
 */
class AddSuspendedModifierInspection : AbstractApplicabilityBasedInspection<KtNamedFunction>(KtNamedFunction::class.java) {
  override fun inspectionHighlightRangeInElement(element: KtNamedFunction) = element.nameIdentifierTextRangeInThis()

  override fun inspectionText(element: KtNamedFunction) = "Function should have suspended modifier"

  override val defaultFixText = "Add suspended modifier"

  override fun isApplicable(element: KtNamedFunction): Boolean {
    if (element.nameIdentifier == null || element.hasModifier(KtTokens.SUSPEND_KEYWORD)) return false
    val functionDescriptor = element.resolveToDescriptorIfAny() ?: return false
    return !functionDescriptor.isSuspend && (ReturnsCheck.ReturnsUnit.check(functionDescriptor)
      || ArrowReturnsCheck.ReturnsNothing.check(functionDescriptor) || ArrowReturnsCheck.ReturnsNullableNothing.check(functionDescriptor))
  }

  override fun applyTo(element: KtNamedFunction, project: Project, editor: Editor?) {
    for (declaration in element.withExpectedActuals()) {
      declaration.addModifier(KtTokens.SUSPEND_KEYWORD)
    }
  }
}
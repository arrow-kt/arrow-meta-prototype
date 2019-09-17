package arrow.meta.utils.ide.editor

import arrow.meta.qq.extensionProviderForIntentions
import com.intellij.codeInsight.intention.IntentionAction
import com.intellij.codeInsight.intention.LowPriorityAction
import com.intellij.codeInsight.intention.PriorityAction
import com.intellij.openapi.editor.Editor
import org.jetbrains.kotlin.diagnostics.Diagnostic
import org.jetbrains.kotlin.idea.intentions.SelfTargetingIntention
import org.jetbrains.kotlin.idea.quickfix.KotlinIntentionActionsFactory
import org.jetbrains.kotlin.idea.quickfix.KotlinSingleIntentionActionFactory
import org.jetbrains.kotlin.psi.KtElement

/**
 * Use [extensionProviderForIntentions] or use in [addQuickFixContributor] for @param intentions
 */
inline fun <reified K : KtElement> addIntentionFor(
  text: String,
  noinline isApplicableTo: (element: K, caretOffset: Int) -> Boolean =
    { element, caretOffset -> false },
  noinline applyTo: (element: K, editor: Editor?) -> Unit =
    { element, editor -> },
  priority: PriorityAction = object : LowPriorityAction {}
): SelfTargetingIntention<K> =
  object : SelfTargetingIntention<K>(K::class.java, text), PriorityAction by priority {
    override fun applyTo(element: K, editor: Editor?) =
      applyTo(element, editor)

    override fun isApplicableTo(element: K, caretOffset: Int): Boolean =
      isApplicableTo(element, caretOffset)
  }

/**
 * Defaults from [KotlinIntentionActionsFactory]
 * @param createAction is solely important to implement
 */
fun addKotlinIntention(
  createAction: (diagnostic: Diagnostic) -> IntentionAction? = { null },
  isApplicableForCodeFragment: Boolean = false,
  doCreateActionsForAllProblems: (sameTypeDiagnostics: Collection<Diagnostic>) -> List<IntentionAction> =
    { emptyList<IntentionAction>() }
): KotlinSingleIntentionActionFactory =
  object : KotlinSingleIntentionActionFactory() {
    override fun createAction(diagnostic: Diagnostic): IntentionAction? =
      createAction(diagnostic)

    override fun doCreateActionsForAllProblems(sameTypeDiagnostics: Collection<Diagnostic>): List<IntentionAction> =
      doCreateActionsForAllProblems(sameTypeDiagnostics)

    override fun isApplicableForCodeFragment(): Boolean =
      isApplicableForCodeFragment
  }
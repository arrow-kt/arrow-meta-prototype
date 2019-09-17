package arrow.meta.utils.ide.editor

import com.intellij.codeInsight.intention.IntentionAction
import com.intellij.diagnostic.Diagnostic
import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.diagnostics.DiagnosticFactory
import org.jetbrains.kotlin.idea.quickfix.KotlinIntentionActionsFactory
import org.jetbrains.kotlin.idea.quickfix.KotlinQuickFixAction
import org.jetbrains.kotlin.idea.quickfix.KotlinSingleIntentionActionFactory
import org.jetbrains.kotlin.idea.quickfix.QuickFixContributor
import org.jetbrains.kotlin.idea.quickfix.QuickFixes

/*fun addQuickFix(
  match: (PsiElement) -> Boolean,
  priority: PriorityAction = object : LowPriorityAction {},
  familyName: String,
  text: String,
  invoke: (project: Project, file: PsiFile, startElement: PsiElement, endElement: PsiElement) -> Unit
): LocalQuickFixOnPsiElement =
  object : LocalQuickFixOnPsiElement(element), PriorityAction by priority {
    override fun getFamilyName(): String = familyName
    override fun getText(): String = text

    override fun invoke(project: Project, file: PsiFile, startElement: PsiElement, endElement: PsiElement): Unit =
      invoke(project, file, startElement, endElement)
  }*/

/**
 * Contributor -> quickFixes.register(DiFac, Inten
 */
data class MetaQuickFixIntention(
  val respondOn: DiagnosticFactory<*>,
  val intentions: List<IntentionAction> = emptyList()
)

data class MetaQuickFixIntentionForKotlin(
  val respondOn: DiagnosticFactory<*>,
  val intentions: List<(Diagnostic) -> KotlinSingleIntentionActionFactory> = emptyList()
)

/**
 * Use [QuickFixContributor.EP_NAME]
 */
fun <K : PsiElement> addQuickFixContributor(
  kotlinIntentions: List<MetaQuickFixIntentionForKotlin> = emptyList(),
  intentions: List<MetaQuickFixIntention> = emptyList()
): QuickFixContributor =
  object : QuickFixContributor {
    override fun registerQuickFixes(quickFixes: QuickFixes) {
      fun DiagnosticFactory<*>.registerIntentions(vararg factory: KotlinIntentionActionsFactory) {

      }


      fun DiagnosticFactory<*>.registerActions(vararg action: IntentionAction) {
        quickFixes.register(this, *action)
      }


    }
  }

fun <K : PsiElement> addKotlinQuickFixBase(
  element: K,

  ): KotlinQuickFixAction<K> =
  object : KotlinQuickFixAction<K>(element)
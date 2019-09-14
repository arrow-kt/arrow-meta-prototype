package arrow.meta.qq

import com.intellij.codeInsight.ContainerProvider
import com.intellij.codeInsight.intention.IntentionAction
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.TimerListener
import com.intellij.openapi.extensions.ExtensionPointName
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import org.jetbrains.kotlin.diagnostics.Diagnostic
import org.jetbrains.kotlin.resolve.diagnostics.DiagnosticSuppressor
import com.intellij.psi.PsiElement as Psi
import org.jetbrains.kotlin.com.intellij.openapi.extensions.ExtensionPointName as EP

interface MetaExtensionProvider {
  fun <E> registerExtension(epName: ExtensionPointName<E>, impl: E): Unit?

  fun <E> registerExtension(epName: EP<E>, impl: E): Unit?

  companion object {

    fun addDiagnosticSuppressor(suppress: (diagnostic: Diagnostic) -> Boolean): DiagnosticSuppressor = object : DiagnosticSuppressor {
      override fun isSuppressed(diagnostic: Diagnostic): Boolean =
        suppress(diagnostic)
    }

    /**
     * ide version the kotlin version is an Interface without functions to implement
     */
    fun addContainerProvider(f: (Psi) -> Psi?): ContainerProvider =
      ContainerProvider { f(it) }
  }
}

interface MetaIntentionExtensionProvider {
  fun registerIntention(intention: IntentionAction, category: String): Unit?
}

interface MetaSyntaxHighlighterExtensionProvider {
  fun registerSyntaxHighlighter(syntaxHighlighterFactory: SyntaxHighlighterFactory): Unit?
}

interface MetaAnActionExtensionProvider {
  fun registerAction(actionId: String, action: AnAction): Unit?
  fun unregisterAction(actionId: String): Unit?
  fun replaceAction(actionId: String, newAction: AnAction): Unit?
  fun addTimerListener(delay: Int, listener: TimerListener): Unit?
  fun addTransparentTimerListener(delay: Int, listener: TimerListener): Unit?
  fun removeTimerListener(listener: TimerListener): Unit?
  fun removeTransparentTimerListener(listener: TimerListener): Unit?
}
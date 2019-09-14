package arrow.meta.plugin.idea

import arrow.meta.qq.MetaAnActionExtensionProvider
import arrow.meta.qq.MetaExtensionProvider
import arrow.meta.qq.MetaIntentionExtensionProvider
import arrow.meta.qq.MetaSyntaxHighlighterExtensionProvider
import com.intellij.codeInsight.intention.IntentionAction
import com.intellij.codeInsight.intention.IntentionManager
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.TimerListener
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import org.jetbrains.kotlin.com.intellij.core.CoreApplicationEnvironment
import org.jetbrains.kotlin.com.intellij.openapi.extensions.ExtensionPointName
import org.jetbrains.kotlin.idea.KotlinLanguage
import com.intellij.core.CoreApplicationEnvironment as CAE
import com.intellij.openapi.extensions.ExtensionPointName as EP

class MetaIdeExtensionProvider : MetaExtensionProvider {
  override fun <E> registerExtension(epName: ExtensionPointName<E>, impl: E): Unit? =
    currentProject()?.getComponent(CoreApplicationEnvironment::class.java)?.addExtension<E>(epName, impl)

  override fun <E> registerExtension(epName: EP<E>, impl: E): Unit? =
    currentProject()?.getComponent(CAE::class.java)?.addExtension<E>(epName, impl)
}

class MetaIdeIntentionExtensionProvider : MetaIntentionExtensionProvider {
  override fun registerIntention(intention: IntentionAction, category: String): Unit? =
    currentProject()?.getComponent(IntentionManager::class.java)?.registerIntentionAndMetaData(intention, category)
}

class MetaIdeSyntaxHighlighterExtensionProvider : MetaSyntaxHighlighterExtensionProvider {
  override fun registerSyntaxHighlighter(syntaxHighlighterFactory: SyntaxHighlighterFactory) =
    currentProject()?.getComponent(SyntaxHighlighterFactory.LANGUAGE_FACTORY::class.java)?.addExplicitExtension(KotlinLanguage.INSTANCE, syntaxHighlighterFactory)
}

class MetaIdeAnActionExtensionProvider : MetaAnActionExtensionProvider {
  override fun registerAction(actionId: String, action: AnAction): Unit? =
    currentProject()?.getComponent(ActionManager::class.java)?.registerAction(actionId, action)

  override fun unregisterAction(actionId: String): Unit? =
    currentProject()?.getComponent(ActionManager::class.java)?.unregisterAction(actionId)

  override fun replaceAction(actionId: String, newAction: AnAction): Unit? =
    currentProject()?.getComponent(ActionManager::class.java)?.replaceAction(actionId, newAction)

  override fun addTimerListener(delay: Int, listener: TimerListener): Unit? =
    currentProject()?.getComponent(ActionManager::class.java)?.addTimerListener(delay, listener)

  override fun addTransparentTimerListener(delay: Int, listener: TimerListener): Unit? =
    currentProject()?.getComponent(ActionManager::class.java)?.addTransparentTimerListener(delay, listener)

  override fun removeTimerListener(listener: TimerListener): Unit? =
    currentProject()?.getComponent(ActionManager::class.java)?.removeTimerListener(listener)

  override fun removeTransparentTimerListener(listener: TimerListener): Unit? =
    currentProject()?.getComponent(ActionManager::class.java)?.removeTransparentTimerListener(listener)
}
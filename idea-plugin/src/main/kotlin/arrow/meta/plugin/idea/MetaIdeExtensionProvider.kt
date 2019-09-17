package arrow.meta.plugin.idea

import arrow.meta.qq.MetaAnActionExtensionProvider
import arrow.meta.qq.MetaExtensionProvider
import arrow.meta.qq.MetaIntentionExtensionProvider
import arrow.meta.qq.MetaSyntaxHighlighterExtensionProvider
import com.intellij.codeInsight.intention.IntentionAction
import com.intellij.codeInsight.intention.IntentionManager
import com.intellij.lang.LanguageExtension
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.TimerListener
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import org.jetbrains.kotlin.com.intellij.core.CoreApplicationEnvironment
import org.jetbrains.kotlin.com.intellij.openapi.extensions.ExtensionPointName
import org.jetbrains.kotlin.idea.KotlinLanguage
import com.intellij.core.CoreApplicationEnvironment as CAE
import com.intellij.openapi.extensions.ExtensionPointName as EP
import org.jetbrains.kotlin.com.intellij.lang.LanguageExtension as LE

class MetaIdeExtensionProvider : MetaExtensionProvider {
  override fun <E> addExtension(EP_Name: ExtensionPointName<E>, impl: E): Unit? =
    currentProject()?.getComponent(CoreApplicationEnvironment::class.java)?.addExtension<E>(EP_Name, impl)

  override fun <E> addExtension(EP_Name: EP<E>, impl: E): Unit? =
    currentProject()?.getComponent(CAE::class.java)?.addExtension<E>(EP_Name, impl)

  override fun <E> addLanguageExtension(LE: LE<E>, impl: E): Unit? =
    currentProject()?.getComponent(CoreApplicationEnvironment::class.java)?.addExplicitExtension(LE, KotlinLanguage.INSTANCE, impl)

  //TODO: Check if shading works
  override fun <E> addLanguageExtension(LE: LanguageExtension<E>, impl: E): Unit? =
    currentProject()?.getComponent(CAE::class.java)?.addExplicitExtension(LE, KotlinLanguage.INSTANCE, impl)
}

class MetaIdeIntentionExtensionProvider : MetaIntentionExtensionProvider {
  override fun addIntention(intention: IntentionAction, category: String): Unit? =
    currentProject()?.getComponent(IntentionManager::class.java)?.registerIntentionAndMetaData(intention, category)

  override fun addIntention(intention: IntentionAction): Unit? =
    currentProject()?.getComponent(IntentionManager::class.java)?.addAction(intention)

  override fun unregisterIntention(intention: IntentionAction): Unit? =
    currentProject()?.getComponent(IntentionManager::class.java)?.unregisterIntention(intention)
}

//TODO: Check if shading works
class MetaIdeSyntaxHighlighterExtensionProvider : MetaSyntaxHighlighterExtensionProvider {
  override fun addSyntaxHighlighter(syntaxHighlighterFactory: SyntaxHighlighterFactory) =
    currentProject()?.getComponent(SyntaxHighlighterFactory.LANGUAGE_FACTORY::class.java)?.addExplicitExtension(KotlinLanguage.INSTANCE, syntaxHighlighterFactory)

  override fun removeSyntaxHighlighter(syntaxHighlighterFactory: SyntaxHighlighterFactory): Unit? =
    currentProject()?.getComponent(SyntaxHighlighterFactory.LANGUAGE_FACTORY::class.java)?.removeExplicitExtension(KotlinLanguage.INSTANCE, syntaxHighlighterFactory)
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
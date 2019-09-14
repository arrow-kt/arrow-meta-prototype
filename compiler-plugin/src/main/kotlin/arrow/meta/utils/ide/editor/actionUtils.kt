package arrow.meta.utils.ide.editor

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.TimerListener
import com.intellij.openapi.application.ModalityState
import javax.swing.Icon


fun addAnAction(
  actionPerformed: (e: AnActionEvent) -> Unit,
  displayTextInToolbar: Boolean = false,
  setInjectedContext: (worksInInjected: Boolean) -> Boolean = { it },
  update: (e: AnActionEvent) -> Unit = { _ -> },
  useSmallerFontForTextInToolbar: Boolean = false,
  startInTransaction: Boolean = false,
  getTemplateText: String? = null,
  beforeActionPerformedUpdate: (e: AnActionEvent) -> Unit
): AnAction =
  object : AnAction() {
    override fun actionPerformed(e: AnActionEvent) = actionPerformed(e)
    override fun displayTextInToolbar(): Boolean = displayTextInToolbar

    override fun setInjectedContext(worksInInjected: Boolean) =
      super.setInjectedContext(setInjectedContext(worksInInjected))

    override fun update(e: AnActionEvent) = update(e)

    override fun useSmallerFontForTextInToolbar(): Boolean =
      useSmallerFontForTextInToolbar

    override fun startInTransaction(): Boolean =
      startInTransaction

    override fun getTemplateText(): String? {
      return getTemplateText ?: super.getTemplateText()
    }

    override fun beforeActionPerformedUpdate(e: AnActionEvent) =
      beforeActionPerformedUpdate(e)
  }

fun addAnAction(
  icon: Icon,
  actionPerformed: (e: AnActionEvent) -> Unit,
  displayTextInToolbar: Boolean = false,
  setInjectedContext: (worksInInjected: Boolean) -> Boolean = { it },
  update: (e: AnActionEvent) -> Unit = { _ -> },
  useSmallerFontForTextInToolbar: Boolean = false,
  startInTransaction: Boolean = false,
  getTemplateText: String? = null,
  beforeActionPerformedUpdate: (e: AnActionEvent) -> Unit
): AnAction =
  object : AnAction(icon) {
    override fun actionPerformed(e: AnActionEvent) = actionPerformed(e)
    override fun displayTextInToolbar(): Boolean = displayTextInToolbar

    override fun setInjectedContext(worksInInjected: Boolean) =
      super.setInjectedContext(setInjectedContext(worksInInjected))

    override fun update(e: AnActionEvent) = update(e)

    override fun useSmallerFontForTextInToolbar(): Boolean =
      useSmallerFontForTextInToolbar

    override fun startInTransaction(): Boolean =
      startInTransaction

    override fun getTemplateText(): String? {
      return getTemplateText ?: super.getTemplateText()
    }

    override fun beforeActionPerformedUpdate(e: AnActionEvent) =
      beforeActionPerformedUpdate(e)
  }

fun addAnAction(
  text: String,
  actionPerformed: (e: AnActionEvent) -> Unit,
  displayTextInToolbar: Boolean = false,
  setInjectedContext: (worksInInjected: Boolean) -> Boolean = { it },
  update: (e: AnActionEvent) -> Unit = { _ -> },
  useSmallerFontForTextInToolbar: Boolean = false,
  startInTransaction: Boolean = false,
  getTemplateText: String? = null,
  beforeActionPerformedUpdate: (e: AnActionEvent) -> Unit
): AnAction =
  object : AnAction(text) {
    override fun actionPerformed(e: AnActionEvent) = actionPerformed(e)
    override fun displayTextInToolbar(): Boolean = displayTextInToolbar

    override fun setInjectedContext(worksInInjected: Boolean) =
      super.setInjectedContext(setInjectedContext(worksInInjected))

    override fun update(e: AnActionEvent) = update(e)

    override fun useSmallerFontForTextInToolbar(): Boolean =
      useSmallerFontForTextInToolbar

    override fun startInTransaction(): Boolean =
      startInTransaction

    override fun getTemplateText(): String? {
      return getTemplateText ?: super.getTemplateText()
    }

    override fun beforeActionPerformedUpdate(e: AnActionEvent) =
      beforeActionPerformedUpdate(e)
  }

fun addTimerListener(
  modalityState: ModalityState,
  run: () -> Unit
): TimerListener =
  object : TimerListener {
    override fun run() = run()

    override fun getModalityState(): ModalityState = modalityState
  }
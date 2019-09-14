package arrow.meta.utils.ide.editor

import com.intellij.codeInsight.intention.HighPriorityAction
import com.intellij.openapi.editor.Editor
import org.jetbrains.kotlin.idea.intentions.SelfTargetingIntention
import org.jetbrains.kotlin.psi.KtElement

inline fun <reified K : KtElement> addIntention(
  text: String,
  noinline isApplicableTo: (element: K, caretOffset: Int) -> Boolean,
  noinline applyTo: (element: K, editor: Editor?) -> Unit
): SelfTargetingIntention<K> =
  object : SelfTargetingIntention<K>(K::class.java, text), HighPriorityAction {
    override fun applyTo(element: K, editor: Editor?) =
      applyTo(element, editor)

    override fun isApplicableTo(element: K, caretOffset: Int): Boolean =
      isApplicableTo(element, caretOffset)
  }

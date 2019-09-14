package arrow.meta.utils.ide.editor

import com.intellij.psi.PsiElement
import com.intellij.usages.Usage
import com.intellij.usages.UsageTarget
import com.intellij.usages.impl.rules.UsageType
import com.intellij.usages.impl.rules.UsageTypeProvider
import com.intellij.usages.rules.ImportFilteringRule

/**
 * A filtering rule that allows you exclude imports from actions such as "find usages"
 */
fun addImportFilteringRule(
  isVisible: (usage: Usage, targets: Array<out UsageTarget>) -> Boolean
): ImportFilteringRule = TODO("fix 'isVisible' overrides nothing. Though, direct impl. builds fine")
/*object : ImportFilteringRule() {
  override fun isVisible(usage: Usage, targets: Array<out UsageTarget>): Boolean {
    return isVisible(usage, targets)
  }

  @Deprecated("Deprecated Ide API from In", ReplaceWith("isVisible(usage, targets)", "com.intellij.usages.UsageTarget"))
  override fun isVisible(usage: Usage): Boolean {
    return super.isVisible(usage)
  }
}*/

/**
 * extensionProvider(
 *  epName = UsageTypeProvider.EP_NAME,
 *  impl = addUsageTypeProvider {
 *    if (it.safeAs<KtNamedFunction>()?.name == "foo")
 *     UsageType("Referenced as foo") else null
 *    }
 *  )
 */
fun addUsageTypeProvider(f: (psiElement: PsiElement) -> UsageType?) = // TODO(): f:(Psi) -> Option<UsageType>
  UsageTypeProvider { if (it != null) f(it) else null }

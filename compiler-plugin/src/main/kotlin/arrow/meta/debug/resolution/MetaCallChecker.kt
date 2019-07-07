package arrow.meta.debug.resolution

import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.resolve.calls.checkers.CallChecker
import org.jetbrains.kotlin.resolve.calls.checkers.CallCheckerContext
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCall

class MetaCallChecker(val delegate: CallChecker) : CallChecker by delegate{
  override fun check(resolvedCall: ResolvedCall<*>, reportOn: PsiElement, context: CallCheckerContext) {
    println("MetaCallChecker.check: $resolvedCall, $reportOn, $context")
    return delegate.check(resolvedCall, reportOn, context)
  }
}
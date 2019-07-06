package arrow.meta.utils

import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.resolve.calls.checkers.CallChecker
import org.jetbrains.kotlin.resolve.calls.checkers.CallCheckerContext
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCall

class MetaCallChecker : CallChecker {
  override fun check(resolvedCall: ResolvedCall<*>, reportOn: PsiElement, context: CallCheckerContext) {
    println("MetaCallChecker.check: $resolvedCall")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}
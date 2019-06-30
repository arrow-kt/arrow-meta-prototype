package arrow.meta.utils

import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.descriptors.ClassifierDescriptor
import org.jetbrains.kotlin.resolve.checkers.ClassifierUsageChecker
import org.jetbrains.kotlin.resolve.checkers.ClassifierUsageCheckerContext

class MetaClassifierUsageChecker : ClassifierUsageChecker {
  override fun check(targetDescriptor: ClassifierDescriptor, element: PsiElement, context: ClassifierUsageCheckerContext) {
    println("MetaClassifierUsageChecker.check: $targetDescriptor")
    TODO(reason = "not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}
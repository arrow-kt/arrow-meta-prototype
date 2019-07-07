package arrow.meta.debug.resolution

import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.descriptors.ClassifierDescriptor
import org.jetbrains.kotlin.resolve.checkers.ClassifierUsageChecker
import org.jetbrains.kotlin.resolve.checkers.ClassifierUsageCheckerContext

class MetaClassifierUsageChecker(val delegate: ClassifierUsageChecker) : ClassifierUsageChecker by delegate {
  override fun check(targetDescriptor: ClassifierDescriptor, element: PsiElement, context: ClassifierUsageCheckerContext) {
    println("MetaClassifierUsageChecker.check: $targetDescriptor, $element, $context")
    return delegate.check(targetDescriptor, element, context)
  }
}
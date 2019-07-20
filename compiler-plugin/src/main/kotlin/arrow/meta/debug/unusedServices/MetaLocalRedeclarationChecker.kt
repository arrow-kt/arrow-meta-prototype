package arrow.meta.debug.unusedServices

import org.jetbrains.kotlin.descriptors.DeclarationDescriptor
import org.jetbrains.kotlin.resolve.scopes.LexicalScope
import org.jetbrains.kotlin.resolve.scopes.LocalRedeclarationChecker

/**
 * @UnusedService Currently we need to investigate how our costume functions can be executed.
 * Meaning we need to hook into the compiler earlier than Analysis. Either with Reflection or else
 */
class MetaLocalRedeclarationChecker(val delegate: LocalRedeclarationChecker) : LocalRedeclarationChecker by delegate {
  override fun checkBeforeAddingToScope(scope: LexicalScope, newDescriptor: DeclarationDescriptor) {
    println("MetaLocalRedeclarationChecker.checkBeforeAddingToScope: $scope, $newDescriptor")
    delegate.checkBeforeAddingToScope(scope, newDescriptor)
  }
}
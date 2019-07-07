package arrow.meta.debug.resolution

import org.jetbrains.kotlin.descriptors.DeclarationDescriptor
import org.jetbrains.kotlin.psi.KtDeclaration
import org.jetbrains.kotlin.resolve.checkers.DeclarationChecker
import org.jetbrains.kotlin.resolve.checkers.DeclarationCheckerContext

class MetaDeclarationChecker(val delegate: DeclarationChecker) : DeclarationChecker by delegate {
  override fun check(declaration: KtDeclaration, descriptor: DeclarationDescriptor, context: DeclarationCheckerContext) {
    println("MetaDeclarationChecker.check: $declaration, $descriptor, $context")
    return delegate.check(declaration, descriptor, context)
  }
}

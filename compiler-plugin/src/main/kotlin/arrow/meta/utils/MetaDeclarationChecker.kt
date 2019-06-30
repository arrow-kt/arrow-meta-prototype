package arrow.meta.utils

import org.jetbrains.kotlin.descriptors.DeclarationDescriptor
import org.jetbrains.kotlin.psi.KtDeclaration
import org.jetbrains.kotlin.resolve.checkers.DeclarationChecker
import org.jetbrains.kotlin.resolve.checkers.DeclarationCheckerContext

class MetaDeclarationChecker: DeclarationChecker {
  override fun check(declaration: KtDeclaration, descriptor: DeclarationDescriptor, context: DeclarationCheckerContext) {
    println("MetaDeclarationChecker.check: $descriptor")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}
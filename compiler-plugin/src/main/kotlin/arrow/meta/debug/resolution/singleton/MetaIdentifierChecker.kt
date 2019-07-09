package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.diagnostics.DiagnosticSink
import org.jetbrains.kotlin.psi.KtDeclaration
import org.jetbrains.kotlin.psi.KtSimpleNameExpression
import org.jetbrains.kotlin.resolve.IdentifierChecker

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaIdentifierChecker(val delegate: IdentifierChecker) : IdentifierChecker by delegate {
  override fun checkDeclaration(declaration: KtDeclaration, diagnosticHolder: DiagnosticSink) {
    println("MetaIdentifierChecker.checkDeclaration: $declaration, $diagnosticHolder")
    delegate.checkDeclaration(declaration, diagnosticHolder)
  }

  override fun checkIdentifier(simpleNameExpression: KtSimpleNameExpression, diagnosticHolder: DiagnosticSink) {
    println("MetaIdentifierChecker.checkIdentifier: $simpleNameExpression, $diagnosticHolder")
    delegate.checkIdentifier(simpleNameExpression, diagnosticHolder)
  }
}
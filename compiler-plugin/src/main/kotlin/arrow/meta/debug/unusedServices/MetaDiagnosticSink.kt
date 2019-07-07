package arrow.meta.debug.unusedServices

import org.jetbrains.kotlin.diagnostics.Diagnostic
import org.jetbrains.kotlin.diagnostics.DiagnosticSink

/**
 * @UnknownPhase Not subscribable in Analysis Phase: Phase still unknown
 */
class MetaDiagnosticSink(val delegate: DiagnosticSink) : DiagnosticSink by delegate {
  override fun report(p0: Diagnostic) {
    println("MetaDiagnosticSink.report: $p0")
    delegate.report(p0)
  }

  override fun wantsDiagnostics(): Boolean {
    println("MetaDiagnosticSink.wantsDiagnostics:")
    return delegate.wantsDiagnostics()
  }
}
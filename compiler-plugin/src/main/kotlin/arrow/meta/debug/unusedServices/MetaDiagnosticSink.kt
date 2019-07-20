package arrow.meta.debug.unusedServices

import org.jetbrains.kotlin.diagnostics.Diagnostic
import org.jetbrains.kotlin.diagnostics.DiagnosticSink

/**
 * @UnusedService Currently we need to investigate how our costume functions can be executed.
 * Meaning we need to hook into the compiler earlier than Analysis. Either with Reflection or else
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
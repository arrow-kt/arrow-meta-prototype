package arrow.meta.debug.unusedServices

import org.jetbrains.kotlin.resolve.calls.model.DiagnosticReporter
import org.jetbrains.kotlin.resolve.calls.model.KotlinCallArgument
import org.jetbrains.kotlin.resolve.calls.model.KotlinCallDiagnostic
import org.jetbrains.kotlin.resolve.calls.model.SimpleKotlinCallArgument
import org.jetbrains.kotlin.resolve.calls.model.TypeArgument

/**
 * @UnusedService Currently we need to investigate how our costume functions can be executed.
 * Meaning we need to hook into the compiler earlier than Analysis. Either with Reflection or else
 */
class MetaDiagnosticReporter(val delegate: DiagnosticReporter) : DiagnosticReporter by delegate {
  override fun constraintError(diagnostic: KotlinCallDiagnostic) {
    println("MetaDiagnosticReporter.constraintError: $diagnostic")
    return delegate.constraintError(diagnostic)
  }

  override fun onCall(diagnostic: KotlinCallDiagnostic) {
    println("MetaDiagnosticReporter.onCall: $diagnostic")
    return delegate.onCall(diagnostic)
  }

  override fun onCallArgument(callArgument: KotlinCallArgument, diagnostic: KotlinCallDiagnostic) {
    println("MetaDiagnosticReporter.onCallArgument: $callArgument, $diagnostic")
    return delegate.onCallArgument(callArgument, diagnostic)
  }

  override fun onCallArgumentName(callArgument: KotlinCallArgument, diagnostic: KotlinCallDiagnostic) {
    println("MetaDiagnosticReporter.onCallArgumentName: $callArgument, $diagnostic")
    return delegate.onCallArgumentName(callArgument, diagnostic)
  }

  override fun onCallArgumentSpread(callArgument: KotlinCallArgument, diagnostic: KotlinCallDiagnostic) {
    println("MetaDiagnosticReporter.onCallArgumentSpread: $callArgument, $diagnostic")
    return delegate.onCallArgumentSpread(callArgument, diagnostic)
  }

  override fun onCallName(diagnostic: KotlinCallDiagnostic) {
    println("MetaDiagnosticReporter.onCallName: $diagnostic")
    return delegate.onCallName(diagnostic)
  }

  override fun onCallReceiver(callReceiver: SimpleKotlinCallArgument, diagnostic: KotlinCallDiagnostic) {
    println("MetaDiagnosticReporter.onCallReceiver: $callReceiver, $diagnostic")
    return delegate.onCallReceiver(callReceiver, diagnostic)
  }

  override fun onExplicitReceiver(diagnostic: KotlinCallDiagnostic) {
    println("MetaDiagnosticReporter.onExplicitReceiver: $diagnostic")
    return delegate.onExplicitReceiver(diagnostic)
  }

  override fun onTypeArgument(typeArgument: TypeArgument, diagnostic: KotlinCallDiagnostic) {
    println("MetaDiagnosticReporter.onTypeArgument: $typeArgument, $diagnostic")
    return delegate.onTypeArgument(typeArgument, diagnostic)
  }

  override fun onTypeArguments(diagnostic: KotlinCallDiagnostic) {
    println("MetaDiagnosticReporter.onTypeArguments: $diagnostic")
    return delegate.onTypeArguments(diagnostic)
  }
}
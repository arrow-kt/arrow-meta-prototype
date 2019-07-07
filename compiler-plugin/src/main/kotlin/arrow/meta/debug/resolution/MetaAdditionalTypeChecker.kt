package arrow.meta.debug.resolution

import org.jetbrains.kotlin.descriptors.ReceiverParameterDescriptor
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.resolve.calls.checkers.AdditionalTypeChecker
import org.jetbrains.kotlin.resolve.calls.context.CallResolutionContext
import org.jetbrains.kotlin.resolve.calls.context.ResolutionContext
import org.jetbrains.kotlin.resolve.scopes.receivers.ReceiverValue
import org.jetbrains.kotlin.types.KotlinType

class MetaAdditionalTypeChecker(val delegate: AdditionalTypeChecker) : AdditionalTypeChecker by delegate {
  override fun checkType(expression: KtExpression, expressionType: KotlinType, expressionTypeWithSmartCast: KotlinType, c: ResolutionContext<*>) {
    println("MetaAdditionalTypeChecker.checkType: $expression, $expressionType, $expressionTypeWithSmartCast, $c")
    return delegate.checkType(expression, expressionType, expressionTypeWithSmartCast, c)
  }

  override fun checkReceiver(receiverParameter: ReceiverParameterDescriptor, receiverArgument: ReceiverValue, safeAccess: Boolean, c: CallResolutionContext<*>) {
    println("MetaAdditionalTypeChecker.checkReceiver: $receiverParameter, $receiverArgument, $safeAccess, $c")
    return delegate.checkReceiver(receiverParameter, receiverArgument, safeAccess, c)
  }
}
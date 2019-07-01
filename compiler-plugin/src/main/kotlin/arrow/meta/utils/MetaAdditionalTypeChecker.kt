package arrow.meta.utils

import org.jetbrains.kotlin.descriptors.ReceiverParameterDescriptor
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.resolve.calls.checkers.AdditionalTypeChecker
import org.jetbrains.kotlin.resolve.calls.context.CallResolutionContext
import org.jetbrains.kotlin.resolve.calls.context.ResolutionContext
import org.jetbrains.kotlin.resolve.scopes.receivers.ReceiverValue
import org.jetbrains.kotlin.types.KotlinType

class MetaAdditionalTypeChecker: AdditionalTypeChecker{
  override fun checkType(expression: KtExpression, expressionType: KotlinType, expressionTypeWithSmartCast: KotlinType, c: ResolutionContext<*>) {
    println("MetaAdditionalTypeChecker.checkType: $expression")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun checkReceiver(receiverParameter: ReceiverParameterDescriptor, receiverArgument: ReceiverValue, safeAccess: Boolean, c: CallResolutionContext<*>) {
    println("MetaAdditionalTypeChecker.checkReceiver: $receiverParameter")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    // super.checkReceiver(receiverParameter, receiverArgument, safeAccess, c)
  }
}
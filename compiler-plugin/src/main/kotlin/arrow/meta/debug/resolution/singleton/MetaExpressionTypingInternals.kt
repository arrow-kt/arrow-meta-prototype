package arrow.meta.debug.resolution.singleton

import arrow.meta.unexposed.kotlin.types.expressions.MetaExpressionTypingInternalsInterface
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtSimpleNameExpression
import org.jetbrains.kotlin.psi.ValueArgument
import org.jetbrains.kotlin.types.expressions.ExpressionTypingComponents
import org.jetbrains.kotlin.types.expressions.ExpressionTypingContext
import org.jetbrains.kotlin.types.expressions.KotlinTypeInfo

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaExpressionTypingInternals(val delegate: MetaExpressionTypingInternalsInterface) : MetaExpressionTypingInternalsInterface by delegate {
  override val component: ExpressionTypingComponents
    get() = delegate.component

  override fun checkInExpression(callElement: KtElement, operationSign: KtSimpleNameExpression, leftArgument: ValueArgument, right: KtExpression?, context: ExpressionTypingContext): KotlinTypeInfo {
    println("MetaExpressionTypingInternals.checkInExpression: $callElement, $operationSign, $leftArgument, $right, $context")
    return delegate.checkInExpression(callElement, operationSign, leftArgument, right, context)
  }

  override suspend fun checkStatementType(expression: KtExpression, context: ExpressionTypingContext) {
    println("MetaExpressionTypingInternals.checkStatementType: $expression, $context")
    delegate.checkStatementType(expression, context)
  }

  override fun safeGetTypeInfo(p0: KtExpression, p1: ExpressionTypingContext?): KotlinTypeInfo {
    println("MetaExpressionTypingInternals.safeGetTypeInfo: $p0, $p1")
    return delegate.safeGetTypeInfo(p0, p1)
  }

  override fun getTypeInfo(p0: KtExpression, p1: ExpressionTypingContext?): KotlinTypeInfo {
    println("MetaExpressionTypingInternals.getTypeInfo: $p0, $p1")
    return delegate.getTypeInfo(p0, p1)
  }

  override fun getTypeInfo(p0: KtExpression, p1: ExpressionTypingContext?, p2: Boolean): KotlinTypeInfo {
    println("MetaExpressionTypingInternals.getTypeInfo: $p0, $p1, $p2")
    return delegate.getTypeInfo(p0, p1, p2)
  }
}
package arrow.meta.unexposed.kotlin.types.expressions

import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtSimpleNameExpression
import org.jetbrains.kotlin.psi.ValueArgument
import org.jetbrains.kotlin.types.expressions.ExpressionTypingComponents
import org.jetbrains.kotlin.types.expressions.ExpressionTypingContext
import org.jetbrains.kotlin.types.expressions.ExpressionTypingFacade
import org.jetbrains.kotlin.types.expressions.KotlinTypeInfo


/**
 * Not sure why [ExpressionTypingInternals] is not available thorough the regular compiler-embeddable artifact
 * The normal Interface resides in [kotlin.types.expressions]
 */
interface MetaExpressionTypingInternalsInterface : ExpressionTypingFacade {
  val component: ExpressionTypingComponents
  fun checkInExpression(callElement: KtElement,
                        operationSign: KtSimpleNameExpression,
                        leftArgument: ValueArgument,
                        right: KtExpression?,
                        context: ExpressionTypingContext): KotlinTypeInfo

  suspend fun checkStatementType(expression: KtExpression, context: ExpressionTypingContext)
}
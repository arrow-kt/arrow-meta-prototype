package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.types.expressions.ExpressionTypingContext
import org.jetbrains.kotlin.types.expressions.ExpressionTypingFacade
import org.jetbrains.kotlin.types.expressions.KotlinTypeInfo

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaExpressionTypingFacade(val delegate: ExpressionTypingFacade) : ExpressionTypingFacade by delegate {
  override fun safeGetTypeInfo(p0: KtExpression, p1: ExpressionTypingContext?): KotlinTypeInfo {
    println("MetaExpressionTypingFacade.safeGetTypeInfo: $p0, $p1")
    return delegate.safeGetTypeInfo(p0, p1)
  }

  override fun getTypeInfo(p0: KtExpression, p1: ExpressionTypingContext?): KotlinTypeInfo {
    println("MetaExpressionTypingFacade.getTypeInfo: $p0, $p1")
    return delegate.getTypeInfo(p0, p1)
  }

  override fun getTypeInfo(p0: KtExpression, p1: ExpressionTypingContext?, p2: Boolean): KotlinTypeInfo {
    println("MetaExpressionTypingFacade.getTypeInfo: $p0, $p1, $p2")
    return delegate.getTypeInfo(p0, p1, p2)
  }

}

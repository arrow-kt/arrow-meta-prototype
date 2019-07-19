package arrow.meta.debug.resolution

import org.jetbrains.kotlin.builtins.KotlinBuiltIns
import org.jetbrains.kotlin.com.intellij.openapi.util.Ref
import org.jetbrains.kotlin.config.LanguageVersionSettings
import org.jetbrains.kotlin.contracts.EffectSystem
import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.resolve.calls.checkers.AdditionalTypeChecker
import org.jetbrains.kotlin.resolve.calls.context.ResolutionContext
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowInfo
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowValueFactory
import org.jetbrains.kotlin.resolve.calls.smartcasts.SmartCastResult
import org.jetbrains.kotlin.resolve.constants.CompileTimeConstant
import org.jetbrains.kotlin.resolve.constants.evaluate.ConstantExpressionEvaluator
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.expressions.DataFlowAnalyzer
import org.jetbrains.kotlin.types.expressions.ExpressionTypingContext
import org.jetbrains.kotlin.types.expressions.ExpressionTypingFacade
import org.jetbrains.kotlin.types.expressions.KotlinTypeInfo

class MetaDataFlowAnalyzer(
  val additionalTypeCheckers: Iterable<AdditionalTypeChecker>,
  val constantExpressionEvaluator: ConstantExpressionEvaluator,
  val module: ModuleDescriptor,
  val builtIns: KotlinBuiltIns,
  val facade: ExpressionTypingFacade,
  val languageVersionSettings: LanguageVersionSettings,
  val effectSystem: EffectSystem,
  val factory: DataFlowValueFactory
) : DataFlowAnalyzer(
  additionalTypeCheckers,
  constantExpressionEvaluator,
  module,
  builtIns,
  facade,
  languageVersionSettings,
  effectSystem,
  factory
) {
  /* TODO: access ExpressionTypingInternals
  override fun illegalStatementType(expression: KtExpression, context: ExpressionTypingContext, facade: ExpressionTypingInternals): KotlinTypeInfo {
    println("MetaDataFlowAnalyzer.
    return super.illegalStatementType(expression, context, facade)
  }*/

  override fun typeHasEqualsFromAny(type: KotlinType, lookupElement: KtElement): Boolean {
    println("MetaDataFlowAnalyzer.typeHasEqualsFromAny: $type, $lookupElement")
    return super.typeHasEqualsFromAny(type, lookupElement)
  }

  override fun checkStatementType(expression: KtExpression, context: ResolutionContext<out ResolutionContext<*>>): KotlinType? {
    println("MetaDataFlowAnalyzer.checkStatementType: $expression, $context")
    return super.checkStatementType(expression, context)
  }

  override fun createCompileTimeConstantTypeInfo(value: CompileTimeConstant<*>, expression: KtExpression, context: ExpressionTypingContext): KotlinTypeInfo {
    println("MetaDataFlowAnalyzer.createCompileTimeConstantTypeInfo: $value, $expression, $context")
    return super.createCompileTimeConstantTypeInfo(value, expression, context)
  }

  override fun extractDataFlowInfoFromCondition(condition: KtExpression?, conditionValue: Boolean, context: ExpressionTypingContext?): DataFlowInfo {
    println("MetaDataFlowAnalyzer.extractDataFlowInfoFromCondition: $condition, $conditionValue, $context")
    return super.extractDataFlowInfoFromCondition(condition, conditionValue, context)
  }

  override fun checkType(expressionType: KotlinType?, expression: KtExpression, context: ResolutionContext<out ResolutionContext<*>>): KotlinType? {
    println("MetaDataFlowAnalyzer.checkType: $expressionType, $expression, $context")
    return super.checkType(expressionType, expression, context)
  }

  override fun checkType(expressionType: KotlinType?, expression: KtExpression, context: ResolutionContext<out ResolutionContext<*>>, reportErrorForTypeMismatch: Boolean): KotlinType? {
    println("MetaDataFlowAnalyzer.checkType: $expressionType, $expression, $context, $reportErrorForTypeMismatch")
    return super.checkType(expressionType, expression, context, reportErrorForTypeMismatch)
  }

  override fun checkType(typeInfo: KotlinTypeInfo, expression: KtExpression, context: ResolutionContext<out ResolutionContext<*>>): KotlinTypeInfo {
    println("MetaDataFlowAnalyzer.checkType: $typeInfo, $expression, $context")
    return super.checkType(typeInfo, expression, context)
  }

  override fun checkType(expressionType: KotlinType?, expressionToCheck: KtExpression, c: ResolutionContext<out ResolutionContext<*>>, hasError: Ref<Boolean>?, reportErrorForTypeMismatch: Boolean): KotlinType? {
    println("MetaDataFlowAnalyzer.checkType: $expressionType, $expressionToCheck, $c, $hasError, $reportErrorForTypeMismatch")
    return super.checkType(expressionType, expressionToCheck, c, hasError, reportErrorForTypeMismatch)
  }

  override fun createCheckedTypeInfo(type: KotlinType?, context: ResolutionContext<*>, expression: KtExpression): KotlinTypeInfo {
    println("MetaDataFlowAnalyzer.createCheckedTypeInfo: $type, $context, $expression")
    return super.createCheckedTypeInfo(type, context, expression)
  }

  override fun checkPossibleCast(expressionType: KotlinType, expression: KtExpression, c: ResolutionContext<out ResolutionContext<*>>): SmartCastResult? {
    println("MetaDataFlowAnalyzer.checkPossibleCast: $expressionType, $expression, $c")
    return super.checkPossibleCast(expressionType, expression, c)
  }
}
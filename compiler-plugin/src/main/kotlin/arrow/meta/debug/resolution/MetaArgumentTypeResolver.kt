package arrow.meta.debug.resolution

import org.jetbrains.kotlin.builtins.KotlinBuiltIns
import org.jetbrains.kotlin.builtins.ReflectionTypes
import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.psi.KtCallableReferenceExpression
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.resolve.StatementFilter
import org.jetbrains.kotlin.resolve.TypeResolver
import org.jetbrains.kotlin.resolve.calls.ArgumentTypeResolver
import org.jetbrains.kotlin.resolve.calls.callResolverUtil.ResolveArgumentsMode
import org.jetbrains.kotlin.resolve.calls.context.CallResolutionContext
import org.jetbrains.kotlin.resolve.calls.context.ResolutionContext
import org.jetbrains.kotlin.resolve.constants.evaluate.ConstantExpressionEvaluator
import org.jetbrains.kotlin.resolve.scopes.LexicalScope
import org.jetbrains.kotlin.types.FunctionPlaceholders
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.expressions.DoubleColonExpressionResolver
import org.jetbrains.kotlin.types.expressions.KotlinTypeInfo

class MetaArgumentTypeResolver(
  val typeResolver: TypeResolver,
  val doubleColonExpressionResolver: DoubleColonExpressionResolver,
  val builtIns: KotlinBuiltIns,
  val reflectionTypes: ReflectionTypes,
  val constantExpressionEvaluator: ConstantExpressionEvaluator,
  val functionPlaceholders: FunctionPlaceholders,
  val moduleDescriptor: ModuleDescriptor
) :
  ArgumentTypeResolver(
    typeResolver,
    doubleColonExpressionResolver,
    builtIns,
    reflectionTypes,
    constantExpressionEvaluator,
    functionPlaceholders,
    moduleDescriptor
  ) {
  override fun getArgumentTypeInfo(expression: KtExpression?, context: CallResolutionContext<*>, resolveArgumentsMode: ResolveArgumentsMode, suspendFunctionTypeExpected: Boolean): KotlinTypeInfo {
    println("MetaArgumentTypeResolver.getArgumentTypeInfo: $expression, $context, $resolveArgumentsMode, $suspendFunctionTypeExpected")
    return super.getArgumentTypeInfo(expression, context, resolveArgumentsMode, suspendFunctionTypeExpected)
  }

  override fun getFunctionLiteralTypeInfo(expression: KtExpression, functionLiteral: KtFunction, context: CallResolutionContext<*>, resolveArgumentsMode: ResolveArgumentsMode, suspendFunctionTypeExpected: Boolean): KotlinTypeInfo {
    println("MetaArgumentTypeResolver.getFunctionLiteralTypeInfo: $expression, $functionLiteral, $context, $resolveArgumentsMode, $suspendFunctionTypeExpected")
    return super.getFunctionLiteralTypeInfo(expression, functionLiteral, context, resolveArgumentsMode, suspendFunctionTypeExpected)
  }

  override fun getShapeTypeOfCallableReference(callableReferenceExpression: KtCallableReferenceExpression, context: CallResolutionContext<*>, expectedTypeIsUnknown: Boolean): KotlinType? {
    println("MetaArgumentTypeResolver.getShapeTypeOfCallableReference: $callableReferenceExpression, $context, $expectedTypeIsUnknown")
    return super.getShapeTypeOfCallableReference(callableReferenceExpression, context, expectedTypeIsUnknown)
  }

  override fun getShapeTypeOfFunctionLiteral(function: KtFunction, scope: LexicalScope, trace: BindingTrace, expectedTypeIsUnknown: Boolean, suspendFunctionTypeExpected: Boolean): KotlinType? {
    println("MetaArgumentTypeResolver.getShapeTypeOfFunctionLiteral: $function, $scope, $trace, $expectedTypeIsUnknown, $suspendFunctionTypeExpected")
    return super.getShapeTypeOfFunctionLiteral(function, scope, trace, expectedTypeIsUnknown, suspendFunctionTypeExpected)
  }

  override fun updateResultArgumentTypeIfNotDenotable(context: ResolutionContext<out ResolutionContext<*>>, expression: KtExpression): KotlinType? {
    println("MetaArgumentTypeResolver.updateResultArgumentTypeIfNotDenotable: $context, $expression")
    return super.updateResultArgumentTypeIfNotDenotable(context, expression)
  }

  override fun updateResultArgumentTypeIfNotDenotable(trace: BindingTrace, statementFilter: StatementFilter, expectedType: KotlinType, expression: KtExpression): KotlinType? {
    println("MetaArgumentTypeResolver.updateResultArgumentTypeIfNotDenotable: $trace, $statementFilter, $expectedType, $expression")
    return super.updateResultArgumentTypeIfNotDenotable(trace, statementFilter, expectedType, expression)
  }

  override fun updateResultArgumentTypeIfNotDenotable(trace: BindingTrace, statementFilter: StatementFilter, expectedType: KotlinType, targetType: KotlinType, expression: KtExpression): KotlinType? {
    println("MetaArgumentTypeResolver.updateResultArgumentTypeIfNotDenotable: $trace, $statementFilter, $expectedType, $targetType, $expression")
    return super.updateResultArgumentTypeIfNotDenotable(trace, statementFilter, expectedType, targetType, expression)
  }

  override fun resolveTypeRefWithDefault(returnTypeRef: KtTypeReference?, scope: LexicalScope, trace: BindingTrace, defaultValue: KotlinType?): KotlinType? {
    println("MetaArgumentTypeResolver.resolveTypeRefWithDefault: $returnTypeRef, $scope, $trace, $defaultValue")
    return super.resolveTypeRefWithDefault(returnTypeRef, scope, trace, defaultValue)
  }

  override fun getCallableReferenceTypeInfo(expression: KtExpression, callableReferenceExpression: KtCallableReferenceExpression, context: CallResolutionContext<*>, resolveArgumentsMode: ResolveArgumentsMode): KotlinTypeInfo {
    println("MetaArgumentTypeResolver.getCallableReferenceTypeInfo: $expression, $callableReferenceExpression, $context, $resolveArgumentsMode")
    return super.getCallableReferenceTypeInfo(expression, callableReferenceExpression, context, resolveArgumentsMode)
  }

  override fun analyzeArgumentsAndRecordTypes(context: CallResolutionContext<*>, resolveArgumentsMode: ResolveArgumentsMode) {
    println("MetaArgumentTypeResolver.analyzeArgumentsAndRecordTypes: $context, $resolveArgumentsMode")
    super.analyzeArgumentsAndRecordTypes(context, resolveArgumentsMode)
  }
}
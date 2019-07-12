package arrow.meta.debug.resolution

import org.jetbrains.kotlin.config.LanguageVersionSettings
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.psi.KtBlockExpression
import org.jetbrains.kotlin.psi.KtDeclarationWithBody
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.resolve.AnnotationChecker
import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.resolve.StatementFilter
import org.jetbrains.kotlin.resolve.calls.components.InferenceSession
import org.jetbrains.kotlin.resolve.calls.context.ContextDependency
import org.jetbrains.kotlin.resolve.calls.context.ResolutionContext
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowInfo
import org.jetbrains.kotlin.resolve.scopes.LexicalScope
import org.jetbrains.kotlin.resolve.scopes.LocalRedeclarationChecker
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.expressions.CoercionStrategy
import org.jetbrains.kotlin.types.expressions.ExpressionTypingComponents
import org.jetbrains.kotlin.types.expressions.ExpressionTypingContext
import org.jetbrains.kotlin.types.expressions.ExpressionTypingServices
import org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher
import org.jetbrains.kotlin.types.expressions.KotlinTypeInfo

/**
 * TODO: try to access the statementFilter from super and implement commented methods
 */
class MetaExpressionTypingServices(
  val components: ExpressionTypingComponents,
  val annotationChecker: AnnotationChecker,
  // override val statementFilter: StatementFilter
  val facade: ExpressionTypingVisitorDispatcher.ForDeclarations
) : ExpressionTypingServices(
  components,
  annotationChecker,
  StatementFilter.NONE,
  facade
) {
  override fun getBodyExpressionType(trace: BindingTrace, outerScope: LexicalScope, dataFlowInfo: DataFlowInfo, function: KtDeclarationWithBody, functionDescriptor: FunctionDescriptor): KotlinType {
    println("MetaExpressionTypingServices.getBodyExpressionType: $trace, $outerScope, $dataFlowInfo, $function, $functionDescriptor")
    return super.getBodyExpressionType(trace, outerScope, dataFlowInfo, function, functionDescriptor)
  }

  override fun getStatementFilter(): StatementFilter {
    println("MetaExpressionTypingServices.getStatementFilter:")
    return super.getStatementFilter()
  }

  /*override fun getBlockReturnedTypeWithWritableScope(scope: LexicalWritableScope, block: MutableList<out KtElement>, coercionStrategyForLastExpression: CoercionStrategy, context: ExpressionTypingContext): KotlinTypeInfo {
    println("MetaExpressionTypingServices.getBlockReturnedTypeWithWritableScope: $scope, $block, $coercionStrategyForLastExpression, $context")
    return super.getBlockReturnedTypeWithWritableScope(scope, block, coercionStrategyForLastExpression, context)
  }*/
  override fun checkFunctionReturnType(functionInnerScope: LexicalScope, function: KtDeclarationWithBody, functionDescriptor: FunctionDescriptor, dataFlowInfo: DataFlowInfo, expectedReturnType: KotlinType?, trace: BindingTrace?) {
    println("MetaExpressionTypingServices.checkFunctionReturnType: $functionInnerScope, $function, $functionDescriptor, $dataFlowInfo, $expectedReturnType, $trace")
    super.checkFunctionReturnType(functionInnerScope, function, functionDescriptor, dataFlowInfo, expectedReturnType, trace)
  }

  /*override fun checkFunctionReturnType(function: KtDeclarationWithBody?, context: ExpressionTypingContext?) {
    println("MetaExpressionTypingServices.checkFunctionReturnType: ")
    super.checkFunctionReturnType(function, context)
  }

  override fun getBlockReturnedTypeWithWritableScope(scope: LexicalWritableScope, block: MutableList<out KtElement>, coercionStrategyForLastExpression: CoercionStrategy, context: ExpressionTypingContext): KotlinTypeInfo {
    println("MetaExpressionTypingServices.getBlockReturnedTypeWithWritableScope: ")
    return super.getBlockReturnedTypeWithWritableScope(scope, block, coercionStrategyForLastExpression, context)
  }*/

  override fun getType(scope: LexicalScope, expression: KtExpression, expectedType: KotlinType, dataFlowInfo: DataFlowInfo, inferenceSession: InferenceSession, trace: BindingTrace): KotlinType? {
    println("MetaExpressionTypingServices.getType: $scope, $expression, $expectedType, $dataFlowInfo, $inferenceSession, $trace")
    return super.getType(scope, expression, expectedType, dataFlowInfo, inferenceSession, trace)
  }

  override fun getTypeInfo(scope: LexicalScope, expression: KtExpression, expectedType: KotlinType, dataFlowInfo: DataFlowInfo, inferenceSession: InferenceSession, trace: BindingTrace, isStatement: Boolean): KotlinTypeInfo {
    println("MetaExpressionTypingServices.getTypeInfo: $scope, $expression, $expectedType, $dataFlowInfo, $inferenceSession, $trace, $isStatement")
    return super.getTypeInfo(scope, expression, expectedType, dataFlowInfo, inferenceSession, trace, isStatement)
  }

  override fun getTypeInfo(scope: LexicalScope, expression: KtExpression, expectedType: KotlinType, dataFlowInfo: DataFlowInfo, inferenceSession: InferenceSession, trace: BindingTrace, isStatement: Boolean, contextExpression: KtExpression, contextDependency: ContextDependency): KotlinTypeInfo {
    println("MetaExpressionTypingServices.getTypeInfo: $scope, $expression, $expectedType, $dataFlowInfo, $inferenceSession, $trace, $isStatement, $contextExpression, $contextDependency")
    return super.getTypeInfo(scope, expression, expectedType, dataFlowInfo, inferenceSession, trace, isStatement, contextExpression, contextDependency)
  }

  override fun getTypeInfo(expression: KtExpression, resolutionContext: ResolutionContext<out ResolutionContext<*>>): KotlinTypeInfo {
    println("MetaExpressionTypingServices.getTypeInfo: $expression, $resolutionContext")
    return super.getTypeInfo(expression, resolutionContext)
  }

  override fun createLocalRedeclarationChecker(trace: BindingTrace?): LocalRedeclarationChecker {
    println("MetaExpressionTypingServices.createLocalRedeclarationChecker: $trace")
    return super.createLocalRedeclarationChecker(trace)
  }

  override fun safeGetType(scope: LexicalScope, expression: KtExpression, expectedType: KotlinType, dataFlowInfo: DataFlowInfo, inferenceSession: InferenceSession, trace: BindingTrace): KotlinType {
    println("MetaExpressionTypingServices.safeGetType: $scope, $expression, $expectedType, $dataFlowInfo, $inferenceSession, $trace")
    return super.safeGetType(scope, expression, expectedType, dataFlowInfo, inferenceSession, trace)
  }

  override fun getBlockReturnedType(expression: KtBlockExpression?, context: ExpressionTypingContext?, isStatement: Boolean): KotlinTypeInfo {
    println("MetaExpressionTypingServices.getBlockReturnedType: $expression, $context, $isStatement")
    return super.getBlockReturnedType(expression, context, isStatement)
  }

  override fun getBlockReturnedType(expression: KtBlockExpression, coercionStrategyForLastExpression: CoercionStrategy, context: ExpressionTypingContext): KotlinTypeInfo {
    println("MetaExpressionTypingServices.getBlockReturnedType: $expression, $coercionStrategyForLastExpression, $context")
    return super.getBlockReturnedType(expression, coercionStrategyForLastExpression, context)
  }

  override fun getLanguageVersionSettings(): LanguageVersionSettings {
    println("MetaExpressionTypingServices.getLanguageVersionSettings:")
    return super.getLanguageVersionSettings()
  }
}

/**
 * TODO: still WIP > simply copied from sources and turned to kotlin
 */
/*fun ExpressionTypingServices.getBlockReturnedTypeWithWritableScope(
  scope: LexicalWritableScope, block: MutableList<out KtElement>, coercionStrategyForLastExpression: CoercionStrategy, context: ExpressionTypingContext
): KotlinTypeInfo = when(block.isEmpty()){
  true -> createTypeInfo(this.expressionTypingComponents.builtIns.getUnitType(), context)
}*/

/**
getBlockReturnedTypeWithWritableScope(@NotNull LexicalWritableScope scope, @NotNull List<? extends KtElement> block, @NotNull CoercionStrategy coercionStrategyForLastExpression, @NotNull ExpressionTypingContext context) {
if (scope == null) {
$$$reportNull$$$0(54);
}

if (block == null) {
$$$reportNull$$$0(55);
}

if (coercionStrategyForLastExpression == null) {
$$$reportNull$$$0(56);
}

if (context == null) {
$$$reportNull$$$0(57);
}

if (block.isEmpty()) {
return
} else {
ExpressionTypingInternals blockLevelVisitor = new ForBlock(this.expressionTypingComponents, this.annotationChecker, scope);
ExpressionTypingContext newContext = (ExpressionTypingContext)((ExpressionTypingContext)context.replaceScope(scope)).replaceExpectedType(TypeUtils.NO_EXPECTED_TYPE);
KotlinTypeInfo result = TypeInfoFactoryKt.noTypeInfo(context);
DataFlowInfo beforeJumpInfo = newContext.dataFlowInfo;
boolean jumpOutPossible = false;
boolean isFirstStatement = true;
Iterator iterator = block.iterator();

while(iterator.hasNext()) {
AbstractFilteringTrace traceForSingleStatement = new ExpressionTypingServices.EffectsFilteringTrace(context.trace);
newContext = (ExpressionTypingContext)newContext.replaceBindingTrace(traceForSingleStatement);
KtElement statement = (KtElement)iterator.next();
if (statement instanceof KtExpression) {
KtExpression statementExpression = (KtExpression)statement;
if (!iterator.hasNext()) {
result = this.getTypeOfLastExpressionInBlock(statementExpression, (ExpressionTypingContext)newContext.replaceExpectedType(context.expectedType), coercionStrategyForLastExpression, blockLevelVisitor);
if (result.getType() != null && statementExpression.getParent() instanceof KtBlockExpression) {
DataFlowValue lastExpressionValue = this.expressionTypingComponents.dataFlowValueFactory.createDataFlowValue(statementExpression, result.getType(), context);
DataFlowValue blockExpressionValue = this.expressionTypingComponents.dataFlowValueFactory.createDataFlowValue((KtBlockExpression)statementExpression.getParent(), result.getType(), context);
result = result.replaceDataFlowInfo(result.getDataFlowInfo().assign(blockExpressionValue, lastExpressionValue, this.expressionTypingComponents.languageVersionSettings));
}
} else {
result = blockLevelVisitor.getTypeInfo(statementExpression, (ExpressionTypingContext)newContext.replaceContextDependency(ContextDependency.INDEPENDENT), true);
}

DataFlowInfo newDataFlowInfo = result.getDataFlowInfo();
if (!jumpOutPossible) {
beforeJumpInfo = result.getJumpFlowInfo();
jumpOutPossible = result.getJumpOutPossible();
}

if (newDataFlowInfo != newContext.dataFlowInfo) {
newContext = (ExpressionTypingContext)newContext.replaceDataFlowInfo(newDataFlowInfo);
}

blockLevelVisitor = new ForBlock(this.expressionTypingComponents, this.annotationChecker, scope);
if (isFirstStatement) {
this.expressionTypingComponents.contractParsingServices.checkContractAndRecordIfPresent(statementExpression, context.trace, scope);
isFirstStatement = false;
}
}
}

return result.replaceJumpOutPossible(jumpOutPossible).replaceJumpFlowInfo(beforeJumpInfo);
}
}
 */
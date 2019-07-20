package arrow.meta.debug.unusedServices

import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.resolve.calls.CallResolver
import org.jetbrains.kotlin.types.expressions.ControlStructureTypingUtils
import org.jetbrains.kotlin.types.expressions.DataFlowAnalyzer

/**
 * @CantOverrideMethods
 * @UnusedService Currently we need to investigate how our costume functions can be executed.
 * Meaning we need to hook into the compiler earlier than Analysis. Either with Reflection or else
 */
class MetaControlStructureTypingUtils(
  callResolver: CallResolver,
  dataFlowAnalyzer: DataFlowAnalyzer,
  moduleDescriptor: ModuleDescriptor
) :
  ControlStructureTypingUtils(callResolver, dataFlowAnalyzer, moduleDescriptor) {
  /*override fun resolveTryAsCall(call: Call, tryExpression: KtTryExpression, catchedExceptions: MutableList<Pair<KtExpression, VariableDescriptor>>, context: ExpressionTypingContext, dataFlowInfoForArguments: MutableDataFlowInfoForArguments?): ResolvedCall<FunctionDescriptor> {
    println("MetaControlStructureTypingUtils.")
    return super.resolveTryAsCall(call, tryExpression, catchedExceptions, context, dataFlowInfoForArguments)
  }

  override fun resolveSpecialConstructionAsCall(call: Call, construct: ResolveConstruct, argumentNames: MutableList<String>, isArgumentNullable: MutableList<Boolean>, context: ExpressionTypingContext, dataFlowInfoForArguments: MutableDataFlowInfoForArguments?): ResolvedCall<FunctionDescriptor> {
    println("MetaControlStructureTypingUtils.")
    return super.resolveSpecialConstructionAsCall(call, construct, argumentNames, isArgumentNullable, context, dataFlowInfoForArguments)
  }*/
}
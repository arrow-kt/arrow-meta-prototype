package arrow.meta.extensions

import org.jetbrains.kotlin.backend.common.BackendContext
import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.codegen.ImplementationBodyCodegen
import org.jetbrains.kotlin.codegen.StackValue
import org.jetbrains.kotlin.codegen.extensions.ExpressionCodegenExtension
import org.jetbrains.kotlin.com.intellij.mock.MockProject
import org.jetbrains.kotlin.ir.declarations.IrFile
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCall

class MetaIDEExpressionCodegenExtension(
  val project: MockProject,
  val phase: ExtensionPhase.Codegen,
  val ctx: CompilerContext
) : ExpressionCodegenExtension {
  override fun applyFunction(
    receiver: StackValue,
    resolvedCall: ResolvedCall<*>,
    c: ExpressionCodegenExtension.Context
  ): StackValue? {
    return phase.run { ctx.applyFunction(receiver, resolvedCall, c) }
  }

  override fun applyProperty(
    receiver: StackValue,
    resolvedCall: ResolvedCall<*>,
    c: ExpressionCodegenExtension.Context
  ): StackValue? {
    return phase.run { ctx.applyProperty(receiver, resolvedCall, c) }
  }

  override fun generateClassSyntheticParts(codegen: ImplementationBodyCodegen) {
    phase.run { ctx.generateClassSyntheticParts(codegen) }
  }
}

class MetaIDEIrExtension(
  val project: MockProject,
  val phase: ExtensionPhase.IRGeneration,
  val compilerContext: CompilerContext
) : IrGenerationExtension {
  override fun generate(file: IrFile, backendContext: BackendContext, bindingContext: BindingContext) {
    println("MetaIDEIrExtension.generate works")
    phase.run { compilerContext.generate(file, backendContext, bindingContext) }
  }
}
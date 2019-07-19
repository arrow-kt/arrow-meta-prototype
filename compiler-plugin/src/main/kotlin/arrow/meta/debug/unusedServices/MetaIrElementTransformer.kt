package arrow.meta.debug.unusedServices

import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.declarations.IrAnonymousInitializer
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrConstructor
import org.jetbrains.kotlin.ir.declarations.IrDeclaration
import org.jetbrains.kotlin.ir.declarations.IrEnumEntry
import org.jetbrains.kotlin.ir.declarations.IrErrorDeclaration
import org.jetbrains.kotlin.ir.declarations.IrExternalPackageFragment
import org.jetbrains.kotlin.ir.declarations.IrField
import org.jetbrains.kotlin.ir.declarations.IrFile
import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.declarations.IrLocalDelegatedProperty
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import org.jetbrains.kotlin.ir.declarations.IrPackageFragment
import org.jetbrains.kotlin.ir.declarations.IrProperty
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction
import org.jetbrains.kotlin.ir.declarations.IrTypeParameter
import org.jetbrains.kotlin.ir.declarations.IrValueParameter
import org.jetbrains.kotlin.ir.declarations.IrVariable
import org.jetbrains.kotlin.ir.expressions.IrBlock
import org.jetbrains.kotlin.ir.expressions.IrBlockBody
import org.jetbrains.kotlin.ir.expressions.IrBody
import org.jetbrains.kotlin.ir.expressions.IrBranch
import org.jetbrains.kotlin.ir.expressions.IrBreak
import org.jetbrains.kotlin.ir.expressions.IrBreakContinue
import org.jetbrains.kotlin.ir.expressions.IrCall
import org.jetbrains.kotlin.ir.expressions.IrCallableReference
import org.jetbrains.kotlin.ir.expressions.IrCatch
import org.jetbrains.kotlin.ir.expressions.IrClassReference
import org.jetbrains.kotlin.ir.expressions.IrComposite
import org.jetbrains.kotlin.ir.expressions.IrConst
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall
import org.jetbrains.kotlin.ir.expressions.IrContainerExpression
import org.jetbrains.kotlin.ir.expressions.IrContinue
import org.jetbrains.kotlin.ir.expressions.IrDeclarationReference
import org.jetbrains.kotlin.ir.expressions.IrDelegatingConstructorCall
import org.jetbrains.kotlin.ir.expressions.IrDoWhileLoop
import org.jetbrains.kotlin.ir.expressions.IrDynamicExpression
import org.jetbrains.kotlin.ir.expressions.IrDynamicMemberExpression
import org.jetbrains.kotlin.ir.expressions.IrDynamicOperatorExpression
import org.jetbrains.kotlin.ir.expressions.IrElseBranch
import org.jetbrains.kotlin.ir.expressions.IrEnumConstructorCall
import org.jetbrains.kotlin.ir.expressions.IrErrorCallExpression
import org.jetbrains.kotlin.ir.expressions.IrErrorExpression
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.expressions.IrExpressionBody
import org.jetbrains.kotlin.ir.expressions.IrFieldAccessExpression
import org.jetbrains.kotlin.ir.expressions.IrFunctionAccessExpression
import org.jetbrains.kotlin.ir.expressions.IrFunctionReference
import org.jetbrains.kotlin.ir.expressions.IrGetClass
import org.jetbrains.kotlin.ir.expressions.IrGetEnumValue
import org.jetbrains.kotlin.ir.expressions.IrGetField
import org.jetbrains.kotlin.ir.expressions.IrGetObjectValue
import org.jetbrains.kotlin.ir.expressions.IrGetSingletonValue
import org.jetbrains.kotlin.ir.expressions.IrGetValue
import org.jetbrains.kotlin.ir.expressions.IrInstanceInitializerCall
import org.jetbrains.kotlin.ir.expressions.IrLocalDelegatedPropertyReference
import org.jetbrains.kotlin.ir.expressions.IrLoop
import org.jetbrains.kotlin.ir.expressions.IrMemberAccessExpression
import org.jetbrains.kotlin.ir.expressions.IrPropertyReference
import org.jetbrains.kotlin.ir.expressions.IrReturn
import org.jetbrains.kotlin.ir.expressions.IrSetField
import org.jetbrains.kotlin.ir.expressions.IrSetVariable
import org.jetbrains.kotlin.ir.expressions.IrSpreadElement
import org.jetbrains.kotlin.ir.expressions.IrStringConcatenation
import org.jetbrains.kotlin.ir.expressions.IrSuspendableExpression
import org.jetbrains.kotlin.ir.expressions.IrSuspensionPoint
import org.jetbrains.kotlin.ir.expressions.IrSyntheticBody
import org.jetbrains.kotlin.ir.expressions.IrThrow
import org.jetbrains.kotlin.ir.expressions.IrTry
import org.jetbrains.kotlin.ir.expressions.IrTypeOperatorCall
import org.jetbrains.kotlin.ir.expressions.IrValueAccessExpression
import org.jetbrains.kotlin.ir.expressions.IrVararg
import org.jetbrains.kotlin.ir.expressions.IrWhen
import org.jetbrains.kotlin.ir.expressions.IrWhileLoop
import org.jetbrains.kotlin.ir.visitors.IrElementTransformer

/**
 * @UnusedService Currently we need to investigate how our costume functions can be executed.
 * Meaning we need to hook into the compiler earlier than Analysis. Either with Reflection or else
 * check [org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid] or [org.jetbrains.kotlin.backend.common.IrElementTransformerVoidWithContext]
 */
class MetaIrElementTransformer<F> : IrElementTransformer<F> {
  override fun visitAnonymousInitializer(declaration: IrAnonymousInitializer, data: F): IrStatement {
    println("MetaIrElementTransformer.visitAnonymousInitializer: $declaration, $data")
    return super.visitAnonymousInitializer(declaration, data)
  }

  override fun visitContainerExpression(expression: IrContainerExpression, data: F): IrExpression {
    println("MetaIrElementTransformer.visitContainerExpression: $expression, $data")
    return super.visitContainerExpression(expression, data)
  }

  override fun visitContinue(jump: IrContinue, data: F): IrExpression {
    println("MetaIrElementTransformer.visitContinue: $jump, $data")
    return super.visitContinue(jump, data)
  }

  override fun visitDeclarationReference(expression: IrDeclarationReference, data: F): IrExpression {
    println("MetaIrElementTransformer.visitDeclarationReference: $expression, $data")
    return super.visitDeclarationReference(expression, data)
  }

  override fun visitDelegatingConstructorCall(expression: IrDelegatingConstructorCall, data: F): IrElement {
    println("MetaIrElementTransformer.visitDelegatingConstructorCall: $expression, $data")
    return super.visitDelegatingConstructorCall(expression, data)
  }

  override fun visitDoWhileLoop(loop: IrDoWhileLoop, data: F): IrExpression {
    println("MetaIrElementTransformer.visitDoWhileLoop: $loop, $data")
    return super.visitDoWhileLoop(loop, data)
  }

  override fun visitDynamicMemberExpression(expression: IrDynamicMemberExpression, data: F): IrExpression {
    println("MetaIrElementTransformer.visitDynamicMemberExpression: $expression, $data")
    return super.visitDynamicMemberExpression(expression, data)
  }

  override fun visitDynamicOperatorExpression(expression: IrDynamicOperatorExpression, data: F): IrExpression {
    println("MetaIrElementTransformer.visitDynamicOperatorExpression: $expression, $data")
    return super.visitDynamicOperatorExpression(expression, data)
  }

  override fun visitElement(element: IrElement, data: F): IrElement {
    println("MetaIrElementTransformer.visitElement: $element, $data")
    return super.visitElement(element, data)
  }

  override fun visitElseBranch(branch: IrElseBranch, data: F): IrElseBranch {
    println("MetaIrElementTransformer.visitElseBranch: $branch, $data")
    return super.visitElseBranch(branch, data)
  }

  override fun visitEnumConstructorCall(expression: IrEnumConstructorCall, data: F): IrElement {
    println("MetaIrElementTransformer.visitEnumConstructorCall: $expression, $data")
    return super.visitEnumConstructorCall(expression, data)
  }

  override fun visitEnumEntry(declaration: IrEnumEntry, data: F): IrStatement {
    println("MetaIrElementTransformer.visitEnumEntry: $declaration, $data")
    return super.visitEnumEntry(declaration, data)
  }

  override fun visitErrorCallExpression(expression: IrErrorCallExpression, data: F): IrExpression {
    println("MetaIrElementTransformer.visitErrorCallExpression: $expression, $data")
    return super.visitErrorCallExpression(expression, data)
  }

  override fun visitErrorDeclaration(declaration: IrErrorDeclaration, data: F): IrStatement {
    println("MetaIrElementTransformer.visitErrorDeclaration: $declaration, $data")
    return super.visitErrorDeclaration(declaration, data)
  }

  override fun visitErrorExpression(expression: IrErrorExpression, data: F): IrExpression {
    println("MetaIrElementTransformer.visitErrorExpression: $expression, $data")
    return super.visitErrorExpression(expression, data)
  }

  override fun visitExpression(expression: IrExpression, data: F): IrExpression {
    println("MetaIrElementTransformer.visitExpression: $expression, $data")
    return super.visitExpression(expression, data)
  }

  override fun visitExpressionBody(body: IrExpressionBody, data: F): IrBody {
    println("MetaIrElementTransformer.visitExpressionBody: $body, $data")
    return super.visitExpressionBody(body, data)
  }

  override fun visitExternalPackageFragment(declaration: IrExternalPackageFragment, data: F): IrExternalPackageFragment {
    println("MetaIrElementTransformer.visitExternalPackageFragment: $declaration, $data")
    return super.visitExternalPackageFragment(declaration, data)
  }

  override fun visitField(declaration: IrField, data: F): IrStatement {
    println("MetaIrElementTransformer.visitField $declaration, $data")
    return super.visitField(declaration, data)
  }

  override fun visitFieldAccess(expression: IrFieldAccessExpression, data: F): IrExpression {
    println("MetaIrElementTransformer.visitFieldAccess: $expression, $data")
    return super.visitFieldAccess(expression, data)
  }

  override fun visitFile(declaration: IrFile, data: F): IrFile {
    println("MetaIrElementTransformer.visitFile: $declaration, $data")
    return super.visitFile(declaration, data)
  }

  override fun visitFunction(declaration: IrFunction, data: F): IrStatement {
    println("MetaIrElementTransformer.visitFunction: $declaration, $data")
    return super.visitFunction(declaration, data)
  }

  override fun visitFunctionAccess(expression: IrFunctionAccessExpression, data: F): IrElement {
    println("MetaIrElementTransformer.visitFunctionAccess: $expression, $data")
    return super.visitFunctionAccess(expression, data)
  }

  override fun visitFunctionReference(expression: IrFunctionReference, data: F): IrElement {
    println("MetaIrElementTransformer.visitFunctionReference: $expression, $data")
    return super.visitFunctionReference(expression, data)
  }

  override fun visitGetClass(expression: IrGetClass, data: F): IrExpression {
    println("MetaIrElementTransformer.visitGetClass: $expression, $data")
    return super.visitGetClass(expression, data)
  }

  override fun visitGetEnumValue(expression: IrGetEnumValue, data: F): IrExpression {
    println("MetaIrElementTransformer.visitGetEnumValue: $expression, $data")
    return super.visitGetEnumValue(expression, data)
  }

  override fun visitGetField(expression: IrGetField, data: F): IrExpression {
    println("MetaIrElementTransformer.visitGetField: $expression, $data")
    return super.visitGetField(expression, data)
  }

  override fun visitGetObjectValue(expression: IrGetObjectValue, data: F): IrExpression {
    println("MetaIrElementTransformer.visitGetObjectValue: $expression, $data")
    return super.visitGetObjectValue(expression, data)
  }

  override fun visitGetValue(expression: IrGetValue, data: F): IrExpression {
    println("MetaIrElementTransformer.visitGetValue: $expression, $data")
    return super.visitGetValue(expression, data)
  }

  override fun visitInstanceInitializerCall(expression: IrInstanceInitializerCall, data: F): IrExpression {
    println("MetaIrElementTransformer.visitInstanceInitializerCall: $expression, $data")
    return super.visitInstanceInitializerCall(expression, data)
  }

  override fun visitLocalDelegatedProperty(declaration: IrLocalDelegatedProperty, data: F): IrStatement {
    println("MetaIrElementTransformer.visitLocalDelegatedProperty: $declaration, $data")
    return super.visitLocalDelegatedProperty(declaration, data)
  }

  override fun visitLocalDelegatedPropertyReference(expression: IrLocalDelegatedPropertyReference, data: F): IrElement {
    println("MetaIrElementTransformer.visitLocalDelegatedPropertyReference: $expression, $data")
    return super.visitLocalDelegatedPropertyReference(expression, data)
  }

  override fun visitLoop(loop: IrLoop, data: F): IrExpression {
    println("MetaIrElementTransformer.visitLoop: $loop, $data")
    return super.visitLoop(loop, data)
  }

  override fun visitMemberAccess(expression: IrMemberAccessExpression, data: F): IrElement {
    println("MetaIrElementTransformer.visitMemberAccess: $expression, $data")
    return super.visitMemberAccess(expression, data)
  }

  override fun visitModuleFragment(declaration: IrModuleFragment, data: F): IrModuleFragment {
    println("MetaIrElementTransformer.visitModuleFragment: $declaration, $data")
    return super.visitModuleFragment(declaration, data)
  }

  override fun visitProperty(declaration: IrProperty, data: F): IrStatement {
    println("MetaIrElementTransformer.visitProperty: $declaration, $data")
    return super.visitProperty(declaration, data)
  }

  override fun visitPropertyReference(expression: IrPropertyReference, data: F): IrElement {
    println("MetaIrElementTransformer.visitPropertyReference: $expression, $data")
    return super.visitPropertyReference(expression, data)
  }

  override fun visitDeclaration(declaration: IrDeclaration, data: F): IrStatement {
    println("MetaIrElementTransformer.visitDeclaration: $declaration, $data")
    return super.visitDeclaration(declaration, data)
  }

  override fun visitDynamicExpression(expression: IrDynamicExpression, data: F): IrExpression {
    println("MetaIrElementTransformer.visitDynamicExpression: $expression, $data")
    return super.visitDynamicExpression(expression, data)
  }

  override fun visitPackageFragment(declaration: IrPackageFragment, data: F): IrElement {
    println("MetaIrElementTransformer.visitPackageFragment: $declaration, $data")
    return super.visitPackageFragment(declaration, data)
  }

  override fun visitReturn(expression: IrReturn, data: F): IrExpression {
    println("MetaIrElementTransformer.visitReturn: $expression, $data")
    return super.visitReturn(expression, data)
  }

  override fun visitSetField(expression: IrSetField, data: F): IrExpression {
    println("MetaIrElementTransformer.visitSetField: $expression, $data")
    return super.visitSetField(expression, data)
  }

  override fun visitSetVariable(expression: IrSetVariable, data: F): IrExpression {
    println("MetaIrElementTransformer.visitSetVariable: $expression, $data")
    return super.visitSetVariable(expression, data)
  }

  override fun visitSimpleFunction(declaration: IrSimpleFunction, data: F): IrStatement {
    println("MetaIrElementTransformer.visitSimpleFunction: $declaration, $data")
    return super.visitSimpleFunction(declaration, data)
  }

  override fun visitSingletonReference(expression: IrGetSingletonValue, data: F): IrExpression {
    println("MetaIrElementTransformer.visitSingletonReference: $expression, $data")
    return super.visitSingletonReference(expression, data)
  }

  override fun visitSpreadElement(spread: IrSpreadElement, data: F): IrSpreadElement {
    println("MetaIrElementTransformer.visitSpreadElement: $spread, $data")
    return super.visitSpreadElement(spread, data)
  }

  override fun visitStringConcatenation(expression: IrStringConcatenation, data: F): IrExpression {
    println("MetaIrElementTransformer.visitStringConcatenation: $expression, $data")
    return super.visitStringConcatenation(expression, data)
  }

  override fun visitSuspendableExpression(expression: IrSuspendableExpression, data: F): IrExpression {
    println("MetaIrElementTransformer.visitSuspendableExpression: $expression, $data")
    return super.visitSuspendableExpression(expression, data)
  }

  override fun visitSuspensionPoint(expression: IrSuspensionPoint, data: F): IrExpression {
    println("MetaIrElementTransformer.visitSuspensionPoint: $expression, $data")
    return super.visitSuspensionPoint(expression, data)
  }

  override fun visitSyntheticBody(body: IrSyntheticBody, data: F): IrBody {
    println("MetaIrElementTransformer.visitSyntheticBody: $body, $data")
    return super.visitSyntheticBody(body, data)
  }

  override fun visitThrow(expression: IrThrow, data: F): IrExpression {
    println("MetaIrElementTransformer.visitThrow: $expression, $data")
    return super.visitThrow(expression, data)
  }

  override fun visitTry(aTry: IrTry, data: F): IrExpression {
    println("MetaIrElementTransformer.visitTry: $aTry, $data")
    return super.visitTry(aTry, data)
  }

  override fun visitTypeOperator(expression: IrTypeOperatorCall, data: F): IrExpression {
    println("MetaIrElementTransformer.visitTypeOperator: $expression, $data")
    return super.visitTypeOperator(expression, data)
  }

  override fun visitTypeParameter(declaration: IrTypeParameter, data: F): IrStatement {
    println("MetaIrElementTransformer.visitValueAccess: $declaration, $data")
    return super.visitTypeParameter(declaration, data)
  }

  override fun visitValueAccess(expression: IrValueAccessExpression, data: F): IrExpression {
    println("MetaIrElementTransformer.visitValueAccess: $expression, $data")
    return super.visitValueAccess(expression, data)
  }

  override fun visitValueParameter(declaration: IrValueParameter, data: F): IrStatement {
    println("MetaIrElementTransformer.visitValueParameter: $declaration, $data")
    return super.visitValueParameter(declaration, data)
  }

  override fun visitVararg(expression: IrVararg, data: F): IrExpression {
    println("MetaIrElementTransformer.visitVararg: $expression, $data")
    return super.visitVararg(expression, data)
  }

  override fun visitVariable(declaration: IrVariable, data: F): IrStatement {
    println("MetaIrElementTransformer.visitVariable: $declaration, $data")
    return super.visitVariable(declaration, data)
  }

  override fun visitWhen(expression: IrWhen, data: F): IrExpression {
    println("MetaIrElementTransformer.visitWhen: $expression, $data")
    return super.visitWhen(expression, data)
  }

  override fun visitWhileLoop(loop: IrWhileLoop, data: F): IrExpression {
    println("MetaIrElementTransformer.visitWhileLoop: $loop, $data")
    return super.visitWhileLoop(loop, data)
  }

  override fun visitBlock(expression: IrBlock, data: F): IrExpression {
    println("MetaIrElementTransformer.visitBlock: $expression, $data")
    return super.visitBlock(expression, data)
  }

  override fun visitBlockBody(body: IrBlockBody, data: F): IrBody {
    println("MetaIrElementTransformer.visitBlockBody: $body, $data")
    return super.visitBlockBody(body, data)
  }

  override fun visitBody(body: IrBody, data: F): IrBody {
    println("MetaIrElementTransformer.visitBody: $body, $data")
    return super.visitBody(body, data)
  }

  override fun visitBranch(branch: IrBranch, data: F): IrBranch {
    println("MetaIrElementTransformer.visitBranch: $branch, $data")
    return super.visitBranch(branch, data)
  }

  override fun visitBreak(jump: IrBreak, data: F): IrExpression {
    println("MetaIrElementTransformer.visitBreak: $jump, $data")
    return super.visitBreak(jump, data)
  }

  override fun visitBreakContinue(jump: IrBreakContinue, data: F): IrExpression {
    println("MetaIrElementTransformer.visitBreakContinue: $jump, $data")
    return super.visitBreakContinue(jump, data)
  }

  override fun visitCall(expression: IrCall, data: F): IrElement {
    println("MetaIrElementTransformer.visitCall: $expression, $data")
    return super.visitCall(expression, data)
  }

  override fun visitCallableReference(expression: IrCallableReference, data: F): IrElement {
    println("MetaIrElementTransformer.visitCallableReference: $expression, $data")
    return super.visitCallableReference(expression, data)
  }

  override fun visitCatch(aCatch: IrCatch, data: F): IrCatch {
    println("MetaIrElementTransformer.visitCatch: $aCatch, $data")
    return super.visitCatch(aCatch, data)
  }

  override fun visitClass(declaration: IrClass, data: F): IrStatement {
    println("MetaIrElementTransformer.visitClass: $declaration, $data")
    return super.visitClass(declaration, data)
  }

  override fun visitClassReference(expression: IrClassReference, data: F): IrExpression {
    println("MetaIrElementTransformer.visitClassReference: $expression, $data")
    return super.visitClassReference(expression, data)
  }

  override fun visitComposite(expression: IrComposite, data: F): IrExpression {
    println("MetaIrElementTransformer.visitComposite: $expression, $data")
    return super.visitComposite(expression, data)
  }

  override fun <T> visitConst(expression: IrConst<T>, data: F): IrExpression {
    println("MetaIrElementTransformer.visitConst: $expression, $data")
    return super.visitConst(expression, data)
  }

  override fun visitConstructor(declaration: IrConstructor, data: F): IrStatement {
    println("MetaIrElementTransformer.visitConstructor: $declaration, $data")
    return super.visitConstructor(declaration, data)
  }

  override fun visitConstructorCall(expression: IrConstructorCall, data: F): IrElement {
    println("MetaIrElementTransformer.visitConstructorCall: $expression, $data")
    return super.visitConstructorCall(expression, data)
  }
}

inline fun <reified F> IrElement.transformChildrenForF(data: F, transformer: MetaIrElementTransformer<F>) {
  transformChildren(transformer, data)
}
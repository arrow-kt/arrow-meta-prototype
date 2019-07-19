package arrow.meta.debug.unusedServices

import org.jetbrains.kotlin.ir.declarations.IrAnonymousInitializer
import org.jetbrains.kotlin.ir.expressions.IrBlock
import org.jetbrains.kotlin.ir.expressions.IrLoop
import org.jetbrains.kotlin.ir.util.DeepCopyIrTreeWithSymbols
import org.jetbrains.kotlin.ir.util.SymbolRemapper
import org.jetbrains.kotlin.ir.util.SymbolRenamer
import org.jetbrains.kotlin.ir.util.TypeRemapper

/**
 * @IllegalStateException: No constructor for this class
 */
class MetaDeepCopyIrTreeWithSymbols(
   val symbolRemapper: SymbolRemapper,
   val typeRemapper: TypeRemapper,
   val symbolRenamer: SymbolRenamer = SymbolRenamer.DEFAULT
) :
  DeepCopyIrTreeWithSymbols(
    symbolRemapper,
    typeRemapper,
    symbolRenamer) {
  override fun getNonTransformedLoop(irLoop: IrLoop): IrLoop {
    println("MetaDeepCopyIrTreeWithSymbols.visit")
    return super.getNonTransformedLoop(irLoop)
  }

  override fun visitAnonymousInitializer(declaration: IrAnonymousInitializer): IrAnonymousInitializer {
    println("MetaDeepCopyIrTreeWithSymbols.visit")
    return super.visitAnonymousInitializer(declaration)
  }

  override fun visitBlock(expression: IrBlock): IrBlock {
    println("MetaDeepCopyIrTreeWithSymbols.visit")
    return super.visitBlock(expression)
  }
}

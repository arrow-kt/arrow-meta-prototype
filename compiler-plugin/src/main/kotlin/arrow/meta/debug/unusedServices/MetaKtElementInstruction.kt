package arrow.meta.debug.unusedServices

import org.jetbrains.kotlin.cfg.pseudocode.PseudoValue
import org.jetbrains.kotlin.cfg.pseudocode.Pseudocode
import org.jetbrains.kotlin.cfg.pseudocode.instructions.BlockScope
import org.jetbrains.kotlin.cfg.pseudocode.instructions.Instruction
import org.jetbrains.kotlin.cfg.pseudocode.instructions.InstructionVisitor
import org.jetbrains.kotlin.cfg.pseudocode.instructions.InstructionVisitorWithResult
import org.jetbrains.kotlin.cfg.pseudocode.instructions.KtElementInstruction
import org.jetbrains.kotlin.psi.KtElement

/**
 * @UnusedService Currently we need to investigate how our costume functions can be executed.
 * Meaning we need to hook into the compiler earlier than Analysis. Either with Reflection or else
 */
class MetaKtElementInstruction(val delegate: KtElementInstruction) : KtElementInstruction by delegate {
  override val blockScope: BlockScope
    get() = delegate.blockScope
  override val copies: Collection<Instruction>
    get() = delegate.copies
  override val dead: Boolean
    get() = delegate.dead
  override val element: KtElement
    get() = delegate.element
  override val inputValues: List<PseudoValue>
    get() = delegate.inputValues
  override val nextInstructions: Collection<Instruction>
    get() = delegate.nextInstructions
  override var owner: Pseudocode
    get() = delegate.owner
    set(value) {}
  override val previousInstructions: Collection<Instruction>
    get() = delegate.previousInstructions

  override fun accept(visitor: InstructionVisitor) {
    println("MetaKtElementInstruction.accept: $visitor")
    return delegate.accept(visitor)
  }

  override fun <R> accept(visitor: InstructionVisitorWithResult<R>): R {
    println("MetaKtElementInstruction.accept: $visitor")
    return delegate.accept(visitor)
  }
}
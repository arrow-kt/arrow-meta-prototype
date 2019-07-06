package arrow.meta.utils

import org.jetbrains.kotlin.descriptors.TypeParameterDescriptor
import org.jetbrains.kotlin.resolve.calls.inference.CallHandle
import org.jetbrains.kotlin.resolve.calls.inference.ConstraintSystem
import org.jetbrains.kotlin.resolve.calls.inference.constraintPosition.ConstraintPosition
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.TypeSubstitutor

/**
 * @UnknownPhase Not subscribable in Analysis Phase: Phase still unknown
 */
class MetaConstraintSystemBuilder : ConstraintSystem.Builder {
  override val typeVariableSubstitutors: Map<CallHandle, TypeSubstitutor>
    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

  override fun add(other: ConstraintSystem.Builder) {
    println("MetaConstraintSystemBuilder.add: $other")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun addSubtypeConstraint(constrainingType: KotlinType?, subjectType: KotlinType?, constraintPosition: ConstraintPosition) {
    println("MetaConstraintSystemBuilder.addSubtypeConstraint: $constrainingType")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun build(): ConstraintSystem {
    println("MetaConstraintSystemBuilder.build:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun fixVariables() {
    println("MetaConstraintSystemBuilder.fixVariables:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun registerTypeVariables(call: CallHandle, typeParameters: Collection<TypeParameterDescriptor>, external: Boolean): TypeSubstitutor {
    println("MetaConstraintSystemBuilder.registerTypeVariables: $typeParameters")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}
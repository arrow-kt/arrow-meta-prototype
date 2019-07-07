package arrow.meta.debug.unusedServices

import org.jetbrains.kotlin.descriptors.TypeParameterDescriptor
import org.jetbrains.kotlin.resolve.calls.inference.CallHandle
import org.jetbrains.kotlin.resolve.calls.inference.ConstraintSystem
import org.jetbrains.kotlin.resolve.calls.inference.constraintPosition.ConstraintPosition
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.TypeSubstitutor

/**
 * @throws ContainerConsistencyException
 * @UnusedService Currently we need to investigate how our costume functions can be executed.
 * Meaning we need to hook into the compiler earlier than Analysis. Either with Reflection or else
 */
class MetaConstraintSystemBuilder(val delegate: ConstraintSystem.Builder) : ConstraintSystem.Builder by delegate {
  override val typeVariableSubstitutors: Map<CallHandle, TypeSubstitutor>
    get() = delegate.typeVariableSubstitutors

  override fun add(other: ConstraintSystem.Builder) {
    println("MetaConstraintSystemBuilder.add: $other")
    return delegate.add(other)
  }

  override fun addSubtypeConstraint(constrainingType: KotlinType?, subjectType: KotlinType?, constraintPosition: ConstraintPosition) {
    println("MetaConstraintSystemBuilder.addSubtypeConstraint: $constrainingType, $subjectType, $constraintPosition")
    return delegate.addSubtypeConstraint(constrainingType, subjectType, constraintPosition)
  }

  override fun build(): ConstraintSystem {
    println("MetaConstraintSystemBuilder.build: ")
    return delegate.build()
  }

  override fun fixVariables() {
    println("MetaConstraintSystemBuilder.fixVariables:")
    return delegate.fixVariables()
  }

  override fun registerTypeVariables(call: CallHandle, typeParameters: Collection<TypeParameterDescriptor>, external: Boolean): TypeSubstitutor {
    println("MetaConstraintSystemBuilder.registerTypeVariables: $call, $typeParameters, $external")
    return delegate.registerTypeVariables(call, typeParameters, external)
  }
}
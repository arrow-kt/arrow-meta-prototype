package arrow.meta.debug.resolution

import org.jetbrains.kotlin.builtins.KotlinBuiltIns
import org.jetbrains.kotlin.descriptors.CallableDescriptor
import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.resolve.calls.results.FlatSignature
import org.jetbrains.kotlin.resolve.calls.results.SimpleConstraintSystem
import org.jetbrains.kotlin.resolve.calls.results.TypeSpecificityComparator

/**
 * OverloadingConflictResolver is used for great utilities like [NewOverloadingConflictResolver], [CallableReferenceOverloadConflictResolver]
 * [FlatSignatureForResolvedCallKt] and [ResolutionResultsHandler]
 */
class MetaOverloadingConflictResolver<C : Any>(
  val builtIns: KotlinBuiltIns,
  val module: ModuleDescriptor,
  val specificityComparator: TypeSpecificityComparator,
  val getResultingDescriptor: (C) -> CallableDescriptor,
  val createEmptyConstraintSystem: () -> SimpleConstraintSystem,
  val createFlatSignature: (C) -> FlatSignature<C>,
  val getVariableCandidates: (C) -> C?, // for variable WithInvoke
  val isFromSources: (CallableDescriptor) -> Boolean,
  val hasSAMConversion: ((C) -> Boolean)?
) /*: OverloadingConflictResolver<Any>(
  builtIns,
  module,
  specificityComparator,
  getResultingDescriptor,
  createEmptyConstraintSystem,
  createFlatSignature,
  getVariableCandidates,
  isFromSources,
  hasSAMConversion
)*/ {

}
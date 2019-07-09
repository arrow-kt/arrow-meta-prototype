package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.descriptors.CallableDescriptor
import org.jetbrains.kotlin.resolve.calls.components.SamConversionTransformer
import org.jetbrains.kotlin.types.UnwrappedType

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaSamConversionTransformer(val delegate: SamConversionTransformer) : SamConversionTransformer by delegate {
  override fun getFunctionTypeForPossibleSamType(possibleSamType: UnwrappedType): UnwrappedType? {
    println("MetaSamConversionTransformer.getFunctionTypeForPossibleSamType: $possibleSamType")
    return delegate.getFunctionTypeForPossibleSamType(possibleSamType)
  }

  override fun shouldRunSamConversionForFunction(candidate: CallableDescriptor): Boolean {
    println("MetaSamConversionTransformer.shouldRunSamConversionForFunction: $candidate")
    return delegate.shouldRunSamConversionForFunction(candidate)
  }
}
package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.types.AbstractTypeApproximator
import org.jetbrains.kotlin.types.model.SimpleTypeMarker
import org.jetbrains.kotlin.types.model.TypeSystemInferenceExtensionContext

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaAbstractTypeApproximator(ctx: TypeSystemInferenceExtensionContext) :
  AbstractTypeApproximator(ctx),
  TypeSystemInferenceExtensionContext by ctx {
  override fun createErrorType(message: String): SimpleTypeMarker {
    println("MetaAbstractTypeApproximator.createErrorType: $message¬")
    return super.createErrorType(message)
  }
  // a lot more Methods to override
}
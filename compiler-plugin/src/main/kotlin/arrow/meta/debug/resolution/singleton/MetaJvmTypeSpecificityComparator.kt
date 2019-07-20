package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.resolve.calls.results.TypeSpecificityComparator
import org.jetbrains.kotlin.types.model.KotlinTypeMarker
import org.jetbrains.kotlin.types.model.TypeSystemInferenceExtensionContextDelegate

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaJvmTypeSpecificityComparator(val ctx: TypeSystemInferenceExtensionContextDelegate, val delegate: TypeSpecificityComparator) : TypeSpecificityComparator by delegate {
  override fun isDefinitelyLessSpecific(specific: KotlinTypeMarker, general: KotlinTypeMarker): Boolean {
    println("MetaJvmTypeSpecificityComparator.isDefinitelyLessSpecific: $specific, $general")
    return delegate.isDefinitelyLessSpecific(specific, general)
  }
}
package arrow.meta.debug.unusedServices

import org.jetbrains.kotlin.serialization.deserialization.LocalClassifierTypeSettings
import org.jetbrains.kotlin.types.SimpleType

/**
 * @UnusedService Currently we need to investigate how our costume functions can be executed.
 * Meaning we need to hook into the compiler earlier than Analysis. Either with Reflection or else
 */
class MetaLocalClassifierTypeSettings(val delegate: LocalClassifierTypeSettings) : LocalClassifierTypeSettings by delegate {
  override val replacementTypeForLocalClassifiers: SimpleType?
    get() = delegate.replacementTypeForLocalClassifiers
}
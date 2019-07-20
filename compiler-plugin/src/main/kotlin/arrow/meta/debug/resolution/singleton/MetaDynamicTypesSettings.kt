package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.types.DynamicTypesSettings

/**
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 * No acces to [PlatformSpecificExtension]
 */
class MetaDynamicTypesSettings : DynamicTypesSettings() {
  override val dynamicTypesAllowed: Boolean
    get() = super.dynamicTypesAllowed
}
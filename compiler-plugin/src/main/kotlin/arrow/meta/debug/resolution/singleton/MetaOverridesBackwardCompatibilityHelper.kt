package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.descriptors.CallableMemberDescriptor
import org.jetbrains.kotlin.resolve.OverridesBackwardCompatibilityHelper

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 * check out [org.jetbrains.kotlin.resolve.jvm.JvmOverridesBackwardCompatibilityHelper]
 **/
class MetaOverridesBackwardCompatibilityHelper(val delegate: OverridesBackwardCompatibilityHelper = OverridesBackwardCompatibilityHelper.Default) : OverridesBackwardCompatibilityHelper by delegate{
  override fun overrideCanBeOmitted(overridingDescriptor: CallableMemberDescriptor): Boolean {
    println("MetaOverridesBackwardCompatibilityHelper.overrideCanBeOmitted: $overridingDescriptor")
    return delegate.overrideCanBeOmitted(overridingDescriptor)
  }
}
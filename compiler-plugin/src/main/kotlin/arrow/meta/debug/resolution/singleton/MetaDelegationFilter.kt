package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.config.LanguageVersionSettings
import org.jetbrains.kotlin.descriptors.CallableMemberDescriptor
import org.jetbrains.kotlin.resolve.lazy.DelegationFilter

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaDelegationFilter(val delegate: DelegationFilter = DelegationFilter.Default) : DelegationFilter by delegate{
  override fun filter(interfaceMember: CallableMemberDescriptor, languageVersionSettings: LanguageVersionSettings): Boolean {
    println("MetaDelegationFilter.filter: $interfaceMember, $languageVersionSettings")
    return delegate.filter(interfaceMember, languageVersionSettings)
  }
}



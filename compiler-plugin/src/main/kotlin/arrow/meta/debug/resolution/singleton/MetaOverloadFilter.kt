package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.descriptors.DeclarationDescriptorNonRoot
import org.jetbrains.kotlin.resolve.OverloadFilter

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaOverloadFilter(val delegate: OverloadFilter = OverloadFilter.Default) : OverloadFilter by delegate{
  override fun filterPackageMemberOverloads(overloads: Collection<DeclarationDescriptorNonRoot>): Collection<DeclarationDescriptorNonRoot> {
    println("MetaOverloadFilter.filterPackageMemberOverloads: $overloads")
    return delegate.filterPackageMemberOverloads(overloads)
  }
}
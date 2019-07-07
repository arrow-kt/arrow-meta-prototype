package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.descriptors.SupertypeLoopChecker
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.TypeConstructor

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaSupertypeLoopChecker(val delegate: SupertypeLoopChecker) : SupertypeLoopChecker by delegate {
  override fun findLoopsInSupertypesAndDisconnect(currentTypeConstructor: TypeConstructor, superTypes: Collection<KotlinType>, neighbors: (TypeConstructor) -> Iterable<KotlinType>, reportLoop: (KotlinType) -> Unit): Collection<KotlinType> {
    println("MetaSupertypeLoopChecker.findLoopsInSupertypesAndDisconnect: $currentTypeConstructor, $superTypes, $neighbors, $reportLoop")
    return delegate.findLoopsInSupertypesAndDisconnect(currentTypeConstructor, superTypes, neighbors, reportLoop)
  }
}
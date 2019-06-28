package arrow.meta.utils

import org.jetbrains.kotlin.descriptors.SupertypeLoopChecker
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.TypeConstructor

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaSupertypeLoopChecker : SupertypeLoopChecker {
  override fun findLoopsInSupertypesAndDisconnect(currentTypeConstructor: TypeConstructor, superTypes: Collection<KotlinType>, neighbors: (TypeConstructor) -> Iterable<KotlinType>, reportLoop: (KotlinType) -> Unit): Collection<KotlinType> {
    println("MetaSupertypeLoopChecker.findLoopsInSupertypesAndDisconnect: $currentTypeConstructor")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}
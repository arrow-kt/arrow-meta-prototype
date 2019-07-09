package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.descriptors.CallableMemberDescriptor
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.serialization.deserialization.ErrorReporter

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaErrorReporter : ErrorReporter {
  override fun reportIncompleteHierarchy(p0: ClassDescriptor, p1: MutableList<String>) {
    println("reportIncompleteHierarchy: ")
    TODO("not implemented")
  }

  override fun reportCannotInferVisibility(p0: CallableMemberDescriptor) {
    println("reportCannotInferVisibility: ")
    TODO("not implemented")
  }
}
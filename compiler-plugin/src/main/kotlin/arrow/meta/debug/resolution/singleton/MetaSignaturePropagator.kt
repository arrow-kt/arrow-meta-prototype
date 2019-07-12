package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.descriptors.CallableMemberDescriptor
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.TypeParameterDescriptor
import org.jetbrains.kotlin.descriptors.ValueParameterDescriptor
import org.jetbrains.kotlin.load.java.components.SignaturePropagator
import org.jetbrains.kotlin.load.java.structure.JavaMethod
import org.jetbrains.kotlin.types.KotlinType

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaSignaturePropagator(val delegate: SignaturePropagator) : SignaturePropagator by delegate {
  override fun resolvePropagatedSignature(p0: JavaMethod, p1: ClassDescriptor, p2: KotlinType, p3: KotlinType?, p4: MutableList<ValueParameterDescriptor>, p5: MutableList<TypeParameterDescriptor>): SignaturePropagator.PropagatedSignature {
    println("MetaSignaturePropagator.resolvePropagatedSignature: $p0, $p1, $p2, $p3, $p4, $p5")
    return delegate.resolvePropagatedSignature(p0, p1, p2, p3, p4, p5)
  }

  override fun reportSignatureErrors(p0: CallableMemberDescriptor, p1: MutableList<String>) {
    println("MetaSignaturePropagator.reportSignatureErrors: $p0, $p1")
    delegate.reportSignatureErrors(p0, p1)
  }
}
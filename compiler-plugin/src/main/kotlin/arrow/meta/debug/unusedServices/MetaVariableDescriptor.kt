package arrow.meta.debug.unusedServices

import org.jetbrains.kotlin.descriptors.CallableDescriptor
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor
import org.jetbrains.kotlin.descriptors.DeclarationDescriptorVisitor
import org.jetbrains.kotlin.descriptors.ReceiverParameterDescriptor
import org.jetbrains.kotlin.descriptors.SourceElement
import org.jetbrains.kotlin.descriptors.TypeParameterDescriptor
import org.jetbrains.kotlin.descriptors.ValueParameterDescriptor
import org.jetbrains.kotlin.descriptors.VariableDescriptor
import org.jetbrains.kotlin.descriptors.Visibility
import org.jetbrains.kotlin.descriptors.annotations.Annotations
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.resolve.constants.ConstantValue
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.TypeSubstitutor

/**
 * @UnusedService Currently we need to investigate how our costume functions can be executed.
 * Meaning we need to hook into the compiler earlier than Analysis. Either with Reflection or else
 */
class MetaVariableDescriptor(val delegate: VariableDescriptor) : VariableDescriptor by delegate {
  override fun getName(): Name {
    println("MetaVariableDescriptor.getName:")
    return delegate.name
  }

  override fun getSource(): SourceElement {
    println("MetaVariableDescriptor.getSource:")
    return delegate.source
  }

  override fun hasSynthesizedParameterNames(): Boolean {
    println("MetaVariableDescriptor.hasSynthesizedParameterNames:")
    return delegate.hasSynthesizedParameterNames()
  }

  override fun getOverriddenDescriptors(): MutableCollection<out CallableDescriptor> {
    println("MetaVariableDescriptor.getOverriddenDescriptors:")
    return delegate.overriddenDescriptors
  }

  override fun getValueParameters(): MutableList<ValueParameterDescriptor> {
    println("MetaVariableDescriptor.getValueParameters:")
    return delegate.valueParameters
  }

  override fun getCompileTimeInitializer(): ConstantValue<*>? {
    println("MetaVariableDescriptor.getCompileTimeInitializer:")
    return delegate.compileTimeInitializer
  }

  override fun getTypeParameters(): List<TypeParameterDescriptor> {
    println("MetaVariableDescriptor.getTypeParameters:")
    return delegate.typeParameters
  }

  override fun getVisibility(): Visibility {
    println("MetaVariableDescriptor.getVisibility:")
    return delegate.visibility
  }

  override fun getOriginal(): CallableDescriptor {
    println("MetaVariableDescriptor.getOriginal: ")
    return delegate.original
  }

  override fun substitute(substitutor: TypeSubstitutor): VariableDescriptor {
    println("MetaVariableDescriptor.substitute: $substitutor")
    return delegate.substitute(substitutor)
  }

  override fun hasStableParameterNames(): Boolean {
    println("MetaVariableDescriptor.hasStableParameterNames:")
    return delegate.hasStableParameterNames()
  }

  override fun getReturnType(): KotlinType? {
    println("MetaVariableDescriptor.getReturnType:")
    return delegate.returnType
  }

  override fun getType(): KotlinType {
    println("MetaVariableDescriptor.getType:")
    return delegate.type
  }

  override fun isVar(): Boolean {
    println("MetaVariableDescriptor.isVar:")
    return delegate.isVar
  }

  override fun getDispatchReceiverParameter(): ReceiverParameterDescriptor? {
    println("MetaVariableDescriptor.getDispatchReceiverParameter:")
    return delegate.dispatchReceiverParameter
  }

  override fun isConst(): Boolean {
    println("MetaVariableDescriptor.isConst:")
    return delegate.isConst
  }

  override fun <V : Any?> getUserData(p0: CallableDescriptor.UserDataKey<V>?): V? {
    println("MetaVariableDescriptor.getUserData: $p0")
    return delegate.getUserData(p0)
  }

  override fun getContainingDeclaration(): DeclarationDescriptor {
    println("MetaVariableDescriptor.getContainingDeclaration:")
    return delegate.containingDeclaration
  }

  override fun isLateInit(): Boolean {
    println("MetaVariableDescriptor.isLateInit:")
    return delegate.isLateInit
  }

  override fun getExtensionReceiverParameter(): ReceiverParameterDescriptor? {
    println("MetaVariableDescriptor.getExtensionReceiverParameter:")
    return delegate.extensionReceiverParameter
  }

  override fun <R : Any?, D : Any?> accept(p0: DeclarationDescriptorVisitor<R, D>?, p1: D): R {
    println("MetaVariableDescriptor.accept: $p0, $p1")
    return delegate.accept(p0, p1)
  }

  override fun acceptVoid(p0: DeclarationDescriptorVisitor<Void, Void>?) {
    println("MetaVariableDescriptor.acceptVoid: $p0")
    return delegate.acceptVoid(p0)
  }

  override val annotations: Annotations
    get() = delegate.annotations
}
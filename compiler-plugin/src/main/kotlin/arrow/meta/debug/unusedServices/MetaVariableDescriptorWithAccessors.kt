package arrow.meta.debug.unusedServices

import org.jetbrains.kotlin.descriptors.CallableDescriptor
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor
import org.jetbrains.kotlin.descriptors.DeclarationDescriptorVisitor
import org.jetbrains.kotlin.descriptors.ReceiverParameterDescriptor
import org.jetbrains.kotlin.descriptors.SourceElement
import org.jetbrains.kotlin.descriptors.TypeParameterDescriptor
import org.jetbrains.kotlin.descriptors.ValueParameterDescriptor
import org.jetbrains.kotlin.descriptors.VariableAccessorDescriptor
import org.jetbrains.kotlin.descriptors.VariableDescriptor
import org.jetbrains.kotlin.descriptors.VariableDescriptorWithAccessors
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
class MetaVariableDescriptorWithAccessors(val delegate: VariableDescriptorWithAccessors) : VariableDescriptorWithAccessors by delegate {
  override val annotations: Annotations
    get() = delegate.annotations
  override val getter: VariableAccessorDescriptor?
    get() = delegate.getter
  override val isDelegated: Boolean
    get() = delegate.isDelegated
  override val setter: VariableAccessorDescriptor?
    get() = delegate.setter

  override fun <R : Any?, D : Any?> accept(p0: DeclarationDescriptorVisitor<R, D>?, p1: D): R {
    println("MetaVariableDescriptorWithAccessors.accept: $p0, $p1")
    return delegate.accept(p0, p1)
  }

  override fun acceptVoid(p0: DeclarationDescriptorVisitor<Void, Void>?) {
    println("MetaVariableDescriptorWithAccessors.acceptVoid: $p0")
    return delegate.acceptVoid(p0)
  }

  override fun getCompileTimeInitializer(): ConstantValue<*>? {
    println("MetaVariableDescriptorWithAccessors.getCompileTimeInitializer:")
    return delegate.compileTimeInitializer
  }

  override fun getContainingDeclaration(): DeclarationDescriptor {
    println("MetaVariableDescriptorWithAccessors.getContainingDeclaration:")
    return delegate.containingDeclaration
  }

  override fun getDispatchReceiverParameter(): ReceiverParameterDescriptor? {
    println("MetaVariableDescriptorWithAccessors.getDispatchReceiverParameter:")
    return delegate.dispatchReceiverParameter
  }

  override fun getExtensionReceiverParameter(): ReceiverParameterDescriptor? {
    println("MetaVariableDescriptorWithAccessors.getExtensionReceiverParameter:")
    return delegate.extensionReceiverParameter
  }

  override fun getName(): Name {
    println("MetaVariableDescriptorWithAccessors.getName:")
    return delegate.name
  }

  override fun getOriginal(): CallableDescriptor {
    println("MetaVariableDescriptorWithAccessors.getOriginal:")
    return delegate.original
  }

  override fun getOverriddenDescriptors(): MutableCollection<out CallableDescriptor> {
    println("MetaVariableDescriptorWithAccessors.getOverriddenDescriptors:")
    return delegate.overriddenDescriptors
  }

  override fun getReturnType(): KotlinType? {
    println("MetaVariableDescriptorWithAccessors.getReturnType:")
    return delegate.returnType
  }

  override fun getSource(): SourceElement {
    println("MetaVariableDescriptorWithAccessors.getSource:")
    return delegate.source
  }

  override fun getType(): KotlinType {
    println("MetaVariableDescriptorWithAccessors.getType:")
    return delegate.type
  }

  override fun getTypeParameters(): List<TypeParameterDescriptor> {
    println("MetaVariableDescriptorWithAccessors.getTypeParameters:")
    return delegate.typeParameters
  }

  override fun <V : Any?> getUserData(p0: CallableDescriptor.UserDataKey<V>?): V? {
    println("MetaVariableDescriptorWithAccessors.getUserData: $p0")
    return delegate.getUserData(p0)
  }

  override fun getValueParameters(): MutableList<ValueParameterDescriptor> {
    println("MetaVariableDescriptorWithAccessors.getValueParameters:")
    return delegate.valueParameters
  }

  override fun getVisibility(): Visibility {
    println("MetaVariableDescriptorWithAccessors.getVisibility:")
    return delegate.visibility
  }

  override fun hasStableParameterNames(): Boolean {
    println("MetaVariableDescriptorWithAccessors.hasStableParameterNames:")
    return delegate.hasStableParameterNames()
  }

  override fun hasSynthesizedParameterNames(): Boolean {
    println("MetaVariableDescriptorWithAccessors.hasSynthesizedParameterNames:")
    return delegate.hasSynthesizedParameterNames()
  }

  override fun isConst(): Boolean {
    println("MetaVariableDescriptorWithAccessors.isConst:")
    return delegate.isConst
  }

  override fun isLateInit(): Boolean {
    println("MetaVariableDescriptorWithAccessors.isLateInit:")
    return delegate.isLateInit
  }

  override fun isVar(): Boolean {
    println("MetaVariableDescriptorWithAccessors.isVar:")
    return delegate.isVar
  }

  override fun substitute(substitutor: TypeSubstitutor): VariableDescriptor {
    println("MetaVariableDescriptorWithAccessors.substitute: $substitutor")
    return delegate.substitute(substitutor)
  }
}
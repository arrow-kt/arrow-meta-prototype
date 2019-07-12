package arrow.meta.debug.unusedServices

import org.jetbrains.kotlin.builtins.KotlinBuiltIns
import org.jetbrains.kotlin.descriptors.ClassifierDescriptor
import org.jetbrains.kotlin.descriptors.TypeParameterDescriptor
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.TypeConstructor

/**
 * @UnusedService Currently we need to investigate how our costume functions can be executed.
 * Meaning we need to hook into the compiler earlier than Analysis. Either with Reflection or else.
 * checkout [FunctionPlaceholderTypeConstructor]
 */
class MetaTypeConstructor(val delegate: TypeConstructor) : TypeConstructor by delegate {
  override fun isFinal(): Boolean {
    println("MetaTypeConstructor.isFinal:")
    return delegate.isFinal
  }

  override fun getBuiltIns(): KotlinBuiltIns {
    println("MetaTypeConstructor.getBuiltIns: ")
    return delegate.builtIns
  }

  override fun getParameters(): List<TypeParameterDescriptor> {
    println("MetaTypeConstructor.getParameters:")
    return delegate.parameters
  }

  override fun isDenotable(): Boolean {
    println("MetaTypeConstructor.isDenotable:")
    return delegate.isDenotable
  }

  override fun getSupertypes(): Collection<KotlinType> {
    println("MetaTypeConstructor.getSupertypes:")
    return delegate.supertypes
  }

  override fun getDeclarationDescriptor(): ClassifierDescriptor? {
    println("MetaTypeConstructor.getDeclarationDescriptor:")
    return delegate.declarationDescriptor
  }
}
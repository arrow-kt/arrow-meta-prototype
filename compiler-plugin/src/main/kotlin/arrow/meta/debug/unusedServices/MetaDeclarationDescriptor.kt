package arrow.meta.debug.unusedServices

import org.jetbrains.kotlin.descriptors.DeclarationDescriptor
import org.jetbrains.kotlin.descriptors.DeclarationDescriptorVisitor
import org.jetbrains.kotlin.descriptors.annotations.Annotations
import org.jetbrains.kotlin.name.Name

/**
 * @UnusedService Currently we need to investigate how our costume functions can be executed.
 * Meaning we need to hook into the compiler earlier than Analysis. Either with Reflection or else
 */
class MetaDeclarationDescriptor(val delegate: DeclarationDescriptor) : DeclarationDescriptor by delegate {
  override fun getContainingDeclaration(): DeclarationDescriptor? {
    println("MetaDeclarationDescriptor.getContainingDeclaration:")
    return delegate.containingDeclaration
  }

  override fun getOriginal(): DeclarationDescriptor {
    println("MetaDeclarationDescriptor.getOriginal:")
    return delegate.original
  }

  override fun getName(): Name {
    println("MetaDeclarationDescriptor.getName:")
    return delegate.name
  }

  override fun <R : Any?, D : Any?> accept(p0: DeclarationDescriptorVisitor<R, D>?, p1: D): R {
    println("MetaDeclarationDescriptor.accept: $p0, $p1")
    return delegate.accept(p0, p1)
  }

  override fun acceptVoid(p0: DeclarationDescriptorVisitor<Void, Void>?) {
    println("MetaDeclarationDescriptor.acceptVoid: $p0")
    return delegate.acceptVoid(p0)
  }

  override val annotations: Annotations
    get() = delegate.annotations
}
package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.descriptors.DeclarationDescriptor
import org.jetbrains.kotlin.psi.KtDeclaration
import org.jetbrains.kotlin.resolve.lazy.LocalDescriptorResolver

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 * [org.jetbrains.kotlin.resolve.lazy.CompilerLocalDescriptorResolver]
 **/
class MetaLocalDescriptorResolver(val delegate: LocalDescriptorResolver): LocalDescriptorResolver by delegate {
  override fun resolveLocalDeclaration(declaration: KtDeclaration): DeclarationDescriptor {
    println("MetaLocalDescriptorResolver.resolveLocalDeclaration: $declaration")
    return delegate.resolveLocalDeclaration(declaration)
  }
}
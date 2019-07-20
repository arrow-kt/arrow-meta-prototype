package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.descriptors.DeclarationDescriptor
import org.jetbrains.kotlin.psi.KtDeclaration
import org.jetbrains.kotlin.resolve.lazy.AbsentDescriptorHandler

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaAbsentDescriptorHandler(val delegate: AbsentDescriptorHandler) : AbsentDescriptorHandler by delegate {
  override fun diagnoseDescriptorNotFound(declaration: KtDeclaration): DeclarationDescriptor {
    println("MetaAbsentDescriptorHandler.diagnoseDescriptorNotFound: $declaration")
    return delegate.diagnoseDescriptorNotFound(declaration)
  }
}
package arrow.meta.extensions

import org.jetbrains.kotlin.container.StorageComponentContainer
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor
import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.extensions.StorageComponentContainerContributor
import org.jetbrains.kotlin.platform.TargetPlatform
import org.jetbrains.kotlin.psi.KtDeclaration
import org.jetbrains.kotlin.resolve.checkers.DeclarationChecker
import org.jetbrains.kotlin.resolve.checkers.DeclarationCheckerContext

class DelegatingContributorChecker(val phase: ExtensionPhase.StorageComponentContainer, val ctx: CompilerContext) : StorageComponentContainerContributor, DeclarationChecker {

  override fun registerModuleComponents(container: StorageComponentContainer, platform: TargetPlatform, moduleDescriptor: ModuleDescriptor) {
    phase.run { ctx.registerModuleComponents(container, moduleDescriptor) }
  }

  override fun check(declaration: KtDeclaration, descriptor: DeclarationDescriptor, context: DeclarationCheckerContext) {
    phase.run { ctx.check(declaration, descriptor, context) }
  }
}

class MetaIDEDelegatingContributorChecker : StorageComponentContainerContributor {
  override fun registerModuleComponents(
    container: StorageComponentContainer,
    platform: TargetPlatform,
    moduleDescriptor: ModuleDescriptor
  ) {
    //This is invoked during runIde: println("MetaIDEDelegatingContributorChecker Is Invoked"), we can't use DelegatingContributorChecker, because intelliJ bails because it cant resolve the dependencies in the constructor
  }
}
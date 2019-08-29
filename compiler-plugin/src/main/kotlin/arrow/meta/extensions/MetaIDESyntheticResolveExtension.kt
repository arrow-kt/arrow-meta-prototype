package arrow.meta.extensions

import org.jetbrains.kotlin.com.intellij.mock.MockProject
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.PackageFragmentDescriptor
import org.jetbrains.kotlin.descriptors.PropertyDescriptor
import org.jetbrains.kotlin.descriptors.SimpleFunctionDescriptor
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.extensions.SyntheticResolveExtension
import org.jetbrains.kotlin.resolve.lazy.LazyClassContext
import org.jetbrains.kotlin.resolve.lazy.declarations.ClassMemberDeclarationProvider
import org.jetbrains.kotlin.resolve.lazy.declarations.PackageMemberDeclarationProvider
import org.jetbrains.kotlin.types.KotlinType
import java.util.*

class MetaIDESyntheticResolveExtension(
  val project: MockProject,
  val phase: ExtensionPhase.SyntheticResolver,
  val compilerContext: CompilerContext
) : SyntheticResolveExtension {
  override fun addSyntheticSupertypes(thisDescriptor: ClassDescriptor, supertypes: MutableList<KotlinType>) {
    phase.run { compilerContext.addSyntheticSupertypes(thisDescriptor, supertypes) }
  }

  override fun generateSyntheticClasses(
    thisDescriptor: ClassDescriptor,
    name: Name,
    ctx: LazyClassContext,
    declarationProvider: ClassMemberDeclarationProvider,
    result: MutableSet<ClassDescriptor>
  ) {
    phase.run {
      compilerContext.generateSyntheticClasses(
        thisDescriptor,
        name,
        ctx,
        declarationProvider,
        result
      )
    }
  }

  override fun generateSyntheticClasses(
    thisDescriptor: PackageFragmentDescriptor,
    name: Name,
    ctx: LazyClassContext,
    declarationProvider: PackageMemberDeclarationProvider,
    result: MutableSet<ClassDescriptor>
  ) {
    phase.run {
      compilerContext.generatePackageSyntheticClasses(
        thisDescriptor,
        name,
        ctx,
        declarationProvider,
        result
      )
    }
  }

  override fun generateSyntheticMethods(
    thisDescriptor: ClassDescriptor,
    name: Name,
    bindingContext: BindingContext,
    fromSupertypes: List<SimpleFunctionDescriptor>,
    result: MutableCollection<SimpleFunctionDescriptor>
  ) {
    phase.run {
      compilerContext.generateSyntheticMethods(
        thisDescriptor,
        name,
        bindingContext,
        fromSupertypes,
        result
      )
    }
  }

  override fun generateSyntheticProperties(
    thisDescriptor: ClassDescriptor,
    name: Name,
    bindingContext: BindingContext,
    fromSupertypes: ArrayList<PropertyDescriptor>,
    result: MutableSet<PropertyDescriptor>
  ) {
    phase.run {
      compilerContext.generateSyntheticProperties(
        thisDescriptor,
        name,
        bindingContext,
        fromSupertypes,
        result
      )
    }
  }

  override fun getSyntheticCompanionObjectNameIfNeeded(thisDescriptor: ClassDescriptor): Name? {
    return phase.run { compilerContext.getSyntheticCompanionObjectNameIfNeeded(thisDescriptor) }
  }

  override fun getSyntheticFunctionNames(thisDescriptor: ClassDescriptor): List<Name> {
    return phase.run { compilerContext.getSyntheticFunctionNames(thisDescriptor) }
  }

  override fun getSyntheticNestedClassNames(thisDescriptor: ClassDescriptor): List<Name> {
    return phase.run { compilerContext.getSyntheticNestedClassNames(thisDescriptor) }
  }
}
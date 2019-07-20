package arrow.meta.debug.unusedServices

import org.jetbrains.kotlin.descriptors.TypeAliasDescriptor
import org.jetbrains.kotlin.descriptors.TypeParameterDescriptor
import org.jetbrains.kotlin.descriptors.annotations.AnnotationDescriptor
import org.jetbrains.kotlin.resolve.TypeAliasExpansionReportStrategy
import org.jetbrains.kotlin.types.KotlinType

/**
 * @UnusedService Currently we need to investigate how our costume functions can be executed.
 * Meaning we need to hook into the compiler earlier than Analysis. Either with Reflection or else
 */
class MetaTypeAliasExpansionReportStrategy(val delegate: TypeAliasExpansionReportStrategy) : TypeAliasExpansionReportStrategy by delegate {
  override fun boundsViolationInSubstitution(bound: KotlinType, unsubstitutedArgument: KotlinType, argument: KotlinType, typeParameter: TypeParameterDescriptor) {
    println("MetaTypeAliasExpansionReportStrategy.boundsViolationInSubstitution: $bound, $unsubstitutedArgument, $argument, $typeParameter")
    delegate.boundsViolationInSubstitution(bound, unsubstitutedArgument, argument, typeParameter)
  }

  override fun conflictingProjection(typeAlias: TypeAliasDescriptor, typeParameter: TypeParameterDescriptor?, substitutedArgument: KotlinType) {
    println("MetaTypeAliasExpansionReportStrategy.conflictingProjection: $typeAlias, $typeParameter, $substitutedArgument")
    delegate.conflictingProjection(typeAlias, typeParameter, substitutedArgument)
  }

  override fun recursiveTypeAlias(typeAlias: TypeAliasDescriptor) {
    println("MetaTypeAliasExpansionReportStrategy.recursiveTypeAlias: $typeAlias")
    delegate.recursiveTypeAlias(typeAlias)
  }

  override fun repeatedAnnotation(annotation: AnnotationDescriptor) {
    println("MetaTypeAliasExpansionReportStrategy.repeatedAnnotation: $annotation")
    delegate.repeatedAnnotation(annotation)
  }

  override fun wrongNumberOfTypeArguments(typeAlias: TypeAliasDescriptor, numberOfParameters: Int) {
    println("MetaTypeAliasExpansionReportStrategy.wrongNumberOfTypeArguments: $typeAlias, $numberOfParameters")
    delegate.wrongNumberOfTypeArguments(typeAlias, numberOfParameters)
  }
}
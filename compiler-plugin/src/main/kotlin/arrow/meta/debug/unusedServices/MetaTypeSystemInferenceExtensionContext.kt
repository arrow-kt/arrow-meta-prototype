package arrow.meta.debug.unusedServices

import org.jetbrains.kotlin.types.AbstractTypeCheckerContext
import org.jetbrains.kotlin.types.model.CaptureStatus
import org.jetbrains.kotlin.types.model.CapturedTypeMarker
import org.jetbrains.kotlin.types.model.KotlinTypeMarker
import org.jetbrains.kotlin.types.model.SimpleTypeMarker
import org.jetbrains.kotlin.types.model.StubTypeMarker
import org.jetbrains.kotlin.types.model.TypeArgumentMarker
import org.jetbrains.kotlin.types.model.TypeConstructorMarker
import org.jetbrains.kotlin.types.model.TypeParameterMarker
import org.jetbrains.kotlin.types.model.TypeSubstitutorMarker
import org.jetbrains.kotlin.types.model.TypeSystemInferenceExtensionContext
import org.jetbrains.kotlin.types.model.TypeVariableMarker
import org.jetbrains.kotlin.types.model.TypeVariance

/**
 * @UnusedService Currently we need to investigate how our costume functions can be executed.
 * Meaning we need to hook into the compiler earlier than Analysis. Either with Reflection or else
 * @follow [AbstractTypeApproximator] for more intel on hijacking or [org.jetbrains.kotlin.resolve.calls.inference.components.TrivialConstraintTypeInferenceOracle]
 */
class MetaTypeSystemInferenceExtensionContext(val delegate: TypeSystemInferenceExtensionContext) : TypeSystemInferenceExtensionContext by delegate {
  override fun anyType(): SimpleTypeMarker {
    println("MetaTypeSystemInferenceExtensionContext.anyType")
    return delegate.anyType()
  }

  override fun captureFromArguments(type: SimpleTypeMarker, status: CaptureStatus): SimpleTypeMarker? {
    println("MetaTypeSystemInferenceExtensionContext.captureFromArguments: $type, $status")
    return delegate.captureFromArguments(type, status)
  }

  override fun captureFromExpression(type: KotlinTypeMarker): KotlinTypeMarker? {
    println("MetaTypeSystemInferenceExtensionContext.captureFromExpression: $type")
    return delegate.captureFromExpression(type)
  }

  override fun createCapturedType(constructorProjection: TypeArgumentMarker, constructorSupertypes: List<KotlinTypeMarker>, lowerType: KotlinTypeMarker?, captureStatus: CaptureStatus): CapturedTypeMarker {
    println("MetaTypeSystemInferenceExtensionContext.createCapturedType: $constructorProjection, $constructorSupertypes, $lowerType, $captureStatus")
    return delegate.createCapturedType(constructorProjection, constructorSupertypes, lowerType, captureStatus)
  }

  override fun createErrorTypeWithCustomConstructor(debugName: String, constructor: TypeConstructorMarker): KotlinTypeMarker {
    println("MetaTypeSystemInferenceExtensionContext.createErrorTypeWithCustomConstructor: $debugName, $constructor")
    return delegate.createErrorTypeWithCustomConstructor(debugName, constructor)
  }

  override fun createFlexibleType(lowerBound: SimpleTypeMarker, upperBound: SimpleTypeMarker): KotlinTypeMarker {
    println("MetaTypeSystemInferenceExtensionContext.createFlexibleType: $lowerBound, $upperBound")
    return delegate.createFlexibleType(lowerBound, upperBound)
  }

  override fun createSimpleType(constructor: TypeConstructorMarker, arguments: List<TypeArgumentMarker>, nullable: Boolean): SimpleTypeMarker {
    println("MetaTypeSystemInferenceExtensionContext.createSimpleType: $constructor, $arguments, $nullable")
    return delegate.createSimpleType(constructor, arguments, nullable)
  }

  override fun createStarProjection(typeParameter: TypeParameterMarker): TypeArgumentMarker {
    println("MetaTypeSystemInferenceExtensionContext.createStarProjection: $typeParameter")
    return delegate.createStarProjection(typeParameter)
  }

  override fun createStubType(typeVariable: TypeVariableMarker): StubTypeMarker {
    println("MetaTypeSystemInferenceExtensionContext.createStubType: $typeVariable")
    return delegate.createStubType(typeVariable)
  }

  override fun createTypeArgument(type: KotlinTypeMarker, variance: TypeVariance): TypeArgumentMarker {
    println("MetaTypeSystemInferenceExtensionContext.createTypeArgument: $type, $variance")
    return delegate.createTypeArgument(type, variance)
  }

  override fun findCommonIntegerLiteralTypesSuperType(explicitSupertypes: List<SimpleTypeMarker>): SimpleTypeMarker? {
    println("MetaTypeSystemInferenceExtensionContext.findCommonIntegerLiteralTypesSuperType: $explicitSupertypes")
    return delegate.findCommonIntegerLiteralTypesSuperType(explicitSupertypes)
  }

  override fun intersectTypes(types: List<KotlinTypeMarker>): KotlinTypeMarker {
    println("MetaTypeSystemInferenceExtensionContext.intersectTypes: $types")
    return delegate.intersectTypes(types)
  }

  override fun intersectTypes(types: List<SimpleTypeMarker>): SimpleTypeMarker {
    println("MetaTypeSystemInferenceExtensionContext.intersectTypes: $types")
    return delegate.intersectTypes(types)
  }

  override fun isEqualTypeConstructors(c1: TypeConstructorMarker, c2: TypeConstructorMarker): Boolean {
    println("MetaTypeSystemInferenceExtensionContext.isEqualTypeConstructors: $c1, $c2")
    return delegate.isEqualTypeConstructors(c1, c2)
  }

  override fun newBaseTypeCheckerContext(errorTypesEqualToAnything: Boolean): AbstractTypeCheckerContext {
    println("MetaTypeSystemInferenceExtensionContext.newBaseTypeCheckerContext: $errorTypesEqualToAnything")
    return delegate.newBaseTypeCheckerContext(errorTypesEqualToAnything)
  }

  override fun nothingType(): SimpleTypeMarker {
    println("MetaTypeSystemInferenceExtensionContext.nothingType")
    return delegate.nothingType()
  }

  override fun nullableAnyType(): SimpleTypeMarker {
    println("MetaTypeSystemInferenceExtensionContext.nullableAnyType")
    return delegate.nullableAnyType()
  }

  override fun nullableNothingType(): SimpleTypeMarker {
    println("MetaTypeSystemInferenceExtensionContext.nullableNothingType")
    return delegate.nullableNothingType()
  }

  override fun prepareType(type: KotlinTypeMarker): KotlinTypeMarker {
    println("MetaTypeSystemInferenceExtensionContext.prepareType: $type")
    return delegate.prepareType(type)
  }

  override fun typeSubstitutorByTypeConstructor(map: Map<TypeConstructorMarker, KotlinTypeMarker>): TypeSubstitutorMarker {
    println("MetaTypeSystemInferenceExtensionContext.typeSubstitutorByTypeConstructor: $map")
    return delegate.typeSubstitutorByTypeConstructor(map)
  }
}
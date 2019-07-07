package arrow.meta.debug.resolution.singleton

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
import org.jetbrains.kotlin.types.model.TypeSystemInferenceExtensionContextDelegate
import org.jetbrains.kotlin.types.model.TypeVariableMarker
import org.jetbrains.kotlin.types.model.TypeVariance

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaTypeSystemInferenceExtensionContextDelegate(val delegate: TypeSystemInferenceExtensionContextDelegate) : TypeSystemInferenceExtensionContextDelegate by delegate {
  override fun anyType(): SimpleTypeMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.anyType")
    return delegate.anyType()
  }

  override fun captureFromArguments(type: SimpleTypeMarker, status: CaptureStatus): SimpleTypeMarker? {
    println("MetaTypeSystemInferenceExtensionContextDelegate.captureFromArguments: $type, $status")
    return delegate.captureFromArguments(type, status)
  }

  override fun captureFromExpression(type: KotlinTypeMarker): KotlinTypeMarker? {
    println("MetaTypeSystemInferenceExtensionContextDelegate.captureFromExpression: $type")
    return delegate.captureFromExpression(type)
  }

  override fun createCapturedType(constructorProjection: TypeArgumentMarker, constructorSupertypes: List<KotlinTypeMarker>, lowerType: KotlinTypeMarker?, captureStatus: CaptureStatus): CapturedTypeMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.createCapturedType: $constructorProjection, $constructorSupertypes,$lowerType, $captureStatus")
    return delegate.createCapturedType(constructorProjection, constructorSupertypes, lowerType, captureStatus)
  }

  override fun createErrorTypeWithCustomConstructor(debugName: String, constructor: TypeConstructorMarker): KotlinTypeMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.createErrorTypeWithCustomConstructor: $debugName, $constructor")
    return delegate.createErrorTypeWithCustomConstructor(debugName, constructor)
  }

  override fun createFlexibleType(lowerBound: SimpleTypeMarker, upperBound: SimpleTypeMarker): KotlinTypeMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.createFlexibleType: $lowerBound, $upperBound")
    return delegate.createFlexibleType(lowerBound, upperBound)
  }

  override fun createSimpleType(constructor: TypeConstructorMarker, arguments: List<TypeArgumentMarker>, nullable: Boolean): SimpleTypeMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.createSimpleType: $constructor, $arguments, $nullable")
    return delegate.createSimpleType(constructor, arguments, nullable)
  }

  override fun createStarProjection(typeParameter: TypeParameterMarker): TypeArgumentMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.createStarProjection: $typeParameter")
    return delegate.createStarProjection(typeParameter)
  }

  override fun createStubType(typeVariable: TypeVariableMarker): StubTypeMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.createStarProjection: $typeVariable")
    return delegate.createStubType(typeVariable)
  }

  override fun createTypeArgument(type: KotlinTypeMarker, variance: TypeVariance): TypeArgumentMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.createTypeArgument: $type, $variance")
    return delegate.createTypeArgument(type, variance)
  }

  override fun findCommonIntegerLiteralTypesSuperType(explicitSupertypes: List<SimpleTypeMarker>): SimpleTypeMarker? {
    println("MetaTypeSystemInferenceExtensionContextDelegate.findCommonIntegerLiteralTypesSuperType: $explicitSupertypes")
    return delegate.findCommonIntegerLiteralTypesSuperType(explicitSupertypes)
  }

  override fun intersectTypes(types: List<KotlinTypeMarker>): KotlinTypeMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.intersectTypes: $types")
    return delegate.intersectTypes(types)
  }

  override fun intersectTypes(types: List<SimpleTypeMarker>): SimpleTypeMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.intersectTypes: $types")
    return delegate.intersectTypes(types)
  }

  override fun isEqualTypeConstructors(c1: TypeConstructorMarker, c2: TypeConstructorMarker): Boolean {
    println("MetaTypeSystemInferenceExtensionContextDelegate.isEqualTypeConstructors: $c1 $c2")
    return delegate.isEqualTypeConstructors(c1, c2)
  }

  override fun newBaseTypeCheckerContext(errorTypesEqualToAnything: Boolean): AbstractTypeCheckerContext {
    println("MetaTypeSystemInferenceExtensionContextDelegate.newBaseTypeCheckerContext: $errorTypesEqualToAnything")
    return delegate.newBaseTypeCheckerContext(errorTypesEqualToAnything)
  }

  override fun nothingType(): SimpleTypeMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.nothingType:")
    return delegate.nothingType()
  }

  override fun nullableAnyType(): SimpleTypeMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.nullableAnyType:")
    return delegate.nullableAnyType()
  }

  override fun nullableNothingType(): SimpleTypeMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.nullableNothingType:")
    return delegate.nullableNothingType()
  }

  override fun prepareType(type: KotlinTypeMarker): KotlinTypeMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.prepareType: $type")
    return delegate.prepareType(type)
  }

  override fun typeSubstitutorByTypeConstructor(map: Map<TypeConstructorMarker, KotlinTypeMarker>): TypeSubstitutorMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.typeSubstitutorByTypeConstructor: $map")
    return delegate.typeSubstitutorByTypeConstructor(map)
  }
}
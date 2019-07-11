package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.builtins.KotlinBuiltIns
import org.jetbrains.kotlin.resolve.calls.components.BuiltInsProvider
import org.jetbrains.kotlin.types.AbstractTypeCheckerContext
import org.jetbrains.kotlin.types.checker.ClassicTypeSystemContext
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
class MetaClassicTypeSystemContextForCS(override val builtIns: KotlinBuiltIns) : TypeSystemInferenceExtensionContextDelegate,
  ClassicTypeSystemContext,
  BuiltInsProvider {
  override fun anyType(): SimpleTypeMarker {
    println("MetaClassicTypeSystemContextForCS.anyType:")
    return super.anyType()
  }

  override fun captureFromArguments(type: SimpleTypeMarker, status: CaptureStatus): SimpleTypeMarker? {
    println("MetaClassicTypeSystemContextForCS.captureFromArguments: $type, $status")
    return super.captureFromArguments(type, status)
  }

  override fun captureFromExpression(type: KotlinTypeMarker): KotlinTypeMarker? {
    println("MetaClassicTypeSystemContextForCS.captureFromExpression: $type")
    return super.captureFromExpression(type)
  }

  override fun createCapturedType(constructorProjection: TypeArgumentMarker, constructorSupertypes: List<KotlinTypeMarker>, lowerType: KotlinTypeMarker?, captureStatus: CaptureStatus): CapturedTypeMarker {
    println("MetaClassicTypeSystemContextForCS.createCapturedType: $constructorProjection, $constructorSupertypes, $lowerType, $captureStatus")
    return super.createCapturedType(constructorProjection, constructorSupertypes, lowerType, captureStatus)
  }

  override fun createErrorTypeWithCustomConstructor(debugName: String, constructor: TypeConstructorMarker): KotlinTypeMarker {
    println("MetaClassicTypeSystemContextForCS.createErrorTypeWithCustomConstructor: $debugName, $constructor")
    return super.createErrorTypeWithCustomConstructor(debugName, constructor)
  }

  override fun createFlexibleType(lowerBound: SimpleTypeMarker, upperBound: SimpleTypeMarker): KotlinTypeMarker {
    println("MetaClassicTypeSystemContextForCS.createFlexibleType: $lowerBound, $upperBound")
    return super.createFlexibleType(lowerBound, upperBound)
  }

  override fun createSimpleType(constructor: TypeConstructorMarker, arguments: List<TypeArgumentMarker>, nullable: Boolean): SimpleTypeMarker {
    println("MetaClassicTypeSystemContextForCS.createSimpleType: $constructor, $arguments, $nullable")
    return super.createSimpleType(constructor, arguments, nullable)
  }

  override fun createStarProjection(typeParameter: TypeParameterMarker): TypeArgumentMarker {
    println("MetaClassicTypeSystemContextForCS.createStarProjection: $typeParameter")
    return super.createStarProjection(typeParameter)
  }

  override fun createStubType(typeVariable: TypeVariableMarker): StubTypeMarker {
    println("MetaClassicTypeSystemContextForCS.createStubType: $typeVariable")
    return super.createStubType(typeVariable)
  }

  override fun createTypeArgument(type: KotlinTypeMarker, variance: TypeVariance): TypeArgumentMarker {
    println("MetaClassicTypeSystemContextForCS.createTypeArgument: $type, $variance")
    return super.createTypeArgument(type, variance)
  }

  override fun findCommonIntegerLiteralTypesSuperType(explicitSupertypes: List<SimpleTypeMarker>): SimpleTypeMarker? {
    println("MetaClassicTypeSystemContextForCS.findCommonIntegerLiteralTypesSuperType: $explicitSupertypes")
    return super.findCommonIntegerLiteralTypesSuperType(explicitSupertypes)
  }

  override fun identicalArguments(a: SimpleTypeMarker, b: SimpleTypeMarker): Boolean {
    println("MetaClassicTypeSystemContextForCS.identicalArguments: $a, $b")
    return super<ClassicTypeSystemContext>.identicalArguments(a, b)
  }

  override fun intersectTypes(types: List<KotlinTypeMarker>): KotlinTypeMarker {
    println("MetaClassicTypeSystemContextForCS.intersectTypes: $types")
    return super.intersectTypes(types)
  }

  override fun intersectTypes(types: List<SimpleTypeMarker>): SimpleTypeMarker {
    println("MetaClassicTypeSystemContextForCS.intersectTypes: $types")
    return super.intersectTypes(types)
  }

  override fun isEqualTypeConstructors(c1: TypeConstructorMarker, c2: TypeConstructorMarker): Boolean {
    println("MetaClassicTypeSystemContextForCS.isEqualTypeConstructors: $c1, $c2")
    return super.isEqualTypeConstructors(c1, c2)
  }

  override fun newBaseTypeCheckerContext(errorTypesEqualToAnything: Boolean): AbstractTypeCheckerContext {
    println("MetaClassicTypeSystemContextForCS.newBaseTypeCheckerContext: $errorTypesEqualToAnything")
    return super.newBaseTypeCheckerContext(errorTypesEqualToAnything)
  }

  override fun nothingType(): SimpleTypeMarker {
    println("MetaClassicTypeSystemContextForCS.nothingType:")
    return super.nothingType()
  }

  override fun nullableAnyType(): SimpleTypeMarker {
    println("MetaClassicTypeSystemContextForCS.nullableAnyType:")
    return super.nullableAnyType()
  }

  override fun nullableNothingType(): SimpleTypeMarker {
    println("MetaClassicTypeSystemContextForCS.nullableNothingType:")
    return super.nullableNothingType()
  }

  override fun prepareType(type: KotlinTypeMarker): KotlinTypeMarker {
    println("MetaClassicTypeSystemContextForCS.prepareType: $type")
    return super.prepareType(type)
  }

  override fun typeSubstitutorByTypeConstructor(map: Map<TypeConstructorMarker, KotlinTypeMarker>): TypeSubstitutorMarker {
    println("MetaClassicTypeSystemContextForCS.typeSubstitutorByTypeConstructor: $map")
    return super.typeSubstitutorByTypeConstructor(map)
  }
}
package arrow.meta.utils

import org.jetbrains.kotlin.types.AbstractTypeCheckerContext
import org.jetbrains.kotlin.types.model.CaptureStatus
import org.jetbrains.kotlin.types.model.CapturedTypeMarker
import org.jetbrains.kotlin.types.model.DefinitelyNotNullTypeMarker
import org.jetbrains.kotlin.types.model.DynamicTypeMarker
import org.jetbrains.kotlin.types.model.FlexibleTypeMarker
import org.jetbrains.kotlin.types.model.KotlinTypeMarker
import org.jetbrains.kotlin.types.model.RawTypeMarker
import org.jetbrains.kotlin.types.model.SimpleTypeMarker
import org.jetbrains.kotlin.types.model.StubTypeMarker
import org.jetbrains.kotlin.types.model.TypeArgumentListMarker
import org.jetbrains.kotlin.types.model.TypeArgumentMarker
import org.jetbrains.kotlin.types.model.TypeConstructorMarker
import org.jetbrains.kotlin.types.model.TypeParameterMarker
import org.jetbrains.kotlin.types.model.TypeSubstitutorMarker
import org.jetbrains.kotlin.types.model.TypeSystemInferenceExtensionContext
import org.jetbrains.kotlin.types.model.TypeVariableMarker
import org.jetbrains.kotlin.types.model.TypeVariance

class MetaTypeSystemInferenceExtensionContext : TypeSystemInferenceExtensionContext {
  override fun anyType(): SimpleTypeMarker {
    println("MetaTypeSystemInferenceExtensionContext.anyType")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun captureFromArguments(type: SimpleTypeMarker, status: CaptureStatus): SimpleTypeMarker? {
    println("MetaTypeSystemInferenceExtensionContext.captureFromArguments: $type")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun captureFromExpression(type: KotlinTypeMarker): KotlinTypeMarker? {
    println("MetaTypeSystemInferenceExtensionContext.captureFromExpression: $type")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun createCapturedType(constructorProjection: TypeArgumentMarker, constructorSupertypes: List<KotlinTypeMarker>, lowerType: KotlinTypeMarker?, captureStatus: CaptureStatus): CapturedTypeMarker {
    println("MetaTypeSystemInferenceExtensionContext.createCapturedType: $constructorProjection")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun createErrorTypeWithCustomConstructor(debugName: String, constructor: TypeConstructorMarker): KotlinTypeMarker {
    println("MetaTypeSystemInferenceExtensionContext.createErrorTypeWithCustomConstructor: $debugName")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun createFlexibleType(lowerBound: SimpleTypeMarker, upperBound: SimpleTypeMarker): KotlinTypeMarker {
    println("MetaTypeSystemInferenceExtensionContext.createFlexibleType: $lowerBound")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun createSimpleType(constructor: TypeConstructorMarker, arguments: List<TypeArgumentMarker>, nullable: Boolean): SimpleTypeMarker {
    println("MetaTypeSystemInferenceExtensionContext.createSimpleType: $constructor")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun createStarProjection(typeParameter: TypeParameterMarker): TypeArgumentMarker {
    println("MetaTypeSystemInferenceExtensionContext.createStarProjection: $typeParameter")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun createStubType(typeVariable: TypeVariableMarker): StubTypeMarker {
    println("MetaTypeSystemInferenceExtensionContext.createStubType: $typeVariable")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun createTypeArgument(type: KotlinTypeMarker, variance: TypeVariance): TypeArgumentMarker {
    println("MetaTypeSystemInferenceExtensionContext.createTypeArgument: $type")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun findCommonIntegerLiteralTypesSuperType(explicitSupertypes: List<SimpleTypeMarker>): SimpleTypeMarker? {
    println("MetaTypeSystemInferenceExtensionContext.findCommonIntegerLiteralTypesSuperType: $explicitSupertypes")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun intersectTypes(types: List<KotlinTypeMarker>): KotlinTypeMarker {
    println("MetaTypeSystemInferenceExtensionContext.intersectTypes: $types")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun intersectTypes(types: List<SimpleTypeMarker>): SimpleTypeMarker {
    println("MetaTypeSystemInferenceExtensionContext.intersectTypes: $types")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun isEqualTypeConstructors(c1: TypeConstructorMarker, c2: TypeConstructorMarker): Boolean {
    println("MetaTypeSystemInferenceExtensionContext.isEqualTypeConstructors: $c1")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun newBaseTypeCheckerContext(errorTypesEqualToAnything: Boolean): AbstractTypeCheckerContext {
    println("MetaTypeSystemInferenceExtensionContext.newBaseTypeCheckerContext: $errorTypesEqualToAnything")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun nothingType(): SimpleTypeMarker {
    println("MetaTypeSystemInferenceExtensionContext.nothingType")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun nullableAnyType(): SimpleTypeMarker {
    println("MetaTypeSystemInferenceExtensionContext.nullableAnyType")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun nullableNothingType(): SimpleTypeMarker {
    println("MetaTypeSystemInferenceExtensionContext.nullableNothingType")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun prepareType(type: KotlinTypeMarker): KotlinTypeMarker {
    println("MetaTypeSystemInferenceExtensionContext.prepareType: $type")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun typeSubstitutorByTypeConstructor(map: Map<TypeConstructorMarker, KotlinTypeMarker>): TypeSubstitutorMarker {
    println("MetaTypeSystemInferenceExtensionContext.typeSubstitutorByTypeConstructor: $map")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun KotlinTypeMarker.argumentsCount(): Int {
    println("MetaTypeSystemInferenceExtensionContext.KotlinTypeMarker.argumentsCount:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun SimpleTypeMarker.asArgumentList(): TypeArgumentListMarker {
    println("MetaTypeSystemInferenceExtensionContext.SimpleTypeMarker.asArgumentList:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun SimpleTypeMarker.asCapturedType(): CapturedTypeMarker? {
    println("MetaTypeSystemInferenceExtensionContext.SimpleTypeMarker.asCapturedType:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun SimpleTypeMarker.asDefinitelyNotNullType(): DefinitelyNotNullTypeMarker? {
    println("MetaTypeSystemInferenceExtensionContext.SimpleTypeMarker.asDefinitelyNotNullType:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun FlexibleTypeMarker.asDynamicType(): DynamicTypeMarker? {
    println("MetaTypeSystemInferenceExtensionContext.FlexibleTypeMarker.asDynamicType:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun KotlinTypeMarker.asFlexibleType(): FlexibleTypeMarker? {
    println("MetaTypeSystemInferenceExtensionContext.KotlinTypeMarker.asFlexibleType:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun FlexibleTypeMarker.asRawType(): RawTypeMarker? {
    println("MetaTypeSystemInferenceExtensionContext.FlexibleTypeMarker.asRawType:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun KotlinTypeMarker.asSimpleType(): SimpleTypeMarker? {
    println("MetaTypeSystemInferenceExtensionContext.KotlinTypeMarker.asSimpleType:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun KotlinTypeMarker.asTypeArgument(): TypeArgumentMarker {
    println("MetaTypeSystemInferenceExtensionContext.KotlinTypeMarker.asTypeArgument:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun KotlinTypeMarker.canHaveUndefinedNullability(): Boolean {
    println("MetaTypeSystemInferenceExtensionContext.KotlinTypeMarker.canHaveUndefinedNullability:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun CapturedTypeMarker.captureStatus(): CaptureStatus {
    println("MetaTypeSystemInferenceExtensionContext.CapturedTypeMarker.captureStatus:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun KotlinTypeMarker.contains(predicate: (KotlinTypeMarker) -> Boolean): Boolean {
    println("MetaTypeSystemInferenceExtensionContext.KotlinTypeMarker.contains: $predicate")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeVariableMarker.defaultType(): SimpleTypeMarker {
    println("MetaTypeSystemInferenceExtensionContext.TypeVariableMarker.defaultType:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeVariableMarker.freshTypeConstructor(): TypeConstructorMarker {
    println("MetaTypeSystemInferenceExtensionContext.TypeVariableMarker.freshTypeConstructor:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeConstructorMarker.getApproximatedIntegerLiteralType(): KotlinTypeMarker {
    println("MetaTypeSystemInferenceExtensionContext.TypeConstructorMarker.getApproximatedIntegerLiteralType:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun KotlinTypeMarker.getArgument(index: Int): TypeArgumentMarker {
    println("MetaTypeSystemInferenceExtensionContext.KotlinTypeMarker.getArgument: $index")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeConstructorMarker.getParameter(index: Int): TypeParameterMarker {
    println("MetaTypeSystemInferenceExtensionContext.TypeConstructorMarker.getParameter: $index")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeArgumentMarker.getType(): KotlinTypeMarker {
    println("MetaTypeSystemInferenceExtensionContext.TypeArgumentMarker.getType:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeParameterMarker.getTypeConstructor(): TypeConstructorMarker {
    println("MetaTypeSystemInferenceExtensionContext.TypeParameterMarker.getTypeConstructor:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeParameterMarker.getUpperBound(index: Int): KotlinTypeMarker {
    println("MetaTypeSystemInferenceExtensionContext.TypeParameterMarker.getUpperBound: $index")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeArgumentMarker.getVariance(): TypeVariance {
    println("MetaTypeSystemInferenceExtensionContext.TypeArgumentMarker.getVariance:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeParameterMarker.getVariance(): TypeVariance {
    println("MetaTypeSystemInferenceExtensionContext.TypeParameterMarker.getVariance:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun KotlinTypeMarker.hasExactAnnotation(): Boolean {
    println("MetaTypeSystemInferenceExtensionContext.KotlinTypeMarker.hasExactAnnotation:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun KotlinTypeMarker.hasNoInferAnnotation(): Boolean {
    println("MetaTypeSystemInferenceExtensionContext.KotlinTypeMarker.hasNoInferAnnotation:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeConstructorMarker.isAnyConstructor(): Boolean {
    println("MetaTypeSystemInferenceExtensionContext.TypeConstructorMarker.isAnyConstructor")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeConstructorMarker.isCapturedTypeConstructor(): Boolean {
    println("MetaTypeSystemInferenceExtensionContext.TypeConstructorMarker.isCapturedTypeConstructor:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeConstructorMarker.isClassTypeConstructor(): Boolean {
    println("MetaTypeSystemInferenceExtensionContext.TypeConstructorMarker.isClassTypeConstructor:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeConstructorMarker.isCommonFinalClassConstructor(): Boolean {
    println("MetaTypeSystemInferenceExtensionContext.TypeConstructorMarker.isCommonFinalClassConstructor:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeConstructorMarker.isDenotable(): Boolean {
    println("MetaTypeSystemInferenceExtensionContext.TypeConstructorMarker.isDenotable:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun KotlinTypeMarker.isError(): Boolean {
    println("MetaTypeSystemInferenceExtensionContext.KotlinTypeMarker.isError:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeConstructorMarker.isIntegerLiteralTypeConstructor(): Boolean {
    println("MetaTypeSystemInferenceExtensionContext.TypeConstructorMarker.isIntegerLiteralTypeConstructor:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeConstructorMarker.isIntersection(): Boolean {
    println("MetaTypeSystemInferenceExtensionContext.TypeConstructorMarker.isIntersection:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun SimpleTypeMarker.isMarkedNullable(): Boolean {
    println("MetaTypeSystemInferenceExtensionContext.SimpleTypeMarker.isMarkedNullable:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun KotlinTypeMarker.isNotNullNothing(): Boolean {
    println("MetaTypeSystemInferenceExtensionContext.KotlinTypeMarker.isNotNullNothing:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeConstructorMarker.isNothingConstructor(): Boolean {
    println("MetaTypeSystemInferenceExtensionContext.TypeConstructorMarker.isNothingConstructor:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun KotlinTypeMarker.isNullableType(): Boolean {
    println("MetaTypeSystemInferenceExtensionContext.KotlinTypeMarker.isNullableType:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun SimpleTypeMarker.isPrimitiveType(): Boolean {
    println("MetaTypeSystemInferenceExtensionContext.SimpleTypeMarker.isPrimitiveType:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun SimpleTypeMarker.isSingleClassifierType(): Boolean {
    println("MetaTypeSystemInferenceExtensionContext.SimpleTypeMarker.isSingleClassifierType:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeArgumentMarker.isStarProjection(): Boolean {
    println("MetaTypeSystemInferenceExtensionContext.TypeArgumentMarker.isStarProjection:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun SimpleTypeMarker.isStubType(): Boolean {
    println("MetaTypeSystemInferenceExtensionContext.SimpleTypeMarker.isStubType")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun KotlinTypeMarker.isUninferredParameter(): Boolean {
    println("MetaTypeSystemInferenceExtensionContext.KotlinTypeMarker.isUninferredParameter")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun KotlinTypeMarker.isUnit(): Boolean {
    println("MetaTypeSystemInferenceExtensionContext.KotlinTypeMarker.isUnit:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeConstructorMarker.isUnitTypeConstructor(): Boolean {
    println("MetaTypeSystemInferenceExtensionContext.TypeConstructorMarker.isUnitTypeConstructor:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun FlexibleTypeMarker.lowerBound(): SimpleTypeMarker {
    println("MetaTypeSystemInferenceExtensionContext.FlexibleTypeMarker.lowerBound:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun CapturedTypeMarker.lowerType(): KotlinTypeMarker? {
    println("MetaTypeSystemInferenceExtensionContext.CapturedTypeMarker.lowerType:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun KotlinTypeMarker.makeDefinitelyNotNullOrNotNull(): KotlinTypeMarker {
    println("MetaTypeSystemInferenceExtensionContext.KotlinTypeMarker.makeDefinitelyNotNullOrNotNull:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun SimpleTypeMarker.makeSimpleTypeDefinitelyNotNullOrNotNull(): SimpleTypeMarker {
    println("MetaTypeSystemInferenceExtensionContext.SimpleTypeMarker.makeSimpleTypeDefinitelyNotNullOrNotNull:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun DefinitelyNotNullTypeMarker.original(): SimpleTypeMarker {
    println("MetaTypeSystemInferenceExtensionContext.DefinitelyNotNullTypeMarker.original:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeConstructorMarker.parametersCount(): Int {
    println("MetaTypeSystemInferenceExtensionContext.TypeConstructorMarker.parametersCount:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun SimpleTypeMarker.possibleIntegerTypes(): Collection<KotlinTypeMarker> {
    println("MetaTypeSystemInferenceExtensionContext.SimpleTypeMarker.possibleIntegerTypes:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun KotlinTypeMarker.removeAnnotations(): KotlinTypeMarker {
    println("MetaTypeSystemInferenceExtensionContext.KotlinTypeMarker.removeAnnotations:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun KotlinTypeMarker.removeExactAnnotation(): KotlinTypeMarker {
    println("MetaTypeSystemInferenceExtensionContext.KotlinTypeMarker.removeExactAnnotation:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun SimpleTypeMarker.replaceArguments(newArguments: List<TypeArgumentMarker>): SimpleTypeMarker {
    println("MetaTypeSystemInferenceExtensionContext.SimpleTypeMarker.replaceArguments:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeSubstitutorMarker.safeSubstitute(type: KotlinTypeMarker): KotlinTypeMarker {
    println("MetaTypeSystemInferenceExtensionContext.TypeSubstitutorMarker.safeSubstitute: $type")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun Collection<KotlinTypeMarker>.singleBestRepresentative(): KotlinTypeMarker? {
    println("MetaTypeSystemInferenceExtensionContext.Collection<KotlinTypeMarker>.singleBestRepresentative:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeConstructorMarker.supertypes(): Collection<KotlinTypeMarker> {
    println("MetaTypeSystemInferenceExtensionContext.TypeConstructorMarker.supertypes:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun SimpleTypeMarker.typeConstructor(): TypeConstructorMarker {
    println("MetaTypeSystemInferenceExtensionContext.SimpleTypeMarker.typeConstructor:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun CapturedTypeMarker.typeConstructorProjection(): TypeArgumentMarker {
    println("MetaTypeSystemInferenceExtensionContext.CapturedTypeMarker.typeConstructorProjection:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun KotlinTypeMarker.typeDepth(): Int {
    println("MetaTypeSystemInferenceExtensionContext.KotlinTypeMarker.typeDepth:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun SimpleTypeMarker.typeDepth(): Int {
    println("MetaTypeSystemInferenceExtensionContext.SimpleTypeMarker.typeDepth:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun FlexibleTypeMarker.upperBound(): SimpleTypeMarker {
    println("MetaTypeSystemInferenceExtensionContext.FlexibleTypeMarker.upperBound:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeParameterMarker.upperBoundCount(): Int {
    println("MetaTypeSystemInferenceExtensionContext.TypeParameterMarker.upperBoundCount:")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun KotlinTypeMarker.withNullability(nullable: Boolean): KotlinTypeMarker {
    println("MetaTypeSystemInferenceExtensionContext.KotlinTypeMarker.withNullability: $nullable")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }

  override fun SimpleTypeMarker.withNullability(nullable: Boolean): SimpleTypeMarker {
    println("MetaTypeSystemInferenceExtensionContext.SimpleTypeMarker.withNullability: $nullable")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates. //To change body of created functions use File | Settings | File Templates.
  }
}
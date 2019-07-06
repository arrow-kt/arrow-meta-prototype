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
import org.jetbrains.kotlin.types.model.TypeSystemInferenceExtensionContextDelegate
import org.jetbrains.kotlin.types.model.TypeVariableMarker
import org.jetbrains.kotlin.types.model.TypeVariance

class MetaTypeSystemInferenceExtensionContextDelegate : TypeSystemInferenceExtensionContextDelegate {
  override fun anyType(): SimpleTypeMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.anyType")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun captureFromArguments(type: SimpleTypeMarker, status: CaptureStatus): SimpleTypeMarker? {
    println("MetaTypeSystemInferenceExtensionContextDelegate.captureFromArguments: $type")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun captureFromExpression(type: KotlinTypeMarker): KotlinTypeMarker? {
    println("MetaTypeSystemInferenceExtensionContextDelegate.captureFromExpression $type")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun createCapturedType(constructorProjection: TypeArgumentMarker, constructorSupertypes: List<KotlinTypeMarker>, lowerType: KotlinTypeMarker?, captureStatus: CaptureStatus): CapturedTypeMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.createCapturedType: $constructorProjection")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun createErrorTypeWithCustomConstructor(debugName: String, constructor: TypeConstructorMarker): KotlinTypeMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.createErrorTypeWithCustomConstructor")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun createFlexibleType(lowerBound: SimpleTypeMarker, upperBound: SimpleTypeMarker): KotlinTypeMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun createSimpleType(constructor: TypeConstructorMarker, arguments: List<TypeArgumentMarker>, nullable: Boolean): SimpleTypeMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun createStarProjection(typeParameter: TypeParameterMarker): TypeArgumentMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun createStubType(typeVariable: TypeVariableMarker): StubTypeMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun createTypeArgument(type: KotlinTypeMarker, variance: TypeVariance): TypeArgumentMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun findCommonIntegerLiteralTypesSuperType(explicitSupertypes: List<SimpleTypeMarker>): SimpleTypeMarker? {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun intersectTypes(types: List<KotlinTypeMarker>): KotlinTypeMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun intersectTypes(types: List<SimpleTypeMarker>): SimpleTypeMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun isEqualTypeConstructors(c1: TypeConstructorMarker, c2: TypeConstructorMarker): Boolean {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun newBaseTypeCheckerContext(errorTypesEqualToAnything: Boolean): AbstractTypeCheckerContext {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun nothingType(): SimpleTypeMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun nullableAnyType(): SimpleTypeMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun nullableNothingType(): SimpleTypeMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun prepareType(type: KotlinTypeMarker): KotlinTypeMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun typeSubstitutorByTypeConstructor(map: Map<TypeConstructorMarker, KotlinTypeMarker>): TypeSubstitutorMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun KotlinTypeMarker.argumentsCount(): Int {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun SimpleTypeMarker.asArgumentList(): TypeArgumentListMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun SimpleTypeMarker.asCapturedType(): CapturedTypeMarker? {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun SimpleTypeMarker.asDefinitelyNotNullType(): DefinitelyNotNullTypeMarker? {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun FlexibleTypeMarker.asDynamicType(): DynamicTypeMarker? {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun KotlinTypeMarker.asFlexibleType(): FlexibleTypeMarker? {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun FlexibleTypeMarker.asRawType(): RawTypeMarker? {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun KotlinTypeMarker.asSimpleType(): SimpleTypeMarker? {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun KotlinTypeMarker.asTypeArgument(): TypeArgumentMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun KotlinTypeMarker.canHaveUndefinedNullability(): Boolean {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun CapturedTypeMarker.captureStatus(): CaptureStatus {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun KotlinTypeMarker.contains(predicate: (KotlinTypeMarker) -> Boolean): Boolean {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeVariableMarker.defaultType(): SimpleTypeMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeVariableMarker.freshTypeConstructor(): TypeConstructorMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeConstructorMarker.getApproximatedIntegerLiteralType(): KotlinTypeMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun KotlinTypeMarker.getArgument(index: Int): TypeArgumentMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeConstructorMarker.getParameter(index: Int): TypeParameterMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeArgumentMarker.getType(): KotlinTypeMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeParameterMarker.getTypeConstructor(): TypeConstructorMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeParameterMarker.getUpperBound(index: Int): KotlinTypeMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeArgumentMarker.getVariance(): TypeVariance {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeParameterMarker.getVariance(): TypeVariance {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun KotlinTypeMarker.hasExactAnnotation(): Boolean {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun KotlinTypeMarker.hasNoInferAnnotation(): Boolean {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeConstructorMarker.isAnyConstructor(): Boolean {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeConstructorMarker.isCapturedTypeConstructor(): Boolean {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeConstructorMarker.isClassTypeConstructor(): Boolean {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeConstructorMarker.isCommonFinalClassConstructor(): Boolean {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeConstructorMarker.isDenotable(): Boolean {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun KotlinTypeMarker.isError(): Boolean {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeConstructorMarker.isIntegerLiteralTypeConstructor(): Boolean {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeConstructorMarker.isIntersection(): Boolean {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun SimpleTypeMarker.isMarkedNullable(): Boolean {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun KotlinTypeMarker.isNotNullNothing(): Boolean {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeConstructorMarker.isNothingConstructor(): Boolean {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun KotlinTypeMarker.isNullableType(): Boolean {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun SimpleTypeMarker.isPrimitiveType(): Boolean {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun SimpleTypeMarker.isSingleClassifierType(): Boolean {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeArgumentMarker.isStarProjection(): Boolean {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun SimpleTypeMarker.isStubType(): Boolean {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun KotlinTypeMarker.isUninferredParameter(): Boolean {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun KotlinTypeMarker.isUnit(): Boolean {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeConstructorMarker.isUnitTypeConstructor(): Boolean {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun FlexibleTypeMarker.lowerBound(): SimpleTypeMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun CapturedTypeMarker.lowerType(): KotlinTypeMarker? {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun KotlinTypeMarker.makeDefinitelyNotNullOrNotNull(): KotlinTypeMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun SimpleTypeMarker.makeSimpleTypeDefinitelyNotNullOrNotNull(): SimpleTypeMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun DefinitelyNotNullTypeMarker.original(): SimpleTypeMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeConstructorMarker.parametersCount(): Int {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun SimpleTypeMarker.possibleIntegerTypes(): Collection<KotlinTypeMarker> {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun KotlinTypeMarker.removeAnnotations(): KotlinTypeMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun KotlinTypeMarker.removeExactAnnotation(): KotlinTypeMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun SimpleTypeMarker.replaceArguments(newArguments: List<TypeArgumentMarker>): SimpleTypeMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeSubstitutorMarker.safeSubstitute(type: KotlinTypeMarker): KotlinTypeMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun Collection<KotlinTypeMarker>.singleBestRepresentative(): KotlinTypeMarker? {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeConstructorMarker.supertypes(): Collection<KotlinTypeMarker> {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun SimpleTypeMarker.typeConstructor(): TypeConstructorMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun CapturedTypeMarker.typeConstructorProjection(): TypeArgumentMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun KotlinTypeMarker.typeDepth(): Int {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun SimpleTypeMarker.typeDepth(): Int {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun FlexibleTypeMarker.upperBound(): SimpleTypeMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun TypeParameterMarker.upperBoundCount(): Int {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun KotlinTypeMarker.withNullability(nullable: Boolean): KotlinTypeMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun SimpleTypeMarker.withNullability(nullable: Boolean): SimpleTypeMarker {
    println("MetaTypeSystemInferenceExtensionContextDelegate.")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}
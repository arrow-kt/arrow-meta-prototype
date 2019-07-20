package arrow.meta.debug.unusedServices

import org.jetbrains.kotlin.descriptors.SourceElement
import org.jetbrains.kotlin.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader
import org.jetbrains.kotlin.load.kotlin.KotlinClassFinder
import org.jetbrains.kotlin.load.kotlin.KotlinJvmBinaryClass
import org.jetbrains.kotlin.metadata.ProtoBuf
import org.jetbrains.kotlin.metadata.deserialization.NameResolver
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.protobuf.MessageLite
import org.jetbrains.kotlin.serialization.deserialization.AnnotatedCallableKind
import org.jetbrains.kotlin.serialization.deserialization.ProtoContainer
import org.jetbrains.kotlin.storage.StorageManager
import org.jetbrains.kotlin.types.KotlinType

class MetaBinaryClassAnnotationAndConstantLoader<A : Any, C : Any>(
  storageManager: StorageManager,
  kotlinClassFinder: KotlinClassFinder
) : AbstractBinaryClassAnnotationAndConstantLoader<A, C>(
  storageManager, kotlinClassFinder
) {
  override fun loadAnnotation(annotationClassId: ClassId, source: SourceElement, result: MutableList<A>): KotlinJvmBinaryClass.AnnotationArgumentVisitor? {
    println("MetaBinaryClassAnnotationAndConstantLoader.loadAnnotation: $annotationClassId, $source, $result")
    return loadAnnotation(annotationClassId, source, result)
  }

  override fun getCachedFileContent(kotlinClass: KotlinJvmBinaryClass): ByteArray? {
    println("MetaBinaryClassAnnotationAndConstantLoader.getCachedFileContent: $kotlinClass")
    return super.getCachedFileContent(kotlinClass)
  }

  override fun loadCallableAnnotations(container: ProtoContainer, proto: MessageLite, kind: AnnotatedCallableKind): List<A> {
    println("MetaBinaryClassAnnotationAndConstantLoader.loadCallableAnnotations: $container, $proto, $kind")
    return super.loadCallableAnnotations(container, proto, kind)
  }

  override fun loadClassAnnotations(container: ProtoContainer.Class): List<A> {
    println("MetaBinaryClassAnnotationAndConstantLoader.loadClassAnnotations: $container")
    return super.loadClassAnnotations(container)
  }

  override fun loadEnumEntryAnnotations(container: ProtoContainer, proto: ProtoBuf.EnumEntry): List<A> {
    println("MetaBinaryClassAnnotationAndConstantLoader.loadEnumEntryAnnotations: $container, $proto")
    return super.loadEnumEntryAnnotations(container, proto)
  }

  override fun loadExtensionReceiverParameterAnnotations(container: ProtoContainer, proto: MessageLite, kind: AnnotatedCallableKind): List<A> {
    println("MetaBinaryClassAnnotationAndConstantLoader.loadExtensionReceiverParameterAnnotations: $container, $proto, $kind")
    return super.loadExtensionReceiverParameterAnnotations(container, proto, kind)
  }

  override fun loadPropertyBackingFieldAnnotations(container: ProtoContainer, proto: ProtoBuf.Property): List<A> {
    println("MetaBinaryClassAnnotationAndConstantLoader.loadPropertyBackingFieldAnnotations: $container, $proto")
    return super.loadPropertyBackingFieldAnnotations(container, proto)
  }

  override fun loadPropertyConstant(container: ProtoContainer, proto: ProtoBuf.Property, expectedType: KotlinType): C? {
    println("MetaBinaryClassAnnotationAndConstantLoader.loadPropertyConstant: $container, $proto, $expectedType")
    return super.loadPropertyConstant(container, proto, expectedType)
  }

  override fun loadPropertyDelegateFieldAnnotations(container: ProtoContainer, proto: ProtoBuf.Property): List<A> {
    println("MetaBinaryClassAnnotationAndConstantLoader.loadPropertyDelegateFieldAnnotations: $container, $proto")
    return super.loadPropertyDelegateFieldAnnotations(container, proto)
  }

  override fun loadTypeAnnotations(proto: ProtoBuf.Type, nameResolver: NameResolver): List<A> {
    println("MetaBinaryClassAnnotationAndConstantLoader.loadTypeAnnotations: $proto, $nameResolver")
    return super.loadTypeAnnotations(proto, nameResolver)
  }

  override fun loadTypeParameterAnnotations(proto: ProtoBuf.TypeParameter, nameResolver: NameResolver): List<A> {
    println("MetaBinaryClassAnnotationAndConstantLoader.loadTypeParameterAnnotations: $proto, $nameResolver")
    return super.loadTypeParameterAnnotations(proto, nameResolver)
  }

  override fun loadValueParameterAnnotations(container: ProtoContainer, callableProto: MessageLite, kind: AnnotatedCallableKind, parameterIndex: Int, proto: ProtoBuf.ValueParameter): List<A> {
    println("MetaBinaryClassAnnotationAndConstantLoader.loadValueParameterAnnotations: $container, $callableProto, $kind, $parameterIndex, $proto")
    return super.loadValueParameterAnnotations(container, callableProto, kind, parameterIndex, proto)
  }

  override fun loadConstant(desc: String, initializer: Any): C? {
    println("MetaBinaryClassAnnotationAndConstantLoader.loadConstant: $desc, $initializer")
    return loadConstant(desc, initializer)
  }

  override fun loadTypeAnnotation(proto: ProtoBuf.Annotation, nameResolver: NameResolver): A {
    println("MetaBinaryClassAnnotationAndConstantLoader.loadTypeAnnotation: $proto, $nameResolver")
    return loadTypeAnnotation(proto, nameResolver)
  }

  override fun transformToUnsignedConstant(constant: C): C? {
    println("MetaBinaryClassAnnotationAndConstantLoader.transformToUnsignedConstant: $constant")
    return transformToUnsignedConstant(constant)
  }
}
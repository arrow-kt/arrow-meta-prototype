package arrow.meta.debug.resolution

import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationAndConstantLoader
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer
import kotlin.reflect.jvm.internal.impl.types.KotlinType

/**
 * registering it primitively
 * @throws ContainerConsistencyException: Could not create the component Singleton: MetaAnnotationAndConstantLoader because it is being initialized. Do we have undetected circular dependency?
 *
 * Intercept the value in [DeserializationComponents] or modify [AnnotationAndConstantLoaderImpl]
 */
class MetaAnnotationAndConstantLoader<A : Any, C : Any>(val delegate: AnnotationAndConstantLoader<A, C>) : AnnotationAndConstantLoader<A, C> by delegate {
  override fun loadPropertyDelegateFieldAnnotations(p0: ProtoContainer, p1: ProtoBuf.Property): MutableList<A> {
    println("MetaAnnotationAndConstantLoader.loadPropertyDelegateFieldAnnotations: $p0, $p1")
    return delegate.loadPropertyDelegateFieldAnnotations(p0, p1)
  }

  override fun loadClassAnnotations(p0: ProtoContainer.Class): MutableList<A> {
    println("MetaAnnotationAndConstantLoader.loadClassAnnotations: $p0")
    return delegate.loadClassAnnotations(p0)
  }

  override fun loadExtensionReceiverParameterAnnotations(p0: ProtoContainer, p1: MessageLite, p2: AnnotatedCallableKind): MutableList<A> {
    println("MetaAnnotationAndConstantLoader.loadExtensionReceiverParameterAnnotations: $p0, $p1, $p2")
    return delegate.loadExtensionReceiverParameterAnnotations(p0, p1, p2)
  }

  override fun loadValueParameterAnnotations(p0: ProtoContainer, p1: MessageLite, p2: AnnotatedCallableKind, p3: Int, p4: ProtoBuf.ValueParameter): MutableList<A> {
    println("MetaAnnotationAndConstantLoader.loadValueParameterAnnotations: $p0, $p1, $p2, $p3, $p4")
    return delegate.loadValueParameterAnnotations(p0, p1, p2, p3, p4)
  }

  override fun loadEnumEntryAnnotations(p0: ProtoContainer, p1: ProtoBuf.EnumEntry): MutableList<A> {
    println("MetaAnnotationAndConstantLoader.loadEnumEntryAnnotations: $p0, $p1")
    return delegate.loadEnumEntryAnnotations(p0, p1)
  }

  override fun loadTypeParameterAnnotations(p0: ProtoBuf.TypeParameter, p1: NameResolver): MutableList<A> {
    println("MetaAnnotationAndConstantLoader.loadTypeParameterAnnotations: $p0, $p1)")
    return delegate.loadTypeParameterAnnotations(p0, p1)
  }

  override fun loadCallableAnnotations(p0: ProtoContainer, p1: MessageLite, p2: AnnotatedCallableKind): MutableList<A> {
    println("MetaAnnotationAndConstantLoader.loadCallableAnnotations: $p0, $p1, $p2")
    return delegate.loadCallableAnnotations(p0, p1, p2)
  }

  override fun loadTypeAnnotations(p0: ProtoBuf.Type, p1: NameResolver): MutableList<A> {
    println("MetaAnnotationAndConstantLoader.loadTypeAnnotations: $p0, $p1")
    return delegate.loadTypeAnnotations(p0, p1)
  }

  override fun loadPropertyConstant(p0: ProtoContainer, p1: ProtoBuf.Property, p2: KotlinType): C? {
    println("MetaAnnotationAndConstantLoader.loadPropertyConstant: $p0, $p1, $p2")
    return delegate.loadPropertyConstant(p0, p1, p2)
  }

  override fun loadPropertyBackingFieldAnnotations(p0: ProtoContainer, p1: ProtoBuf.Property): MutableList<A> {
    println("MetaAnnotationAndConstantLoader.loadPropertyBackingFieldAnnotations: $p0, $p1")
    return delegate.loadPropertyBackingFieldAnnotations(p0, p1)
  }
}
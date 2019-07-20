package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.ConstructorDescriptor
import org.jetbrains.kotlin.descriptors.PropertyDescriptor
import org.jetbrains.kotlin.descriptors.SimpleFunctionDescriptor
import org.jetbrains.kotlin.load.java.components.AbstractJavaResolverCache
import org.jetbrains.kotlin.load.java.structure.JavaClass
import org.jetbrains.kotlin.load.java.structure.JavaElement
import org.jetbrains.kotlin.load.java.structure.JavaField
import org.jetbrains.kotlin.load.java.structure.JavaMethod
import org.jetbrains.kotlin.load.java.structure.impl.JavaClassImpl
import org.jetbrains.kotlin.load.java.structure.impl.JavaElementImpl
import org.jetbrains.kotlin.load.java.structure.impl.JavaFieldImpl
import org.jetbrains.kotlin.load.java.structure.impl.JavaMethodImpl
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.BindingContextUtils
import org.jetbrains.kotlin.resolve.lazy.ResolveSession

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaLazyResolveBasedCache(resolveSession: ResolveSession) : AbstractJavaResolverCache(resolveSession) {
  override fun recordClass(p0: JavaClass, p1: ClassDescriptor) {
    println("MetaLazyResolveBasedCache.recordClass: $p0, $p1")
    trace.record(BindingContext.CLASS, (p0 as? JavaClassImpl)?.psi ?: return, p1)
  }

  override fun getClassResolvedFromSource(fqName: FqName): ClassDescriptor? {
    println("MetaLazyResolveBasedCache.getClassResolvedFromSource: $fqName")
    return super.getClassResolvedFromSource(fqName)
  }

  override fun recordConstructor(p0: JavaElement, p1: ConstructorDescriptor) {
    println("MetaLazyResolveBasedCache.recordConstructor: $p0, $p1")
    trace.record(BindingContext.CONSTRUCTOR, (p0 as? JavaElementImpl<*>)?.psi ?: return, p1)
  }

  override fun recordField(p0: JavaField, p1: PropertyDescriptor) {
    println("MetaLazyResolveBasedCache.recordField: $p0, $p1")
    trace.record(BindingContext.VARIABLE, (p0 as? JavaFieldImpl)?.psi ?: return, p1)
  }

  override fun recordMethod(p0: JavaMethod, p1: SimpleFunctionDescriptor) {
    println("MetaLazyResolveBasedCache.recordMethod: $p0, $p1")
    BindingContextUtils.recordFunctionDeclarationToDescriptor(trace, (p0 as? JavaMethodImpl)?.psi ?: return, p1)
  }
}
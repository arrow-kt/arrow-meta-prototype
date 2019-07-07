package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.descriptors.PropertyDescriptor
import org.jetbrains.kotlin.load.java.components.JavaPropertyInitializerEvaluator
import org.jetbrains.kotlin.load.java.structure.JavaField
import org.jetbrains.kotlin.resolve.constants.ConstantValue

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaJavaPropertyInitializerEvaluator(val delegate: JavaPropertyInitializerEvaluator) : JavaPropertyInitializerEvaluator by delegate {
  override fun getInitializerConstant(field: JavaField, descriptor: PropertyDescriptor): ConstantValue<*>? {
    println("MetaJavaPropertyInitializerEvaluator.getInitializerConstant: $field, $descriptor")
    return delegate.getInitializerConstant(field, descriptor)
  }
}
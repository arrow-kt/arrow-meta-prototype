package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.load.java.components.JavaSourceElementFactoryImpl
import org.jetbrains.kotlin.load.java.sources.JavaSourceElement
import org.jetbrains.kotlin.load.java.sources.JavaSourceElementFactory
import org.jetbrains.kotlin.load.java.structure.JavaElement

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaJavaSourceElementFactory(val delegate: JavaSourceElementFactory = JavaSourceElementFactoryImpl()) : JavaSourceElementFactory by delegate{
  override fun source(javaElement: JavaElement): JavaSourceElement {
    println("MetaJavaSourceElementFactory.source: $javaElement")
    return delegate.source(javaElement)
  }
}
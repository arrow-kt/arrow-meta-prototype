package arrow.meta.debug.unusedServices

import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.serialization.deserialization.ClassData
import org.jetbrains.kotlin.serialization.deserialization.ClassDataFinder

/**
 * @UnusedService Currently we need to investigate how our costume functions can be executed.
 * Meaning we need to hook into the compiler earlier than Analysis. Either with Reflection or else
 */
class MetaClassDataFinder(val delegate : ClassDataFinder) : ClassDataFinder by delegate {
  override fun findClassData(p0: ClassId): ClassData? {
    println("MetaClassDataFinder.findClassData: $p0")
    return delegate.findClassData(p0)
  }
}


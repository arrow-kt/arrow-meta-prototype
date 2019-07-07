package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.incremental.components.ExpectActualTracker
import java.io.File

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaExpectActualTracker(val delegate: ExpectActualTracker) : ExpectActualTracker by delegate{
  override fun report(expectedFile: File, actualFile: File) {
    println("MetaExpectActualTracker.report: $expectedFile ~ $actualFile")
    return delegate.report(expectedFile, actualFile)
  }
}
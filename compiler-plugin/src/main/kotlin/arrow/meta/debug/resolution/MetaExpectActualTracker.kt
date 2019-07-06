package arrow.meta.utils

import org.jetbrains.kotlin.incremental.components.ExpectActualTracker
import java.io.File

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaExpectActualTracker : ExpectActualTracker {
  override fun report(expectedFile: File, actualFile: File) {
    println("MetaExpectActualTracker.report: $expectedFile ~ $actualFile")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}
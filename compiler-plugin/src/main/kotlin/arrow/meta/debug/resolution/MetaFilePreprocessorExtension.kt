package arrow.meta.debug.resolution

import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.resolve.FilePreprocessorExtension

class MetaFilePreprocessorExtension(delegate: FilePreprocessorExtension) : FilePreprocessorExtension by delegate{
  override fun preprocessFile(file: KtFile) {
    println("MetaFilePreprocessorExtension.preprocessFile: $file")
    TODO()
  }
}
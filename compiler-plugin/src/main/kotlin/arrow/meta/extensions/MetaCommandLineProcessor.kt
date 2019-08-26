package arrow.meta.extensions

import org.jetbrains.kotlin.compiler.plugin.CliOption
import org.jetbrains.kotlin.compiler.plugin.CommandLineProcessor

class MetaCommandLineProcessor : CommandLineProcessor {

  override val pluginId: String = "arrow.meta.plugin.compiler".also { println("Compiler plugin added") }

  override val pluginOptions: Collection<CliOption> = emptyList()

}

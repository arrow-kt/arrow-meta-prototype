package arrow.meta

import arrow.meta.comprehensions.comprehensions
import arrow.meta.extensions.ExtensionPhase
import arrow.meta.extensions.MetaComponentRegistrar
import arrow.meta.higherkind.higherKindedTypes
import arrow.meta.typeclasses.typeClasses
import org.jetbrains.kotlin.com.intellij.mock.MockProject
import org.jetbrains.kotlin.config.CompilerConfiguration

class MetaPlugin : MetaComponentRegistrar {
  override fun registerProjectComponents(project: MockProject, configuration: CompilerConfiguration) {
    println("it get's called")
    super.registerProjectComponents(project, configuration)
  }

  override fun intercept(): List<ExtensionPhase> =
    higherKindedTypes +
      typeClasses +
      comprehensions
}
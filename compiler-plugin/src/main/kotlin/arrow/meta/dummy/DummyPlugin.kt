package arrow.meta.dummy

import arrow.meta.extensions.ExtensionPhase
import arrow.meta.extensions.MetaComponentRegistrar
import arrow.meta.qq.classOrObject
import arrow.meta.qq.iconProvider
import org.jetbrains.kotlin.idea.KotlinIconProviderService
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.utils.addToStdlib.safeAs

val MetaComponentRegistrar.dummy: Pair<Name, List<ExtensionPhase>>
  get() =
    Name.identifier("Dummy") to
      meta(
        iconProvider(
          getIcon = { psiElement, flags ->
            if (psiElement.safeAs<KtNamedFunction>()?.name == "foo") iconFor(")
            else null
          }
        ),
        classOrObject({ name == "TestClass" }) { c ->
          println("Processing Dummy: ${c.name}")
          listOfNotNull(
            """
              |interface SynthSuperType<A> {
              |  fun fromSynthSuperType(): Unit = println("fromSynthSuperType")
              |}
            """,
            """
              |$modality $visibility $kind $name<$typeParameters>($valueParameters): SynthSuperType<Int> {
              |  $body
              |  fun test2(): String = "Boom!"
              |  fun test(): Unit = println(test2())
              |  
              |  val xx: Int = 1
              |  val yy: Double = 0.0
              |  
              |  class ZZ
              |  class XX
              |  
              |  companion object Factory
              |}
              |"""
          )
        }
      )

package arrow.meta.dummy

import arrow.meta.extensions.ExtensionPhase
import arrow.meta.extensions.MetaComponentRegistrar
import arrow.meta.qq.classOrObject
import arrow.meta.qq.extensionProvider
import com.intellij.codeInsight.daemon.impl.IntentionMenuContributor.EP_NAME
import org.jetbrains.kotlin.idea.quickfix.QuickFixContributor
import org.jetbrains.kotlin.idea.quickfix.QuickFixes
import org.jetbrains.kotlin.name.Name

val MetaComponentRegistrar.dummy: Pair<Name, List<ExtensionPhase>>
  get() =
    Name.identifier("Dummy") to
      meta(
        extensionProvider(
          EP_NAME = EP_NAME,
          object : QuickFixContributor {
            override fun registerQuickFixes(quickFixes: QuickFixes) {
              TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
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

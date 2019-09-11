package arrow.meta.plugin.idea

import arrow.meta.qq.MetaExtensionProvider
import org.jetbrains.kotlin.com.intellij.core.CoreApplicationEnvironment
import org.jetbrains.kotlin.com.intellij.openapi.extensions.ExtensionPointName
import com.intellij.core.CoreApplicationEnvironment as CAE
import com.intellij.openapi.extensions.ExtensionPointName as EP

class MetaIdeExtensionProvider : MetaExtensionProvider {
  override fun <E> registerExtension(epName: ExtensionPointName<E>, impl: E): Unit? =
    currentProject()?.getComponent(CoreApplicationEnvironment::class.java)?.addExtension<E>(epName, impl)

  override fun <E> registerExtension(epName: EP<E>, impl: E): Unit? =
    currentProject()?.getComponent(CAE::class.java)?.addExtension(epName, impl)
}

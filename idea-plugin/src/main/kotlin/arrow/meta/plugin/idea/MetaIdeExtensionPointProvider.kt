package arrow.meta.plugin.idea

import arrow.meta.qq.MetaExtensionPointProvider
import com.intellij.core.CoreApplicationEnvironment
import com.intellij.openapi.extensions.BaseExtensionPointName
import com.intellij.openapi.extensions.ExtensionPointName
import com.intellij.openapi.extensions.Extensions

class MetaIdeExtensionPointProvider : MetaExtensionPointProvider {
  override fun <T> registerExtensionPoint(EP_NAME: BaseExtensionPointName, aClass: Class<T>): Unit =
    CoreApplicationEnvironment.registerExtensionPoint(Extensions.getArea(currentProject()), EP_NAME, aClass)

  override fun <T> registerExtensionPoint(EP_NAME: ExtensionPointName<T>, aClass: Class<T>): Unit =
    CoreApplicationEnvironment.registerExtensionPoint(Extensions.getArea(currentProject()), EP_NAME, aClass)
}

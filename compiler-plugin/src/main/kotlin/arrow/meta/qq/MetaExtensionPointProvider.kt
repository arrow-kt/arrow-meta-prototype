package arrow.meta.qq

import com.intellij.openapi.extensions.BaseExtensionPointName
import com.intellij.openapi.extensions.ExtensionPointName

/**
 * Currently specialized to Project level
 * TODO: Generalize it to any ExtensionArea
 */
interface MetaExtensionPointProvider {
  fun <T> registerExtensionPoint(EP_NAME: BaseExtensionPointName, aClass: Class<T>): Unit
  fun <T> registerExtensionPoint(EP_NAME: ExtensionPointName<T>, aClass: Class<T>): Unit
}
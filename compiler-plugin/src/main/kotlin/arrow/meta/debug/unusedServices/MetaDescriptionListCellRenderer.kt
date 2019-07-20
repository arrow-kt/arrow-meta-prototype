package arrow.meta.debug.unusedServices

import org.jetbrains.kotlin.utils.DescriptionAware
import java.awt.Component
import javax.swing.DefaultListCellRenderer
import javax.swing.JList

/**
 * @UnusedService Currently we need to investigate how our costume functions can be executed.
 * Meaning we need to hook into the compiler earlier than Analysis. Either with Reflection or else
 */
class MetaDescriptionListCellRenderer : DefaultListCellRenderer() {
  override fun getListCellRendererComponent(list: JList<*>?, value: Any?, index: Int, isSelected: Boolean, cellHasFocus: Boolean): Component {
    println("MetaDescriptionListCellRenderer.getListCellRendererComponent: $list, $value, $index, $isSelected, $cellHasFocus")
    return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus).apply {
      text = (value as? DescriptionAware)?.description ?: ""
    }
  }
}
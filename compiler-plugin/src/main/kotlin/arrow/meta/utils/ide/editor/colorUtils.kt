package arrow.meta.utils.ide.editor

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import javax.swing.Icon

fun addColorSettingsPage(
  icon: Icon,
  displayName: String,
  demoText: String,
  highlighter: SyntaxHighlighter,
  additionalHighlightingTagToDescriptorMap: MutableMap<String, TextAttributesKey>,
  attributesDescriptor: Array<AttributesDescriptor>,
  colorDescriptor: Array<ColorDescriptor>
): ColorSettingsPage =
  object : ColorSettingsPage {
    override fun getHighlighter(): SyntaxHighlighter = highlighter

    override fun getAdditionalHighlightingTagToDescriptorMap(): MutableMap<String, TextAttributesKey> =
      additionalHighlightingTagToDescriptorMap

    override fun getIcon(): Icon? = icon

    override fun getAttributeDescriptors(): Array<AttributesDescriptor> =
      attributesDescriptor

    override fun getColorDescriptors(): Array<ColorDescriptor> =
      colorDescriptor

    override fun getDisplayName(): String = displayName


    override fun getDemoText(): String = demoText
  }
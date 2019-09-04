package arrow.meta.plugin.idea.icon

import com.intellij.openapi.util.IconLoader
import javax.swing.Icon

/**
 * The icons are 16x16, the format and number of colors doesn't matter, but svg is preferred option, because of resolution
 *
 * ## Example File Icons
 * - used Extension `org.jetbrains.kotlin.idea.KotlinIconProvider`
 * ```
 * <iconProvider implementation="org.jetbrains.kotlin.idea.KotlinIconProvider"/>
 * ```
 *
 * ## Example Line Icons
 * - used Extension `com.intellij.codeInsight.daemon.LineMarkerProvider
 * ```
 * <codeInsight.lineMarkerProvider language="kotlin" implementationClass="org.jetbrains.kotlin.idea.highlighter.KotlinSuspendCallLineMarkerProvider"/>
 * ```
 * More here: https://www.jetbrains.org/intellij/sdk/docs/reference_guide/work_with_icons_and_images.html?search=icon
 */
interface ArrowIcons {
  companion object {
    val IMPURE_LINEMARKER_ICON: Icon
      get() = IconLoader.getIcon("/icons/DeleteRed.png")

    val TEST: Icon
      get() = IconLoader.getIcon("/icons/suspendCall_dark.svg")
  }
}
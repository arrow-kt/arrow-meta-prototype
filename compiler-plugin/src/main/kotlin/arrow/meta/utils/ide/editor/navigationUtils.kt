package arrow.meta.utils.ide.editor

import com.intellij.navigation.ChooseByNameContributor
import com.intellij.navigation.NavigationItem
import com.intellij.openapi.project.Project

/**
 * [ChooseByNameContributor.FILE_EP_NAME]
 * [ChooseByNameContributor.CLASS_EP_NAME]
 * [ChooseByNameContributor.SYMBOL_EP_NAME]
 */
fun addChooseByNameContributor(
  itemsByName: (name: String?, pattern: String?, project: Project?, includeNonProjectItems: Boolean) -> Array<NavigationItem>,
  names: (project: Project?, includeNonProjectItems: Boolean) -> Array<String>
): ChooseByNameContributor =
  object : ChooseByNameContributor {
    override fun getItemsByName(name: String?, pattern: String?, project: Project?, includeNonProjectItems: Boolean): Array<NavigationItem> =
      itemsByName(name, pattern, project, includeNonProjectItems)

    override fun getNames(project: Project?, includeNonProjectItems: Boolean): Array<String> =
      names(project, includeNonProjectItems)
  }

package arrow.meta.plugin.idea

import com.intellij.ide.hierarchy.HierarchyBrowser
import com.intellij.ide.hierarchy.type.JavaTypeHierarchyProvider
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.psi.PsiElement

/*
Not working
 */
class ArrowTypeHierarchyProvider : JavaTypeHierarchyProvider() {
  override fun getTarget(dataContext: DataContext): PsiElement? {
    println("ArrowTypeHierarchyProvider.getTarget: $dataContext")
    return super.getTarget(dataContext)
  }

  override fun createHierarchyBrowser(target: PsiElement): HierarchyBrowser {
    println("ArrowTypeHierarchyProvider.createHierarchyBrowser: $target")
    return super.createHierarchyBrowser(target)
  }

  override fun browserActivated(hierarchyBrowser: HierarchyBrowser) {
    println("ArrowTypeHierarchyProvider.browserActivated: $hierarchyBrowser")
    super.browserActivated(hierarchyBrowser)
  }
}
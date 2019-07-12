package arrow.meta.debug.unusedServices

import org.jetbrains.kotlin.com.intellij.openapi.project.Project
import org.jetbrains.kotlin.com.intellij.psi.search.GlobalSearchScope
import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.load.kotlin.VirtualFileFinder
import org.jetbrains.kotlin.load.kotlin.VirtualFileFinderFactory

/**
 * @UnusedService Currently we need to investigate how our costume functions can be executed.
 * Meaning we need to hook into the compiler earlier than Analysis. Either with Reflection or else
 */
class MetaVirtualFileFinderFactory(val delegate: VirtualFileFinderFactory) : VirtualFileFinderFactory by delegate {
  override fun create(project: Project, module: ModuleDescriptor): VirtualFileFinder {
    println("MetaVirtualFileFinderFactory.create: $project, $module")
    return delegate.create(project, module)
  }

  override fun create(scope: GlobalSearchScope): VirtualFileFinder {
    println("MetaVirtualFileFinderFactory.create: $scope")
    return delegate.create(scope)
  }
}
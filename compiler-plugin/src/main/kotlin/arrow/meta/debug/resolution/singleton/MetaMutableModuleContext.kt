package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.com.intellij.openapi.project.Project
import org.jetbrains.kotlin.context.MutableModuleContext
import org.jetbrains.kotlin.descriptors.impl.ModuleDescriptorImpl
import org.jetbrains.kotlin.storage.ExceptionTracker
import org.jetbrains.kotlin.storage.StorageManager
/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 * Nothing else than a composed interface encompassing GlobalContext for more investigation check [MutableModuleContextImpl]
 **/
class MetaMutableModuleContext(val delegate: MutableModuleContext) : MutableModuleContext by delegate{
  override val exceptionTracker: ExceptionTracker
    get() = delegate.exceptionTracker
  override val module: ModuleDescriptorImpl
    get() = delegate.module
  override val project: Project
    get() = delegate.project
  override val storageManager: StorageManager
    get() = delegate.storageManager
}
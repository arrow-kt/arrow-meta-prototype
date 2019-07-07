package arrow.meta.utils

import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.descriptors.NotFoundClasses
import org.jetbrains.kotlin.resolve.calls.checkers.AbstractReflectionApiCallChecker
import org.jetbrains.kotlin.resolve.calls.checkers.CallCheckerContext
import org.jetbrains.kotlin.storage.StorageManager

class MetaAbstractReflectionApiCallChecker(
  module: ModuleDescriptor,
  notFoundClasses: NotFoundClasses,
  storageManager: StorageManager
) : AbstractReflectionApiCallChecker(module, notFoundClasses, storageManager){
  override val isWholeReflectionApiAvailable: Boolean
    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

  override fun report(element: PsiElement, context: CallCheckerContext) {
    println("MetaAbstractReflectionApiCallChecker.report: $element")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}
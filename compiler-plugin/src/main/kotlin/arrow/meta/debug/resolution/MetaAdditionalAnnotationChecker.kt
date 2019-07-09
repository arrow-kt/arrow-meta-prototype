package arrow.meta.debug.resolution

import org.jetbrains.kotlin.descriptors.annotations.KotlinTarget
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import org.jetbrains.kotlin.resolve.AdditionalAnnotationChecker
import org.jetbrains.kotlin.resolve.BindingTrace

class MetaAdditionalAnnotationChecker(val delegate : AdditionalAnnotationChecker) : AdditionalAnnotationChecker by delegate {
  override fun checkEntries(entries: List<KtAnnotationEntry>, actualTargets: List<KotlinTarget>, trace: BindingTrace) {
    println("MetaAdditionalAnnotationChecker.checkEntries: $entries, $actualTargets, $trace")
    return delegate.checkEntries(entries, actualTargets, trace)
  }
}
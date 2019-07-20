package arrow.meta.debug.resolution

import org.jetbrains.kotlin.config.LanguageVersionSettings
import org.jetbrains.kotlin.resolve.AnalyzerExtensions
import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.resolve.BodiesResolveContext
import org.jetbrains.kotlin.resolve.inline.ReasonableInlineRule

class MetaAnalyzerExtensions(
  trace: BindingTrace,
  reasonableInlineRules: Iterable<ReasonableInlineRule>,
  languageVersionSettings: LanguageVersionSettings
) :
  AnalyzerExtensions(
    trace, reasonableInlineRules, languageVersionSettings
  ) {
  override fun process(bodiesResolveContext: BodiesResolveContext) {
    println("MetaAnalyzerExtensions.process: $bodiesResolveContext")
    super.process(bodiesResolveContext)
  }
}
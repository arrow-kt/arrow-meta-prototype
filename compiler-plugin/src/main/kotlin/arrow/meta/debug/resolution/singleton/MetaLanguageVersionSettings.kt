package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.config.AnalysisFlag
import org.jetbrains.kotlin.config.ApiVersion
import org.jetbrains.kotlin.config.LanguageFeature
import org.jetbrains.kotlin.config.LanguageVersion
import org.jetbrains.kotlin.config.LanguageVersionSettings

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaLanguageVersionSettings(val delegate: LanguageVersionSettings) : LanguageVersionSettings by delegate {
  override val apiVersion: ApiVersion
    get() = delegate.apiVersion
  override val languageVersion: LanguageVersion
    get() = delegate.languageVersion
  
  override fun getFeatureSupport(feature: LanguageFeature): LanguageFeature.State {
    println("MetaLanguageVersionSettings.getFeatureSupport: $feature")
    return delegate.getFeatureSupport(feature)
  }

  override fun <T> getFlag(flag: AnalysisFlag<T>): T {
    println("MetaLanguageVersionSettings.getFlag: $flag")
    return delegate.getFlag(flag)
  }

  override fun isPreRelease(): Boolean {
    println("MetaLanguageVersionSettings.isPreRelease:")
    return delegate.isPreRelease()
  }
}
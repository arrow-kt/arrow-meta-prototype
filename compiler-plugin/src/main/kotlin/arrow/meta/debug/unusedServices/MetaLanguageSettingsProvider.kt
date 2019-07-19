package arrow.meta.debug.unusedServices

import org.jetbrains.kotlin.analyzer.LanguageSettingsProvider
import org.jetbrains.kotlin.analyzer.ModuleInfo
import org.jetbrains.kotlin.com.intellij.openapi.project.Project
import org.jetbrains.kotlin.config.LanguageVersionSettings
import org.jetbrains.kotlin.config.TargetPlatformVersion

/**
 * @UnusedService Currently we need to investigate how our costume functions can be executed.
 * Meaning we need to hook into the compiler earlier than Analysis. Either with Reflection or else
 */
class MetaLanguageSettingsProvider(val delegate: LanguageSettingsProvider) : LanguageSettingsProvider by delegate {
  override fun getLanguageVersionSettings(moduleInfo: ModuleInfo, project: Project, isReleaseCoroutines: Boolean?): LanguageVersionSettings {
    println("MetaLanguageSettingsProvider.getLanguageVersionSettings: $moduleInfo, $project, $isReleaseCoroutines")
    return delegate.getLanguageVersionSettings(moduleInfo, project, isReleaseCoroutines)
  }

  override fun getTargetPlatform(moduleInfo: ModuleInfo, project: Project): TargetPlatformVersion {
    println("MetaLanguageSettingsProvider.getTargetPlatform: $moduleInfo, $project")
    return delegate.getTargetPlatform(moduleInfo, project)
  }
}
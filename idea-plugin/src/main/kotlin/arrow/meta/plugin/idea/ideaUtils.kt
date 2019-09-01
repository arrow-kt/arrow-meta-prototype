package arrow.meta.plugin.idea

import org.jetbrains.kotlin.analyzer.ModuleInfo
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.idea.core.unwrapModuleSourceInfo
import org.jetbrains.kotlin.idea.facet.KotlinFacet
import org.jetbrains.kotlin.resolve.descriptorUtil.module

fun <T> getIfEnabledOn(clazz: ClassDescriptor, body: () -> T): T? {
  val module = clazz.module.getCapability(ModuleInfo.Capability)?.unwrapModuleSourceInfo()?.module ?: return null
  val facet = KotlinFacet.get(module) ?: return null
  val pluginClasspath = facet.configuration.settings.compilerArguments?.pluginClasspaths ?: return null
  if (pluginClasspath.none(ArrowImportHandler::isPluginJarPath)) return null
  return body()
}

fun runIfEnabledOn(clazz: ClassDescriptor, body: () -> Unit) { getIfEnabledOn<Unit>(clazz, body) }
package arrow.meta.utils

import org.jetbrains.kotlin.resolve.ImportPath
import org.jetbrains.kotlin.resolve.MultiTargetPlatform
import org.jetbrains.kotlin.resolve.PlatformConfigurator
import org.jetbrains.kotlin.resolve.TargetPlatform
import org.jetbrains.kotlin.storage.StorageManager

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 * Hijacks Default Imports we can hook into this service earlier than [arrow.meta.services.MetaPlatformDependentAnalyzerServices]
 **/
class MetaJvmTargetPlatform(val delegate: TargetPlatform) : TargetPlatform("JVM") {
  override val multiTargetPlatform: MultiTargetPlatform
    get() = delegate.multiTargetPlatform
  override val platformConfigurator: PlatformConfigurator
    get() = delegate.platformConfigurator
  override val defaultLowPriorityImports: List<ImportPath> = delegate.defaultLowPriorityImports

  override fun computePlatformSpecificDefaultImports(storageManager: StorageManager, result: MutableList<ImportPath>) {
    println("MetaJvmTargetPlatform.computePlatformSpecificDefaultImports: $storageManager, $result")
    // result.add(ImportPath.fromString("arrow.core.*")) <- after we find a method to add that correctly
    TODO()
  }
}
/*
* enable Debvug mode in Arrow Meta
* so delegate is missing
*
* */
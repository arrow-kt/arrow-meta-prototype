package arrow.meta.debug.resolution.singleton

import com.intellij.psi.search.GlobalSearchScope
import org.jetbrains.kotlin.config.LanguageVersionSettings
import org.jetbrains.kotlin.load.kotlin.PackagePartProvider
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.serialization.deserialization.MetadataPartProvider

interface MetaPartProvider : PackagePartProvider, MetadataPartProvider // composed into one interface, solely to provide one delegate

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaJvmPackagePartProvider(
  val languageVersionSettings: LanguageVersionSettings,
  private val scope: GlobalSearchScope, // TODO: scope <- still WIP, because it bails in TypeResolution.
  // I reckon that the scope variable is not available at the aforementioned stage
  val delegate: MetaPartProvider
) : MetaPartProvider by delegate {
  override fun findPackageParts(packageFqName: String): List<String> {
    println("MetaJvmPackagePartProvider.findPackageParts: $packageFqName")
    return delegate.findPackageParts(packageFqName)
  }

  override fun getAnnotationsOnBinaryModule(moduleName: String): List<ClassId> {
    println("MetaJvmPackagePartProvider.getAnnotationsOnBinaryModule: $moduleName")
    return delegate.getAnnotationsOnBinaryModule(moduleName)
  }

  override fun findMetadataPackageParts(packageFqName: String): List<String> {
    println("MetaJvmPackagePartProvider.findMetadataPackageParts: $packageFqName")
    return delegate.findMetadataPackageParts(packageFqName)
  }
}
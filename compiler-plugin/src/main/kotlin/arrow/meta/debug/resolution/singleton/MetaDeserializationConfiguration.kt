package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.serialization.deserialization.DeserializationConfiguration

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 * check out [CompilerDeserializationConfiguration]
 **/
class MetaDeserializationConfiguration(val delegate: DeserializationConfiguration) : DeserializationConfiguration by delegate {
  override val isJvmPackageNameSupported: Boolean
    get() = delegate.isJvmPackageNameSupported
  override val readDeserializedContracts: Boolean
    get() = delegate.readDeserializedContracts
  override val releaseCoroutines: Boolean
    get() = delegate.releaseCoroutines
  override val reportErrorsOnPreReleaseDependencies: Boolean
    get() = delegate.reportErrorsOnPreReleaseDependencies
  override val skipMetadataVersionCheck: Boolean
    get() = delegate.skipMetadataVersionCheck
  override val typeAliasesAllowed: Boolean
    get() = delegate.typeAliasesAllowed
}
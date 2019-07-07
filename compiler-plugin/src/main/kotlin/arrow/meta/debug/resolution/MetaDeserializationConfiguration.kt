package arrow.meta.debug.resolution

import org.jetbrains.kotlin.serialization.deserialization.DeserializationConfiguration

class MetaDeserializationConfiguration(val delegate: DeserializationConfiguration) : DeserializationConfiguration by delegate {
  override val isJvmPackageNameSupported: Boolean = delegate.isJvmPackageNameSupported

  override val readDeserializedContracts: Boolean = delegate.readDeserializedContracts

  override val releaseCoroutines: Boolean = delegate.releaseCoroutines

  override val reportErrorsOnPreReleaseDependencies: Boolean = delegate.reportErrorsOnPreReleaseDependencies

  override val skipMetadataVersionCheck: Boolean = delegate.skipMetadataVersionCheck

  override val typeAliasesAllowed: Boolean = delegate.typeAliasesAllowed
}

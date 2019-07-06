package arrow.meta.utils

import org.jetbrains.kotlin.serialization.deserialization.DeserializationConfiguration

class MetaDeserializationConfiguration: DeserializationConfiguration {
  override val isJvmPackageNameSupported: Boolean = TODO()

  override val readDeserializedContracts: Boolean = TODO()

  override val releaseCoroutines: Boolean = TODO()

  override val reportErrorsOnPreReleaseDependencies: Boolean = TODO()

  override val skipMetadataVersionCheck: Boolean = TODO()

  override val typeAliasesAllowed: Boolean = TODO()
}
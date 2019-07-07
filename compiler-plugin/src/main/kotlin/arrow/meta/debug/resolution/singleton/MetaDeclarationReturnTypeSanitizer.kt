package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.config.LanguageVersionSettings
import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.resolve.DeclarationReturnTypeSanitizer
import org.jetbrains.kotlin.types.UnwrappedType
import org.jetbrains.kotlin.types.WrappedTypeFactory

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaDeclarationReturnTypeSanitizer(val delegate: DeclarationReturnTypeSanitizer = DeclarationReturnTypeSanitizer.Default) : DeclarationReturnTypeSanitizer by delegate {
  override fun sanitizeReturnType(inferred: UnwrappedType, wrappedTypeFactory: WrappedTypeFactory, trace: BindingTrace, languageVersionSettings: LanguageVersionSettings): UnwrappedType {
    println("MetaDeclarationReturnTypeSanitizer.sanitizeReturnType: $inferred, $wrappedTypeFactory, $trace, $languageVersionSettings")
    return delegate.sanitizeReturnType(inferred, wrappedTypeFactory, trace, languageVersionSettings)
  }
}
package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.resolve.scopes.SyntheticScope
import org.jetbrains.kotlin.resolve.scopes.SyntheticScopes

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaSyntheticScopes(val delegate: SyntheticScopes) : SyntheticScopes by delegate {
  override val scopes: Collection<SyntheticScope>
    get() = delegate.scopes
}
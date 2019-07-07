package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.incremental.components.LookupTracker
import org.jetbrains.kotlin.incremental.components.Position
import org.jetbrains.kotlin.incremental.components.ScopeKind

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaLookupTracker(val delegate: LookupTracker) : LookupTracker by delegate {
  override val requiresPosition: Boolean
    get() = delegate.requiresPosition

  override fun record(filePath: String, position: Position, scopeFqName: String, scopeKind: ScopeKind, name: String) {
    println(" MetaLookupTracker.record: $filePath, $position, $scopeFqName, $scopeKind, $name")
    delegate.record(filePath, position, scopeFqName, scopeKind, name)
  }
}
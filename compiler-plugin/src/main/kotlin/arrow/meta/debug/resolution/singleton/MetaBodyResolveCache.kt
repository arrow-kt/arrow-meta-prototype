package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.BodyResolveCache

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 * check out [org.jetbrains.kotlin.resolve.BodyResolveCache.ThrowException]
 **/
class MetaBodyResolveCache(val delegate: BodyResolveCache) : BodyResolveCache by delegate {
  override fun resolveFunctionBody(function: KtNamedFunction): BindingContext {
    println("MetaBodyResolveCache.resolveFunctionBody: $function")
    return delegate.resolveFunctionBody(function)
  }
}
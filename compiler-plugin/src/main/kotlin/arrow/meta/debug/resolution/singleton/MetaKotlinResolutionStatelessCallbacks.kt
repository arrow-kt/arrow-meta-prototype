package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.builtins.KotlinBuiltIns
import org.jetbrains.kotlin.config.LanguageVersionSettings
import org.jetbrains.kotlin.descriptors.CallableDescriptor
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor
import org.jetbrains.kotlin.descriptors.ValueParameterDescriptor
import org.jetbrains.kotlin.resolve.calls.components.KotlinResolutionCallbacks
import org.jetbrains.kotlin.resolve.calls.components.KotlinResolutionStatelessCallbacks
import org.jetbrains.kotlin.resolve.calls.inference.components.ConstraintInjector
import org.jetbrains.kotlin.resolve.calls.model.CallableReferenceKotlinCallArgument
import org.jetbrains.kotlin.resolve.calls.model.KotlinCall
import org.jetbrains.kotlin.resolve.calls.model.KotlinCallArgument
import org.jetbrains.kotlin.resolve.calls.model.KotlinResolutionCandidate
import org.jetbrains.kotlin.resolve.calls.model.SimpleKotlinCallArgument
import org.jetbrains.kotlin.resolve.calls.results.SimpleConstraintSystem
import org.jetbrains.kotlin.resolve.calls.tower.ImplicitScopeTower

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaKotlinResolutionStatelessCallbacks(val delegate: KotlinResolutionStatelessCallbacks) : KotlinResolutionStatelessCallbacks by delegate {
  override fun createConstraintSystemForOverloadResolution(constraintInjector: ConstraintInjector, builtIns: KotlinBuiltIns): SimpleConstraintSystem {
    println("MetaKotlinResolutionStatelessCallbacks.createConstraintSystemForOverloadResolution: $constraintInjector, $builtIns")
    return delegate.createConstraintSystemForOverloadResolution(constraintInjector, builtIns)
  }

  override fun getScopeTowerForCallableReferenceArgument(argument: CallableReferenceKotlinCallArgument): ImplicitScopeTower {
    println("MetaKotlinResolutionStatelessCallbacks.getScopeTowerForCallableReferenceArgument: $argument")
    return delegate.getScopeTowerForCallableReferenceArgument(argument)
  }

  override fun getVariableCandidateIfInvoke(functionCall: KotlinCall): KotlinResolutionCandidate? {
    println("MetaKotlinResolutionStatelessCallbacks.getVariableCandidateIfInvoke: $functionCall")
    return delegate.getVariableCandidateIfInvoke(functionCall)
  }

  override fun isApplicableCallForBuilderInference(descriptor: CallableDescriptor, languageVersionSettings: LanguageVersionSettings): Boolean {
    println("MetaKotlinResolutionStatelessCallbacks.isApplicableCallForBuilderInference: $descriptor, $languageVersionSettings")
    return delegate.isApplicableCallForBuilderInference(descriptor, languageVersionSettings)
  }

  override fun isCoroutineCall(argument: KotlinCallArgument, parameter: ValueParameterDescriptor): Boolean {
    println("MetaKotlinResolutionStatelessCallbacks.isCoroutineCall: $argument, $parameter")
    return delegate.isCoroutineCall(argument, parameter)
  }

  override fun isDescriptorFromSource(descriptor: CallableDescriptor): Boolean {
    println("MetaKotlinResolutionStatelessCallbacks.isDescriptorFromSource: $descriptor")
    return delegate.isDescriptorFromSource(descriptor)
  }

  override fun isHiddenInResolution(descriptor: DeclarationDescriptor, kotlinCall: KotlinCall, resolutionCallbacks: KotlinResolutionCallbacks): Boolean {
    println("MetaKotlinResolutionStatelessCallbacks.isHiddenInResolution: $descriptor, $kotlinCall, $resolutionCallbacks")
    return delegate.isHiddenInResolution(descriptor, kotlinCall, resolutionCallbacks)
  }

  override fun isInfixCall(kotlinCall: KotlinCall): Boolean {
    println("MetaKotlinResolutionStatelessCallbacks.isInfixCall: $kotlinCall")
    return delegate.isInfixCall(kotlinCall)
  }

  override fun isOperatorCall(kotlinCall: KotlinCall): Boolean {
    println("MetaKotlinResolutionStatelessCallbacks.isOperatorCall: $kotlinCall")
    return delegate.isOperatorCall(kotlinCall)
  }

  override fun isSuperExpression(receiver: SimpleKotlinCallArgument?): Boolean {
    println("MetaKotlinResolutionStatelessCallbacks.isSuperExpression: $receiver")
    return delegate.isSuperExpression(receiver)
  }

  override fun isSuperOrDelegatingConstructorCall(kotlinCall: KotlinCall): Boolean {
    println("MetaKotlinResolutionStatelessCallbacks.isSuperOrDelegatingConstructorCall: $kotlinCall")
    return delegate.isSuperOrDelegatingConstructorCall(kotlinCall)
  }
}
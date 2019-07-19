package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.descriptors.DeclarationDescriptor
import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.descriptors.VariableDescriptor
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.calls.context.ResolutionContext
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowValue
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowValueFactory
import org.jetbrains.kotlin.resolve.scopes.receivers.ReceiverValue
import org.jetbrains.kotlin.types.KotlinType

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 * Hence, we need to intercept the Singleton [DataFlowValueFactoryImpl] from the container with JavaReflection
 * Delegate the functions we want to keep and pass the one we want to modify to the container as a new Singleton
 **/
class MetaDataFlowValueFactory(val delegate: DataFlowValueFactory) : DataFlowValueFactory by delegate{
  override fun createDataFlowValue(expression: KtExpression, type: KotlinType, bindingContext: BindingContext, containingDeclarationOrModule: DeclarationDescriptor): DataFlowValue {
    println("MetaDataFlowValueFactory.createDataFlowValue: $expression, $type, $bindingContext, $containingDeclarationOrModule")
    return delegate.createDataFlowValue(expression, type, bindingContext, containingDeclarationOrModule)
  }

  override fun createDataFlowValue(expression: KtExpression, type: KotlinType, resolutionContext: ResolutionContext<*>): DataFlowValue {
    println("MetaDataFlowValueFactory.createDataFlowValue: $expression, $type, $resolutionContext")
    return delegate.createDataFlowValue(expression, type, resolutionContext)
  }

  override fun createDataFlowValue(receiverValue: ReceiverValue, bindingContext: BindingContext, containingDeclarationOrModule: DeclarationDescriptor): DataFlowValue {
    println("MetaDataFlowValueFactory.createDataFlowValue: $receiverValue, $bindingContext, $containingDeclarationOrModule")
    return delegate.createDataFlowValue(receiverValue, bindingContext, containingDeclarationOrModule)
  }

  override fun createDataFlowValue(receiverValue: ReceiverValue, resolutionContext: ResolutionContext<*>): DataFlowValue {
    println("MetaDataFlowValueFactory.createDataFlowValue: $receiverValue, $resolutionContext")
    return delegate.createDataFlowValue(receiverValue, resolutionContext)
  }

  override fun createDataFlowValueForProperty(property: KtProperty, variableDescriptor: VariableDescriptor, bindingContext: BindingContext, usageContainingModule: ModuleDescriptor?): DataFlowValue {
    println("MetaDataFlowValueFactory.createDataFlowValueForProperty: $property, $variableDescriptor, $bindingContext, $usageContainingModule")
    return delegate.createDataFlowValueForProperty(property, variableDescriptor, bindingContext, usageContainingModule)
  }

  override fun createDataFlowValueForStableReceiver(receiver: ReceiverValue): DataFlowValue {
    println("MetaDataFlowValueFactory.createDataFlowValueForStableReceiver: $receiver")
    return delegate.createDataFlowValueForStableReceiver(receiver)
  }

}
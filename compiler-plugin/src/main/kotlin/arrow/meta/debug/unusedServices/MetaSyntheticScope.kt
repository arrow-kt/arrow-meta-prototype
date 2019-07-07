package arrow.meta.debug.unusedServices

import org.jetbrains.kotlin.descriptors.ConstructorDescriptor
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.descriptors.PropertyDescriptor
import org.jetbrains.kotlin.incremental.components.LookupLocation
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.resolve.scopes.ResolutionScope
import org.jetbrains.kotlin.resolve.scopes.SyntheticScope
import org.jetbrains.kotlin.types.KotlinType

/**
 * @UnusedService Currently we need to investigate how our costume functions can be executed.
 * Meaning we need to hook into the compiler earlier than Analysis. Either with Reflection or else
 */
class MetaSyntheticScope(val delegate: SyntheticScope) : SyntheticScope by delegate {
  override fun getSyntheticConstructor(constructor: ConstructorDescriptor): ConstructorDescriptor? {
    println("MetaSyntheticScope.getSyntheticConstructor: $constructor")
    return delegate.getSyntheticConstructor(constructor)
  }

  override fun getSyntheticConstructors(scope: ResolutionScope): Collection<FunctionDescriptor> {
    println("MetaSyntheticScope.getSyntheticConstructors: $scope")
    return delegate.getSyntheticConstructors(scope)
  }

  override fun getSyntheticConstructors(scope: ResolutionScope, name: Name, location: LookupLocation): Collection<FunctionDescriptor> {
    println("MetaSyntheticScope.getSyntheticConstructors: $scope, $name, $location")
    return delegate.getSyntheticConstructors(scope, name, location)
  }

  override fun getSyntheticExtensionProperties(receiverTypes: Collection<KotlinType>, location: LookupLocation): Collection<PropertyDescriptor> {
    println("MetaSyntheticScope.getSyntheticExtensionProperties: $receiverTypes, $location")
    return delegate.getSyntheticExtensionProperties(receiverTypes, location)
  }

  override fun getSyntheticExtensionProperties(receiverTypes: Collection<KotlinType>, name: Name, location: LookupLocation): Collection<PropertyDescriptor> {
    println("MetaSyntheticScope.getSyntheticExtensionProperties: $receiverTypes, $name, $location")
    return delegate.getSyntheticExtensionProperties(receiverTypes, name, location)
  }

  override fun getSyntheticMemberFunctions(receiverTypes: Collection<KotlinType>): Collection<FunctionDescriptor> {
    println("MetaSyntheticScope.getSyntheticMemberFunctions: $receiverTypes")
    return delegate.getSyntheticMemberFunctions(receiverTypes)
  }

  override fun getSyntheticMemberFunctions(receiverTypes: Collection<KotlinType>, name: Name, location: LookupLocation): Collection<FunctionDescriptor> {
    println("MetaSyntheticScope.getSyntheticMemberFunctions: $receiverTypes, $name, $location")
    return delegate.getSyntheticMemberFunctions(receiverTypes, name, location)
  }

  override fun getSyntheticStaticFunctions(scope: ResolutionScope): Collection<FunctionDescriptor> {
    println("MetaSyntheticScope.getSyntheticStaticFunctions: $scope")
    return delegate.getSyntheticStaticFunctions(scope)
  }

  override fun getSyntheticStaticFunctions(scope: ResolutionScope, name: Name, location: LookupLocation): Collection<FunctionDescriptor> {
    println("MetaSyntheticScope.getSyntheticStaticFunctions: $scope, $name, $location")
    return delegate.getSyntheticStaticFunctions(scope, name, location)
  }
}
package arrow.meta.utils

import org.jetbrains.kotlin.descriptors.CallableDescriptor
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.metadata.ProtoBuf
import org.jetbrains.kotlin.metadata.deserialization.TypeTable
import org.jetbrains.kotlin.serialization.deserialization.ContractDeserializer
import org.jetbrains.kotlin.serialization.deserialization.ContractProvider
import org.jetbrains.kotlin.serialization.deserialization.TypeDeserializer

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaContractDeserializer : ContractDeserializer {
  override fun deserializeContractFromFunction(proto: ProtoBuf.Function, ownerFunction: FunctionDescriptor, typeTable: TypeTable, typeDeserializer: TypeDeserializer): Pair<CallableDescriptor.UserDataKey<*>, ContractProvider>? {
    println("MetaContractDeserializer.deserializeContractFromFunction: $proto")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}
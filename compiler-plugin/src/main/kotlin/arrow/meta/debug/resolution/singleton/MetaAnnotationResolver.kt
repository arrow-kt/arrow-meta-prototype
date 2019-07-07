package arrow.meta.debug.resolution.singleton

import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.descriptors.ValueParameterDescriptor
import org.jetbrains.kotlin.descriptors.annotations.Annotations
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import org.jetbrains.kotlin.resolve.AnnotationResolver
import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.resolve.calls.model.ResolvedValueArgument
import org.jetbrains.kotlin.resolve.calls.results.OverloadResolutionResults
import org.jetbrains.kotlin.resolve.constants.ConstantValue
import org.jetbrains.kotlin.resolve.scopes.LexicalScope
import org.jetbrains.kotlin.types.KotlinType

/***
 * @throws InvalidCardinalityException If not added correctly - the compiler requires a Singleton
 **/
class MetaAnnotationResolver(val delegate: AnnotationResolver) : AnnotationResolver() {
  override fun getAnnotationArgumentValue(trace: BindingTrace, valueParameter: ValueParameterDescriptor, resolvedArgument: ResolvedValueArgument): ConstantValue<*>? {
    println("MetaAnnotationResolver.getAnnotationArgumentValue $resolvedArgument")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun resolveAnnotationCall(annotationEntry: KtAnnotationEntry, scope: LexicalScope, trace: BindingTrace): OverloadResolutionResults<FunctionDescriptor> {
    println("MetaAnnotationResolver.resolveAnnotationCall $annotationEntry")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun resolveAnnotationEntries(scope: LexicalScope, annotationEntries: List<KtAnnotationEntry>, trace: BindingTrace, shouldResolveArguments: Boolean): Annotations {
    println("MetaAnnotationResolver.resolveAnnotationEntries $scope")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun resolveAnnotationType(scope: LexicalScope, entryElement: KtAnnotationEntry, trace: BindingTrace): KotlinType {
    println("MetaAnnotationResolver.resolveAnnotationType $scope")
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

}
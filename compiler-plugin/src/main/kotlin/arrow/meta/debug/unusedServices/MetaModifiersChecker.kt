package arrow.meta.debug.unusedServices

import org.jetbrains.kotlin.config.LanguageVersionSettings
import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.incremental.components.ExpectActualTracker
import org.jetbrains.kotlin.resolve.AnnotationChecker
import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.resolve.ModifiersChecker
import org.jetbrains.kotlin.resolve.checkers.DeclarationChecker
import org.jetbrains.kotlin.resolve.deprecation.DeprecationResolver

/**
 * @UnusedService cannot access ModifiersCheckingProcedure
 * @DontUncomment It breaks Runtime execution
 */
/*
class MetaModifiersChecker(
  val annotationChecker: AnnotationChecker,
  val declarationCheckers: Iterable<DeclarationChecker>,
  val languageVersionSettings: LanguageVersionSettings,
  val expectActualTracker: ExpectActualTracker,
  val deprecationResolver: DeprecationResolver,
  val moduleDescriptor: ModuleDescriptor
) :
  ModifiersChecker(
    annotationChecker,
    declarationCheckers,
    languageVersionSettings,
    expectActualTracker,
    deprecationResolver,
    moduleDescriptor
  ) {
  override fun withTrace(trace: BindingTrace): ModifiersCheckingProcedure {
    println("MetaModifiersChecker.withTrace: $trace")
    return super.withTrace(trace)
  }
}*/

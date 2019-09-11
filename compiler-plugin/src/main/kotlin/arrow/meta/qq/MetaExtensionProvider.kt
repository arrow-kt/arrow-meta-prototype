package arrow.meta.qq

import com.intellij.codeInsight.ContainerProvider
import com.intellij.codeInsight.intention.IntentionAction
import com.intellij.lang.jvm.JvmClass
import com.intellij.lang.jvm.JvmMethod
import com.intellij.lang.jvm.JvmModifiersOwner
import com.intellij.lang.jvm.actions.AnnotationRequest
import com.intellij.lang.jvm.actions.ChangeModifierRequest
import com.intellij.lang.jvm.actions.ChangeParametersRequest
import com.intellij.lang.jvm.actions.CreateConstructorRequest
import com.intellij.lang.jvm.actions.CreateFieldRequest
import com.intellij.lang.jvm.actions.CreateMethodRequest
import com.intellij.lang.jvm.actions.JvmElementActionsFactory
import com.intellij.openapi.extensions.ExtensionPointName
import com.intellij.usages.impl.rules.UsageType
import com.intellij.usages.impl.rules.UsageTypeProvider
import org.jetbrains.kotlin.com.intellij.ide.IconProvider
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.diagnostics.Diagnostic
import org.jetbrains.kotlin.resolve.diagnostics.DiagnosticSuppressor
import javax.swing.Icon
import com.intellij.psi.PsiElement as Psi
import org.jetbrains.kotlin.com.intellij.openapi.extensions.ExtensionPointName as EP

/*interface MetaIconProvider {
  fun registerIconProvider(f: MetaIconProvider.(psiElement: PsiElement, flags: Int) -> Icon?): Unit
  fun iconFor(psiElement: PsiElement): Icon
}*/

interface MetaExtensionProvider {
  fun <E> registerExtension(epName: ExtensionPointName<E>, impl: E): Unit?

  fun <E> registerExtension(epName: EP<E>, impl: E): Unit?

  companion object {
    /**
     * extensionProvider(
     *  epName = UsageTypeProvider.EP_NAME,
     *  impl = addUsageTypeProvider {
     *    if (it.safeAs<KtNamedFunction>()?.name == "foo")
     *     UsageType("Referenced as foo") else null
     *    }
     *  )
     */
    fun addUsageTypeProvider(f: (psiElement: Psi) -> UsageType?) = // f:(Psi) -> Option<UsageType>
      UsageTypeProvider { if (it != null) f(it) else null }


    /**
     * extensionProvider(
     *  epName = IconProvider.EXTENSION_POINT_NAME,
     *   impl = addIcon(KotlinIcons.SUSPEND_CALL) { psiElement, flag ->
     *     psiElement.safeAs<KtNamedFunction>()?.name == "foo"
     *  }
     * )
     */
    fun addIcon(icon: Icon, matchOn: (psiElement: PsiElement, flag: Int) -> Boolean) = object : IconProvider() {
      override fun getIcon(p0: PsiElement, p1: Int): Icon? =
        if (matchOn(p0, p1)) icon else null
    }

    fun addDiagnosticSuppressor(suppress: (diagnostic: Diagnostic) -> Boolean): DiagnosticSuppressor = object : DiagnosticSuppressor {
      override fun isSuppressed(diagnostic: Diagnostic): Boolean =
        suppress(diagnostic)
    }

    /**
     * ide version the kotlin version is an Interface without functions to implement
     */
    fun addContainerProvider(f: (Psi) -> Psi?): ContainerProvider =
      ContainerProvider { f(it) }

    /**
     * check [org.jetbrains.kotlin.idea.quickfix.crossLanguage.KotlinElementActionsFactory]
     */
    fun addJvmElementActionsFactory(
      annotationActions: (target: JvmModifiersOwner, request: AnnotationRequest) -> List<IntentionAction> =
        { _, _ -> emptyList<IntentionAction>() },
      constructorActions: (targetClass: JvmClass, request: CreateConstructorRequest) -> List<IntentionAction> =
        { _, _ -> emptyList<IntentionAction>() },
      fieldActions: (targetClass: JvmClass, request: CreateFieldRequest) -> List<IntentionAction> =
        { _, _ -> emptyList<IntentionAction>() },
      methodActions: (targetClass: JvmClass, request: CreateMethodRequest) -> List<IntentionAction> =
        { _, _ -> emptyList<IntentionAction>() },
      changeModifierActions: (target: JvmModifiersOwner, request: ChangeModifierRequest) -> List<IntentionAction> =
        { _, _ -> emptyList<IntentionAction>() },
      changeParameterActions: (target: JvmMethod, request: ChangeParametersRequest) -> List<IntentionAction> =
        { _, _ -> emptyList<IntentionAction>() }
    ): JvmElementActionsFactory = object : JvmElementActionsFactory() {
      override fun createAddAnnotationActions(target: JvmModifiersOwner, request: AnnotationRequest): List<IntentionAction> =
        annotationActions(target, request)

      override fun createAddConstructorActions(targetClass: JvmClass, request: CreateConstructorRequest): List<IntentionAction> =
        constructorActions(targetClass, request)

      override fun createAddFieldActions(targetClass: JvmClass, request: CreateFieldRequest): List<IntentionAction> =
        fieldActions(targetClass, request)

      override fun createAddMethodActions(targetClass: JvmClass, request: CreateMethodRequest): List<IntentionAction> =
        methodActions(targetClass, request)

      override fun createChangeModifierActions(target: JvmModifiersOwner, request: ChangeModifierRequest): List<IntentionAction> =
        changeModifierActions(target, request)

      override fun createChangeParametersActions(target: JvmMethod, request: ChangeParametersRequest): List<IntentionAction> =
        changeParameterActions(target, request)
    }

  }
}


/* currentProject is not available but after the Refactoring task
class MetaIdeExtensionProvider : MetaExtensionProvider {
  override fun <E> registerExtension(epName: com.intellij.openapi.extensions.ExtensionPointName<E>, impl: E): Unit? =
    currentProject()?.getComponent(CoreApplicationEnvironment::class.java)?.addExtension(epName, impl)
}*/

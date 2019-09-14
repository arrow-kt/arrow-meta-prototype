package arrow.meta.utils.ide.editor

import arrow.meta.extensions.MetaComponentRegistrar
import com.intellij.lang.ImportOptimizer
import com.intellij.lang.refactoring.NamesValidator
import com.intellij.lang.refactoring.RefactoringSupportProvider
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.refactoring.RefactoringActionHandler
import com.intellij.refactoring.changeSignature.ChangeSignatureHandler
import com.intellij.refactoring.rename.RenamePsiFileProcessor

fun addImportOptimizer(
  supports: (file: PsiFile?) -> Boolean,
  processFile: (file: PsiFile?) -> Runnable
): ImportOptimizer =
  object : ImportOptimizer {
    override fun supports(file: PsiFile?): Boolean = supports(file)

    override fun processFile(file: PsiFile?): Runnable = processFile(file)
  }

/**
 * Can be used for Introducing Variables or more
 */
fun addRefactoringActionHandler(
  invokeWithEditor: (project: Project, editor: Editor?, file: PsiFile?, dataContext: DataContext?) -> Unit,
  invoke: (project: Project, elements: Array<out PsiElement>, dataContext: DataContext?) -> Unit
): RefactoringActionHandler =
  object : RefactoringActionHandler {
    override fun invoke(project: Project, editor: Editor?, file: PsiFile?, dataContext: DataContext?) =
      invokeWithEditor(project, editor, file, dataContext)

    /**
     * Not called when introducing a variable
     */
    override fun invoke(project: Project, elements: Array<out PsiElement>, dataContext: DataContext?) =
      invoke(project, elements, dataContext)
  }

fun addNamesValidator(
  isKeyword: (name: String, project: Project?) -> Boolean,
  isIdentifier: (name: String, project: Project?) -> Boolean
): NamesValidator =
  object : NamesValidator {
    override fun isKeyword(name: String, project: Project?): Boolean =
      isKeyword(name, project)

    override fun isIdentifier(name: String, project: Project?): Boolean =
      isIdentifier(name, project)
  }

/**
 * TODO: Test Default values
 */
fun addRefactoringSupportProvider(
  introduceFunctionalParameterHandler: RefactoringActionHandler? = null,
  pushDownHandler: RefactoringActionHandler? = null,
  introduceFunctionalVariableHandler: RefactoringActionHandler? = null,
  isInplaceRenameAvailable: (element: PsiElement, context: PsiElement?) -> Boolean =
    { _, _ -> false },
  extractInterfaceHandler: RefactoringActionHandler? = null,
  introduceConstantHandler: RefactoringActionHandler? = null,
  introduceVariableHandler: RefactoringActionHandler? = null,
  introduceVariableHandlerOnPsi: (element: PsiElement?) -> RefactoringActionHandler? =
    { _ -> null },
  extractModuleHandler: RefactoringActionHandler? = null,
  isInplaceIntroduceAvailable: (element: PsiElement, context: PsiElement?) -> Boolean =
    { _, _ -> false },
  pullUpHandler: RefactoringActionHandler? = null,
  isSafeDeleteAvailable: (element: PsiElement) -> Boolean =
    { _ -> false },
  introduceFieldHandler: RefactoringActionHandler? = null,
  isMemberInplaceRenameAvailable: (element: PsiElement, context: PsiElement?) -> Boolean =
    { _, _ -> false },
  extractMethodHandler: RefactoringActionHandler? = null,
  changeSignatureHandler: ChangeSignatureHandler? = null,
  extractClassHandler: RefactoringActionHandler? = null,
  isAvailable: (context: PsiElement) -> Boolean =
    { _ -> true },
  extractSuperClassHandler: RefactoringActionHandler? = null,
  introduceParameterHandler: RefactoringActionHandler? = null
): RefactoringSupportProvider =
  object : RefactoringSupportProvider() {
    override fun getIntroduceFunctionalParameterHandler(): RefactoringActionHandler? =
      introduceFunctionalParameterHandler

    override fun getPushDownHandler(): RefactoringActionHandler? =
      pushDownHandler

    override fun getIntroduceFunctionalVariableHandler(): RefactoringActionHandler {
      return introduceFunctionalVariableHandler ?: super.getIntroduceFunctionalVariableHandler()
    }

    override fun isInplaceRenameAvailable(element: PsiElement, context: PsiElement?): Boolean =
      isInplaceRenameAvailable(element, context)

    override fun getExtractInterfaceHandler(): RefactoringActionHandler? =
      extractInterfaceHandler

    override fun getIntroduceConstantHandler(): RefactoringActionHandler? =
      introduceConstantHandler

    override fun getIntroduceVariableHandler(): RefactoringActionHandler? =
      introduceVariableHandler

    override fun getIntroduceVariableHandler(element: PsiElement?): RefactoringActionHandler? =
      introduceVariableHandlerOnPsi(element)

    override fun getExtractModuleHandler(): RefactoringActionHandler? =
      extractModuleHandler

    override fun isInplaceIntroduceAvailable(element: PsiElement, context: PsiElement?): Boolean =
      isInplaceIntroduceAvailable(element, context)

    override fun isSafeDeleteAvailable(element: PsiElement): Boolean =
      isSafeDeleteAvailable(element)

    override fun getPullUpHandler(): RefactoringActionHandler? =
      pullUpHandler

    override fun getIntroduceFieldHandler(): RefactoringActionHandler? =
      introduceFieldHandler

    override fun isMemberInplaceRenameAvailable(element: PsiElement, context: PsiElement?): Boolean =
      isMemberInplaceRenameAvailable(element, context)

    override fun getExtractMethodHandler(): RefactoringActionHandler? =
      extractMethodHandler

    override fun getChangeSignatureHandler(): ChangeSignatureHandler? =
      changeSignatureHandler

    override fun getExtractClassHandler(): RefactoringActionHandler? =
      extractClassHandler

    override fun isAvailable(context: PsiElement): Boolean =
      isAvailable(context)

    override fun getExtractSuperClassHandler(): RefactoringActionHandler? =
      extractSuperClassHandler

    override fun getIntroduceParameterHandler(): RefactoringActionHandler? =
      introduceParameterHandler
  }

/**
 * [RenamePsiFileProcessor.EP_NAME]
 */
fun MetaComponentRegistrar.addRenamePsiFileProcessor(): RenamePsiFileProcessor = TODO()
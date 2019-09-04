package arrow.meta.plugin.idea.icon

import com.intellij.codeInsight.daemon.LineMarkerInfo
import com.intellij.codeInsight.daemon.LineMarkerProvider
import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.idea.debugger.sequence.psi.resolveType
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtThrowExpression
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.typeUtil.isNothingOrNullableNothing
import org.jetbrains.kotlin.types.typeUtil.isUnit

class ImpureLineMarkerProvider : LineMarkerProvider {
  override fun getLineMarkerInfo(element: PsiElement): LineMarkerInfo<*>? = null

  override fun collectSlowLineMarkers(elements: MutableList<PsiElement>, result: MutableCollection<LineMarkerInfo<PsiElement>>) {
    //Simple Example
    addLineMarker(elements, result,
      match = { it is KtThrowExpression },
      infoMarker = { p ->
        arrowLineMarkerInfo(ArrowIcons.TEST, p,
          "Impure expression returning `Nothing` due to `throw` expression only allowed in `suspend` functions.\n" +
            "Generally, it is not recommended to `throw` Exceptions and instead to represent exceptional cases with datatypes and typeclasses as described https://next.arrow-kt.io/docs/patterns/error_handling/"
        )
      })
    /* WIP
    addLineMarker(result = result, elements = elements,
      match = {
        when (val x = it) {
          is KtThrowExpression -> true
          is KtNamedFunction -> with(x as KtNamedFunction) {
            !hasModifier(KtTokens.SUSPEND_KEYWORD) && // bailFast
              (resolveToDescriptorIfAny()?.let { f ->
                !f.isSynthesized && !f.isSuspendLambdaOrLocalFunction() &&
                  f.name.asString().let {
                    !it.startsWith("<get") && !it.startsWith("<set") && it != "<init>"
                  } && f.returnType?.let { r -> r.isUnit() || r.isNothingOrNullableNothing() } ?: false
              } ?: false)
                .or((this as KtExpression).checkExpressionPurity)
          }
          else -> false
        }
      },
      infoMarker = {
        when (it) {
          is KtThrowExpression -> arrowLineMarkerInfo(ArrowIcons.TEST, it,
            "Impure expression returning `Nothing` due to `throw` expression only allowed in `suspend` functions.\n" +
              "Generally, it is not recommended to `throw` Exceptions and instead to represent exceptional cases with datatypes and typeclasses as described https://next.arrow-kt.io/docs/patterns/error_handling/")
          is KtCallExpression -> arrowLineMarkerInfo(ArrowIcons.IMPURE_LINEMARKER_ICON, it,
            "Impure expression in function returning `Unit` or `Nothing` only allowed in `suspend` functions")
          is KtNamedFunction -> arrowLineMarkerInfo(ArrowIcons.IMPURE_LINEMARKER_ICON, it, "Unit or Nothing return on a non suspended function")
          else -> null
        }
      }
    )*/
  }
}

val KtExpression.checkExpressionPurity: Boolean
  get() {
    val expressionRetType: KotlinType = this.resolveType()
    return expressionRetType.isUnit() || expressionRetType.isNothingOrNullableNothing()
  }
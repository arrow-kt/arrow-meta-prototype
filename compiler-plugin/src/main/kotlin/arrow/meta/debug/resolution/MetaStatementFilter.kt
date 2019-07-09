package arrow.meta.debug.resolution

import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.resolve.StatementFilter

class MetaStatementFilter : StatementFilter() {
  override val filter: ((KtExpression) -> Boolean)? =
    super.filter
}
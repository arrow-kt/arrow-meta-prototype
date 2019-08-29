package arrow.meta.plugin.idea.inspection

import org.jetbrains.kotlin.idea.util.actualsForExpected
import org.jetbrains.kotlin.idea.util.liftToExpected
import org.jetbrains.kotlin.psi.KtDeclaration

fun KtDeclaration.withExpectedActuals(): List<KtDeclaration> {
  val expect = liftToExpected() ?: return listOf(this)
  val actuals = expect.actualsForExpected()
  return listOf(expect) + actuals
}

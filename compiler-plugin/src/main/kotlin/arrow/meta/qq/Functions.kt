package arrow.meta.qq

import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtFunction

interface Functions : Quote<KtElement, KtFunction, Functions.FuncScope> {
  class FuncScope(
    val name: Name,
    val parameterName: Name,
    val returnType: Name
  )

  /*override fun transform(ktElement: KtFunction): FuncScope =
    FuncScope(
      name = Name.identifier(ktElement.nameAsSafeName.identifier),
      parameterName = Name.identifier(ktElement.receiverTypeReference?.name.orEmpty())
    )

  companion object : Quote.Factory<KtElement, KtFunction, FuncScope> {
    override operator fun invoke(
      quasiQuoteContext: QuasiQuoteContext,
      containingDeclaration: KtElement,
      match: KtFunction.() -> Boolean,
      map: Functions.FuncScope.(quotedTemplate: KtFunction) -> List<String>
    ): Functions =
      object : Functions {
        override val quasiQuoteContext: QuasiQuoteContext = quasiQuoteContext
        override fun KtFunction.match(): Boolean = match(this)
        override fun Functions.FuncScope.map(quotedTemplate: KtFunction): List<String> = map(quotedTemplate)
        override val containingDeclaration: KtElement = containingDeclaration
      }
  }*/
}
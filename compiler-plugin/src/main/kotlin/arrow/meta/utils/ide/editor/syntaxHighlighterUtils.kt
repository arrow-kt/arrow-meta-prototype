package arrow.meta.utils.ide.editor

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.psi.tree.IElementType
import org.jetbrains.kotlin.lexer.KotlinLexer

fun addSyntaxHighlighterFactory(
  syntaxHighlighter: SyntaxHighlighter
): SyntaxHighlighterFactory =
  object : SingleLazyInstanceSyntaxHighlighterFactory() {
    override fun createHighlighter(): SyntaxHighlighter =
      syntaxHighlighter
  }

fun addSyntaxHighlighter(
  tokenHighlights: (tokenType: IElementType?) -> Array<TextAttributesKey>,
  highlightingLexer: Lexer = KotlinLexer()
): SyntaxHighlighter =
  object : SyntaxHighlighterBase() {
    override fun getTokenHighlights(tokenType: IElementType?): Array<TextAttributesKey> =
      tokenHighlights(tokenType)

    override fun getHighlightingLexer(): Lexer =
      highlightingLexer
  }

package me.tomassetti.kanvas

import me.tomassetti.lexers.SandyLexer
import org.antlr.v4.runtime.Lexer
import org.fife.ui.rsyntaxtextarea.Style
import org.fife.ui.rsyntaxtextarea.SyntaxScheme
import java.awt.Color

object sandySyntaxScheme : SyntaxScheme(true) {
    override fun getStyle(index: Int): Style {
        val style = Style()
        val color = when (index) {
            SandyLexer.VAR -> Color.GREEN
            SandyLexer.ASSIGN -> Color.GREEN
            SandyLexer.ASTERISK, SandyLexer.DIVISION, SandyLexer.PLUS, SandyLexer.MINUS -> Color.WHITE
            SandyLexer.DECLIT, SandyLexer.INTLIT -> Color.BLUE
            SandyLexer.ID -> Color.MAGENTA
            SandyLexer.LPAREN, SandyLexer.RPAREN -> Color.WHITE
            else -> null
        }
        if (color != null) {
            style.foreground = color
        }
        return style
    }
}

object sandyLanguageSupport : LanguageSupport {
    override val syntaxScheme: SyntaxScheme
        get() = sandySyntaxScheme
    override val antlrLexerFactory: AntlrLexerFactory
        get() = object : AntlrLexerFactory {
            override fun create(code: String): Lexer = SandyLexer(org.antlr.v4.runtime.ANTLRInputStream(code))
        }
}
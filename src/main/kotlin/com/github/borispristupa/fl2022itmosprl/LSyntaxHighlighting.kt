package com.github.borispristupa.fl2022itmosprl

import com.github.borispristupa.fl2022itmosprl.lang.LLanguage
import com.github.borispristupa.fl2022itmosprl.lang.LLexer
import com.github.borispristupa.fl2022itmosprl.lang.LTokenType
import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.tree.IElementType
import javax.swing.Icon

class LSyntaxHighlighter : SyntaxHighlighterBase() {
    override fun getHighlightingLexer(): Lexer {
        return LLexer()
    }

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        return when (tokenType) {
            LTokenType.ID -> DefaultLanguageHighlighterColors.IDENTIFIER

            LTokenType.COMMENT -> DefaultLanguageHighlighterColors.LINE_COMMENT

            LTokenType.STR_LITERAL -> DefaultLanguageHighlighterColors.STRING

            LTokenType.DECIMAL, LTokenType.BINARY ->
                DefaultLanguageHighlighterColors.NUMBER

            LTokenType.SKIP, LTokenType.IF, LTokenType.ELSE, LTokenType.WHILE ->
                DefaultLanguageHighlighterColors.KEYWORD

            LTokenType.LT, LTokenType.LEQ, LTokenType.GT, LTokenType.GEQ,
            LTokenType.EQ, LTokenType.NEQ, LTokenType.ASSIGN_EQ,
            LTokenType.AND, LTokenType.OR, LTokenType.NOT,
            LTokenType.PLUS, LTokenType.MINUS, LTokenType.MULT, LTokenType.DIV, LTokenType.POWER ->
                DefaultLanguageHighlighterColors.OPERATION_SIGN

            LTokenType.LPAREN, LTokenType.RPAREN ->
                DefaultLanguageHighlighterColors.PARENTHESES

            LTokenType.LCURLY, LTokenType.RCURLY ->
                DefaultLanguageHighlighterColors.BRACES

            LTokenType.COMMA -> DefaultLanguageHighlighterColors.COMMA

            LTokenType.SEMICOLON -> DefaultLanguageHighlighterColors.SEMICOLON

            else -> null
        }.let { pack(it) }
    }
}

class LSyntaxHighlighterFactory : SyntaxHighlighterFactory() {
    override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?): SyntaxHighlighter {
        return LSyntaxHighlighter()
    }
}

class LColorSettingsPage : ColorSettingsPage {
    override fun getAttributeDescriptors(): Array<AttributesDescriptor> {
        return arrayOf(
            AttributesDescriptor("Comment", DefaultLanguageHighlighterColors.LINE_COMMENT),
            AttributesDescriptor("Keyword", DefaultLanguageHighlighterColors.KEYWORD),
            AttributesDescriptor("Identifier", DefaultLanguageHighlighterColors.IDENTIFIER),
            AttributesDescriptor("Number", DefaultLanguageHighlighterColors.NUMBER),
            AttributesDescriptor("String literal", DefaultLanguageHighlighterColors.STRING),
            AttributesDescriptor("Operator", DefaultLanguageHighlighterColors.OPERATION_SIGN),
            AttributesDescriptor("Comma", DefaultLanguageHighlighterColors.COMMA),
            AttributesDescriptor("Semicolon", DefaultLanguageHighlighterColors.SEMICOLON),
            AttributesDescriptor("Braces", DefaultLanguageHighlighterColors.BRACES),
            AttributesDescriptor("Parentheses", DefaultLanguageHighlighterColors.PARENTHESES),
        )
    }

    override fun getColorDescriptors(): Array<ColorDescriptor> {
        return ColorDescriptor.EMPTY_ARRAY
    }

    override fun getDisplayName(): String {
        return LLanguage.displayName
    }

    override fun getIcon(): Icon {
        return LLanguage.icon
    }

    override fun getHighlighter(): SyntaxHighlighter {
        return LSyntaxHighlighter()
    }

    override fun getDemoText(): String {
        return """
            my_fun1() {
                print(x) # типа комментарий
            }

            my_fun2(x, y) {
                a = fn(fn(x), fn, main);
                if (x) return(x + y - "Hello")
                else return(y+1);
                skip
            }

            my_fun3() {
                x = 1 + 2 * 3 / 4 ^ 5 || 6 && 7 || 8 && ! 9 == 10;
                y = (1 + ((2 * 3) / (4 ^ 5))) || ((6 && 7) || (8 && (! (9 == 10))))
            }

            main() {
                x = 0b1010;
                while (x) {
                    my_fun1();
                    someVar1 = my_fun2(x, x^2)
                }
            }
        """.trimIndent()
    }

    override fun getAdditionalHighlightingTagToDescriptorMap(): MutableMap<String, TextAttributesKey>? {
        return null
    }
}

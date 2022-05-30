package com.github.borispristupa.fl2022itmosprl.lang

import com.github.borispristupa.fl2022itmosprl.lang.psi.LFile
import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet

class LParserDefinition: ParserDefinition {
    override fun createLexer(project: Project?): Lexer {
        return LLexer()
    }

    override fun createParser(project: Project?): PsiParser {
        return LParser()
    }

    override fun getFileNodeType(): IFileElementType {
        return LElementType.FILE
    }

    override fun getCommentTokens(): TokenSet {
        return TokenSet.create(LTokenType.COMMENT)
    }

    override fun getStringLiteralElements(): TokenSet {
        return TokenSet.create(LTokenType.STR_LITERAL)
    }

    override fun createElement(node: ASTNode): PsiElement {
        return LElementType.createElement(node)
    }

    override fun createFile(viewProvider: FileViewProvider): PsiFile {
        return LFile(viewProvider)
    }
}
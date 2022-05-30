package com.github.borispristupa.fl2022itmosprl.lang

import com.github.borispristupa.fl2022itmosprl.lang.prattparser.prattParser
import com.intellij.lang.ASTNode
import com.intellij.lang.PsiBuilder
import com.intellij.lang.PsiParser
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType

class LParser : PsiParser {
    override fun parse(root: IElementType, builder: PsiBuilder): ASTNode {
        Parser(builder).parseFile()
        return builder.treeBuilt
    }

    private class Parser(private val builder: PsiBuilder) {
        fun parseFile() {
            val mark = builder.mark()
            var mainParsed = false
            val fns = mutableSetOf<String>()

            while (builder.tokenType != null) {
                when (builder.tokenType) {
                    LTokenType.MAIN -> {
                        if (mainParsed) {
                            builder.error("Main function is already defined")
                        }
                        parseMainDef()
                        mainParsed = true
                    }
                    LTokenType.ID -> {
                        val fn = builder.tokenText!!
                        if (fn in fns) {
                            builder.error("'$fn' function is already defined")
                        }
                        parseFnDef()
                        fns += fn
                    }
                    else -> advance()
                }
            }

            mark.done(LElementType.FILE)
        }

        fun parseMainDef() {
            val mark = builder.mark()
            assertAdvance(LTokenType.MAIN)
            if (expectAdvance(LTokenType.LPAREN, "'('")) {
                expectAdvance(LTokenType.RPAREN, "')'")
            }
            parseStatementList()
            mark.done(LElementType.MAIN_DEF)
        }

        fun parseFnDef() {
            val mark = builder.mark()
            assertAdvance(LTokenType.ID)
            if (expectAdvance(LTokenType.LPAREN, "'('")) {
                parseParameterList()
                expectAdvance(LTokenType.RPAREN, "')'")
            }
            parseStatementList()
            mark.done(LElementType.FN_DEF)
        }

        fun parseParameterList() {
            val mark = builder.mark()

            if (builder.tokenType == LTokenType.RPAREN) {
                mark.done(LElementType.PARAMETER_LIST)
                return
            }

            val ids = mutableSetOf<String>()
            parseParameter(ids)?.let { ids += it }

            while (builder.tokenType != null
                && builder.tokenType != LTokenType.RPAREN
                && builder.tokenType != LTokenType.SEMICOLON
                && builder.tokenType !in LTokenType.BRACES
            ) {
                val offset = builder.currentOffset

                expectAdvance(LTokenType.COMMA, "comma")
                parseParameter(ids)?.let { ids += it }

                if (offset == builder.currentOffset) {
                    advance()
                }
            }
            mark.done(LElementType.PARAMETER_LIST)
        }

        fun parseParameter(defined: Set<String>): String? {
            val mark = builder.mark()
            var result: String? = null
            if (builder.tokenType == LTokenType.ID) {
                val name = builder.tokenText!!
                if (name in defined) {
                    builder.error("'$name' parameter is already defined")
                }
                result = name
                advance()
            } else {
                errorAdvance("parameter name")
            }
            mark.done(LElementType.PARAMETER_DEF)
            return result
        }

        fun parseStatementList() {
            val mark = builder.mark()
            if (expectAdvance(LTokenType.LCURLY, "'{'")) {
                parseListOfStatements()
                expectAdvance(LTokenType.RCURLY, "'}'")
            }
            mark.done(LElementType.STATEMENT_LIST)
        }

        fun parseListOfStatements() {
            parseLoop("statement", LTokenType.SEMICOLON, "';'", listOf(LTokenType.RCURLY)) {
                parseStatement()
            }
        }

        fun parseStatement() {
            when (builder.tokenType) {
                null -> {
                    builder.mark().apply {
                        error("Expected a statement (or there is an extra ';')")
                    }
                }
                LTokenType.SKIP -> parseSkip()
                LTokenType.LCURLY -> parseStatementList()
                LTokenType.WHILE -> parseWhile()
                LTokenType.IF -> parseIf()
                LTokenType.ID, LTokenType.MAIN -> {
                    when (builder.lookAhead(1)) {
                        LTokenType.ASSIGN_EQ -> parseAssignment()
                        LTokenType.LPAREN -> parseFnCallStatement()
                        else -> builder.error("Expected an assignment or a function call")
                    }
                }
                else -> {
                    builder.mark().apply {
                        error("Expected an identifier")
                    }
                    advance()
                }
            }
        }

        fun parseSkip() {
            val mark = builder.mark()
            assertAdvance(LTokenType.SKIP)
            mark.done(LElementType.SKIP_STATEMENT)
        }

        fun parseWhile() {
            val mark = builder.mark()
            assertAdvance(LTokenType.WHILE)

            expectAdvance(LTokenType.LPAREN, "'('")
            parseExpression()
            expectAdvance(LTokenType.RPAREN, "')'")

            parseStatement()
            mark.done(LElementType.WHILE_STATEMENT)
        }

        fun parseIf() {
            val mark = builder.mark()
            assertAdvance(LTokenType.IF)

            expectAdvance(LTokenType.LPAREN, "'('")
            parseExpression()
            expectAdvance(LTokenType.RPAREN, "')'")

            parseStatement()

            if (builder.tokenType == LTokenType.ELSE) {
                assertAdvance(LTokenType.ELSE)
                parseStatement()
            }

            mark.done(LElementType.IF_STATEMENT)
        }

        fun parseAssignment() {
            val mark = builder.mark()
            builder.mark().apply {
                advance()
                done(LElementType.REF)
            }
            assertAdvance(LTokenType.ASSIGN_EQ)
            parseExpression()
            mark.done(LElementType.ASSIGNMENT_STATEMENT)
        }

        fun parseFnCallStatement() {
            val mark = builder.mark()
            builder.mark().apply {
                advance()
                done(LElementType.REF)
            }
            assertAdvance(LTokenType.LPAREN)
            parseExpressionList()
            expectAdvance(LTokenType.RPAREN, "')'")
            mark.done(LElementType.FUNCTION_CALL_STATEMENT)
        }

        fun parseExpressionList() {
            parseLoop(
                "argument",
                LTokenType.COMMA,
                "','",
                listOf(LTokenType.RPAREN, LTokenType.LCURLY, LTokenType.RCURLY)
            ) {
                parseExpression()
            }
        }

        fun parseExpression() {
            prattParser(builder) {
                parseExpressionList()
            }.parseExpression()
        }

        private fun advance() {
            builder.advanceLexer()
            while (builder.tokenType == TokenType.BAD_CHARACTER) {
                val badMark = builder.mark()
                builder.advanceLexer()
                badMark.error("Unexpected character")
            }
        }

        private fun errorAdvance(expectedName: String) {
            val mark = builder.mark()
            advance()
            mark.error("Expected $expectedName")
        }

        private fun expectAdvance(expectedTt: LTokenType, expectedName: String): Boolean {
            return if (builder.tokenType == expectedTt) {
                advance()
                true
            } else {
                builder.error("Expected $expectedName")
                false
            }
        }

        private fun assertAdvance(tt: IElementType) {
            assert(builder.tokenType == tt)
            advance()
        }

        private fun parseLoop(
            itemName: String,
            separator: LTokenType?,
            separatorName: String?,
            stopper: List<LTokenType>,
            itemParser: () -> Unit
        ) {
            assert(separator == null && separatorName == null || separator != null && separatorName != null)

            if (builder.tokenType == null || builder.tokenType in stopper) {
                return
            }

            itemParser()

            while (builder.tokenType != null && builder.tokenType !in stopper) {
                val offsetBeforeBody = builder.currentOffset

                if (separator == null) {
                } else if (builder.tokenType == separator) {
                    assertAdvance(separator)
                } else {
                    builder.error("Expected $separatorName")
                }

                if (builder.tokenType == null || builder.tokenType in stopper) {
                    builder.error("Extra $separatorName")
                    break
                }
                itemParser()

                if (builder.currentOffset == offsetBeforeBody) {
                    errorAdvance(itemName)
                    continue
                }
            }
        }
    }
}

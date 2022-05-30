package com.github.borispristupa.fl2022itmosprl.lang.prattparser

import com.github.borispristupa.fl2022itmosprl.lang.LElementType
import com.github.borispristupa.fl2022itmosprl.lang.LTokenType
import com.intellij.lang.PsiBuilder
import com.intellij.psi.tree.IElementType

class PrattParser(val psiBuilder: PsiBuilder) {
    private val prefixParselets = mutableMapOf<IElementType, PrefixParselet>()
    private val infixParselets = mutableMapOf<IElementType, InfixParcelet>()

    fun register(tokenType: IElementType, prefixParselet: PrefixParselet) {
        prefixParselets[tokenType] = prefixParselet
    }

    fun register(tokenType: IElementType, infixParcelet: InfixParcelet) {
        infixParselets[tokenType] = infixParcelet
    }

    fun parseExpression() {
        parseExpression(0)
    }

    fun parseExpression(minPrecedence: Int) {
        val tokenType = psiBuilder.tokenType
        if (tokenType == null) {
            error("Unexpected EOF")
            return
        }

        val prefixParselet = prefixParselets[tokenType]
        if (prefixParselet == null) {
            error("Unexpected token $tokenType")
            return
        }

        var leftMark = prefixParselet.parse(this)
        var maxPrecedence = Precedence.CALL
        while (minPrecedence <= getPrecedence()) {
            val infixOpTokenType = psiBuilder.tokenType
            if (infixOpTokenType == null) {
                error("Unexpected EOF")
                return
            }

            if (maxPrecedence < getPrecedence()) {
                error("Unassociative operation")
            }

            val infixParselet = infixParselets[psiBuilder.tokenType] ?: return
            leftMark = infixParselet.parse(this, leftMark)

            if (!infixParselet.associative) {
                maxPrecedence = infixParselet.precedence - 1
            }
        }
    }

    fun error(message: String) {
        psiBuilder.mark().apply {
            psiBuilder.error(message)
            drop()
        }
    }

    private fun getPrecedence() : Int {
        return psiBuilder.tokenType?.let {
            infixParselets[it]?.precedence
        } ?: 0
    }
}

fun prattParser(psiBuilder: PsiBuilder, parseExpressionList: (PrattParser) -> Unit) = PrattParser(psiBuilder).apply {
    register(LTokenType.LPAREN, FnCallParselet(parseExpressionList))
    register(LTokenType.LPAREN, ParenGroupParselet())

    register(LTokenType.ID, AtomParselet(LElementType.REF))
    register(LTokenType.MAIN, AtomParselet(LElementType.REF))
    register(LTokenType.DECIMAL, AtomParselet(LElementType.DECIMAL))
    register(LTokenType.BINARY, AtomParselet(LElementType.BINARY))
    register(LTokenType.STR_LITERAL, AtomParselet(LElementType.STRING))

    register(LTokenType.POWER, BinaryOperatorParselet(Precedence.POWER,
        associative = true,
        isRight = true,
        operator = LElementType.BinaryOperator.POWER
    ))
    register(LTokenType.MINUS, UnaryOperatorParselet(Precedence.NEGATE, LElementType.UnaryOperator.MINUS))

    register(LTokenType.MULT, BinaryOperatorParselet(Precedence.PRODUCT,
        associative = true,
        isRight = false,
        operator = LElementType.BinaryOperator.MULT
    ))
    register(LTokenType.DIV, BinaryOperatorParselet(Precedence.PRODUCT, true, false, LElementType.BinaryOperator.DIV))

    register(LTokenType.PLUS, BinaryOperatorParselet(Precedence.SUM,
        associative = true,
        isRight = false,
        operator = LElementType.BinaryOperator.PLUS
    ))
    register(LTokenType.MINUS, BinaryOperatorParselet(Precedence.SUM,
        associative = true,
        isRight = false,
        operator = LElementType.BinaryOperator.MINUS
    ))

    register(LTokenType.EQ, BinaryOperatorParselet(Precedence.CMP,
        associative = false,
        isRight = false,
        operator = LElementType.BinaryOperator.EQ
    ))
    register(LTokenType.NEQ, BinaryOperatorParselet(Precedence.CMP,
        associative = false,
        isRight = false,
        operator = LElementType.BinaryOperator.NEQ
    ))
    register(LTokenType.GEQ, BinaryOperatorParselet(Precedence.CMP,
        associative = false,
        isRight = false,
        operator = LElementType.BinaryOperator.GEQ
    ))
    register(LTokenType.GT, BinaryOperatorParselet(Precedence.CMP,
        associative = false,
        isRight = false,
        operator = LElementType.BinaryOperator.GT
    ))
    register(LTokenType.LEQ, BinaryOperatorParselet(Precedence.CMP,
        associative = false,
        isRight = false,
        operator = LElementType.BinaryOperator.LEQ
    ))
    register(LTokenType.LT, BinaryOperatorParselet(Precedence.CMP,
        associative = false,
        isRight = false,
        operator = LElementType.BinaryOperator.LT
    ))

    register(LTokenType.NOT, UnaryOperatorParselet(Precedence.NOT, LElementType.UnaryOperator.NOT))
    register(LTokenType.AND, BinaryOperatorParselet(Precedence.AND,
        associative = true,
        isRight = true,
        operator = LElementType.BinaryOperator.AND
    ))
    register(LTokenType.OR, BinaryOperatorParselet(Precedence.OR,
        associative = true,
        isRight = true,
        operator = LElementType.BinaryOperator.OR
    ))
}

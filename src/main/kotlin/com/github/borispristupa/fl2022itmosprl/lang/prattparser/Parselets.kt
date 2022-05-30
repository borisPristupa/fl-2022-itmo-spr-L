package com.github.borispristupa.fl2022itmosprl.lang.prattparser

import com.github.borispristupa.fl2022itmosprl.lang.LElementType
import com.github.borispristupa.fl2022itmosprl.lang.LTokenType
import com.intellij.lang.PsiBuilder

interface PrefixParselet {
    fun parse(parser: PrattParser): PsiBuilder.Marker
}

interface InfixParcelet {
    val precedence: Int
    val associative: Boolean

    fun parse(parser: PrattParser, left: PsiBuilder.Marker): PsiBuilder.Marker
}

class AtomParselet(private val elementType: LElementType): PrefixParselet {
    override fun parse(parser: PrattParser): PsiBuilder.Marker {
        val mark = parser.psiBuilder.mark()
        parser.psiBuilder.advanceLexer()
        mark.done(elementType)
        return mark
    }
}

class ParenGroupParselet: PrefixParselet {
    override fun parse(parser: PrattParser): PsiBuilder.Marker {
        val mark = parser.psiBuilder.mark()
        parser.psiBuilder.advanceLexer()
        parser.parseExpression()
        if (parser.psiBuilder.tokenType == LTokenType.RPAREN) {
            parser.psiBuilder.advanceLexer()
        } else {
            parser.error("Expected ')'")
        }
        mark.done(LElementType.PAREN_EXPRESSION)
        return mark
    }
}

class UnaryOperatorParselet(
    private val precedence: Int,
    private val operator: LElementType.UnaryOperator
): PrefixParselet {
    override fun parse(parser: PrattParser): PsiBuilder.Marker {
        val mark = parser.psiBuilder.mark()
        parser.psiBuilder.advanceLexer()
        parser.parseExpression(precedence + 1)
        mark.done(LElementType.UNARY_EXPRESSION(operator))
        return mark
    }
}

class BinaryOperatorParselet(
    override val precedence: Int,
    override val associative: Boolean,
    private val isRight: Boolean,
    private val operator: LElementType.BinaryOperator
): InfixParcelet {
    init {
        require(!isRight || associative)
    }

    override fun parse(parser: PrattParser, left: PsiBuilder.Marker): PsiBuilder.Marker {
        val mark = left.precede()
        parser.psiBuilder.advanceLexer()

        val minPrecedence = if (isRight) precedence else precedence + 1
        parser.parseExpression(minPrecedence)
        mark.done(LElementType.BINARY_EXPRESSION(operator))
        return mark
    }
}

class FnCallParselet(private val parseExpressionList: (PrattParser) -> Unit): InfixParcelet {
    override val precedence: Int
        get() = Precedence.CALL

    override val associative: Boolean
        get() = false

    override fun parse(parser: PrattParser, left: PsiBuilder.Marker): PsiBuilder.Marker {
        val mark = left.precede()
        parser.psiBuilder.advanceLexer()
        parseExpressionList(parser)
        if (parser.psiBuilder.tokenType == LTokenType.RPAREN) {
            parser.psiBuilder.advanceLexer()
        } else {
            parser.error("Expected ')'")
        }
        mark.done(LElementType.FUNCTION_CALL_EXPRESSION)
        return mark
    }
}

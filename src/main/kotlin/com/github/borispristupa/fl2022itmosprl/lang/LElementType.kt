package com.github.borispristupa.fl2022itmosprl.lang

import com.github.borispristupa.fl2022itmosprl.lang.psi.LFnDef
import com.github.borispristupa.fl2022itmosprl.lang.psi.LParameterDef
import com.github.borispristupa.fl2022itmosprl.lang.psi.LPsiElement
import com.github.borispristupa.fl2022itmosprl.lang.psi.LPsiRef
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import org.jetbrains.annotations.NonNls

/*
file: fn_def* main_def fn_def*

fn_def: ID '(' parameter_list? ')' '{' operator_list? '}'
parameter_list: ID (',' parameter_list)*

main_def: 'main' '(' ')' '{' operator_list? '}'

operator_list: operator (';' operator_list)*

operator: 'skip' | if | while | assign | fn_call | '{' operator_list? '}'

if: 'if' '(' expr ')' operator ('else' operator)?

while: 'while' '(' expr ')' operator

assign: ID '=' expr

fn_call: REF '(' arg_list? ')'
arg_list: expr (',' expr)*

# в порядке возрастания приоритета
expr: expr '||' expr   # rassoc
    | expr '&&' expr   # rassoc
    | '!' expr
    | expr_cmp         # not assoc
    | expr_plus_minus  # lassoc
    | expr_mult_div    # lassoc
    | '-' expr
    | expr '^' expr    # rassoc
    | fn_call
    | '(' expr ')'
    | DECIMAL_LITERAL
    | BINARY_LITERAL
    | STRING_LITERAL
    | 'main'
    | REF

expr_cmp: expr '>=' expr
    | expr '>' expr
    | expr '<=' expr
    | expr '<' expr
    | expr '/=' expr
    | expr '==' expr

expr_plus_minus: expr '+' expr | expr '-' expr
expr_mult_div: expr '*' expr | expr '/' expr
 */
sealed class LElementType(@NonNls debugName: String): IElementType(debugName, LLanguage) {
    override fun toString(): String {
        return LLanguage.id + "ElementType." + super.toString()
    }

    object FILE : IFileElementType(LLanguage)

    object FN_DEF : LElementType("FUNCTION_DEFINITION")
    object MAIN_DEF : LElementType("MAIN_DEFINITION")

    object PARAMETER_LIST : LElementType("PARAMETER_LIST")
    object PARAMETER_DEF : LElementType("PARAMETER_DEFINITION")

    object STATEMENT_LIST : LElementType("STATEMENT_LIST")

    object IF_STATEMENT : LElementType("IF")
    object SKIP_STATEMENT : LElementType("SKIP")
    object WHILE_STATEMENT : LElementType("WHILE")
    object ASSIGNMENT_STATEMENT : LElementType("ASSIGNMENT")
    object FUNCTION_CALL_STATEMENT : LElementType("FUNCTION_CALL_STATEMENT")

    object REF : LElementType("REFERENCE")
    object DECIMAL : LElementType("DECIMAL")
    object BINARY : LElementType("BINARY")
    object STRING : LElementType("STRING")
    object PAREN_EXPRESSION : LElementType("EXPRESSION_IN_PARENTHESES")
    object FUNCTION_CALL_EXPRESSION : LElementType("FUNCTION_CALL_EXPRESSION")

    class UNARY_EXPRESSION(val operator: UnaryOperator) : LElementType("UNARY_EXPRESSION")

    enum class UnaryOperator {
        MINUS, NOT
    }

    class BINARY_EXPRESSION(val operator: BinaryOperator) : LElementType("BINARY_EXPRESSION")

    enum class BinaryOperator {
        PLUS, MINUS, MULT, DIV, POWER,
        LEQ, LT, GEQ, GT, NEQ, EQ,
        AND, OR
    }

    companion object {
        val EXPRESSION = TokenSet.create(
            REF, DECIMAL, BINARY, STRING, PAREN_EXPRESSION, FUNCTION_CALL_EXPRESSION
        )
        val STATEMENTS = TokenSet.create(
            STATEMENT_LIST, SKIP_STATEMENT, IF_STATEMENT, WHILE_STATEMENT, ASSIGNMENT_STATEMENT, FUNCTION_CALL_STATEMENT
        )

        fun createElement(node: ASTNode): PsiElement {
            return when (node.elementType) {
                PARAMETER_LIST,
                STATEMENT_LIST, SKIP_STATEMENT, IF_STATEMENT, WHILE_STATEMENT, ASSIGNMENT_STATEMENT, FUNCTION_CALL_STATEMENT,
                DECIMAL, BINARY, STRING, PAREN_EXPRESSION, FUNCTION_CALL_EXPRESSION,
                is UNARY_EXPRESSION, is BINARY_EXPRESSION ->
                    LPsiElement(node)

                FN_DEF, MAIN_DEF -> LFnDef(node)

                PARAMETER_DEF -> LParameterDef(node)

                REF -> LPsiRef(node)

                else -> error("Unknown element type: " + node.elementType)
            }
        }
    }
}

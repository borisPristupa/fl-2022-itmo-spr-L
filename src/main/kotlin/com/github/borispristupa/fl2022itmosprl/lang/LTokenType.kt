package com.github.borispristupa.fl2022itmosprl.lang

import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.TokenSet
import org.jetbrains.annotations.NonNls

sealed class LTokenType(@NonNls debugName: String) : IElementType(debugName, LLanguage) {
    override fun toString(): String {
        return LLanguage.id + "TokenType." + super.toString()
    }

    object MAIN : LTokenType("MAIN")
    object ID : LTokenType("ID")

    object DECIMAL : LTokenType("DECIMAL")
    object BINARY : LTokenType("BINARY")
    object STR_LITERAL : LTokenType("STR_LITERAL")

    object LPAREN : LTokenType("LPAREN")
    object RPAREN : LTokenType("RPAREN")
    object LCURLY : LTokenType("LCURLY")
    object RCURLY : LTokenType("RCURLY")

    object SKIP : LTokenType("SKIP")
    object IF : LTokenType("IF")
    object ELSE : LTokenType("ELSE")
    object WHILE : LTokenType("WHILE")

    object COMMA : LTokenType("COMMA")
    object SEMICOLON : LTokenType("SEMICOLON")

    object ASSIGN_EQ : LTokenType("ASSIGN_EQ")

    object AND : LTokenType("AND")
    object OR : LTokenType("OR")
    object NOT : LTokenType("NOT")
    object EQ : LTokenType("EQ")
    object NEQ : LTokenType("NEQ")
    object LEQ : LTokenType("LEQ")
    object LT : LTokenType("LT")
    object GEQ : LTokenType("GEQ")
    object GT : LTokenType("GT")

    object PLUS : LTokenType("PLUS")
    object MINUS : LTokenType("MINUS")
    object MULT : LTokenType("MULT")
    object DIV : LTokenType("DIV")
    object POWER : LTokenType("POWER")

    object COMMENT : LTokenType("COMMENT")

    companion object {
        val BRACES = TokenSet.create(LCURLY, RCURLY)
    }
}
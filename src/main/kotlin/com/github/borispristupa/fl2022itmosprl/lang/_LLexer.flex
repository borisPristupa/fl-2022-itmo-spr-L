package com.github.borispristupa.fl2022itmosprl.lang;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.github.borispristupa.fl2022itmosprl.lang.LTokenType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
%%
%{
    private StringBuffer string = new StringBuffer();

    public _LLexer() {
      this((java.io.Reader)null);
    }
%}

%public
%class _LLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

DEC_DIGIT = [0-9]
DEC_INTEGER = {DEC_DIGIT}+
BIN_DIGIT = [01]
BIN_INTEGER = "0b" {BIN_DIGIT}+

LINE_WS = [\ \t\f]+
EOL = "\r"|"\n"|"\r\n"
WHITE_SPACE=({LINE_WS}|{EOL})+

COMMENT = "#" .*

ALPHA_NUM = [a-zA-Z0-9_]
ID = [a-zA-Z_] {ALPHA_NUM}*

%state STRING

%%
<YYINITIAL> {
    {COMMENT}        { return LTokenType.COMMENT.INSTANCE; }
    \"               { string.setLength(0); yybegin(STRING); }
    {WHITE_SPACE}    { return WHITE_SPACE; }

    "main"           { return LTokenType.MAIN.INSTANCE; }
    "skip"           { return LTokenType.SKIP.INSTANCE; }
    "if"             { return LTokenType.IF.INSTANCE; }
    "else"           { return LTokenType.ELSE.INSTANCE; }
    "while"          { return LTokenType.WHILE.INSTANCE; }
    {DEC_INTEGER}    { return LTokenType.DECIMAL.INSTANCE; }
    {BIN_INTEGER}    { return LTokenType.BINARY.INSTANCE; }
    {ID}             { return LTokenType.ID.INSTANCE; }

    "^"              { return LTokenType.POWER.INSTANCE; }
    "/"              { return LTokenType.DIV.INSTANCE; }
    "*"              { return LTokenType.MULT.INSTANCE; }
    "-"              { return LTokenType.MINUS.INSTANCE; }
    "+"              { return LTokenType.PLUS.INSTANCE; }
    ">="             { return LTokenType.GEQ.INSTANCE; }
    ">"              { return LTokenType.GT.INSTANCE; }
    "<="             { return LTokenType.LEQ.INSTANCE; }
    "<"              { return LTokenType.LT.INSTANCE; }
    "/="             { return LTokenType.NEQ.INSTANCE; }
    "=="             { return LTokenType.EQ.INSTANCE; }
    "!"              { return LTokenType.NOT.INSTANCE; }
    "&&"             { return LTokenType.AND.INSTANCE; }
    "||"             { return LTokenType.OR.INSTANCE; }
    "="              { return LTokenType.ASSIGN_EQ.INSTANCE; }

    ";"              { return LTokenType.SEMICOLON.INSTANCE; }
    ","              { return LTokenType.COMMA.INSTANCE; }
    "("              { return LTokenType.LPAREN.INSTANCE; }
    ")"              { return LTokenType.RPAREN.INSTANCE; }
    "{"              { return LTokenType.LCURLY.INSTANCE; }
    "}"              { return LTokenType.RCURLY.INSTANCE; }
}

<STRING> {
    \"               { yybegin(YYINITIAL);
                       return LTokenType.STR_LITERAL.INSTANCE; }
    [^\n\r\"\\]+     { string.append( yytext() ); }
    \\t              { string.append('\t'); }
    \\n              { string.append('\n'); }
    \\r              { string.append('\r'); }
    \\\"             { string.append('\"'); }
    \\               { string.append('\\'); }
}

[^] { return BAD_CHARACTER; }

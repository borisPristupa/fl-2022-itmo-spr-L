grammar L;

// parser rules
start : functionSpecifier* entryPoint functionSpecifier* EOF;

statement : funcInnerStatement (SEMICOLON funcInnerStatement)*;

funcInnerStatement : whileStatement
    | ifStatement
    | assignment
    | functionInvokation
    | bracesBlockStatement
    | skipStatement;

entryPoint : MAIN LPARENTHESIS  RPARENTHESIS LBRACE (funcInnerStatement
(SEMICOLON funcInnerStatement)*)? RBRACE;

functionSpecifier  : NAME LPARENTHESIS ((NAME COMMA)*  NAME?) RPARENTHESIS LBRACE (funcInnerStatement
(SEMICOLON funcInnerStatement)*)? RBRACE;

whileStatement : WHILE LPARENTHESIS logicExpr RPARENTHESIS statement;

ifStatement : IF LPARENTHESIS logicExpr RPARENTHESIS statement+
 | IF LPARENTHESIS logicExpr RPARENTHESIS statement+ ELSE  statement+;

bracesBlockStatement : LBRACE statement? RBRACE;

skipStatement : SKIP_OP;

functionInvokation : NAME LPARENTHESIS  (((MAIN | logicExpr) COMMA)* (MAIN | logicExpr))?
RPARENTHESIS;

assignment : NAME EQ logicExpr;

arithmeticExpr : <assoc=right> arithmeticExpr POW arithmeticExpr
      | MINUS arithmeticExpr
      | arithmeticExpr (MULT | DIV) arithmeticExpr
      | arithmeticExpr (PLUS | MINUS) arithmeticExpr
      | baseExpr;

baseExpr : LPARENTHESIS logicExpr RPARENTHESIS
    | NAME
    | '-' baseExpr
    | DigitLiteral
    | STRING
    | functionInvokation;

compare : arithmeticExpr (EQUALS | NOTEQUALS | GE | GT | LE | LT) arithmeticExpr | arithmeticExpr;

logicExpr : NOT logicExpr
    | <assoc=right> logicExpr AND logicExpr
    | <assoc=right> logicExpr OR logicExpr
    | compare
    | arithmeticExpr;

// lexer rules
WHILE : 'while';
IF : 'if';
ELSE : 'else';

SKIP_OP : 'skip';

EQ : '=';
PLUS : '+';
MINUS : '-';
POW : '^';
MULT : '*';
DIV : '/';
AND : '&&';
OR : '||';
LPARENTHESIS :  '(' ;
RPARENTHESIS : ')' ;
LBRACE :  '{' ;
RBRACE :  '}' ;
LT : '<' ;
GT : '>' ;
LE : '<=';
GE : '>=';
EQUALS : '==';
NOT : '!';
NOTEQUALS : '/=';
COMMA : ',';
SEMICOLON : ';';
MAIN : 'main';
NAME : LETTER (LETTER | NUMBER | '_')*;
RETURN : 'return';

LETTER : [a-zA-Z];
STRING : '"' (LETTER | NUMBER | '_')* '"';

DigitLiteral : IntegerLiteral
    | FloatLiteral
    | BinLiteral;

IntegerLiteral : NUMBER+;
FloatLiteral : NUMBER+ DOT NUMBER*;
BinLiteral : BIN BIN_NUM*;

NONZERONUM : [1-9];
NUMBER : [0-9];
DOT : '.';
BIN : '0b';
BIN_NUM : [0-1];

Whitespace
    :
[ \t]+
    -> skip;

EOL :
( '\r' '\n'| '\n')
    -> skip;

COMMENT :
'#' ~[\n]*
    -> skip;

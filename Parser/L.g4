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

functionSpecifier  : NAME LPARENTHESIS (NAME COMMA | NAME)* RPARENTHESIS LBRACE (funcInnerStatement
(SEMICOLON funcInnerStatement)*)? RBRACE;

whileStatement : WHILE LPARENTHESIS relationExpression RPARENTHESIS statement;

ifStatement : IF LPARENTHESIS relationExpression RPARENTHESIS statement+
 | IF LPARENTHESIS relationExpression RPARENTHESIS statement+ ELSE  statement+;

bracesBlockStatement : LBRACE statement? RBRACE;

skipStatement : SKIP_OP;

functionInvokation : NAME LPARENTHESIS  ((functionArgs COMMA)* functionArgs)? RPARENTHESIS;

functionArgs : MAIN | arithmeticExpression | baseExpression | functionInvokation;

assignment : NAME EQ (expression | functionInvokation);

arithmeticExpression : powerExpression
    | multDivExpression
    | addExpression;

expression : arithmeticExpression
    | logicalAndExpression
    | logicalOrExpression
    | relationExpression;

baseExpression : LPARENTHESIS expression* RPARENTHESIS
    | NAME
    | unaryExpression
    | DigitLiteral
    | STRING
    | functionInvokation;

unaryExpression : '-' baseExpression;

powerExpression : baseExpression POW powerExpression | baseExpression;

multDivExpression : multDivExpression (MULT | DIV) (powerExpression | relationExpression) | powerExpression;

addExpression : addExpression (PLUS | MINUS) (multDivExpression | relationExpression) | multDivExpression;

compoundRelationExpr : (arithmeticExpression | logicalOrExpression| logicalAndExpression | baseExpression);

relationExpression : compoundRelationExpr NOTEQUALS logicalOrExpression
    | compoundRelationExpr EQUALS logicalOrExpression
    | compoundRelationExpr LE relationExpression
    | compoundRelationExpr LT relationExpression
    | compoundRelationExpr GT relationExpression
    | compoundRelationExpr GE relationExpression
    | NOT relationExpression
    | compoundRelationExpr
    | compoundRelationExpr AND relationExpression
    | compoundRelationExpr OR relationExpression;

logicalAndExpression : logicalAndExpression AND baseExpression | baseExpression;
logicalOrExpression : logicalOrExpression OR logicalAndExpression | logicalAndExpression;

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
NAME : LETTER (LETTER | NUMBER | UNDERLINE)*;
COMMENT_SYMBOL : '#';
RETURN : 'return';

LETTER : [a-zA-Z];
STRING : '"' (LETTER | NUMBER | UNDERLINE)* '"';
UNDERLINE : '_';

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

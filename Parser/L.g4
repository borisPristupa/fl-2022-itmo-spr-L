grammar L;

// parser rules
start : statement* EOF
      ;

statement : whileStatement EOL*
    | ifStatement EOL*
    | assignment EOL*
    | bracesBlockStatement EOL*
    | functionSpecifier EOL*;

whileStatement : WHILE LPARENTHESIS relationExpression RPARENTHESIS statement*;

ifStatement : IF LPARENTHESIS relationExpression RPARENTHESIS statement
 | IF LPARENTHESIS relationExpression RPARENTHESIS statement ELSE statement*;

bracesBlockStatement : LBRACE statement* statement RBRACE;

functionSpecifier : NAME LPARENTHESIS (NAME COMMA | NAME)* RPARENTHESIS bracesBlockStatement;

assignment : NAME EQ expression;

expression : (powerExpression
    | multDivExpression
    | addExpression
    | logicalAndExpression
    | logicalOrExpression
    | relationExpression) SEMICOLON;

baseExpression : LPARENTHESIS expression RPARENTHESIS
    | NAME
    | unaryExpression
    | DigitLiteral;

unaryExpression : '-' baseExpression
    | '!' logicalOrExpression;

powerExpression : baseExpression POW powerExpression | baseExpression;

multDivExpression : multDivExpression (MULT | DIV) powerExpression | powerExpression;

addExpression : addExpression (PLUS | MINUS) multDivExpression | multDivExpression;

relationExpression : addExpression NOTEQUALS addExpression
    | addExpression EQUALS addExpression
    | addExpression LE addExpression
    | addExpression LT  addExpression
    | addExpression GT  addExpression
    | addExpression GE addExpression;

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
LPARENTHESIS : '(';
RPARENTHESIS : ')';
LBRACE : '{';
RBRACE : '}';
LT : '<';
GT : '>';
LE : '<=';
GE : '>=';
EQUALS : '==';
NOTEQUALS : '/=';
COMMA : ',';
SEMICOLON : ';';

NAME : LETTER (LETTER | NUMBER | UNDERLINE)*;

LETTER : [a-zA-Z];
UNDERLINE : '_';

DigitLiteral : IntegerLiteral
    | FloatLiteral
    | BinLiteral;

IntegerLiteral : NUMBER+;
FloatLiteral : NUMBER + DOT NUMBER*;
BinLiteral : BIN BIN_NUM*;

NONZERONUM : [1-9];
NUMBER : [0-9];
DOT : '.';
EOL : ( '\r' '\n'| '\n');
BIN : '0b';
BIN_NUM : [0,1];

Whitespace
    :   [ \t]+
        -> skip
    ;

LineComment
    :   '#' ~[\n]*
        -> skip
    ;
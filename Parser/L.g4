grammar L;

// parser rules
start : statement* EOF
      ;

statement :  WHILE LPARENTHESIS relationExpression RPARENTHESIS LBRACE statement RBRACE EOL?
    | ifStatement EOL?
    | assignment EOL?
    | expression EOL?;

ifStatement : IF LPARENTHESIS relationExpression RPARENTHESIS THEN LBRACE statement RBRACE;

assignment : NAME EQ expression;

expression : multDivExpression
    | addExpression
    | logicalAndExpression
    | logicalOrExpression
    | relationExpression;

baseExpression : LPARENTHESIS expression RPARENTHESIS
    | NAME
    | unaryExpression
    | DigitLiteral;

unaryExpression : '-' baseExpression
    | '!' logicalOrExpression;

multDivExpression : baseExpression ((MULT | DIV) baseExpression)*;

addExpression : multDivExpression ((PLUS | MINUS) multDivExpression)*;

relationExpression : addExpression NOTEQUALS addExpression
    | addExpression EQUALS addExpression
    | addExpression LE addExpression
    | addExpression LT  addExpression
    | addExpression GT  addExpression
    | addExpression GE addExpression;

logicalAndExpression : baseExpression ('&&' baseExpression)*;
logicalOrExpression : logicalAndExpression ('||' logicalAndExpression)*;

// lexer rules
WHILE : 'while';
IF : 'if';
THEN : 'then';

EQ : '=';
PLUS : '+';
POW : '^';
MINUS : '-';
MULT : '*';
DIV : '/';
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

NAME : LETTER (LETTER | NUMBER | UNDERLINE)*;

LETTER : [a-zA-Z];
UNDERLINE : '_';

DigitLiteral : IntegerLiteral
    | FloatLiteral
    | BinLiteral;

IntegerLiteral : NONZERONUM NUMBER*;
FloatLiteral : NUMBER * DOT NUMBER*;
BinLiteral : BIN BIN_NUM*;

NONZERONUM : [1-9];
NUMBER : [0-9];
DOT : '.';
EOL : ('\n');
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
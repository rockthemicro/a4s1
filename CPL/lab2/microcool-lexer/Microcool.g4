lexer grammar Microcool;

WS : [ \t\r\n]+ -> skip;
SG_LINE_COMM : '--' ~[\r\n]* -> skip;
MLT_LINE_COMM : '(*' -> skip, pushMode(MULTICOMM);

IF : 'if';
THEN : 'then';
ELSE : 'else';
FI : 'fi';

ASSIGN : '<-';
MINUS : '-';
PLUS : '+';
MULTIPLY : '*';
DIVIDE : '/';

LESS_OR_EQ : '<=';
LESS : '<';
EQ : '=';

OPEN_PAR : '(';
CLOSED_PAR : ')';

START_BLOCK : '{';
END_BLOCK : '}';

DBL_DOT : ':';

EOI : ';';

TRUE : 'true';
FALSE : 'false';
ID_NAME : [a-z][a-zA-Z_]*;

TYPE_NAME : [A-Z][a-zA-Z_]*;

FLOAT_LITERAL : INT_LITERAL* '.' INT_LITERAL+ | INT_LITERAL+ '.' INT_LITERAL*;
INT_LITERAL : '0' | [1-9][0-9]*;




mode MULTICOMM;

IMBR_MLT_LINE_COMM : '(*' -> skip, pushMode(MULTICOMM);
END_OF_MLT_LINE_COMM : '*)' -> skip, popMode;

ERROR : . EOF;
ANYTHING : . -> skip;

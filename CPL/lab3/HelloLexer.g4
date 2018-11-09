lexer grammar HelloLexer;

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
COMMA : ',';

EOI : ';';

TRUE : 'true';
FALSE : 'false';
ID : [a-z][a-zA-Z_0-9]*;

TYPE_NAME : [A-Z][a-zA-Z_]*;

FLOAT : INT* '.' INT+ | INT+ '.' INT*;
INT : '0' | [1-9][0-9]*;




mode MULTICOMM;

IMBR_MLT_LINE_COMM : '(*' -> skip, pushMode(MULTICOMM);
END_OF_MLT_LINE_COMM : '*)' -> skip, popMode;

ERROR : . EOF;
ANYTHING : . -> skip;

lexer grammar CoolLexer;

tokens { ERROR } 

@header{
    package cool.lexer;	
}

@members{    
    private void raiseError(String msg) {
        setText(msg);
        setType(ERROR);
    }
    
    static int stringCnt = 0;
    static StringBuffer buffer = null;
}

WS
    :   [ \n\f\r\t]+ -> skip
    ;
    
SG_LINE_COMM : '--' ~[\r\n]* -> skip;
MLT_LINE_COMM : '(*' -> more, pushMode(MULTICOMM);
UNMATCHED_COMM : '*)' { raiseError("Unmatched *)"); };

EOI : ';';
DBL_DOT : ':';
COMMA : ',';
DOT : '.';
AT : '@';

OPEN_AC : '{';
CLOSED_AC : '}';

OPEN_PAR : '(';
CLOSED_PAR : ')';

NEG : '~';

PLUS : '+';
SUB : '-';
MUL : '*';
DIV : '/';

ASSIGN : '<-';
LESS_OR_EQ : '<=';
TO : '=>';
LESS : '<';
EQ : '=';

INHERITS : 'inherits';
SELF : 'SELF_TYPE';

ISVOID : 'isvoid';
NOT : 'not';

CLASS : 'class';
NEW : 'new';

IF : 'if';
THEN : 'then';
ELSE : 'else';
FI : 'fi';

WHILE : 'while';
LOOP : 'loop';
POOL : 'pool';

LET : 'let';
IN : 'in';

CASE : 'case';
OF : 'of';
ESAC : 'esac';

BOOLEAN : 'true' | 'false';

CLASS_NAME : [A-Z][a-zA-Z0-9]*;

ID : [a-z][a-zA-Z0-9_]*;
NR : [0-9]+;
STRING2 : '"' { stringCnt = 0; buffer = new StringBuffer(); } -> more, pushMode(STRING_MODE);

PROBLEMATIC_CHAR : . { raiseError("Invalid character: " + getText().toString()); };






mode STRING_MODE;

ERROR_STRING : . EOF { raiseError("EOF in string constant"); } -> popMode;

ESCAPED_NEWLINE : ('\\\n' | '\\\r\n') {
	stringCnt++;
	buffer.append("\n");
} -> more;

TAB_TEXT : '\\t' {
	stringCnt++;
	buffer.append("\t");
} -> more;

NEWLINE_TEXT : '\\n' {
	stringCnt++;
	buffer.append("\n");
} -> more;

BACKSPACE_TEXT : '\\b' {
	stringCnt++;
	buffer.append("\b");
} -> more;

FORM_TEXT : '\\f' {
	stringCnt++;
	buffer.append("\f");
} -> more;

BACKSLASH : '\\\\' {
	stringCnt++;
	buffer.append("\\");
} -> more;
ESC : '\\' -> more;

STRING : '"' {
	if (stringCnt > 1024) {
		raiseError("String constant too long");
	} else if (buffer != null) {
		setText(new String(buffer));
		buffer = null;
	}
	
	stringCnt = 0;
} -> popMode;
NULL_CHAR : '\u0000' { raiseError("String contains null character"); } -> mode(CONSUME_TILL_EOS);
NON_ESCAPED_NEWLINE : ('\n' | '\r\n') { raiseError("Unterminated string constant"); } -> popMode;

ANYTHING_STRING : . { stringCnt++; buffer.append(getText()); } -> more;

mode CONSUME_TILL_EOS;

UNEXPETED_END : . EOF -> skip, popMode;
REST_OF_STRING : .*? '"' -> skip, popMode;






mode MULTICOMM;

IMBR_MLT_LINE_COMM : '(*' -> more, pushMode(MULTICOMM);
END_OF_MLT_LINE_COMM : '*)' -> more, popMode;

ERROR_MULTICOMM : . EOF { raiseError("EOF in comment"); } -> mode(DEFAULT_MODE);
ANYTHING : . -> more;
                          
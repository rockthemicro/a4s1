grammar Hello;

main: (expr ';')+
	;

expr: expr1				# bare
	| expr1 '+' expr	# add
	;

expr1: expr2			# bare1
	| expr2 '*' expr1	# mul
	| expr2 '/' expr1	# div
	| expr2 '%' expr1	# mod
	;

expr2: integer	# int
	;

integer: INT;

INT: [0-9]+;

WS: [ \t\n\r] -> skip;


expr : expr1 + expr | expr1;
expr1 : expr2 * expr1 | expr2;
expr2 : Int;


3 * 7 + 4

expr1 3 * 7
expr 4


expr : expr + expr | expr * expr | Int


(3 * 7) + 4 | 3 * (7 + 4)

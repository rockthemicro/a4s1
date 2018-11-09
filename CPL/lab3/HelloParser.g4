parser grammar HelloParser;

/* Fișierele generate de analizorul lexical trebuie situate în același director
 * cu analizorul sintactic.
 */
options {
    tokenVocab = HelloLexer;
}

/* O expresie poate fi o decizie, un identificator sau un întreg.
 * 
 * În absența numelor din dreapta, precedate de # (if, id și int), în listenerii
 * și visitorii generați automat, ar fi definit doar contextul ExprContext, cu
 * informații amestecate pentru toate cele trei alternative. Însă, în prezența
 * celor trei etichete, care diferențiază alternativele, vor fi generate și
 * cele trei contexte particulare, IfContext, IdContext și IntContext, cu
 * informații specifice fiecărei alternative.
 * 
 * Pentru fiecare dintre regulile lexicale sau sintactice referite într-o regulă
 * sintactică, obiectul Context va conține o funcție cu numele regulii. Spre
 * exemplu, obiectul IntContext include o metodă, INT(), care va întoarce
 * nodul aferent din arborele de derivare. Însă, având în vedere că alternativa
 * if conține referiri multiple la regula expr, obiectul IfContext va conține
 * o metodă expr(), care, în loc să întoarcă un singur nod din arbore, va
 * întoarce o listă ordonată cu cele trei noduri menționate, aferente condiției,
 * ramurii THEN și ramurii ELSE. De asemenea, există și o variantă
 * supraîncărcată a metodei, expr(int index), care întoarce nodul de la poziția
 * index, între 0 și 2.
 *  
 * În cazul în care referirea la un nod prin poziția sa este insuficient de
 * expresivă, se pot adăuga etichete pentru fiecare referire în parte, ca în
 * cazul cond, thenBranch și elseBranch. În conscință, obiectul IfContext va
 * conține și câmpurile cond, thenBranch și elseBranch, având tipurile nodurilor
 * din arbore.
 */

program : instr*;
instr
	: func_call EOI
	| func_def EOI
	| var_def_decl EOI
	| var_def EOI
	| assignment EOI
	| expr EOI
	;

variable : ID | INT;

arithmetics : arithmetics1 '+' arithmetics | arithmetics1;
arithmetics1 : variable '*' arithmetics1 | variable;

var_def : ID DBL_DOT TYPE_NAME;

var_def_decl : ID DBL_DOT TYPE_NAME ASSIGN expr;

assignment : ID ASSIGN expr;

comparison
	: ID LESS_OR_EQ expr
	| ID EQ expr
	| ID LESS expr
	;

expr
    : IF cond=expr THEN thenBranch=expr ELSE elseBranch=expr FI 	# if
    | func_call								# fcall
    | arithmetics							# arithm
    | comparison							# cmp
    | ID                                                        	# id
    | INT                                                       	# int
    ;

func_call
	: ID OPEN_PAR func_call CLOSED_PAR
	| ID OPEN_PAR ID CLOSED_PAR
	| ID OPEN_PAR INT CLOSED_PAR
	;

func_args : var_def COMMA func_args | var_def;

func_def
	: ID OPEN_PAR func_args CLOSED_PAR DBL_DOT TYPE_NAME START_BLOCK arithmetics END_BLOCK;

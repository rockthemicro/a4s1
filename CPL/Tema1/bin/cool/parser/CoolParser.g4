parser grammar CoolParser;

options {
    tokenVocab = CoolLexer;
}

@header{
    package cool.parser;
}

type
	: CLASS_NAME
	| SELF
	;

constant
	: NR		# nr
	| BOOLEAN	# boolean
	| STRING	# string
	;

let_bind : ID DBL_DOT type (ASSIGN expr)?;

f_call_args
	: expr					# sg_arg_function
	| expr (COMMA expr)+	# mlt_arg_function
	;

f_call : ID OPEN_PAR f_call_args? CLOSED_PAR;

expr
	: expr (AT type)? DOT f_call												# expr_expr_fcall
	| f_call																	# expr_fcall
	| OPEN_AC (expr EOI)+ CLOSED_AC												# expr_acolades
	| IF expr THEN expr ELSE expr FI											# expr_if
	| WHILE expr LOOP expr POOL													# expr_while
	| LET let_bind (COMMA let_bind)* IN expr									# expr_let
	| CASE expr OF (ID DBL_DOT type TO expr EOI)+ ESAC							# expr_case
	| OPEN_PAR expr CLOSED_PAR													# expr_paran
	| ID																		# expr_id
	| constant																	# expr_const
	| NEG expr																	# expr_neg
	| ISVOID expr																# expr_isvoid
	| expr op = (MUL | DIV) expr												# expr_muldiv
	| expr op = (PLUS | SUB) expr												# expr_plussub
	| expr op = (LESS | LESS_OR_EQ | EQ) expr									# expr_comp
	| NOT expr																	# expr_not
	| <assoc=right> ID ASSIGN expr												# expr_assign
	| NEW type																	# expr_new
	;

attribute
	: ID DBL_DOT type ASSIGN expr	# attr_asgn
	| ID DBL_DOT type				# attr_no_asgn
	;
	
method_params
	:	ID DBL_DOT type (COMMA ID DBL_DOT type)*	# m_args
	|												# empty_m_args
	;
method_body
	: expr			# sg_expr_meth
	| (expr EOI)*	# mlt_expr_meth
	;

method : ID OPEN_PAR method_params CLOSED_PAR DBL_DOT ret=(CLASS_NAME | SELF) OPEN_AC method_body CLOSED_AC;

attr_or_meth
	: attribute	# attr
	| method	# meth
	;

class_body : (attr_or_meth EOI)*;

class_header
	: CLASS CLASS_NAME INHERITS CLASS_NAME	# inheriter_class
	| CLASS CLASS_NAME						# simple_class
	;

class_node : class_header OPEN_AC class_body CLOSED_AC EOI;

program : (class_node* | expr* | method*) EOF;

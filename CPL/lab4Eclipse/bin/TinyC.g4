/*
BSD License

Copyright (c) 2013, Tom Everett
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions
are met:

1. Redistributions of source code must retain the above copyright
   notice, this list of conditions and the following disclaimer.
2. Redistributions in binary form must reproduce the above copyright
   notice, this list of conditions and the following disclaimer in the
   documentation and/or other materials provided with the distribution.
3. Neither the name of Tom Everett nor the names of its contributors
   may be used to endorse or promote products derived from this software
   without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

grammar TinyC;

program
   : statement +
   ;

statement
   : 'if' paren_expr statement				# if_then_statement
   | 'if' paren_expr statement 'else' statement		# if_then_else_statement
   | 'while' paren_expr statement			# while_statement
   | 'do' statement 'while' paren_expr ';'		# do_while_statement
   | '{' statement* '}'					# block_statement
   | expr ';'						# expr_statement
   | ';'						# null_statement
   ;

paren_expr
   : '(' expr ')'
   ;

expr
   : test						# bare_test
   | id '=' expr					# attr_expr
   ;

test
   : sum						# bare_sum
   | sum '<' sum					# comp_test
   ;

sum
   : term						# bare_term
   | term '+' sum					# addition
   | term '-' sum					# substraction
   ;

term
   : id							# bare_id
   | integer						# bare_int
   | paren_expr						# paren_term
   ;

id
   : STRING
   ;

integer
   : decInteger
   | hexInteger
   | binInteger
   ;

decInteger
   : DECINTEGER
   ;

hexInteger
   : HEXINTEGER
   ;

binInteger
   : BININTEGER
   ;

DECINTEGER
   : [0-9]+
   ;

HEXINTEGER
   : ('0x'|'0X') [0-9a-fA-F]+
   ;

BININTEGER
   : ('0b'|'0B') [0-1]+
   ;

STRING
   : [a-z]+
   ;

WS
   : [ \r\n\t] -> skip
   ;


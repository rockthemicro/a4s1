
all: src/cool/lexer/CoolLexer.java src/cool/parser/CoolParser.java

src/cool/lexer/CoolLexer.java: src/cool/lexer/CoolLexer.g4
	java -jar lib/antlr-4.7.1-complete.jar src/cool/lexer/CoolLexer.g4

src/cool/parser/CoolParser.java: src/cool/parser/CoolParser.g4 src/cool/lexer/CoolLexer.java
	cp src/cool/lexer/CoolLexer.tokens src/cool/parser/
	java -jar lib/antlr-4.7.1-complete.jar -listener -visitor src/cool/parser/CoolParser.g4

clean:
	rm -rf src/cool/parser/CoolParser.java src/cool/lexer/CoolLexer.java src/cool/parser/CoolParserVisitor.java src/cool/parser/CoolParserBaseVisitor.java src/cool/parser/CoolListener.java src/cool/parser/CoolParserListener.java src/cool/parser/CoolParserBaseListener.java src/cool/lexer/*.tokens src/cool/lexer/*.interp src/cool/parser/*.tokens src/cool/parser/*.interp

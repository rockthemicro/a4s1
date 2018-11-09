Test.class: Test.java HelloParser.class HelloLexer.class
	javac -cp .:antlr-4.7.1-complete.jar Test.java

HelloParser.class: HelloParser.java
	javac -cp .:antlr-4.7.1-complete.jar HelloParser.java

HelloLexer.class: HelloLexer.java
	javac -cp .:antlr-4.7.1-complete.jar HelloLexer.java

HelloLexer.java: HelloLexer.g4
	java -jar antlr-4.7.1-complete.jar -visitor -listener HelloLexer.g4

HelloParser.java: HelloParser.g4 HelloLexer.java
	java -jar antlr-4.7.1-complete.jar -visitor -listener HelloParser.g4

clean:
	rm -f *.class Hello*.java *.interp *.tokens

run: Test.class
	java -cp .:antlr-4.7.1-complete.jar Test

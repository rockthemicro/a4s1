Test.class: Test.java HelloParser.class
	javac -cp .:antlr-4.7.1-complete.jar Test.java

HelloParser.class: HelloParser.java
	javac -cp .:antlr-4.7.1-complete.jar HelloParser.java

HelloParser.java: Hello.g4
	java -jar antlr-4.7.1-complete.jar -visitor -listener Hello.g4

clean:
	rm -f *.class Hello*.java *.interp *.tokens

run: Test.class
	java -cp .:antlr-4.7.1-complete.jar Test

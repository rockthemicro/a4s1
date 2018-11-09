Test.class: Test.java TinyCParser.class
	javac -cp .:antlr-4.7.1-complete.jar Test.java

TinyCParser.class: TinyCParser.java
	javac -cp .:antlr-4.7.1-complete.jar TinyCParser.java

TinyCParser.java: TinyC.g4
	java -jar antlr-4.7.1-complete.jar -visitor -no-listener TinyC.g4

clean:
	rm -f *.class TinyC*.java *.interp *.tokens

run: Test.class
	java -cp .:antlr-4.7.1-complete.jar Test

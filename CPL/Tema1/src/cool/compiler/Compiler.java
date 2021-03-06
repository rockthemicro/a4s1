package cool.compiler;

import java.io.File;
import java.io.IOException;

import cool.structures.ClassSymbol;
import cool.visitors.*;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import cool.lexer.CoolLexer;
import cool.parser.CoolParser;
import cool.parser.CoolParser.ProgramContext;
import cool.structures.SymbolTable;
import org.stringtemplate.v4.STGroupFile;


public class Compiler {
    // Annotates class nodes with the names of files where they are defined.
    public static ParseTreeProperty<String> fileNames = new ParseTreeProperty<>();

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("No file(s) given");
            return;
        }
        
        CoolLexer lexer = null;
        CommonTokenStream tokenStream = null;
        CoolParser parser = null;
        ParserRuleContext globalTree = null;
        
        // True if any lexical or syntax errors occur.
        boolean lexicalSyntaxErrors = false;
        
        // Parse each input file and build one big parse tree out of
        // individual parse trees.
        for (var fileName : args) {
            var input = CharStreams.fromFileName(fileName);
            
            // Lexer
            if (lexer == null)
                lexer = new CoolLexer(input);
            else
                lexer.setInputStream(input);

            // Token stream
            if (tokenStream == null)
                tokenStream = new CommonTokenStream(lexer);
            else
                tokenStream.setTokenSource(lexer);

            // Parser
            if (parser == null)
                parser = new CoolParser(tokenStream);
            else
                parser.setTokenStream(tokenStream);
            
            
            // adaugat pt testarea lexerului
            boolean lexicalErrors = false;
            tokenStream.fill();
            for (var token : tokenStream.getTokens()) {
            	/*
            	var text = token.getText();
                var name = CoolLexer.VOCABULARY.getSymbolicName(token.getType());
                System.out.println(text + " : " + name);
                */
            	
                if (token.getType() == CoolLexer.ERROR) {
                	String newMsg = "\"" + new File(fileName).getName() + "\", line " +
                            token.getLine() + ":" + token.getCharPositionInLine() + ", ";
                	newMsg += "Lexical error: " + token.getText();

                	System.out.println(newMsg);
                	
                	lexicalErrors = true;
                }
                
            }
            
            // Customized error listener, for including file names in error
            // messages.
            var errorListener = new BaseErrorListener() {
                public boolean errors = false;
                
                @Override
                public void syntaxError(Recognizer<?, ?> recognizer,
                                        Object offendingSymbol,
                                        int line, int charPositionInLine,
                                        String msg,
                                        RecognitionException e) {
                    errors = true;
                    String newMsg = "\"" + new File(fileName).getName() + "\", line " +
                                        line + ":" + charPositionInLine + ", ";
                    
                    Token token = (Token)offendingSymbol;
                    if (token.getType() != CoolLexer.ERROR) {
                        newMsg += "Syntax error: " + msg;
                    	System.err.println(newMsg);
                    }
                }
            };
            parser.removeErrorListeners();
            parser.addErrorListener(errorListener);
            
            if (lexicalErrors == false) {
            	// Actual parsing
            	ProgramContext tree = parser.program();

            	if (globalTree == null)
            		globalTree = tree;
            	else
            		// Add the current parse tree's children to the global tree.
            		for (int i = 0; i < tree.getChildCount(); i++)
            			globalTree.addAnyChild(tree.getChild(i));

            	// Annotate class nodes with file names, to be used later
            	// in semantic error messages.
            	for (int i = 0; i < tree.getChildCount(); i++) {
            		var child = tree.getChild(i);
            		// The only ParserRuleContext children of the program node
            		// are class nodes.
            		if (child instanceof ParserRuleContext)
            			fileNames.put(child, fileName);
            	}
            }
            
            // Record any lexical or syntax errors.
            lexicalSyntaxErrors |= errorListener.errors;
            lexicalSyntaxErrors |= lexicalErrors;
            

        }
        
        // Stop before semantic analysis phase, in case errors occurred.
        if (lexicalSyntaxErrors) {
            System.err.println("Compilation halted");
            return;
        }
        
        // Create and print AST
        var visitor = new BuildASTVisitor();
        var ast = visitor.visit(globalTree);
        
        // output pentru tema1
        // ast.print(0);
        
        // Populate global scope.
        SymbolTable.defineBasicClasses();
        
        // Semantic analysis - rezolvarea simbolurilor
        var symbolsDefineVisitor = new ASTSymbolsDefineVisitor();
        ast.accept(symbolsDefineVisitor);

        var symbolsSolveVisitor = new ASTSymbolsSolveVisitor();
        ast.accept(symbolsSolveVisitor);

        var symbolsSolveVisitor2 = new ASTSymbolsSolveVisitor2();
        ast.accept(symbolsSolveVisitor2);

        if (SymbolTable.hasSemanticErrors()) {
            System.err.println("Compilation halted");
            return;
        }

        // Semantic analysis - verificarea tipurilor
        var astTypeCheckingVisitor = new ASTTypeCheckingVisitor();
        ast.accept(astTypeCheckingVisitor);

        if (SymbolTable.hasSemanticErrors()) {
            System.err.println("Compilation halted");
            return;
        }

        if (SymbolTable.globals.lookup("Main") == null) {
            System.err.println("Nu exista clasa Main!");
            return;
        } else if (((ClassSymbol) SymbolTable.globals.lookup("Main")).lookupMethod("main") == null) {
            System.err.println("Clasa Main nu are metoda main!");
            return;
        }

        // cand incarc pe vmchecker, tai src/
        var group = new STGroupFile("src/cool/codegen/cgen.stg");
        var astCodeGenVisitor = new ASTCodeGenVisitor(group);
        ast.accept(astCodeGenVisitor);

        astCodeGenVisitor.computeResultMIPS();
        System.out.println(astCodeGenVisitor.getResultMIPS().render());

    }
}

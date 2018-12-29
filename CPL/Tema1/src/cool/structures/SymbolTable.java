package cool.structures;

import java.io.File;

import org.antlr.v4.runtime.*;

import cool.compiler.Compiler;
import cool.parser.CoolParser;

public class SymbolTable {
    public static Scope globals;
    
    private static boolean semanticErrors;
    
    public static void defineBasicClasses() {
        globals = new DefaultScope(null);
        semanticErrors = false;

        globals.add(new TypeSymbol("SELF_TYPE"));


        // define Int class
        var intSymbol = new ClassSymbol("Int", globals);
        globals.add(intSymbol);


        // define Bool class
        var boolSymbol = new ClassSymbol("Bool", globals);
        globals.add(boolSymbol);


        // define String class
        var stringSymbol = new ClassSymbol("String", globals);
        globals.add(stringSymbol);

        var lengthMethod = new MethodSymbol("length", stringSymbol);
        lengthMethod.setType(intSymbol);
        stringSymbol.add(lengthMethod);

        var concatMethod = new MethodSymbol("concat", stringSymbol);
        concatMethod.setType(stringSymbol);
        stringSymbol.add(concatMethod);

        var substrMethod = new MethodSymbol("substr", stringSymbol);
        substrMethod.setType(stringSymbol);
        stringSymbol.add(substrMethod);


        // define Object class
        var objectSymbol = new ClassSymbol("Object", globals);
        globals.add(objectSymbol);

        var abortMethod = new MethodSymbol("abort", objectSymbol);
        abortMethod.setType(objectSymbol);
        objectSymbol.add(abortMethod);

        var type_nameMethod = new MethodSymbol("type_name", objectSymbol);
        type_nameMethod.setType(stringSymbol);
        objectSymbol.add(type_nameMethod);

        var copyMethod = new MethodSymbol("copy", objectSymbol);
        copyMethod.setType((TypeSymbol) globals.lookup("SELF_TYPE"));
        objectSymbol.add(copyMethod);


        // define IO class
        var ioSymbol = new ClassSymbol("IO", globals);
        globals.add(ioSymbol);

        var out_stringMethod = new MethodSymbol("out_string", ioSymbol);
        out_stringMethod.setType((TypeSymbol) globals.lookup("SELF_TYPE"));
        ioSymbol.add(out_stringMethod);

        var out_intMethod = new MethodSymbol("out_int", ioSymbol);
        out_intMethod.setType((TypeSymbol) globals.lookup("SELF_TYPE"));
        ioSymbol.add(out_intMethod);

        var in_stringMethod = new MethodSymbol("in_string", ioSymbol);
        in_stringMethod.setType(stringSymbol);
        ioSymbol.add(in_stringMethod);

        var in_intMethod = new MethodSymbol("in_int", ioSymbol);
        in_intMethod.setType(intSymbol);
        ioSymbol.add(in_intMethod);
    }
    
    /**
     * Displays a semantic error message.
     * 
     * @param ctx Used to determine the enclosing class context of this error,
     *            which knows the file name in which the class was defined.
     * @param info Used for line and column information.
     * @param str The error message.
     */
    public static void error(ParserRuleContext ctx, Token info, String str) {
        while (! (ctx.getParent() instanceof CoolParser.ProgramContext))
            ctx = ctx.getParent();
        
        String message = "\"" + new File(Compiler.fileNames.get(ctx)).getName()
                + "\", line " + info.getLine()
                + ":" + (info.getCharPositionInLine() + 1)
                + ", Semantic error: " + str;
        
        System.err.println(message);
        
        semanticErrors = true;
    }
    
    public static void error(String str) {
        String message = "Semantic error: " + str;
        
        System.err.println(message);
        
        semanticErrors = true;
    }
    
    public static boolean hasSemanticErrors() {
        return semanticErrors;
    }
}

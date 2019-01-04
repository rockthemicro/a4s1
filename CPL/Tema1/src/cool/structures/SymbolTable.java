package cool.structures;

import java.io.File;

import cool.compiler.ASTManyExprNode;
import cool.compiler.ASTMethodNode;
import cool.compiler.ASTMethodParamsNode;
import org.antlr.v4.runtime.*;

import cool.compiler.Compiler;
import cool.parser.CoolParser;

public class SymbolTable {
    public static Scope globals;
    
    private static boolean semanticErrors;

    public static void setupASTMethodNode(MethodSymbol methodSymbol, ClassSymbol classSymbol) {
        methodSymbol.astMethodNode = new ASTMethodNode();

        methodSymbol.astMethodNode.id = methodSymbol.getName();
        methodSymbol.astMethodNode.retType = methodSymbol.getType().getName();

        methodSymbol.astMethodNode.methodSymbol = methodSymbol;
        methodSymbol.astMethodNode.classSymbol = classSymbol;

        methodSymbol.astMethodNode.params = new ASTMethodParamsNode();
        methodSymbol.astMethodNode.body = new ASTManyExprNode();
    }

    public static void addMethodNodeParam(MethodSymbol methodSymbol, String id, String type) {
        methodSymbol.astMethodNode.params.ids.add(id);
        methodSymbol.astMethodNode.params.types.add(type);
    }
    
    public static void defineBasicClasses() {
        globals = new DefaultScope(null);
        semanticErrors = false;

        globals.add(new ClassSymbol("SELF_TYPE", globals));

        // define Object class
        var objectSymbol = new ClassSymbol("Object", globals);
        globals.add(objectSymbol);

        // define Int class
        var intSymbol = new ClassSymbol("Int", objectSymbol);
        globals.add(intSymbol);


        // define Bool class
        var boolSymbol = new ClassSymbol("Bool", objectSymbol);
        globals.add(boolSymbol);


        // define String class
        var stringSymbol = new ClassSymbol("String", objectSymbol);
        globals.add(stringSymbol);

        var lengthMethod = new MethodSymbol("length", stringSymbol);
        lengthMethod.setType(intSymbol);
        stringSymbol.add(lengthMethod);
        setupASTMethodNode(lengthMethod, stringSymbol);

        var concatMethod = new MethodSymbol("concat", stringSymbol);
        concatMethod.setType(stringSymbol);
        stringSymbol.add(concatMethod);
        setupASTMethodNode(concatMethod, stringSymbol);
        addMethodNodeParam(concatMethod, "s", "String");

        var substrMethod = new MethodSymbol("substr", stringSymbol);
        substrMethod.setType(stringSymbol);
        stringSymbol.add(substrMethod);
        setupASTMethodNode(substrMethod, stringSymbol);
        addMethodNodeParam(substrMethod, "i", "Int");
        addMethodNodeParam(substrMethod, "l", "Int");

        // define object methods
        var abortMethod = new MethodSymbol("abort", objectSymbol);
        abortMethod.setType(objectSymbol);
        objectSymbol.add(abortMethod);
        setupASTMethodNode(abortMethod, objectSymbol);

        var type_nameMethod = new MethodSymbol("type_name", objectSymbol);
        type_nameMethod.setType(stringSymbol);
        objectSymbol.add(type_nameMethod);
        setupASTMethodNode(type_nameMethod, objectSymbol);

        var copyMethod = new MethodSymbol("copy", objectSymbol);
        copyMethod.setType((TypeSymbol) globals.lookup("SELF_TYPE"));
        objectSymbol.add(copyMethod);
        setupASTMethodNode(copyMethod, objectSymbol);


        // define IO class
        var ioSymbol = new ClassSymbol("IO", objectSymbol);
        globals.add(ioSymbol);

        var out_stringMethod = new MethodSymbol("out_string", ioSymbol);
        out_stringMethod.setType((TypeSymbol) globals.lookup("SELF_TYPE"));
        ioSymbol.add(out_stringMethod);
        setupASTMethodNode(out_stringMethod, ioSymbol);
        addMethodNodeParam(out_stringMethod, "x", "String");

        var out_intMethod = new MethodSymbol("out_int", ioSymbol);
        out_intMethod.setType((TypeSymbol) globals.lookup("SELF_TYPE"));
        ioSymbol.add(out_intMethod);
        setupASTMethodNode(out_intMethod, ioSymbol);
        addMethodNodeParam(out_intMethod, "x", "Int");

        var in_stringMethod = new MethodSymbol("in_string", ioSymbol);
        in_stringMethod.setType(stringSymbol);
        ioSymbol.add(in_stringMethod);
        setupASTMethodNode(in_stringMethod, ioSymbol);

        var in_intMethod = new MethodSymbol("in_int", ioSymbol);
        in_intMethod.setType(intSymbol);
        ioSymbol.add(in_intMethod);
        setupASTMethodNode(in_intMethod, ioSymbol);
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

    public static void setSemanticErrors(boolean semanticErrors) {
        SymbolTable.semanticErrors = semanticErrors;
    }
}

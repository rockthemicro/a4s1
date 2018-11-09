import java.io.IOException;

import org.antlr.v4.runtime.*;

public class Test {
    public static void main(String[] args) throws IOException {
        CharStream input = CharStreams.fromFileName("program3.txt");
        
        HelloLexer lexer = new HelloLexer(input);
        TokenStream tokenStream = new CommonTokenStream(lexer);
        
        HelloParser parser = new HelloParser(tokenStream);
        ParserRuleContext tree = parser.main();

        HelloBaseVisitor visitor = new HelloBaseVisitor<Integer>() {
            @Override
            public Integer visitMain(HelloParser.MainContext ctx){
            	for(ParserRuleContext expr : ctx.expr()) {
            		System.out.println(visit(expr));
            	}
            	
            	return 42;
            }
            
            @Override
            public Integer visitBare(HelloParser.BareContext ctx) {
                return visit(ctx.expr1());
            }
            
            @Override
            public Integer visitAdd(HelloParser.AddContext ctx) {
                return visit(ctx.expr1()) + visit(ctx.expr());
            }
            
            @Override
            public Integer visitBare1(HelloParser.Bare1Context ctx) {
                return visit(ctx.expr2());
            }
            
            @Override
            public Integer visitMul(HelloParser.MulContext ctx) {
                return visit(ctx.expr2()) * visit(ctx.expr1());
            }

            @Override
            public Integer visitDiv(HelloParser.DivContext ctx) {
                return visit(ctx.expr2()) / visit(ctx.expr1());
            }

            @Override
            public Integer visitMod(HelloParser.ModContext ctx) {
                return visit(ctx.expr2()) % visit(ctx.expr1());
            }
            
            @Override
            public Integer visitInteger(HelloParser.IntegerContext ctx) {
            	return Integer.parseInt(ctx.INT().getText());
            }
        };
        
        visitor.visit(tree);
    }
}

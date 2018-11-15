import java.io.IOException;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;


public class Test {

    public static void main(String[] args) throws IOException {
        var input = CharStreams.fromFileName("symbols.txt");
        
        var lexer = new HelloLexer(input);
        var tokenStream = new CommonTokenStream(lexer);
       
        /*
        tokenStream.fill();
        List<Token> tokens = tokenStream.getTokens();
        for (var token : tokens) {
            var text = token.getText();
            var type = HelloLexer.VOCABULARY.getSymbolicName(token.getType());
            
            System.out.println(text + " : " + type);
        }
        */
        
        var parser = new HelloParser(tokenStream);
        var tree = parser.program();
        System.out.println(tree.toStringTree(parser));

        // Un obiect ParseTreeProperty<T> îi poate asocia fiecărui nod
        // din arbore un obiect de tipul T. În cazul nostru, folosim un obiect
        // ParseTreeProperty<Symbol>, pentru a reține simbolul aferent
        // unui anumit nod din arbore, odată ce l-am definit sau rezolvat
        // pe primul. Astfel, evităm căutarea lui în domeniile de vizibilitate
        // la parcurgerile ulterioare ale arborelui.
        var symbols = new ParseTreeProperty<Symbol>();
    
        var resolver = new HelloParserBaseListener() {
            Scope currentScope = null;
            
            @Override
            public void enterBlock(HelloParser.BlockContext ctx) {
                currentScope = new DefaultScope(currentScope);
            }
            
            @Override
            public void exitBlock(HelloParser.BlockContext ctx) {
                currentScope = currentScope.getParent();
            }
            
            @Override
            public void enterDef(HelloParser.DefContext ctx) {
                // La definirea unei variabile, creăm un nou simbol.
                var sym = new IdSymbol(ctx.ID().getText());
                
                // Adăugăm simbolul în domeniul de vizibilitatea curent,
                // semnalând eroare dacă exista deja acolo.
                if (! currentScope.add(sym))
                    error(ctx.ID().getSymbol(),
                          ctx.ID().getText() + " redefined");
                else
                    // Atașăm simbolul nodului din arbore.
                    symbols.put(ctx.ID(), sym);
            }
            
            @Override
            public void enterId(HelloParser.IdContext ctx) {
                // La referirea unei variabile, o căutăm în domeniul
                // de vizibilitate curent.
                var sym = currentScope.lookup(ctx.ID().getText());
                
                // Semnalăm eroare dacă nu există.
                if (sym == null)
                    error(ctx.ID().getSymbol(),
                          ctx.ID().getText() + " undefined");
                else
                    // Atașăm simbolul nodului din arbore.
                    symbols.put(ctx.ID(), sym);
            }
        };
        
        // Un walker realizează o parcurgere în adâncime a arborelui de
        // derivare, invocând la momentul potrivit metodele enter/ exit.
        var walker = new ParseTreeWalker();
        walker.walk(resolver, tree);
    
    }
    
    public static void error(Token token, String message) {
        System.err.println("line " + token.getLine()
                + ":" + token.getCharPositionInLine()
                + ", " + message);
    }

}

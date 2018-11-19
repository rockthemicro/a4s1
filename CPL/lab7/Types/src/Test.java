import java.io.IOException;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.ParseTreeWalker;


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
        
        // Domeniul de vizibilitate global conține inițial doar numele
        // tipurilor.
        var globals = new DefaultScope(null);
        globals.add(new TypeSymbol("Int"));
        globals.add(new TypeSymbol("Bool"));
        globals.add(new TypeSymbol("Float"));
        
        // Pentru verificarea tipurilor, reținem tipurile aferente anumitor
        // noduri din arbore.
        var types = new ParseTreeProperty<TypeSymbol>();
    
        var resolver = new HelloParserBaseListener() {
            // Menținem domeniul de vizibilitate curent, în raport cu care
            // vom defini sau vom rezolva simboluri. El va fi modificat
            // la intrarea sau la părăsirea unei construcții de limbaj care
            // induce un nou domeniu de vizibilitate.
            Scope currentScope = globals;
            
            @Override
            public void enterDef(HelloParser.DefContext ctx) {
                // La definirea unei variabile, creăm un nou simbol.
                var sym = new IdSymbol(ctx.ID().getText());
                
                // Adăugăm simbolul în domeniul de vizibilitatea curent,
                // semnalând eroare dacă exista deja acolo.
                if (! currentScope.add(sym)) {
                    error(ctx.ID().getSymbol(),
                          ctx.ID().getText() + " redefined");
                    return;
                }
                
                // Atașăm simbolul nodului din arbore.
                symbols.put(ctx.ID(), sym);
                
                // Rezolvăm tipul variabilei definite.
                var typeSym = globals.lookup(ctx.TYPE().getText());
                
                // Dacă tipul nu există, semnalăm eroare.
                if (typeSym == null) {
                    error(ctx.TYPE().getSymbol(),
                          "Variable " + ctx.ID().getText()
                              + " has undefined type "
                              + ctx.TYPE().getText());
                    return;
                }
                
                // Altfel, salvăm informația de tip în simbolul asociat
                // variabilei.
                sym.setType((TypeSymbol)typeSym);
                symbols.put(ctx.TYPE(), typeSym);
            }
            
            @Override
            public void enterFdef(HelloParser.FdefContext ctx) {
            	var methSym = new MethodSymbol(ctx.ID(0).getText(), currentScope);
            	if (! currentScope.add(methSym)) { // adauga in scopeul in care se afla definita metodei
            		error(ctx.ID(0).getSymbol(), ctx.ID(0).getText() + " redefined method");
            	}

        		symbols.put(ctx.ID(0), methSym); // adauga symbolul efectiv in arborele de parsare
        		
            	for (int i = 1; i < ctx.ID().size(); i++) {
            		var sym = new IdSymbol(ctx.ID(i).getText());
            		methSym.put(sym);
            	}
            	currentScope = methSym;
            	
            	var typeSym = globals.lookup(ctx.TYPE(ctx.TYPE().size() - 1).getText());
            	methSym.setType((TypeSymbol) typeSym);
            }
            
            
            @Override
            public void exitFdef(HelloParser.FdefContext ctx) {
            	currentScope = currentScope.getParent();
            }
            
            @Override
            public void enterFcall(HelloParser.FcallContext ctx) {
            	var sym = currentScope.lookup(ctx.ID(0).getText());
            	var sym2 = globals.lookup(ctx.ID(0).getText());
            	
            	var sym3 = sym;
            	if (sym3 == null) sym3 = sym2;
            	
            	if (sym == null && sym2 == null)
                    error(ctx.ID(0).getSymbol(),
                          ctx.ID(0).getText() + " undefined function");
                else
                    // Atașăm simbolul nodului din arbore.
                    symbols.put(ctx.ID(0), sym);
            	
            	for (int i = 1; i < ctx.ID().size(); i++) {
            		sym = currentScope.lookup(ctx.ID(i).getText());
            		
            		if (sym == null)
                        error(ctx.ID(i).getSymbol(),
                              ctx.ID(i).getText() + " undefined variable");
                    else
                        // Atașăm simbolul nodului din arbore.
                        symbols.put(ctx.ID(i), sym);
                		
            	}
            	
            	if (sym3 != null)
            		System.out.println("Functia " + ctx.ID(0).getText() + " are tipul " + (((MethodSymbol)sym3).getType()).getName());
            }
            
            @Override
            public void enterId(HelloParser.IdContext ctx) {
                // La referirea unei variabile, o căutăm în domeniul
                // de vizibilitate curent.
                var sym = currentScope.lookup(ctx.ID().getText());
                
                // Semnalăm eroare dacă nu există.
                if (sym == null) {
                    error(ctx.ID().getSymbol(),
                          ctx.ID().getText() + " undefined");
                    return;
                }
                
                // Atașăm simbolul nodului din arbore.
                symbols.put(ctx.ID(), sym);
                
                // Utilizăm drept informație de tip atașată acestui nod
                // informația de tip salvată deja în simbol încă de la
                // definirea variabilei.
                types.put(ctx, ((IdSymbol)sym).getType());
            }
            
            @Override
            public void enterInt(HelloParser.IntContext ctx) {
                // Tipul unui literal întreg este Int.
                types.put(ctx, (TypeSymbol)globals.lookup("Int"));
            }
            
            @Override
            public void exitPlus(HelloParser.PlusContext ctx) {
                // Pentru tiparea unui nod +, avem nevoie de tipurile celor doi
                // operanzi.
                var typeSym0 = types.get(ctx.expr(0));
                var typeSym1 = types.get(ctx.expr(1));
                
                // Verificarea evită propagarea în cascadă a erorilor
                // către expresiile exterioare. O puteți elimina pentru
                // a observa efectul.
                if (typeSym0 == null || typeSym1 == null)
                    return;
                
                // Este necesar ca ambii operanzi să aibă tipul Int.
                if (typeSym0 != globals.lookup("Int") ||
                    typeSym1 != globals.lookup("Int")) {
                    error(ctx.PLUS().getSymbol(),
                          "Operand of + has type other than Int");
                    return;
                }
                
                // Dacă este îndeplinită condiția de mai sus, tipul sumei
                // este tot Int.
                types.put(ctx, (TypeSymbol)globals.lookup("Int"));
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

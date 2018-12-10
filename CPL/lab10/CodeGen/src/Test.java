import java.io.IOException;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;


public class Test {

    public static void main(String[] args) throws IOException {
        var input = CharStreams.fromFileName("program.txt");
        
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
        //System.out.println(tree.toStringTree(parser));

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
            public void enterBlock(HelloParser.BlockContext ctx) {
                // La intrarea într-un bloc, creăm un nou domeniu de
                // vizibilitate.
                currentScope = new DefaultScope(currentScope);
            }
            
            @Override
            public void exitBlock(HelloParser.BlockContext ctx) {
                // La părăsirea unui bloc, părăsim și domeniul de vizibilitate
                // aferent.
                currentScope = currentScope.getParent();
            }
            
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
                // Pentru tipare unui nod +, avem nevoie de tipurile celor doi
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
        
        // Obiectele ST reprezintă șabloane StringTemplate, care conțin text
        // obișnuit și atribute, ultimele fiind flancate între < și >.
        // Valorile atributelor sunt definite utilizând metoda add, și pot
        // fi de orice tip, inclusiv alte șabloane imbricate.
        // Sunt permise multiple valori pentru același atribut, acestea fiind
        // enumerate folosind opțiunea separator.
        // Șirul final de caractere este obținut cu metoda render.
        ST st = new ST("Cool <name; separator=\", \">");
        st.add("name", "compiler");
        st.add("name", "interpreter");
        //System.out.println(st.render());
        
        ST st2 = new ST("Cool <name; separator=\", \">");
        st2.add("name", st);
        //System.out.println(st2.render());
        
        // Definiții mai modulare pentru șabloane se pot da în fișiere .stg
        // (string template group). Șabloanele pot fi parametrizate, similar
        // funcțiilor și pot fi accesate prin metoda getInstanceOf.
        var group = new STGroupFile("cgen.stg");
        ST literal = group.getInstanceOf("literal");
        literal.add("value", 5);
        //System.out.println(literal.render());
        
        ST plus = group.getInstanceOf("plus");
        plus.add("e1", literal);
        plus.add("e2", literal);
        //System.out.println(plus.render());
        
        // Vizitator care generează cod pentru expresiile aritmetice formate din
        // literali întregi, adunări și paranteze.
        // sonticaaaaa
        var codeGenerator = new HelloParserBaseVisitor<ST>() {
            @Override
            public ST visitInt(HelloParser.IntContext ctx) {
            	Integer nr = Integer.parseInt(ctx.INT().getText());
            	if (nr > 0xFFFF) {
            		int nr_low = nr & 0xFFFF;
            		
            		int nr_high = nr & 0xFFFF0000;
            		nr_high = nr_high >> 16;
            		
            		return group.getInstanceOf("bigLiteral").add("higher", Integer.toString(nr_high))
            				.add("lower", Integer.toString(nr_low));
            	} else {
            		return group.getInstanceOf("literal")
                        .add("value", ctx.INT().getText());
            	}
            }
            
            @Override
            public ST visitIf(HelloParser.IfContext ctx) {
            	var ifThenElse = group.getInstanceOf("ifthenelse");
            	ifThenElse.add("cond", ctx.cond.getText());
            	ifThenElse.add("then", ctx.thenBranch.getText());
            	ifThenElse.add("elsee", ctx.elseBranch.getText());
            	
            	return ifThenElse;
            }
            
            @Override
            public ST visitDefin(HelloParser.DefinContext ctx) {
            	// TODO: asta nu e bun, trebuie ca asta sa fie in zona de date (.data)
            	var globalVar = group.getInstanceOf("global_var");
            	globalVar.add("var", ctx.def().ID().getText());
            	
            	return globalVar;
            }
            
            @Override
            public ST visitPlus(HelloParser.PlusContext ctx) {
                var plus = group.getInstanceOf("plus");
                plus.add("e1", visit(ctx.expr(0)));
                plus.add("e2", visit(ctx.expr(1)));
                return plus;
            }
            
            @Override
            public ST visitSub(HelloParser.SubContext ctx) {
            	var sub = group.getInstanceOf("sub");
                sub.add("e1", visit(ctx.expr(0)));
                sub.add("e2", visit(ctx.expr(1)));
                return sub;
			}
            
            
            @Override
            public ST visitDiv(HelloParser.DivContext ctx) {
            	var div = group.getInstanceOf("div");
            	div.add("e1", visit(ctx.expr(0)));
            	div.add("e2", visit(ctx.expr(1)));
            	return div;
            }
            
            @Override
            public ST visitMod(HelloParser.ModContext ctx) {
            	var mod = group.getInstanceOf("mod");
            	mod.add("e1", visit(ctx.expr(0)));
            	mod.add("e2", visit(ctx.expr(1)));
            	return mod;
            }
            
            @Override
            public ST visitProgram(HelloParser.ProgramContext ctx) {
                // Aici, exploatăm posibilitatea precizării de valori multiple
                // ale atributului expresie dintr-o secvență.
                var seq = group.getInstanceOf("sequence");
                for (var e : ctx.expr())
                    seq.add("e", visit(e));
                return seq;
            }
            
            @Override
            public ST visitParen(HelloParser.ParenContext ctx) {
                return visit(ctx.expr());
            }
        };
        
        System.out.println(codeGenerator.visit(tree).render());
    }
    
    public static void error(Token token, String message) {
        System.err.println("line " + token.getLine()
                + ":" + token.getCharPositionInLine()
                + ", " + message);
    }

}

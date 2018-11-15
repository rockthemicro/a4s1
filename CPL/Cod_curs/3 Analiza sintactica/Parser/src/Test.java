import java.io.IOException;
import java.util.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;


public class Test {

    public static void main(String[] args) throws IOException {
        var input = CharStreams.fromFileName("program2.txt");
        
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
        var tree = parser.expr();
        System.out.println(tree.toStringTree(parser));
        
        // Interfața HelloParserListener conține, pentru fiecare alternativă
        // etichetată, câte o pereche de metode enter/ exit. Spre exemplu,
        // pentru eticheta if, avem perechea de metode enterIf(IfContext)
        // și exitIf(IfContext). Clasa HelloParserBaseListener oferă
        // implementări goale ale acestor metode, astfel încât noi să putem
        // supradefini doar metodele de interes.
        //
        // Listenerii au avantajul că parcurgerea arborelui de derivare este
        // realizată automat, pe baza unui walker, ca mai jos. Dezavantajul
        // constă în faptul că este parcurs întregul arbore de derivare, chiar
        // dacă pe noi ne intesează doar anumite noduri particulare.
        var listener = new HelloParserBaseListener() {
            @Override
            public void exitInt(HelloParser.IntContext ctx) {
                // Afișăm fiecare literal întreg, când îl întâlnim.
                System.out.println("Found integer literal: " + ctx.INT());
            }
        };
        
        // Un walker realizează o parcurgere în adâncime a arborelui de
        // derivare, invocând la momentul potrivit metodele enter/ exit.
        var walker = new ParseTreeWalker();
        walker.walk(listener, tree);
        
        // Interfața HelloParserVisitor<T> conține câte o metodă pentru fiecare
        // alternativă etichetată. Spre exemplu, pentru eticheta if, avem 
        // metoda T visitIf(IfContext). Clasa HelloParserBaseVisitor<T> oferă
        // implementări implicite ale acestor metode, astfel încât noi să putem
        // supradefini doar metodele de interes.
        //
        // De remarcat că, spre deosebire de listeneri, metodele de vizitare
        // pot întoarce și o valoare utilă, care poate fi prelucrată recursiv.
        // Acest lucru, alături de faptul că putem vizita doar nodurile de
        // interes pentru noi, constituie avantajul vizitatorilor. Dezavantajul
        // constă tocmai în faptul că e necesară vizitarea explicită a copiilor,
        // mai ales când trebuie să parcurgem întregul arbore.
        //
        // Vizitatorul de mai jos extrage recursiv toate numele de variabilele
        // dintr-o expresie, sub forma unei liste de String-uri.
        var visitor = new HelloParserBaseVisitor<List<String>>() {
            @Override
            public List<String> visitId(HelloParser.IdContext ctx) {
                return Arrays.asList(ctx.ID().toString());
            }
            
            @Override
            public List<String> visitInt(HelloParser.IntContext ctx) {
                // Un întreg nu conține variabile.
                return Arrays.asList();
            }
            
            @Override
            public List<String> visitIf(HelloParser.IfContext ctx) {
                List<String> list = new LinkedList<String>();
                
                // Vizităm explicit copiii și adăugăm conținutul listelor lor
                // la lista curentă.
                for (var expr : ctx.expr()) {
                    list.addAll(visit(expr));
                }
                
                // Alternativ, putem vizita explicit copiii, prin numele lor.
                /*
                list.addAll(visit(ctx.cond));
                list.addAll(visit(ctx.thenBranch));
                list.addAll(visit(ctx.elseBranch));  
                */
                
                return list;
            }
        };
        
        System.out.println("Variable names: " + visitor.visit(tree));
    }

}

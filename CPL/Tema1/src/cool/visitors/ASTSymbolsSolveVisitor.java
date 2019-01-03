package cool.visitors;

import cool.compiler.*;
import cool.parser.CoolParser;
import cool.structures.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;

@SuppressWarnings("Duplicates")
public class ASTSymbolsSolveVisitor extends ASTNopVisitor {

    @Override
    public void visit(ASTClassNode node) {
        String className = node.name;
        String parentName = node.inherits;

        CoolParser.Class_nodeContext ctx2 = (CoolParser.Class_nodeContext) node.ctx;
        CoolParser.Class_headerContext ctx3 = ctx2.class_header();

        // Nu permitem parinti pe Int, String, Bool si SELF_TYPE
        if (parentName != null
                && (parentName.equals("Int")
                || parentName.equals("String")
                || parentName.equals("Bool")
                || parentName.equals("SELF_TYPE"))) {

            SymbolTable.error(node.ctx, ((CoolParser.Inheriter_classContext) ctx3).parent,
                    "Class " + className + " has illegal parent " + parentName);

        }

        if (parentName != null) {

            // Deja am tratat aceasta eroare in ASTSymbolsDefineVisitor
            if (parentName.equals("SELF_TYPE"))
                return;

            // Daca mostenim o clasa, dar clasa mostenita e nedefinita, avem o eroare
            ClassSymbol parentSymbol = (ClassSymbol) SymbolTable.globals.lookup(parentName);

            if (parentSymbol == null) {
                SymbolTable.error(node.ctx, ((CoolParser.Inheriter_classContext) ctx3).parent,
                        "Class " + className + " has undefined parent " + parentName);

                return;
            }

            ClassSymbol currSymbol = (ClassSymbol) SymbolTable.globals.lookup(className);
            currSymbol.setParent(parentSymbol);

            ArrayList<Token> cycleTokens = new ArrayList<>();
            ArrayList<String> cycleClassNames = new ArrayList<>();
            ArrayList<ParserRuleContext> cycleContexts = new ArrayList<>();

            // Incercam sa detectam un ciclu de mostenire
            while (true) {
                if (parentSymbol.astClassNode == null)
                    break;

                CoolParser.Class_nodeContext parentCtx = (CoolParser.Class_nodeContext) parentSymbol.astClassNode.ctx;
                CoolParser.Class_headerContext parentHeaderCtx = parentCtx.class_header();

                cycleContexts.add(parentSymbol.astClassNode.ctx);
                cycleClassNames.add(parentSymbol.getName());
                if (parentHeaderCtx instanceof CoolParser.Inheriter_classContext) {
                    cycleTokens.add(((CoolParser.Inheriter_classContext) parentHeaderCtx).name);
                } else {
                    cycleTokens.add(((CoolParser.Simple_classContext) parentHeaderCtx).name);
                }

                if (currSymbol.getName().equals(parentSymbol.getName())) {

                    for (int i = 0; i < cycleClassNames.size(); i++) {
                        SymbolTable.error(cycleContexts.get(i), cycleTokens.get(i), "Inheritance cycle for class "
                                + cycleClassNames.get(i));
                    }

                    break;
                }

                if (parentSymbol.getClassParent() != null) {
                    parentSymbol = (ClassSymbol) SymbolTable.globals.lookup(parentSymbol.getClassParent().getName());

                } else {
                    break;
                }
            }

        }

        super.visit(node);
    }

    @Override
    public void visit(ASTAttributeNode node) {
        if (node.classSymbol == null)
            return;

        // nu permitem redefinirea unui atribut mostenit
        if (node.classSymbol.getClassParent() != null && node.classSymbol.getClassParent().lookup(node.id) != null) {
            if (node.ctx instanceof CoolParser.Attr_no_asgnContext) {
                var ctx = (CoolParser.Attr_no_asgnContext) node.ctx;

                SymbolTable.error(node.ctx, ctx.ID().getSymbol(),
                        "Class " + node.classSymbol.getName() + " redefines inherited attribute " + node.id);

            } else {
                var ctx = (CoolParser.Attr_asgnContext) node.ctx;

                SymbolTable.error(node.ctx, ctx.ID().getSymbol(),
                        "Class " + node.classSymbol.getName() + " redefines inherited attribute " + node.id);
            }

            return;
        }

        if (SymbolTable.globals.lookup(node.type) == null) {
            if (node.ctx instanceof CoolParser.Attr_no_asgnContext) {
                var ctx = (CoolParser.Attr_no_asgnContext) node.ctx;

                SymbolTable.error(node.ctx, ctx.type().CLASS_NAME().getSymbol(),
                        "Class " + node.classSymbol.getName() + " has attribute " + node.id + " with undefined type " + node.type);

            } else {
                var ctx = (CoolParser.Attr_asgnContext) node.ctx;

                SymbolTable.error(node.ctx, ctx.type().CLASS_NAME().getSymbol(),
                        "Class " + node.classSymbol.getName() + " has attribute " + node.id + " with undefined type " + node.type);
            }

            return;
        }

        node.idSymbol.setType((TypeSymbol) SymbolTable.globals.lookup(node.type));

        super.visit(node);
    }

    @Override
    public void visit(ASTMethodNode node) {
        if (node.methodSymbol == null)
            return;

        TypeSymbol returnType = (TypeSymbol) SymbolTable.globals.lookup(node.retType);
        if (returnType == null) {
            var ctx = (CoolParser.MethodContext) node.ctx;

            SymbolTable.error(node.ctx, ctx.ret,
                    "Class " + node.classSymbol.getName() + " has method " + node.id + " with undefined return type "
                            + node.retType);
        } else {
            node.methodSymbol.setType(returnType);
        }

        super.visit(node);
    }

    @Override
    public void visit(ASTMethodParamsNode node) {
        for (int i = 0; i < node.ids.size(); i++) {
            String id = node.ids.get(i);
            String type = node.types.get(i);
            boolean redefined = false;
            var classSymbol = SymbolTable.globals.lookup(type);

            // nu permitem parametri cu numele self
            if (id.equals("self")) {
                SymbolTable.error(node.ctx, node.idTokens.get(i),
                        "Method " + node.methodNode.methodSymbol.getName() + " of class " + node.methodNode.classSymbol.getName()
                                + " has formal parameter with illegal name self");

                continue;
            }

            // nu permitem redefinirea parametrilor formali
            for (int j = 0; j < i; j++) {
                String previousId = node.ids.get(j);

                if (previousId.equals(id)) {
                    redefined = true;
                    SymbolTable.error(node.ctx, node.idTokens.get(i),
                            "Method " + node.methodNode.methodSymbol.getName() + " of class " + node.methodNode.classSymbol.getName()
                                    + " redefines formal parameter " + id);

                    break;
                }
            }

            if (redefined)
                continue;

            // nu permitem parametri cu tipul SELF_TYPE
            if (type.equals("SELF_TYPE")) {
                SymbolTable.error(node.ctx, node.typeTokens.get(i),
                        "Method " + node.methodNode.methodSymbol.getName() + " of class " + node.methodNode.classSymbol.getName()
                                + " has formal parameter " + id + " with illegal type SELF_TYPE");

                continue;
            }

            var idSymbol = new IdSymbol(id);
            node.methodNode.methodSymbol.add(idSymbol);

            // verificam daca tipul parametrului este definit
            if (classSymbol == null) {
                SymbolTable.error(node.ctx, node.typeTokens.get(i), "Method " + node.methodNode.id + " of class " +
                        node.methodNode.classSymbol.getName() + " has formal parameter " + id + " with undefined type " +
                        type);

            } else {
                idSymbol.setType((ClassSymbol) classSymbol);
            }
        }

        super.visit(node);
    }

    @Override
    public void visit(ASTLetNode node) {
        if (SymbolTable.globals.lookup(node.type) == null) {
            SymbolTable.error(node.ctx, ((CoolParser.Let_bindContext) node.ctx).type().CLASS_NAME().getSymbol(),
                    "Let variable " + node.id + " has undefined type " + node.type);

            return;
        }

        if (node.id.equals("self")) {
            SymbolTable.error(node.ctx, ((CoolParser.Let_bindContext) node.ctx).ID().getSymbol(),
                    "Let variable has illegal name self");

            return;
        }

        var idSymbol = new IdSymbol(node.id);
        idSymbol.setType((TypeSymbol) SymbolTable.globals.lookup(node.type));

        node.exprScope.add(idSymbol);

        // necesar pentru ca, in momentul in care definim urmatoarele variabile, vrem ca
        // acestea sa aiba acces la ce am definit noi
        for (int i = node.idx + 1; i < node.parent.letNodes.size(); i++) {
            node.parent.letNodes.get(i).currentScope.add(idSymbol);
        }

        super.visit(node);
    }

    @Override
    public void visit(ASTExprCaseNode node) {
        for (int i = 0; i < node.ids.size(); i++) {
            var id = node.ids.get(i);
            var type = node.types.get(i);

            if (id.equals("self")) {
                SymbolTable.error(node.ctx, ((CoolParser.Expr_caseContext) node.ctx).ID(i).getSymbol(),
                        "Case variable has illegal name self");
            }

            if (type.equals("SELF_TYPE")) {
                SymbolTable.error(node.ctx, ((CoolParser.Expr_caseContext) node.ctx).type(i).SELF().getSymbol(),
                        "Case variable " + id + " has illegal type SELF_TYPE");

            } else if (SymbolTable.globals.lookup(type) == null) {
                SymbolTable.error(node.ctx, ((CoolParser.Expr_caseContext) node.ctx).type(i).CLASS_NAME().getSymbol(),
                        "Case variable " + id + " has undefined type " + type);
            }

            var idSymbol = new IdSymbol(id);
            idSymbol.setType((TypeSymbol) SymbolTable.globals.lookup(type));
            node.scopes.get(i).add(idSymbol);
        }

        super.visit(node);
    }

}
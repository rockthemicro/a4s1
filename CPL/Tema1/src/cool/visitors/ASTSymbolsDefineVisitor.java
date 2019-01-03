package cool.visitors;

import cool.compiler.*;
import cool.parser.CoolParser;
import cool.structures.*;

@SuppressWarnings("Duplicates")
public class ASTSymbolsDefineVisitor extends ASTNopVisitor {

    ClassSymbol currClassSymbol = null;
    MethodSymbol currMethodSymbol = null;
    Scope currentScope = null;

    @Override
    public void visit(ASTClassNode node) {
        String className = node.name;
        String parentName = node.inherits;

        CoolParser.Class_nodeContext ctx2 = (CoolParser.Class_nodeContext) node.ctx;
        CoolParser.Class_headerContext ctx3 = ctx2.class_header();

        // Nu permitem clase cu numele SELF_TYPE
        if (className.equals("SELF_TYPE")) {
            if (ctx3 instanceof CoolParser.Inheriter_classContext) {
                SymbolTable.error(node.ctx, ((CoolParser.Inheriter_classContext) ctx3).name,
                        "Class has illegal name SELF_TYPE");

            } else if (ctx3 instanceof  CoolParser.Simple_classContext) {
                SymbolTable.error(node.ctx, ((CoolParser.Simple_classContext) ctx3).name,
                        "Class has illegal name SELF_TYPE");
            }

            return;
        }

        // Nu permitem clase duplicate
        if (SymbolTable.globals.lookup(className) != null) {
            if (ctx3 instanceof CoolParser.Inheriter_classContext) {
                SymbolTable.error(node.ctx, ((CoolParser.Inheriter_classContext) ctx3).name,
                        "Class " + className + " is redefined");

            } else if (ctx3 instanceof  CoolParser.Simple_classContext) {
                SymbolTable.error(node.ctx, ((CoolParser.Simple_classContext) ctx3).name,
                        "Class " + className + " is redefined");
            }

            return;
        }

        var classSymbol = new ClassSymbol(className, (ClassSymbol) SymbolTable.globals.lookup("Object"));
        classSymbol.astClassNode = node;
        SymbolTable.globals.add(classSymbol);

        // asta va fi folosit ulterior in definirea scopeului parinte al metodelor
        currClassSymbol = classSymbol;

        // pentru simularea intrarii si iesirii din scopeuri
        currentScope = classSymbol;

        // atasam scope-symbolul classSymbol nodului curent
        node.classSymbol = classSymbol;
        node.currentScope = classSymbol;

        super.visit(node);
    }

    @Override
    public void visit(ASTAttributeNode node) {

        // nu permitem atribute cu numele self
        if (node.id.equals("self")) {
            if (node.ctx instanceof CoolParser.Attr_no_asgnContext) {
                var ctx = (CoolParser.Attr_no_asgnContext) node.ctx;

                SymbolTable.error(node.ctx, ctx.ID().getSymbol(),
                        "Class " + currClassSymbol.getName() + " has attribute with illegal name self");

            } else {
                var ctx = (CoolParser.Attr_asgnContext) node.ctx;

                SymbolTable.error(node.ctx, ctx.ID().getSymbol(),
                        "Class " + currClassSymbol.getName() + " has attribute with illegal name self");
            }

            return;
        }

        // nu permitem redefinirea unui atribut
        if (currClassSymbol.attrExists(node.id)) {
            if (node.ctx instanceof CoolParser.Attr_no_asgnContext) {
                var ctx = (CoolParser.Attr_no_asgnContext) node.ctx;

                SymbolTable.error(node.ctx, ctx.ID().getSymbol(),
                        "Class " + currClassSymbol.getName() + " redefines attribute " + node.id);

            } else {
                var ctx = (CoolParser.Attr_asgnContext) node.ctx;

                SymbolTable.error(node.ctx, ctx.ID().getSymbol(),
                        "Class " + currClassSymbol.getName() + " redefines attribute " + node.id);
            }

            return;
        }

        var idSymbol = new IdSymbol(node.id);

        currClassSymbol.add(idSymbol);

        node.currentScope = currClassSymbol;
        node.idSymbol = idSymbol;
        node.classSymbol = currClassSymbol;

        super.visit(node);

    }

    @Override
    public void visit(ASTMethodNode node) {
        node.params.methodNode = node;

        if (currClassSymbol.methodExists(node.id)) {
            var ctx = (CoolParser.MethodContext) node.ctx;

            SymbolTable.error(node.ctx, ctx.ID().getSymbol(),
                    "Class " + currClassSymbol.getName() + " redefines method " + node.id);

            return;
        }

        var methodSymbol = new MethodSymbol(node.id, currClassSymbol);
        methodSymbol.astMethodNode = node;

        currClassSymbol.add(methodSymbol);
        currMethodSymbol = methodSymbol;

        node.classSymbol = currClassSymbol;
        node.methodSymbol = methodSymbol;

        currentScope = currMethodSymbol;
        node.currentScope = currMethodSymbol;
        super.visit(node);
    }

    @Override
    public void visit(ASTMethodParamsNode node) {
        node.currentScope = currentScope;
    }

    @Override
    public void visit(ASTExprLetNode node) {
        Scope oldCurrentScope = currentScope;


        var letScope = new DefaultScope(currentScope);
        node.currentScope = letScope;
        currentScope = letScope;

        super.visit(node);

        currentScope = oldCurrentScope;
    }

    @Override
    public void visit(ASTLetNode node) {
        // setam drept currentScope o copie a parintelui Scopeului curent; facem acest lucru deoarece
        // ne dorim ca, pe masura ce definim variabile locale in let, DOAR urmatoarele variabile ce sunt definite
        // sa aiba acces la ce am definit pana acum
        node.currentScope = new DefaultScope(currentScope.getParent());
        node.exprScope = currentScope;

        var oldCurrentScope = currentScope;
        currentScope = node.currentScope;

        super.visit(node);

        currentScope = oldCurrentScope;
    }

    @Override
    public void visit(ASTExprCaseNode node) {
        node.currentScope = currentScope;
        node.expr.accept(this);

        for (int i = 0; i < node.ids.size(); i++) {
            node.scopes.add(new DefaultScope(currentScope));
            var oldCurrScope = currentScope;

            currentScope = node.scopes.get(i);
            node.exprs.get(i).accept(this);
            currentScope = oldCurrScope;
        }
    }

    @Override
    public void visit(ASTClassBodyNode node) {
        node.currentScope = currentScope;
        super.visit(node);
    }

    @Override
    public void visit(ASTExprArithmNode node) {
        node.currentScope = currentScope;
        super.visit(node);
    }

    @Override
    public void visit(ASTExprAssignNode node) {
        node.currentScope = currentScope;
        super.visit(node);
    }

    @Override
    public void visit(ASTExprBlockNode node) {
        node.currentScope = currentScope;
        super.visit(node);
    }

    @Override
    public void visit(ASTExprCompNode node) {
        node.currentScope = currentScope;
        super.visit(node);
    }

    @Override
    public void visit(ASTExprConstNode node) {
        node.currentScope = currentScope;
        super.visit(node);
    }

    @Override
    public void visit(ASTExprExprFcallNode node) {
        node.currentScope = currentScope;
        super.visit(node);
    }

    @Override
    public void visit(ASTExprFcallNode node) {
        node.currentScope = currentScope;
        super.visit(node);
    }

    @Override
    public void visit(ASTExprIdNode node) {
        node.currentScope = currentScope;
        super.visit(node);
    }

    @Override
    public void visit(ASTExprIfNode node) {
        node.currentScope = currentScope;
        super.visit(node);
    }

    @Override
    public void visit(ASTExprNegNode node) {
        node.currentScope = currentScope;
        super.visit(node);
    }

    @Override
    public void visit(ASTExprNewNode node) {
        node.currentScope = currentScope;
        super.visit(node);
    }

    @Override
    public void visit(ASTExprNotNode node) {
        node.currentScope = currentScope;
        super.visit(node);
    }

    @Override
    public void visit(ASTExprVoidNode node) {
        node.currentScope = currentScope;
        super.visit(node);
    }

    @Override
    public void visit(ASTExprWhileNode node) {
        node.currentScope = currentScope;
        super.visit(node);
    }

    @Override
    public void visit(ASTFcallNode node) {
        node.currentScope = currentScope;
        super.visit(node);
    }

    @Override
    public void visit(ASTManyExprNode node) {
        node.currentScope = currentScope;
        super.visit(node);
    }

    @Override
    public void visit(ASTProgramNode node) {
        node.currentScope = SymbolTable.globals;
        super.visit(node);
    }
}


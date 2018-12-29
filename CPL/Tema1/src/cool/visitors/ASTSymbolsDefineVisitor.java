package cool.visitors;

import cool.compiler.ASTAttributeNode;
import cool.compiler.ASTClassNode;
import cool.compiler.ASTMethodNode;
import cool.compiler.ASTMethodParamsNode;
import cool.parser.CoolParser;
import cool.structures.ClassSymbol;
import cool.structures.IdSymbol;
import cool.structures.MethodSymbol;
import cool.structures.SymbolTable;

@SuppressWarnings("Duplicates")
public class ASTSymbolsDefineVisitor extends ASTNopVisitor {

    ClassSymbol currClassScope = null;
    MethodSymbol currMethodSymbol = null;

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

        var classSymbol = new ClassSymbol(className, SymbolTable.globals);
        classSymbol.astClassNode = node;
        SymbolTable.globals.add(classSymbol);

        // asta va fi folosit ulterior in definirea scopeului parinte al metodelor
        currClassScope = classSymbol;

        // atasam scope-symbolul classSymbol nodului curent
        node.classSymbol = classSymbol;
    }

    @Override
    public void visit(ASTAttributeNode node) {

        // nu permitem atribute cu numele self
        if (node.id.equals("self")) {
            if (node.ctx instanceof CoolParser.Attr_no_asgnContext) {
                var ctx = (CoolParser.Attr_no_asgnContext) node.ctx;

                SymbolTable.error(node.ctx, ctx.ID().getSymbol(),
                        "Class " + currClassScope.getName() + " has attribute with illegal name self");

            } else {
                var ctx = (CoolParser.Attr_asgnContext) node.ctx;

                SymbolTable.error(node.ctx, ctx.ID().getSymbol(),
                        "Class " + currClassScope.getName() + " has attribute with illegal name self");
            }

            return;
        }

        // nu permitem redefinirea unui atribut
        if (currClassScope.exists(node.id)) {
            if (node.ctx instanceof CoolParser.Attr_no_asgnContext) {
                var ctx = (CoolParser.Attr_no_asgnContext) node.ctx;

                SymbolTable.error(node.ctx, ctx.ID().getSymbol(),
                        "Class " + currClassScope.getName() + " redefines attribute " + node.id);

            } else {
                var ctx = (CoolParser.Attr_asgnContext) node.ctx;

                SymbolTable.error(node.ctx, ctx.ID().getSymbol(),
                        "Class " + currClassScope.getName() + " redefines attribute " + node.id);
            }

            return;
        }

        var idSymbol = new IdSymbol(node.id);

        currClassScope.add(idSymbol);
        node.idSymbol = idSymbol;
        node.classSymbol = currClassScope;
    }

    @Override
    public void visit(ASTMethodNode node) {
        node.params.methodNode = node;

        if (currClassScope.exists(node.id)) {
            var ctx = (CoolParser.MethodContext) node.ctx;

            SymbolTable.error(node.ctx, ctx.ID().getSymbol(),
                    "Class " + currClassScope.getName() + " redefines method " + node.id);

            return;
        }

        var methodSymbol = new MethodSymbol(node.id, currClassScope);
        methodSymbol.astMethodNode = node;

        currClassScope.add(methodSymbol);
        currMethodSymbol = methodSymbol;

        node.classSymbol = currClassScope;
        node.methodSymbol = methodSymbol;
    }

}

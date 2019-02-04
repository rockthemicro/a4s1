package cool.visitors;

import cool.compiler.*;
import cool.parser.CoolParser;
import cool.structures.*;

import java.util.ArrayList;

@SuppressWarnings("Duplicates")
public class ASTTypeCheckingVisitor extends ASTNopVisitor {

    private String lastTypeChecked = "";

    private boolean isOperand(ASTBaseNode node) {
        if (node instanceof ASTExprIdNode || node instanceof ASTExprConstNode) {
            return true;
        }

        return false;
    }

    public ClassSymbol getEnclosingClass(Scope scope) {
        while (scope != null && scope instanceof ClassSymbol == false) {
            scope = scope.getParent();
        }

        if (scope != null) {
            ClassSymbol classSymbol = (ClassSymbol) scope;
            return classSymbol;
        }

        return null;
    }

    @Override
    public void visit(ASTExprArithmNode node) {
        node.expr1.accept(this);
        if (isOperand(node.expr1) && !lastTypeChecked.equals("Int")) {
            if (node.ctx instanceof CoolParser.Expr_muldivContext) {
                SymbolTable.error(node.ctx, ((CoolParser.Expr_muldivContext) node.ctx).expr(0).start,
                        "Operand of " + node.operation + " has type " + lastTypeChecked + " instead of Int");

            } else if (node.ctx instanceof CoolParser.Expr_plussubContext) {
                SymbolTable.error(node.ctx, ((CoolParser.Expr_plussubContext) node.ctx).expr(0).start,
                        "Operand of " + node.operation + " has type " + lastTypeChecked + " instead of Int");

            }
        }

        node.expr2.accept(this);
        if (isOperand(node.expr2) && !lastTypeChecked.equals("Int")) {
            if (node.ctx instanceof CoolParser.Expr_muldivContext) {
                SymbolTable.error(node.ctx, ((CoolParser.Expr_muldivContext) node.ctx).expr(1).start,
                        "Operand of " + node.operation + " has type " + lastTypeChecked + " instead of Int");

            } else if (node.ctx instanceof CoolParser.Expr_plussubContext) {
                SymbolTable.error(node.ctx, ((CoolParser.Expr_plussubContext) node.ctx).expr(1).start,
                        "Operand of " + node.operation + " has type " + lastTypeChecked + " instead of Int");

            }
        }

        lastTypeChecked = "Int";
    }

    @Override
    public void visit(ASTExprNegNode node) {
        super.visit(node);

        if (isOperand(node.expr) && !lastTypeChecked.equals("Int")) {
            SymbolTable.error(node.ctx, ((CoolParser.Expr_negContext) node.ctx).expr().start,
                    "Operand of ~ has type " + lastTypeChecked + " instead of Int");
        }

        lastTypeChecked = "Int";
    }

    @Override
    public void visit(ASTExprIdNode node) {
        super.visit(node);

        if (!node.id.equals("self")) {
            lastTypeChecked = ((IdSymbol) node.currentScope.lookup(node.id)).getType().getName();
        } else {

            // consideram, deocamdata, ca tipul lui self din clasa C este C, nu SELF_TYPE(C) cum ar trebui
            /*
            Scope scope = node.currentScope;
            while (scope != null && scope instanceof ClassSymbol == false) {
                scope = scope.getParent();
            }

            if (scope != null) {
                ClassSymbol classSymbol = (ClassSymbol) scope;
                lastTypeChecked = classSymbol.getName();
            }
            */

            /* TODO asta o sa trebuiasca refacut de la testul 18 incolo, cand se abordeaza SELF_TYPE
            */
            lastTypeChecked = "SELF_TYPE";

        }
    }

    @Override
    public void visit(ASTExprAssignNode node) {
        super.visit(node);
        String exprType = lastTypeChecked;

        if (node.id.equals("self")) {
            SymbolTable.error(node.ctx, ((CoolParser.Expr_assignContext) node.ctx).ID().getSymbol(),
                    "Cannot assign to self");
        } else {
            IdSymbol idSymbol = (IdSymbol) node.currentScope.lookup(node.id);
            var exprClass = (ClassSymbol) SymbolTable.globals.lookup(exprType);

            if (idSymbol.getType().getName().equals("SELF_TYPE") && exprType.equals("SELF_TYPE"))
                return;

            if (idSymbol.getType().getName().equals("SELF_TYPE") && exprClass != null) {

                var enclosingClass = getEnclosingClass(node.currentScope);
                if (enclosingClass != null && exprClass.isChildOf(enclosingClass.getName()) == false) {
                    SymbolTable.error(node.ctx, ((CoolParser.Expr_assignContext) node.ctx).expr().start,
                            "Type " + exprType + " of assigned expression is incompatible with declared type " + idSymbol.getType().getName() +
                                    " of identifier " + node.id);
                }

            } else if (exprType.equals("SELF_TYPE")) {
                var enclosingClass = getEnclosingClass(node.currentScope);
                if (enclosingClass != null && enclosingClass.isChildOf(idSymbol.getType().getName()) == false) {

                    SymbolTable.error(node.ctx, ((CoolParser.Expr_assignContext) node.ctx).expr().start,
                            "Type " + exprType + " of assigned expression is incompatible with declared type " + idSymbol.getType().getName() +
                                    " of identifier " + node.id);
                }
            } else if (exprType.equals(idSymbol.getType().getName()) == false
                    && exprClass != null && exprClass.isChildOf(idSymbol.getType().getName()) == false) {

                SymbolTable.error(node.ctx, ((CoolParser.Expr_assignContext) node.ctx).expr().start,
                        "Type " + exprType + " of assigned expression is incompatible with declared type " + idSymbol.getType().getName() +
                        " of identifier " + node.id);
            }

        }
        lastTypeChecked = exprType;
    }

    @Override
    public void visit(ASTLetNode node) {
        if (node.expr != null) {
            node.expr.accept(this);
            String exprType = lastTypeChecked;
            var exprClass = (ClassSymbol) SymbolTable.globals.lookup(exprType);

            if (node.type.equals("SELF_TYPE") && exprType.equals("SELF_TYPE"))
                return;

            if (node.type.equals("SELF_TYPE")) {

                var enclosingClass = getEnclosingClass(node.currentScope);
                if (exprClass.isChildOf(enclosingClass.getName()) == false) {
                    SymbolTable.error(node.ctx, ((CoolParser.Let_bindContext) node.ctx).expr().start,
                            "Type " + exprType + " of initialization expression of identifier " + node.id +
                                    " is incompatible with declared type " + node.type);
                }

            } else if (exprType.equals("SELF_TYPE")) {
                var enclosingClass = getEnclosingClass(node.currentScope);
                if (enclosingClass.isChildOf(node.type) == false) {
                    SymbolTable.error(node.ctx, ((CoolParser.Let_bindContext) node.ctx).expr().start,
                            "Type " + exprType + " of initialization expression of identifier " + node.id +
                                    " is incompatible with declared type " + node.type);
                }

            }else if (exprType.equals(node.type) == false
                    && exprClass != null && exprClass.isChildOf(node.type) == false) {

                SymbolTable.error(node.ctx, ((CoolParser.Let_bindContext) node.ctx).expr().start,
                        "Type " + exprType + " of initialization expression of identifier " + node.id +
                                " is incompatible with declared type " + node.type);
            }
        }
    }

    @Override
    public void visit(ASTAttributeNode node) {
        if (node.expr != null) {
            node.expr.accept(this);
            String exprType = lastTypeChecked;
            var exprClass = (ClassSymbol) SymbolTable.globals.lookup(exprType);

            if (node.type.equals("SELF_TYPE") && exprType.equals("SELF_TYPE"))
                return;

            if (node.type.equals("SELF_TYPE")) {

                var enclosingClass = getEnclosingClass(node.currentScope);
                if (exprClass.isChildOf(enclosingClass.getName()) == false) {
                    SymbolTable.error(node.ctx, ((CoolParser.Attr_asgnContext) node.ctx).expr().start,
                            "Type " + exprType + " of initialization expression of attribute " + node.id +
                                    " is incompatible with declared type " + node.type);
                }

            } else if (exprType.equals("SELF_TYPE")) {
                var enclosingClass = getEnclosingClass(node.currentScope);
                if (enclosingClass.isChildOf(node.type) == false) {
                    SymbolTable.error(node.ctx, ((CoolParser.Attr_asgnContext) node.ctx).expr().start,
                            "Type " + exprType + " of initialization expression of attribute " + node.id +
                                    " is incompatible with declared type " + node.type);
                }

            } else if (exprType.equals(node.type) == false
                    && exprClass != null && exprClass.isChildOf(node.type) == false) {

                SymbolTable.error(node.ctx, ((CoolParser.Attr_asgnContext) node.ctx).expr().start,
                        "Type " + exprType + " of initialization expression of attribute " + node.id +
                                " is incompatible with declared type " + node.type);
            }
        } else {
            lastTypeChecked = node.type;
        }

    }

    @Override
    public void visit(ASTMethodNode node) {
        super.visit(node);
        String bodyType = lastTypeChecked;
        var bodyClass = (ClassSymbol) SymbolTable.globals.lookup(bodyType);

        if (node.retType.equals("SELF_TYPE") && bodyClass.getName().equals("SELF_TYPE"))
            return;

        if (bodyType.equals("SELF_TYPE")) {
            var enclosingClass = getEnclosingClass(node.currentScope);

            if (enclosingClass != null && enclosingClass.isChildOf(node.retType) == false) {
                SymbolTable.error(node.ctx, ((CoolParser.MethodContext) node.ctx).method_body().start,
                        "Type " + bodyType + " of the body of method " + node.id +
                                " is incompatible with declared return type " + node.retType);
            }

        } else if (node.retType.equals("SELF_TYPE")) {

            if (bodyClass.isChildOf(node.classSymbol.getName()) == false) {
                SymbolTable.error(node.ctx, ((CoolParser.MethodContext) node.ctx).method_body().start,
                        "Type " + bodyType + " of the body of method " + node.id +
                                " is incompatible with declared return type " + node.retType);
            }

        } else if (node.retType.equals("SELF_TYPE") == false && bodyType.equals(node.retType) == false
                && bodyClass != null && bodyClass.isChildOf(node.retType) == false) {

            SymbolTable.error(node.ctx, ((CoolParser.MethodContext) node.ctx).method_body().start,
                    "Type " + bodyType + " of the body of method " + node.id +
                            " is incompatible with declared return type " + node.retType);
        }
    }

    @Override
    public void visit(ASTExprConstNode node) {
        super.visit(node);
        lastTypeChecked = node.getStringType();
    }

    @Override
    public void visit(ASTExprCompNode node) {
        node.expr1.accept(this);
        var type1 = lastTypeChecked;

        node.expr2.accept(this);
        var type2 = lastTypeChecked;

        if (node.comparator.equals("=")) {
            var possibleError = false;

            switch(type1) {
                case "Int":
                case "String":
                case "Bool":
                    possibleError = true;
                    break;

                default:
                    break;
            }

            switch(type2) {
                case "Int":
                case "String":
                case "Bool":
                    possibleError = true;
                    break;

                default:
                    break;
            }

            if (possibleError && !type1.equals(type2)) {
                SymbolTable.error(node.ctx, ((CoolParser.Expr_compContext) node.ctx).op,
                        "Cannot compare " + type1 + " with " + type2);

            }

        } else {
            if (isOperand(node.expr1) && !type1.equals("Int")) {
                SymbolTable.error(node.ctx, ((CoolParser.Expr_compContext) node.ctx).expr(0).start,
                        "Operand of " + node.comparator + " has type " + type1 + " instead of Int");

            }

            if (isOperand(node.expr2) && !type2.equals("Int")) {
                SymbolTable.error(node.ctx, ((CoolParser.Expr_compContext) node.ctx).expr(1).start,
                        "Operand of " + node.comparator + " has type " + type2 + " instead of Int");

            }
        }

        lastTypeChecked = "Bool";
    }

    @Override
    public void visit(ASTExprNotNode node) {
        super.visit(node);

        if (isOperand(node.expr) && !lastTypeChecked.equals("Bool")) {
            SymbolTable.error(node.ctx, ((CoolParser.Expr_notContext) node.ctx).expr().start,
                    "Operand of not has type " + lastTypeChecked + " instead of Bool");
        }

        lastTypeChecked = "Bool";
    }

    @Override
    public void visit(ASTExprNewNode node) {
        super.visit(node);

        var exprClass = (ClassSymbol) SymbolTable.globals.lookup(node.type);
        if (exprClass == null) {
            SymbolTable.error(node.ctx, ((CoolParser.Expr_newContext) node.ctx).type().start,
                    "new is used with undefined type " + node.type);
        }

        lastTypeChecked = node.type;
    }

    @Override
    public void visit(ASTExprVoidNode node) {
        super.visit(node);
        lastTypeChecked = "Bool";
    }

    @Override
    public void visit(ASTExprWhileNode node) {
        node.cond.accept(this);
        String condType = lastTypeChecked;

        if (condType.equals("Bool") == false) {
            SymbolTable.error(node.ctx, ((CoolParser.Expr_whileContext) node.ctx).expr(0).start,
                    "While condition has type " + condType + " instead of Bool");
        }

        node.body.accept(this);

        lastTypeChecked = "Object";
    }

    @Override
    public void visit(ASTExprIfNode node) {
        node.ifExpr.accept(this);
        String condType = lastTypeChecked;

        node.thenExpr.accept(this);
        String trueType = lastTypeChecked;

        node.elseExpr.accept(this);
        String falseType = lastTypeChecked;

        if (condType.equals("Bool") == false) {
            SymbolTable.error(node.ctx, ((CoolParser.Expr_ifContext) node.ctx).expr(0).start,
                    "If condition has type " + condType + " instead of Bool");
        }

        lastTypeChecked = ClassSymbol.getCommonType(trueType, falseType);
    }

    @Override
    public void visit(ASTExprCaseNode node) {
        ArrayList<String> caseTypes = new ArrayList<>();

        node.expr.accept(this);
        for (var exp : node.exprs) {
            exp.accept(this);
            caseTypes.add(lastTypeChecked);
        }

        String commonType = caseTypes.get(0);
        for (int i = 1; i < caseTypes.size(); i++) {
            commonType = ClassSymbol.getCommonType(commonType, caseTypes.get(i));
        }

        lastTypeChecked = commonType;
    }

    // obj.f() sau self.f() sau obj@T.f()
    @Override
    public void visit(ASTExprExprFcallNode node) {
        boolean staticDisp = (node.type != null);
        MethodSymbol methodSymbol = null;

        node.expr.accept(this);
        String exprType = lastTypeChecked;

        if (staticDisp && node.type.equals("SELF_TYPE")) {
            SymbolTable.error(node.ctx, ((CoolParser.Expr_expr_fcallContext) node.ctx).type().start,
                    "Type of static dispatch cannot be SELF_TYPE");

        } else if (staticDisp) {
            ClassSymbol typeClass = (ClassSymbol) SymbolTable.globals.lookup(node.type);
            ClassSymbol exprClass = (ClassSymbol) SymbolTable.globals.lookup(exprType);

            // daca nu am gasit tipul static la care se face upcast
            if (typeClass == null) {
                SymbolTable.error(node.ctx, ((CoolParser.Expr_expr_fcallContext) node.ctx).type().start,
                        "Type " + node.type + " of static dispatch is undefined");

            } else if (exprClass != null && exprClass.isChildOf(node.type) == false) {
                SymbolTable.error(node.ctx, ((CoolParser.Expr_expr_fcallContext) node.ctx).type().start,
                        "Type " + node.type + " of static dispatch is not a superclass of type " + exprType);

                // ignoram tipul specificat de static dispatch in cazul in care nu e parinte
                typeClass = exprClass;
            }

            if (typeClass != null) {
                methodSymbol = typeClass.lookupMethod(node.fcall.id);
                if (methodSymbol == null) {
                    SymbolTable.error(node.ctx, ((CoolParser.Expr_expr_fcallContext) node.ctx).f_call().ID().getSymbol(),
                            "Undefined method " + node.fcall.id + " in class " + typeClass.getName());

                }
            }

        } else {
            ClassSymbol exprClass = null;
            if (exprType.equals("SELF_TYPE")) {
                exprClass = getEnclosingClass(node.currentScope);
            } else {
                exprClass = (ClassSymbol) SymbolTable.globals.lookup(exprType);
            }

            if (exprClass != null) {
                methodSymbol = exprClass.lookupMethod(node.fcall.id);
                if (methodSymbol == null) {
                    SymbolTable.error(node.ctx, ((CoolParser.Expr_expr_fcallContext) node.ctx).f_call().ID().getSymbol(),
                            "Undefined method " + node.fcall.id + " in class " + exprClass.getName());

                }
            }
        }

        if (methodSymbol != null) {
            node.fcall.methodSymbol = methodSymbol;
            node.fcall.staticClass = exprType;
            if (exprType.equals("SELF_TYPE")) {
                node.fcall.staticClass = getEnclosingClass(node.currentScope).getName();
            }
            node.fcall.accept(this);
            lastTypeChecked = methodSymbol.getType().getName();

            if (lastTypeChecked.equals("SELF_TYPE")) {
                lastTypeChecked = exprType;
            }
        }
    }

    // f()
    @Override
    public void visit(ASTExprFcallNode node) {
        ClassSymbol classSymbol = getEnclosingClass(node.currentScope);

        if (classSymbol == null) {
            return;
        }

        MethodSymbol methodSymbol = classSymbol.lookupMethod(node.fcall.id);
        if (methodSymbol == null) {
            SymbolTable.error(node.ctx, ((CoolParser.Expr_fcallContext) node.ctx).f_call().ID().getSymbol(),
                    "Undefined method " + node.fcall.id + " in class " + classSymbol.getName());
            return;
        }

        node.fcall.methodSymbol = methodSymbol;
        node.fcall.accept(this);
        lastTypeChecked = methodSymbol.getType().getName();

        if (lastTypeChecked.equals("SELF_TYPE")) {
            lastTypeChecked = classSymbol.getName();
        }
    }

    // nu setam lastTypeChecked pt ca mereu se va ocupa de asta un parinte de-al lui ASTFcallNode (adica
    // ASTExprFcallNode sau ASTExprExprFcallNode)
    @Override
    public void visit(ASTFcallNode node) {
        if (node.params != null) {
            String className = null;
            if (node.staticClass != null)
                className = node.staticClass;
            else
                className = node.methodSymbol.astMethodNode.classSymbol.getName();

            ArrayList<ASTBaseNode> params = node.params.children;
            if (params.size() != node.methodSymbol.astMethodNode.params.ids.size()) {
                SymbolTable.error(node.ctx, ((CoolParser.F_callContext) node.ctx).ID().getSymbol(),
                        "Method " + node.id + " of class " + className +
                        " is applied to wrong number of arguments");

                super.visit(node);
                return;
            }

            for (int i = 0; i < params.size(); i++) {
                params.get(i).accept(this);
                String paramType = lastTypeChecked;
                ClassSymbol classSymbol = (ClassSymbol) SymbolTable.globals.lookup(paramType);

                if (paramType.equals(node.methodSymbol.astMethodNode.params.types.get(i)) == false
                        && classSymbol != null
                        && classSymbol.isChildOf(node.methodSymbol.astMethodNode.params.types.get(i)) == false) {

                    if (((CoolParser.F_callContext) node.ctx).f_call_args() instanceof CoolParser.Sg_arg_functionContext) {
                        var ctx = (CoolParser.Sg_arg_functionContext) ((CoolParser.F_callContext) node.ctx).f_call_args();
                        SymbolTable.error(node.ctx, ctx.expr().start,
                                "In call to method " + node.id + " of class " + className +
                                        ", actual type " + paramType + " of formal parameter " + node.methodSymbol.astMethodNode.params.ids.get(i) +
                                        " is incompatible with declared type " + node.methodSymbol.astMethodNode.params.types.get(i));

                    } else {
                        var ctx = (CoolParser.Mlt_arg_functionContext) ((CoolParser.F_callContext) node.ctx).f_call_args();
                        SymbolTable.error(node.ctx, ctx.expr(i).start,
                                "In call to method " + node.id + " of class " + className +
                                        ", actual type " + paramType + " of formal parameter " + node.methodSymbol.astMethodNode.params.ids.get(i) +
                                        " is incompatible with declared type " + node.methodSymbol.astMethodNode.params.types.get(i));
                    }
                }
            }

        }
    }
}

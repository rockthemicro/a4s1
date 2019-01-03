package cool.visitors;

import cool.compiler.*;

public class ASTNopVisitor implements ASTVisitor {
    @Override
    public void visit(ASTAttributeNode node) {
        if (node.expr != null) {
            node.expr.accept(this);
        }
    }

    @Override
    public void visit(ASTClassBodyNode node) {
        for (var child : node.children) {
            child.accept(this);
        }
    }

    @Override
    public void visit(ASTClassNode node) {
        if (node.body != null) {
            node.body.accept(this);
        }
    }

    @Override
    public void visit(ASTExprArithmNode node) {
        node.expr1.accept(this);
        node.expr2.accept(this);
    }

    @Override
    public void visit(ASTExprAssignNode node) {
        if (node.expr != null) {
            node.expr.accept(this);
        }
    }

    @Override
    public void visit(ASTExprBlockNode node) {
        node.child.accept(this);
    }

    @Override
    public void visit(ASTExprCaseNode node) {
        node.expr.accept(this);
        for (var exp : node.exprs) {
            exp.accept(this);
        }
    }

    @Override
    public void visit(ASTExprCompNode node) {
        node.expr1.accept(this);
        node.expr2.accept(this);

    }

    @Override
    public void visit(ASTExprConstNode node) {

    }

    @Override
    public void visit(ASTExprExprFcallNode node) {
        node.expr.accept(this);
        node.fcall.accept(this);

    }

    @Override
    public void visit(ASTExprFcallNode node) {
        node.fcall.accept(this);
    }

    @Override
    public void visit(ASTExprIdNode node) {

    }

    @Override
    public void visit(ASTExprIfNode node) {
        node.ifExpr.accept(this);
        node.thenExpr.accept(this);
        node.elseExpr.accept(this);
    }

    @Override
    public void visit(ASTExprLetNode node) {
        for (var let : node.letNodes) {
            let.accept(this);
        }
        node.expr.accept(this);
    }

    @Override
    public void visit(ASTExprNegNode node) {
        node.expr.accept(this);
    }

    @Override
    public void visit(ASTExprNewNode node) {

    }

    @Override
    public void visit(ASTExprNotNode node) {
        node.expr.accept(this);
    }

    @Override
    public void visit(ASTExprVoidNode node) {
        node.expr.accept(this);
    }

    @Override
    public void visit(ASTExprWhileNode node) {
        node.cond.accept(this);
        node.body.accept(this);
    }

    @Override
    public void visit(ASTFcallNode node) {
        if (node.params != null) {
            node.params.accept(this);
        }
    }

    @Override
    public void visit(ASTLetNode node) {
        if (node.expr != null) {
            node.expr.accept(this);
        }
    }

    @Override
    public void visit(ASTManyExprNode node) {
        for (var child : node.children) {
            child.accept(this);
        }
    }

    @Override
    public void visit(ASTMethodNode node) {
        if (node.params != null) {
            node.params.accept(this);
        }

        if (node.body != null) {
            node.body.accept(this);
        }
    }

    @Override
    public void visit(ASTMethodParamsNode node) {

    }

    @Override
    public void visit(ASTProgramNode node) {
        for (var classNode : node.classes) {
            if (classNode != null) {
                classNode.accept(this);
            }
        }
    }
}

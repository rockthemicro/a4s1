package cool.visitors;

import cool.compiler.*;

public interface ASTVisitor {
    void visit(ASTAttributeNode node);

    void visit(ASTClassBodyNode node);

    void visit(ASTClassNode node);

    void visit(ASTExprArithmNode node);

    void visit(ASTExprAssignNode node);

    void visit(ASTExprBlockNode node);

    void visit(ASTExprCaseNode node);

    void visit(ASTExprCompNode node);

    void visit(ASTExprConstNode node);

    void visit(ASTExprExprFcallNode node);

    void visit(ASTExprFcallNode node);

    void visit(ASTExprIdNode node);

    void visit(ASTExprIfNode node);

    void visit(ASTExprLetNode node);

    void visit(ASTExprNegNode node);

    void visit(ASTExprNewNode node);

    void visit(ASTExprNotNode node);

    void visit(ASTExprVoidNode node);

    void visit(ASTExprWhileNode node);

    void visit(ASTFcallNode node);

    void visit(ASTLetNode node);

    void visit(ASTManyExprNode node);

    void visit(ASTMethodNode node);

    void visit(ASTMethodParamsNode node);

    void visit(ASTProgramNode node);
}

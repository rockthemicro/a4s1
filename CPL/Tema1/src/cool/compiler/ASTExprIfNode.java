package cool.compiler;

import cool.visitors.ASTVisitor;

public class ASTExprIfNode extends ASTBaseNode {
	
	ASTBaseNode ifExpr = null;
	ASTBaseNode thenExpr = null;
	ASTBaseNode elseExpr = null;
	
	@Override
	public void print(int depth) {
		super.printSpaces(depth);
		System.out.println("if");
		
		ifExpr.print(depth + 1);
		thenExpr.print(depth + 1);
		elseExpr.print(depth + 1);
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visit(this);

		ifExpr.accept(v);
		thenExpr.accept(v);
		elseExpr.accept(v);
	}

}

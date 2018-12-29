package cool.compiler;

import cool.visitors.ASTVisitor;

public class ASTExprVoidNode extends ASTBaseNode {

	ASTBaseNode expr = null;
	
	@Override
	public void print(int depth) {
		super.printSpaces(depth);
		System.out.println("isvoid");
		
		if (expr != null) {
			expr.print(depth + 1);
		}
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visit(this);

		expr.accept(v);
	}

}

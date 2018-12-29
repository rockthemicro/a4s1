package cool.compiler;

import cool.visitors.ASTVisitor;

public class ASTExprAssignNode extends ASTBaseNode {

	String id = null;
	ASTBaseNode expr = null;
	
	@Override
	public void print(int depth) {
		super.printSpaces(depth);
		System.out.println("<-");
		
		super.printSpaces(depth + 1);
		System.out.println(id);
		
		if (expr != null) {
			expr.print(depth + 1);
		}
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visit(this);

		if (expr != null) {
			expr.accept(v);
		}
	}
}

package cool.compiler;

import cool.visitors.ASTVisitor;

public class ASTExprNotNode extends ASTBaseNode {

	public ASTBaseNode expr = null;
	
	@Override
	public void print(int depth) {
		super.printSpaces(depth);
		System.out.println("not");
		
		expr.print(depth + 1);
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visit(this);

		//expr.accept(v);
	}

}

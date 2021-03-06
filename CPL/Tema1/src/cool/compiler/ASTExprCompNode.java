package cool.compiler;

import cool.visitors.ASTVisitor;

public class ASTExprCompNode extends ASTBaseNode {
	
	public ASTBaseNode expr1 = null;
	public ASTBaseNode expr2 = null;
	public String comparator = null;
	
	@Override
	public void print(int depth) {
		super.printSpaces(depth);
		System.out.println(comparator);
		
		expr1.print(depth + 1);
		expr2.print(depth + 1);
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visit(this);
	}

}

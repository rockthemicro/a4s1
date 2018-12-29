package cool.compiler;

import cool.visitors.ASTVisitor;

public class ASTExprBlockNode extends ASTBaseNode {
	ASTManyExprNode child = new ASTManyExprNode();
	
	@Override
	public void print(int depth) {
		super.printSpaces(depth);
		System.out.println("block");
		
		child.print(depth + 1);
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visit(this);

		child.accept(v);
	}
}

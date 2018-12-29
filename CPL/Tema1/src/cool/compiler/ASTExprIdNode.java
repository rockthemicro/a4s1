package cool.compiler;

import cool.visitors.ASTVisitor;

public class ASTExprIdNode extends ASTBaseNode {

	String id = null;
	
	@Override
	public void print(int depth) {
		super.printSpaces(depth);
		System.out.println(id);
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visit(this);
	}

}

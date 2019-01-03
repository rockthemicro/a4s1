package cool.compiler;

import cool.visitors.ASTVisitor;

public class ASTExprNewNode extends ASTBaseNode {

	public String type = null;
	
	@Override
	public void print(int depth) {
		super.printSpaces(depth);
		System.out.println("new");
		
		super.printSpaces(depth + 1);
		System.out.println(type);
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visit(this);
	}

}

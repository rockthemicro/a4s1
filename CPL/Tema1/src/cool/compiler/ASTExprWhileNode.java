package cool.compiler;

import cool.visitors.ASTVisitor;

public class ASTExprWhileNode extends ASTBaseNode {

	public ASTBaseNode cond = null;
	public ASTBaseNode body = null;
	
	@Override
	public void print(int depth) {
		super.printSpaces(depth);
		System.out.println("while");
		
		cond.print(depth + 1);
		body.print(depth + 1);
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visit(this);

		/*
		cond.accept(v);
		body.accept(v);
		*/
	}
}

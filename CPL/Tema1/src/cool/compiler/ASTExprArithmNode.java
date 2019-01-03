package cool.compiler;

import cool.visitors.ASTVisitor;

public class ASTExprArithmNode extends ASTBaseNode {

	public ASTBaseNode expr1 = null;
	public ASTBaseNode expr2 = null;
	public String operation = null;
	
	@Override
	public void print(int depth) {
		super.printSpaces(depth);
		System.out.println(operation);
		
		expr1.print(depth + 1);
		expr2.print(depth + 1);
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visit(this);

		/*
		expr1.accept(v);
		expr2.accept(v);
		*/
	}

}

package cool.compiler;

import cool.visitors.ASTVisitor;

public class ASTLetNode extends ASTBaseNode {

	String id = null;
	String type = null;
	ASTBaseNode expr = null;

	@SuppressWarnings("Duplicates")
	@Override
	public void print(int depth) {
		super.printSpaces(depth);
		System.out.println("local");
		
		super.printSpaces(depth + 1);
		System.out.println(id);
		
		super.printSpaces(depth + 1);
		System.out.println(type);
		
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

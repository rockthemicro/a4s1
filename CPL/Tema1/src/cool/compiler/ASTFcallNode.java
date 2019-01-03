package cool.compiler;

import cool.visitors.ASTVisitor;

public class ASTFcallNode extends ASTBaseNode {

	public String id = null;
	public ASTBaseNode params = null;
	
	@Override
	public void print(int depth) {
		super.printSpaces(depth);
		System.out.println(id);
		
		if (params != null) {
			params.print(depth);
		}
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visit(this);

		/*
		if (params != null) {
			params.accept(v);
		}
		*/
	}

}

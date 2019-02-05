package cool.compiler;

import cool.structures.MethodSymbol;
import cool.visitors.ASTVisitor;

public class ASTFcallNode extends ASTBaseNode {

	public String id = null;
	public ASTManyExprNode params = null;
	public MethodSymbol methodSymbol = null;
	public String staticClass = null;
	
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
	}

}

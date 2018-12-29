package cool.compiler;

import cool.structures.ClassSymbol;
import cool.structures.MethodSymbol;
import cool.visitors.ASTVisitor;

public class ASTMethodNode extends ASTBaseNode {
	
	public String id = null;
	public String retType = null;
	public ASTMethodParamsNode params = null;
	public ASTManyExprNode body = null;

	public ClassSymbol classSymbol = null;
	public MethodSymbol methodSymbol = null;
	
	@Override
	public void print(int depth) {
		super.printSpaces(depth);
		System.out.println("method");
		
		super.printSpaces(depth + 1);
		System.out.println(id);
		
		if (params != null) {
			params.print(depth + 1);
		}
		
		super.printSpaces(depth + 1);
		System.out.println(retType);
		
		if (body != null) {
			body.print(depth + 1);
		}
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visit(this);

		if (params != null) {
			params.accept(v);
		}

		if (body != null) {
			body.accept(v);
		}
	}

}

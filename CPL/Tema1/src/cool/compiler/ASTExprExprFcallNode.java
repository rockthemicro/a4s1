package cool.compiler;

import cool.visitors.ASTVisitor;

// modeleaza apeluri de genul expr.f() si expr@TYPE.f()
public class ASTExprExprFcallNode extends ASTBaseNode {
	public ASTBaseNode expr = null;
	public String type = null;
	public ASTFcallNode fcall = null;
	
	@Override
	public void print(int depth) {
		super.printSpaces(depth);
		System.out.println(".");
		
		expr.print(depth + 1);
		if (type != null) {
			super.printSpaces(depth + 1);
			System.out.println(type);	
		}
		fcall.print(depth + 1);
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visit(this);
	}

}

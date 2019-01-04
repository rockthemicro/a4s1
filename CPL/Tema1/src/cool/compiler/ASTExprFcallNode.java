package cool.compiler;

import cool.visitors.ASTVisitor;

public class ASTExprFcallNode extends ASTBaseNode {
	public ASTFcallNode fcall = null;
	
	@Override
	public void print(int depth) {
		super.printSpaces(depth);
		System.out.println("implicit dispatch");
		
		if (fcall != null) {
			fcall.print(depth + 1);
		}
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visit(this);

		//fcall.accept(v);
	}

}

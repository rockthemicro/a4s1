package cool.compiler;

public class ASTExprWhileNode extends ASTBaseNode {

	ASTBaseNode cond = null;
	ASTBaseNode body = null;
	
	@Override
	public void print(int depth) {
		super.printSpaces(depth);
		System.out.println("while");
		
		cond.print(depth + 1);
		body.print(depth + 1);
	}

}

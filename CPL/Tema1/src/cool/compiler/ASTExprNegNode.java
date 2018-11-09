package cool.compiler;

public class ASTExprNegNode extends ASTBaseNode {

	ASTBaseNode expr = null;
	
	@Override
	public void print(int depth) {
		super.printSpaces(depth);
		System.out.println("~");
		
		if (expr != null) {
			expr.print(depth + 1);
		}
	}

}

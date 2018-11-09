package cool.compiler;

public class ASTExprVoidNode extends ASTBaseNode {

	ASTBaseNode expr = null;
	
	@Override
	public void print(int depth) {
		super.printSpaces(depth);
		System.out.println("isvoid");
		
		if (expr != null) {
			expr.print(depth + 1);
		}
	}

}

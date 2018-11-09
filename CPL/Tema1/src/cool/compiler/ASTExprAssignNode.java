package cool.compiler;

public class ASTExprAssignNode extends ASTBaseNode {

	String id = null;
	ASTBaseNode expr = null;
	
	@Override
	public void print(int depth) {
		super.printSpaces(depth);
		System.out.println("<-");
		
		super.printSpaces(depth + 1);
		System.out.println(id);
		
		if (expr != null) {
			expr.print(depth + 1);
		}
	}
}

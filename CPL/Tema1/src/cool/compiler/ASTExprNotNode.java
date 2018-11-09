package cool.compiler;

public class ASTExprNotNode extends ASTBaseNode {

	ASTBaseNode expr = null;
	
	@Override
	public void print(int depth) {
		super.printSpaces(depth);
		System.out.println("not");
		
		expr.print(depth + 1);
	}

}

package cool.compiler;

public class ASTExprCompNode extends ASTBaseNode {
	
	ASTBaseNode expr1 = null;
	ASTBaseNode expr2 = null;
	String comparator = null;
	
	@Override
	public void print(int depth) {
		super.printSpaces(depth);
		System.out.println(comparator);
		
		expr1.print(depth + 1);
		expr2.print(depth + 1);
	}

}

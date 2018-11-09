package cool.compiler;

public class ASTExprArithmNode extends ASTBaseNode {

	ASTBaseNode expr1 = null;
	ASTBaseNode expr2 = null;
	String operation = null;
	
	@Override
	public void print(int depth) {
		super.printSpaces(depth);
		System.out.println(operation);
		
		expr1.print(depth + 1);
		expr2.print(depth + 1);
	}

}

package cool.compiler;

public class ASTExprIfNode extends ASTBaseNode {
	
	ASTBaseNode ifExpr = null;
	ASTBaseNode thenExpr = null;
	ASTBaseNode elseExpr = null;
	
	@Override
	public void print(int depth) {
		super.printSpaces(depth);
		System.out.println("if");
		
		ifExpr.print(depth + 1);
		thenExpr.print(depth + 1);
		elseExpr.print(depth + 1);
	}

}

package cool.compiler;

public class ASTExprBlockNode extends ASTBaseNode {
	ASTManyExprNode child = new ASTManyExprNode();
	
	@Override
	public void print(int depth) {
		super.printSpaces(depth);
		System.out.println("block");
		
		child.print(depth + 1);
	}

}

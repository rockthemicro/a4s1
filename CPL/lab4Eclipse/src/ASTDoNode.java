public class ASTDoNode extends ASTBaseNode {
	ASTBaseNode statement = null;
	ASTBaseNode expr = null;
	
	@Override
	public void printTree(int depth) {
		super.printDepthTabs(depth);
		System.out.println("DO");
		
		statement.printTree(depth + 1);
		expr.printTree(depth + 1);
	}
}

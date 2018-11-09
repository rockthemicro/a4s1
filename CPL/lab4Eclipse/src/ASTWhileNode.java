public class ASTWhileNode extends ASTBaseNode {
	ASTBaseNode expr = null;
	ASTBaseNode statement = null;
	
	@Override
	public void printTree(int depth) {
		super.printDepthTabs(depth);
		System.out.println("WHILE");

		expr.printTree(depth + 1);
		statement.printTree(depth + 1);
	}
}

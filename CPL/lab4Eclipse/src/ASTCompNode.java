public class ASTCompNode extends ASTBaseNode {
	ASTBaseNode leftSum = null;
	ASTBaseNode rightSum = null;

	@Override
	public void printTree(int depth) {
		super.printDepthTabs(depth);
		System.out.println("<");

		leftSum.printTree(depth + 1);
		rightSum.printTree(depth + 1);
	}
}

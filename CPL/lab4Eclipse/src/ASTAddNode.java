public class ASTAddNode extends ASTBaseNode {
	ASTBaseNode left = null;
	ASTBaseNode right = null;

	@Override
	public void printTree(int depth) {
		super.printDepthTabs(depth);
		System.out.println("+");

		left.printTree(depth + 1);
		right.printTree(depth + 1);
	}
}

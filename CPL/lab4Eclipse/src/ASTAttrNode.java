public class ASTAttrNode extends ASTBaseNode {
	String id = null;
	ASTBaseNode expr = null;

	@Override
	public void printTree(int depth) {
		super.printDepthTabs(depth);
		System.out.println("=");

		super.printDepthTabs(depth + 1);
		System.out.println(id);
		
		expr.printTree(depth + 1);
	}
}

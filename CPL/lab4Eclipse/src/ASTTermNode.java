
public class ASTTermNode extends ASTBaseNode {

	String value = null;
	ASTBaseNode expr = null;
	
	@Override
	public void printTree(int depth) {
		if (value != null) {
			super.printDepthTabs(depth);
			System.out.println(value);
		} else if (expr != null) {
			expr.printTree(depth);
		}
	}
}

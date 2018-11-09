public class ASTIfNode extends ASTBaseNode {
	ASTBaseNode if_statement = null;
	ASTBaseNode paren_statement = null;
	ASTBaseNode else_statement = null;

	@Override
	public void printTree(int depth) {
		super.printDepthTabs(depth);
		System.out.println("IF");

		paren_statement.printTree(depth + 1);
		if_statement.printTree(depth + 1);

		if (else_statement != null) {
			super.printDepthTabs(depth);
			System.out.println("Else");

			else_statement.printTree(depth + 1);
		}
	}
}

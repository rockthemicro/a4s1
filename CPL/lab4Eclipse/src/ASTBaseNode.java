public abstract class ASTBaseNode {

	public void printDepthTabs(int depth) {
		String spaces = "    ";
		for (int i = 0; i < depth; i++) {
			System.out.print(spaces);
		}
	}

	public abstract void printTree(int depth);
}

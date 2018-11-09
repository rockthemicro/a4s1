import org.antlr.v4.runtime.*;

public class ASTBaseNode {

	public void printDepthTabs(int depth) {
		String spaces = "    ";
		for (int i = 0; i < depth; i++) {
			System.out.print(spaces);
		}
	}

	public void printTree(int depth) { }
}


import org.antlr.v4.runtime.*;

public class ASTAttrNode extends ASTBaseNode {
	ASTBaseNode id = null;
	ASTBaseNode expr = null;

	@Override
	public void printTree(int depth) {
		super.printDepthTabs(depth);
		System.out.println("=");

		id.printTree(depth + 1);
		expr.printTree(depth + 1);
	}
}

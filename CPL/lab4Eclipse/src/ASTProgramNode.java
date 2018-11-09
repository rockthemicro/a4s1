import java.util.ArrayList;

public class ASTProgramNode extends ASTBaseNode {

	ArrayList<ASTBaseNode> statements = new ArrayList<>();

	@Override
	public void printTree(int depth) {
		super.printDepthTabs(depth);
		System.out.println("PROGRAM");

		for (var statement : statements) {
			if (statement != null)
				statement.printTree(depth + 1);
		}
	}
}

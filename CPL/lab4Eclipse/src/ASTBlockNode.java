import java.util.ArrayList;

public class ASTBlockNode extends ASTBaseNode {
	
	ArrayList<ASTBaseNode> statements = new ArrayList<>();

	@Override
	public void printTree(int depth) {
		for (var statement : statements) {
			if (statement != null) {
				statement.printTree(depth);
			}
		}
	}

}
import org.antlr.v4.runtime.*;
import java.util.LinkedList;

public class ASTProgramNode extends ASTBaseNode {

	LinkedList<ASTBaseNode> statements = new LinkedList<>();

	@Override
	public void printTree(int depth) {
		super.printDepthTabs(depth);
		System.out.println("Program");

		for (var statement : statements) {
			statement.printTree(depth + 1);
		}
	}
}

package cool.compiler;

import java.util.ArrayList;

public class ASTClassBodyNode extends ASTBaseNode {

	ArrayList<ASTBaseNode> children = new ArrayList<>();
	
	@Override
	public void print(int depth) {
		for (var child : children) {
			if (child != null) {
				child.print(depth);
			}
		}
	}

}

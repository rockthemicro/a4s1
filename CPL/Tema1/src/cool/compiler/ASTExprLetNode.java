package cool.compiler;

import java.util.ArrayList;

public class ASTExprLetNode extends ASTBaseNode {

	ArrayList<ASTLetNode> letNodes = new ArrayList<>();
	ASTBaseNode expr = null;
	
	@Override
	public void print(int depth) {
		super.printSpaces(depth);
		System.out.println("let");
		
		for (var letNode : letNodes) {
			letNode.print(depth + 1);
		}
		
		expr.print(depth + 1);
	}

}

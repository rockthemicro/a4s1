package cool.compiler;

import java.util.ArrayList;

public class ASTProgramNode extends ASTBaseNode {
	ArrayList<ASTBaseNode> classes = new ArrayList<>();

	@Override
	public void print(int depth) {
		printSpaces(depth);
		System.out.println("program");
		
		for (var classNode : classes) {
			if (classNode != null) {
				classNode.print(depth + 1);
			}
		}
	}
}

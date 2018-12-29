package cool.compiler;

import cool.visitors.ASTVisitor;

import java.util.ArrayList;

public class ASTProgramNode extends ASTBaseNode {
	public ArrayList<ASTBaseNode> classes = new ArrayList<>();

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

	@Override
	public void accept(ASTVisitor v) {
		v.visit(this);

		for (var classNode : classes) {
			if (classNode != null) {
				classNode.accept(v);
			}
		}
	}
}

package cool.compiler;

import cool.visitors.ASTVisitor;

import java.util.ArrayList;

public class ASTExprLetNode extends ASTBaseNode {

	public ArrayList<ASTLetNode> letNodes = new ArrayList<>();
	public ASTBaseNode expr = null;

	@Override
	public void print(int depth) {
		super.printSpaces(depth);
		System.out.println("let");
		
		for (var letNode : letNodes) {
			letNode.print(depth + 1);
		}
		
		expr.print(depth + 1);
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visit(this);

		/*
		for (var let : letNodes) {
			let.accept(v);
		}
		expr.accept(v);
		*/
	}

}

package cool.compiler;

import cool.visitors.ASTVisitor;

import java.util.ArrayList;

public class ASTManyExprNode extends ASTBaseNode {

	public ArrayList<ASTBaseNode> children = new ArrayList<>();
	
	@Override
	public void print(int depth) {
		
		for (var child : children) {
			
			if (child != null) {
				child.print(depth);
			}	
		}
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visit(this);
	}
}

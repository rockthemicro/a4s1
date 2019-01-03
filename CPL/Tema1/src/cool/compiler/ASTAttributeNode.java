package cool.compiler;

import cool.structures.ClassSymbol;
import cool.structures.IdSymbol;
import cool.visitors.ASTVisitor;

@SuppressWarnings("Duplicates")
public class ASTAttributeNode extends ASTBaseNode {
	public String id = null;
	public String type = null;
	public ASTBaseNode expr = null;
	public IdSymbol idSymbol = null;
	public ClassSymbol classSymbol = null;

	@Override
	public void print(int depth) {
		super.printSpaces(depth);
		System.out.println("attribute");
		
		super.printSpaces(depth + 1);
		System.out.println(id);
		
		super.printSpaces(depth + 1);
		System.out.println(type);
		
		if (expr != null) {
			expr.print(depth + 1);
		}
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visit(this);
		/*
		if (expr != null) {
			expr.accept(v);
		}
		*/
	}

}

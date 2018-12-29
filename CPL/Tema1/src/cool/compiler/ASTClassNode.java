package cool.compiler;

import cool.structures.ClassSymbol;
import cool.visitors.ASTVisitor;

public class ASTClassNode extends ASTBaseNode {
	public String name = null;
	public String inherits = null;
	public ASTBaseNode body = null;
	public ClassSymbol classSymbol = null;

	public void print(int depth) {
		printSpaces(depth);
		System.out.println("class");
		
		printSpaces(depth + 1);
		System.out.println(name);
		
		if (inherits != null) {
			printSpaces(depth + 1);
			System.out.println(inherits);
		}
		
		if (body != null) {
			body.print(depth + 1);
		}
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visit(this);

		if (body != null) {
			body.accept(v);
		}
	}

}

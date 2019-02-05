package cool.compiler;

import cool.structures.ClassSymbol;
import cool.structures.SymbolTable;
import cool.visitors.ASTVisitor;

import java.util.ArrayList;
import java.util.Set;

@SuppressWarnings("Duplicates")
public class ASTClassNode extends ASTBaseNode {
	public String name = null;
	public String inherits = null;
	public ASTBaseNode body = null;
	public ClassSymbol classSymbol = null;
	public Integer inheritedAttributeOffset = 0;

	public ArrayList<ASTAttributeNode> getAttributes() {
		ASTClassBodyNode cast_body = (ASTClassBodyNode) body;
		ArrayList<ASTAttributeNode> result = new ArrayList<>();

		for (var child : cast_body.children) {
			if (child instanceof ASTAttributeNode) {
				result.add((ASTAttributeNode) child);
			}
		}

		return result;
	}

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
	}

}

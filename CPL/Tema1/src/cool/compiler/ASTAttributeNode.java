package cool.compiler;

public class ASTAttributeNode extends ASTBaseNode {
	String id = null;
	String type = null;
	ASTBaseNode expr = null;
	
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

}

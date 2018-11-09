package cool.compiler;

public class ASTMethodNode extends ASTBaseNode {
	
	String id = null;
	String retType = null;
	ASTBaseNode params = null;
	ASTBaseNode body = null;
	
	@Override
	public void print(int depth) {
		super.printSpaces(depth);
		System.out.println("method");
		
		super.printSpaces(depth + 1);
		System.out.println(id);
		
		if (params != null) {
			params.print(depth + 1);
		}
		
		super.printSpaces(depth + 1);
		System.out.println(retType);
		
		if (body != null) {
			body.print(depth + 1);
		}
	}

}

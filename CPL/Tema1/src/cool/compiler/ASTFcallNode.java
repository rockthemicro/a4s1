package cool.compiler;

public class ASTFcallNode extends ASTBaseNode {

	String id = null;
	ASTBaseNode params = null;
	
	@Override
	public void print(int depth) {
		super.printSpaces(depth);
		System.out.println(id);
		
		if (params != null) {
			params.print(depth);
		}
	}

}

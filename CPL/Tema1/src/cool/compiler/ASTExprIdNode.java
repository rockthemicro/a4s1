package cool.compiler;

public class ASTExprIdNode extends ASTBaseNode {

	String id = null;
	
	@Override
	public void print(int depth) {
		super.printSpaces(depth);
		System.out.println(id);
	}

}

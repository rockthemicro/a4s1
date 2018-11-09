package cool.compiler;

public class ASTExprNewNode extends ASTBaseNode {

	String type = null;
	
	@Override
	public void print(int depth) {
		super.printSpaces(depth);
		System.out.println("new");
		
		super.printSpaces(depth + 1);
		System.out.println(type);
	}

}

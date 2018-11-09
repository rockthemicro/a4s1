package cool.compiler;

public class ASTExprExprFcallNode extends ASTBaseNode {
	ASTBaseNode expr = null;
	String type = null;
	ASTBaseNode fcall = null;
	
	@Override
	public void print(int depth) {
		super.printSpaces(depth);
		System.out.println(".");
		
		expr.print(depth + 1);
		if (type != null) {
			super.printSpaces(depth + 1);
			System.out.println(type);	
		}
		fcall.print(depth + 1);
	}

}

package cool.compiler;

public class ASTExprFcallNode extends ASTBaseNode {
	ASTBaseNode fcall = null;
	
	@Override
	public void print(int depth) {
		super.printSpaces(depth);
		System.out.println("implicit dispatch");
		
		if (fcall != null) {
			fcall.print(depth + 1);
		}
	}

}

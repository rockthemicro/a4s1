package cool.compiler;

public class ASTClassNode extends ASTBaseNode {
	String name = null;
	String inherits = null;
	ASTBaseNode body = null;

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

}

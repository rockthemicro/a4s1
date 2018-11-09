package cool.compiler;

public abstract class ASTBaseNode {
	public void printSpaces(int n) {
		for (int i = 0; i < n; i++) {
			System.out.print("  ");
		}
	}
	
	public abstract void print(int depth);
}

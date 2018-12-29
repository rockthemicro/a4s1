package cool.compiler;

import cool.visitors.ASTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class ASTBaseNode {
	public ParserRuleContext ctx = null;

	public abstract void accept(ASTVisitor v);

	public void printSpaces(int n) {
		for (int i = 0; i < n; i++) {
			System.out.print("  ");
		}
	}

	public abstract void print(int depth);
}

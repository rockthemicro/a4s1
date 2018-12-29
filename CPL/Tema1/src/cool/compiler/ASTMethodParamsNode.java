package cool.compiler;

import cool.structures.ClassSymbol;
import cool.structures.MethodSymbol;
import cool.visitors.ASTVisitor;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;

public class ASTMethodParamsNode extends ASTBaseNode {

	public ArrayList<String> ids = new ArrayList<>();
    public ArrayList<Token> idTokens = new ArrayList<>();

    public ArrayList<String> types = new ArrayList<>();
    public ArrayList<Token> typeTokens = new ArrayList<>();

	public ASTMethodNode methodNode = null;

	@Override
	public void print(int depth) {
		for (int i = 0; i < ids.size(); i++) {
			super.printSpaces(depth);
			System.out.println("formal");
			
			super.printSpaces(depth + 1);
			System.out.println(ids.get(i));
			
			super.printSpaces(depth + 1);
			System.out.println(types.get(i));
		}
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visit(this);
	}

}

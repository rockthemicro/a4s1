package cool.compiler;

import cool.structures.Scope;
import cool.visitors.ASTVisitor;

import java.util.ArrayList;

public class ASTExprCaseNode extends ASTBaseNode {

	public ASTBaseNode expr = null;
	public ArrayList<String> ids = new ArrayList<>();
	public ArrayList<String> types = new ArrayList<>();
	public ArrayList<ASTBaseNode> exprs = new ArrayList<>();
	public ArrayList<Scope> scopes = new ArrayList<Scope>();
	
	@Override
	public void print(int depth) {
		super.printSpaces(depth);
		System.out.println("case");
		
		expr.print(depth + 1);
		
		for (int i = 0; i < ids.size(); i++) {
			super.printSpaces(depth + 1);
			System.out.println("case branch");
			
			super.printSpaces(depth + 2);
			System.out.println(ids.get(i));
			
			super.printSpaces(depth + 2);
			System.out.println(types.get(i));
			
			exprs.get(i).print(depth + 2);
		}
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visit(this);

		/*
		expr.accept(v);
		for (var exp : exprs) {
			exp.accept(v);
		}
		*/
	}

}

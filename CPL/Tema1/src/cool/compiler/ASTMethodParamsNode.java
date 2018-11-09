package cool.compiler;

import java.util.ArrayList;

public class ASTMethodParamsNode extends ASTBaseNode {

	ArrayList<String> ids = new ArrayList<>();
	ArrayList<String> types = new ArrayList<>();
	
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

}

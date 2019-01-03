package cool.compiler;

import cool.visitors.ASTVisitor;

public class ASTExprConstNode extends ASTBaseNode {
	
	public String value = null;
	public Types type = null;
	
	@Override
	public void print(int depth) {
		super.printSpaces(depth);
		System.out.println(value);
	}
	
	enum Types {
		NUMBER,
		BOOLEAN,
		STRING
	}

	public String getStringType() {
		switch (this.type) {
			case NUMBER:
				return "Int";
			case STRING:
				return "String";
			case BOOLEAN:
				return "Bool";
		}

		return "Int";
	}
	
	public void setNumberType() {
		this.type = Types.NUMBER;
	}

	public void setBooleanType() {
		this.type = Types.BOOLEAN;
	}
	
	public void setStringType() {
		this.type = Types.STRING;
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visit(this);
	}
}

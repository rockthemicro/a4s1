package cool.compiler;

public class ASTExprConstNode extends ASTBaseNode {
	
	String value = null;
	Types type = null;
	
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
	
	public void setNumberType() {
		this.type = Types.NUMBER;
	}

	public void setBooleanType() {
		this.type = Types.BOOLEAN;
	}
	
	public void setStringType() {
		this.type = Types.STRING;
	}
}

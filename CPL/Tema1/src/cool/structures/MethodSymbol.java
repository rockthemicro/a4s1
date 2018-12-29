package cool.structures;

import cool.compiler.ASTMethodNode;

import java.util.LinkedHashMap;
import java.util.Map;

public class MethodSymbol extends IdSymbol implements Scope {

    // For implementing Scope
	protected Map<String, Symbol> symbols = new LinkedHashMap<>();
    protected Scope parent;

    // Also present in parent class; it's for defining the type of this Symbol (IdSymbol)
    protected TypeSymbol type;

    public ASTMethodNode astMethodNode = null;

	public MethodSymbol(String name, Scope parent) {
		super(name);
		this.parent = parent;
	}

    public void setType(TypeSymbol type) {
        this.type = type;
    }
    
    public TypeSymbol getType() {
        return type;
    }
	
	public void put(Symbol sym) {
		symbols.put(sym.getName(), sym);
	}
	
    @Override
    public boolean add(Symbol sym) {
        // Ne asigurăm că simbolul nu există deja în domeniul de vizibilitate
        // curent.
        if (symbols.containsKey(sym.getName()))
            return false;
        
        symbols.put(sym.getName(), sym);
        
        return true;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Symbol lookup(String s) {
        var sym = symbols.get(s);
        
        if (sym != null)
            return sym;

        // Dacă nu găsim simbolul în domeniul de vizibilitate curent, îl căutăm
        // în domeniul de deasupra.
        if (parent != null)
            return parent.lookup(s);
        else
            return null;
    }

    @Override
    public Scope getParent() {
        return parent;
    }

    public boolean exists(String s) {

        if (symbols.get(s) != null) {
            return true;
        } else {
            return false;
        }
    }
}

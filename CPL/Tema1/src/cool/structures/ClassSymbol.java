package cool.structures;

import cool.compiler.ASTClassNode;

import java.util.LinkedHashMap;
import java.util.Map;

public class ClassSymbol extends TypeSymbol implements Scope {

    // For implementing Scope
	protected Map<String, Symbol> symbols = new LinkedHashMap<>();
    protected Scope parent;

    public ASTClassNode astClassNode = null;

	public ClassSymbol(String name, Scope parent) {
		super(name);
		this.parent = parent;
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

    public MethodSymbol lookupMethod(String s) {
        var sym = symbols.get(s);

        if (sym != null && sym instanceof  MethodSymbol)
            return (MethodSymbol) sym;

        // Dacă nu găsim metoda în domeniul de vizibilitate al clasei curente, o căutăm
        // în domeniul de deasupra.
        if (parent != null && parent instanceof ClassSymbol)
            return ((ClassSymbol) parent).lookupMethod(s);
        else
            return null;
    }

    @Override
    public Scope getParent() {
        return parent;
    }

    public void setParent(Scope parent) {
	    this.parent = parent;
    }

    public ClassSymbol getClassParent() {
	    if (parent instanceof ClassSymbol)
	        return (ClassSymbol) parent;

	    return null;
    }

    public boolean exists(String s) {

	    if (symbols.get(s) != null) {
	        return true;
        } else {
	        return false;
        }
    }

}

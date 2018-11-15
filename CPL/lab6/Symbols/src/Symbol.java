import java.util.LinkedHashMap;
import java.util.Map;

public class Symbol {
    protected String name;
    
    public Symbol(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}

class IdSymbol extends Symbol {
    public IdSymbol(String name) {
        super(name);
    }
};

class MethodSymbol extends IdSymbol implements Scope {

	protected Map<String, Symbol> symbols = new LinkedHashMap<>();
    protected Scope parent;
	
	public MethodSymbol(String name, Scope parent) {
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

    @Override
    public Symbol lookup(String s) {
        var sym = symbols.get(s);
        
        if (sym != null)
            return sym;
        
        // Dacă nu găsim simbolul în domeniul de vizibilitate curent, îl căutăm
        // în domeniul de deasupra.
        if (parent != null)
            return parent.lookup(s);
        
        return null;
    }

    @Override
    public Scope getParent() {
        return parent;
    }

}

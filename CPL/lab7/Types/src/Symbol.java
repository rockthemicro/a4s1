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
    protected TypeSymbol type;
    
    public IdSymbol(String name) {
        super(name);
    }
    
    public void setType(TypeSymbol type) {
        this.type = type;
    }
    
    public TypeSymbol getType() {
        return type;
    }
}

class TypeSymbol extends Symbol {
    public TypeSymbol(String name) {
        super(name);
    }
}

/*
// O metodă este atât simbol, cât și domeniu de vizibilitate pentru parametrii
// săi formali.
class MethodSymbol extends IdSymbol implements Scope {
    
}
*/

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

/*
// O metodă este atât simbol, cât și domeniu de vizibilitate pentru parametrii
// săi formali.
class MethodSymbol extends IdSymbol implements Scope {
    
}
*/
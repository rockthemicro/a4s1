package cool.structures;

import cool.compiler.ASTAttributeNode;
import cool.compiler.ASTClassNode;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("Duplicates")
public class ClassSymbol extends TypeSymbol implements Scope {

    // For implementing Scope
	protected Map<String, Symbol> symbols = new LinkedHashMap<>();
	protected Map<String, MethodSymbol> methodSymbols = new LinkedHashMap<>();
    protected Scope parent;

    public ASTClassNode astClassNode = null;

    private Boolean dispatchTableBuilt = false;
    public ArrayList<String> dispatchMethodNames = new ArrayList<>();

    private Boolean prototypeBuilt = false;
    public ArrayList<String> attrTypes = new ArrayList<>();
    public ArrayList<String> attrNames = new ArrayList<>();

    public ClassSymbol(String name, Scope parent) {
		super(name);
		this.parent = parent;
	}


    public void buildDispatchTable() {
        if (dispatchTableBuilt)
            return;

        String className = this.getName();

        ClassSymbol cs = (ClassSymbol) SymbolTable.globals.lookup(className);
        ArrayList<ClassSymbol> classParents = cs.getAllClassParents();

        for (int i = 0; i < classParents.size(); i++) {
            ClassSymbol parentOrCs = classParents.get(i);
            Set<String> methods = parentOrCs.getMethods();
            for (String method : methods) {
                boolean alreadyExists = false;

                // daca metoda este suprascrisa mai jos in lantul de mostenire, nu o mai marcam
                // pentru clasa curenta 'className'
                for (int j = i + 1; j < classParents.size(); j++) {
                    Set<String> tmpMethods = classParents.get(j).getMethods();
                    if (tmpMethods.contains(method)) {
                        alreadyExists = true;
                        break;
                    }
                }

                if (!alreadyExists) {
                    this.dispatchMethodNames.add(parentOrCs.getName() + "." + method);
                }
            }
        }

        this.dispatchTableBuilt = true;
    }

    public void buildPrototype() {
        if (prototypeBuilt)
            return;

        ArrayList<ClassSymbol> classParents = this.getAllClassParents();
        for (ClassSymbol parentOrCs : classParents) {

            if (parentOrCs.astClassNode != null) {
                /* obtinem atributele unui parinte (sau ale noastre) */
                ArrayList<ASTAttributeNode> attributes = parentOrCs.astClassNode.getAttributes();
                for (var attribute : attributes) {
                    attrNames.add(attribute.id);
                    attrTypes.add(attribute.type);

                }
            }
        }

        this.prototypeBuilt = true;
    }

    public boolean isChildOf(String type) {
	    if (this.getName().equals("SELF_TYPE") && type.equals("SELF_TYPE"))
	        return true;

	    ClassSymbol parentClass = this;
	    while (parentClass != null) {
            if (parentClass.getName().equals(type))
                return true;

	        parentClass = parentClass.getClassParent();
        }

	    return false;
    }

    public static String getCommonType(String type1, String type2) {
	    ClassSymbol class1 = (ClassSymbol) SymbolTable.globals.lookup(type1);
        ClassSymbol class2 = (ClassSymbol) SymbolTable.globals.lookup(type2);
        ArrayList<String> type1Hierarchy = new ArrayList<>();

        if (class1 == null || class2 == null)
            return null;

        while (class1 != null) {
            type1Hierarchy.add(class1.getName());
            if (class1.getName().equals("Object"))
                break;

            class1 = class1.getClassParent();
        }

        while (class2 != null) {
            if (type1Hierarchy.contains(class2.getName()))
                return class2.getName();

            class2 = class2.getClassParent();
        }

        return "Object";
    }

	public void put(Symbol sym) {
		symbols.put(sym.getName(), sym);
	}
	
    @Override
    public boolean add(Symbol sym) {
	    if (sym instanceof MethodSymbol) {
            // Ne asigurăm că simbolul nu există deja în domeniul de vizibilitate
            // curent.
            if (methodSymbols.containsKey(sym.getName()))
                return false;

            methodSymbols.put(sym.getName(), (MethodSymbol) sym);

            return true;

        } else {
            // Ne asigurăm că simbolul nu există deja în domeniul de vizibilitate
            // curent.
            if (symbols.containsKey(sym.getName()))
                return false;

            symbols.put(sym.getName(), sym);

            return true;
        }
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
        var sym = methodSymbols.get(s);

        if (sym != null)
            return sym;

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

    @Override
    public Scope lookupScope(String str) {
        var sym = symbols.get(str);

        if (sym != null)
            return this;

        if (parent != null)
            return parent.lookupScope(str);

        return null;
    }

    public void setParent(Scope parent) {
	    this.parent = parent;
    }

    public ClassSymbol getClassParent() {
	    if (parent instanceof ClassSymbol)
	        return (ClassSymbol) parent;

	    return null;
    }

    public boolean attrExists(String s) {

        if (symbols.get(s) != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean methodExists(String s) {

        if (methodSymbols.get(s) != null) {
            return true;
        } else {
            return false;
        }
    }

    public Set<String> getMethods() {
	    return this.methodSymbols.keySet();
    }

    public ArrayList<ClassSymbol> getAllClassParents() {
	    ArrayList<ClassSymbol> result = new ArrayList<>();
	    result.add(this);
	    ClassSymbol classParent = this.getClassParent();

	    while (classParent != null) {
	        result.add(0, classParent);
	        classParent = classParent.getClassParent();
        }

	    return result;
    }
}

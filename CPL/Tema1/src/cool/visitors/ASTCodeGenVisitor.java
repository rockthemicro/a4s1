package cool.visitors;

import cool.compiler.ASTClassNode;
import cool.structures.ClassSymbol;
import cool.structures.SymbolTable;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

@SuppressWarnings("Duplicates")
public class ASTCodeGenVisitor extends ASTNopVisitor {
    private STGroupFile group = null;
    private ST resultMIPS = null;

    private Integer objectClassTag = 0;
    private Integer ioClassTag = 1;
    private Integer intClassTag = 2;
    private Integer stringClassTag = 3;
    private Integer boolClassTag = 4;
    private Integer mainClassTag = 5;
    private Integer classTagCounter = 6;

    private Integer intConstTagCounter = 0;
    private Integer stringConstTagCounter = 0;

    private LinkedHashMap<String, Integer> stringConstMap = new LinkedHashMap<>();
    private LinkedHashMap<Integer, Integer> intConstMap = new LinkedHashMap<>();

    private ArrayList<Integer> classStringConstTags = new ArrayList<>();
    private ArrayList<Integer> classTags = new ArrayList<>();
    private ArrayList<String> classNames = new ArrayList<>();
    private ArrayList<Integer> classSizes = new ArrayList<>();
    private ArrayList<ArrayList<String>> classAttrTypes = new ArrayList<>();
    private ArrayList<String> globals = new ArrayList<>();

    public ST getStringConst(Integer id, String name) {
        Integer size = 4 + ((name.length() + 1) / 4);
        if ((name.length() + 1) % 4 != 0) size++;

        Integer intConstTag = obtainIntConstTag(name.length());

        ST str = this.group.getInstanceOf("string_const");
        str.add("id", id);
        str.add("stringClassTag", stringClassTag);
        str.add("size", size);
        str.add("intConstTag", intConstTag);
        str.add("name", name);

        return str;
    }

    public ASTCodeGenVisitor(STGroupFile group) {
        Integer tag = 0;
        this.group = group;

        this.resultMIPS = this.group.getInstanceOf("sequence");
        this.resultMIPS.add("e", this.group.getInstanceOf("data"));

        this.globals.add("class_nameTab");
        this.globals.add("Int_protObj");
        this.globals.add("String_protObj");
        this.globals.add("bool_const0");
        this.globals.add("bool_const1");
        this.globals.add("Main_protObj");
        this.globals.add("_int_tag");
        this.globals.add("_string_tag");
        this.globals.add("_bool_tag");

        tag = obtainStringConstTag("");

        tag = obtainStringConstTag("Object");
        classStringConstTags.add(tag);
        classTags.add(objectClassTag);
        classNames.add("Object");
        classSizes.add(3);
        classAttrTypes.add(null);

        tag = obtainStringConstTag("IO");
        classStringConstTags.add(tag);
        classTags.add(ioClassTag);
        classNames.add("IO");
        classSizes.add(3);
        classAttrTypes.add(null);

        tag = obtainStringConstTag("Int");
        classStringConstTags.add(tag);
        classTags.add(intClassTag);
        classNames.add("Int");
        classSizes.add(4);
        classAttrTypes.add(null);

        tag = obtainStringConstTag("String");
        classStringConstTags.add(tag);
        classTags.add(stringClassTag);
        classNames.add("String");
        classSizes.add(5);
        classAttrTypes.add(null);

        tag = obtainStringConstTag("Bool");
        classStringConstTags.add(tag);
        classTags.add(boolClassTag);
        classNames.add("Bool");
        classSizes.add(4);
        classAttrTypes.add(null);
    }

    @Override
    public void visit(ASTClassNode node) {
        super.visit(node);
    }

    public void computeResultMIPS() {
        for (var global : this.globals) {
            this.resultMIPS.add("e", this.group.getInstanceOf("global").add("e", global));
        }

        // adaugam toate variabilele globale, de care e nevoie si in trap.handler.nogc
        this.resultMIPS.add("e", this.group.getInstanceOf("label").add("e", "_int_tag"));
        this.resultMIPS.add("e", this.group.getInstanceOf("word").add("e", this.intClassTag));
        this.resultMIPS.add("e", this.group.getInstanceOf("label").add("e", "_string_tag"));
        this.resultMIPS.add("e", this.group.getInstanceOf("word").add("e", this.stringClassTag));
        this.resultMIPS.add("e", this.group.getInstanceOf("label").add("e", "_bool_tag"));
        this.resultMIPS.add("e", this.group.getInstanceOf("word").add("e", this.boolClassTag));

        // adaugam constantele string
        this.stringConstMap.forEach((k, v) -> {
            this.resultMIPS.add("e", getStringConst(v, k));
        });

        // adaugam constantele int
        this.intConstMap.forEach((k, v) -> {
            this.resultMIPS.add("e", this.group.getInstanceOf("int_const")
                    .add("id", v)
                    .add("intClassTag", this.intClassTag)
                    .add("val", k));
        });

        // adaugam constantele boolene
        this.resultMIPS.add("e", this.group.getInstanceOf("bool_const")
                        .add("id", 0)
                        .add("boolClassTag", this.boolClassTag));
        this.resultMIPS.add("e", this.group.getInstanceOf("bool_const")
                .add("id", 1)
                .add("boolClassTag", this.boolClassTag));

        // adaugam class_nameTab
        this.resultMIPS.add("e", this.group.getInstanceOf("label")
                .add("e", "class_nameTab"));
        for (Integer classTag : this.classStringConstTags) {
            this.resultMIPS.add("e", this.group.getInstanceOf("word")
                    .add("e", "str_const" + classTag));
        }

        // adaugam class_objTab
        this.resultMIPS.add("e", this.group.getInstanceOf("label")
                .add("e", "class_objTab"));
        for (String className : this.classNames) {
            this.resultMIPS.add("e", this.group.getInstanceOf("word")
                    .add("e", className + "_protObj"));
            this.resultMIPS.add("e", this.group.getInstanceOf("word")
                    .add("e", className + "_init"));
        }

        // adaugam toate prototipurile de obiecte
        for (int i = 0; i < this.classNames.size(); i++) {
            this.resultMIPS.add("e", getProtObj(this.classNames.get(i), this.classTags.get(i),
                    this.classSizes.get(i), this.classAttrTypes.get(i)));
        }

        // adaugam toate dispatch tableurile
        for (String className : this.classNames) {
            ClassSymbol cs = (ClassSymbol) SymbolTable.globals.lookup(className);
            ArrayList<ClassSymbol> classParents = cs.getAllClassParents();
            ST dispTab = this.group.getInstanceOf("disp_tab");

            dispTab.add("className", className);
            for (ClassSymbol parentOrCs : classParents) {
                Set<String> methods = parentOrCs.getMethods();
                for (String method : methods) {
                    dispTab.add("meth", ".word  " + parentOrCs.getName() + "." + method);
                }
            }

            this.resultMIPS.add("e", dispTab);
        }

        // adaugam startul zonei de text
        this.resultMIPS.add("e", this.group.getInstanceOf("heap_start"));

        // adaugam functiile init
        for (String className : this.classNames) {
            this.resultMIPS.add("e", this.group.getInstanceOf("func_start")
                    .add("name", className + "_init"));

            ClassSymbol cs = (ClassSymbol) SymbolTable.globals.lookup(className);
            ClassSymbol parent = cs.getClassParent();
            if (parent != null) {
                ST jal = this.group.getInstanceOf("anything");
                jal.add("e", "    jal     " + parent.getName() + "_init");
                this.resultMIPS.add("e", jal);
            }

            this.resultMIPS.add("e", this.group.getInstanceOf("return_self")); // move $a0 $s0
            this.resultMIPS.add("e", this.group.getInstanceOf("func_end"));
        }

        // generare de cod pt functia main
        this.resultMIPS.add("e", this.group.getInstanceOf("func_start")
                .add("name", "Main.main"));
        this.resultMIPS.add("e", this.group.getInstanceOf("return_0"));
        this.resultMIPS.add("e", this.group.getInstanceOf("func_end"));
    }

    public ST getResultMIPS() {
        if (this.resultMIPS != null)
            return this.resultMIPS;

        return new ST("Cool");
    }

    private Integer getClassTagCounter() {
        return this.classTagCounter;
    }

    private Integer getAndIncClassTagCounter() {
        this.classTagCounter += 1;
        return this.classTagCounter - 1;
    }

    private Integer getIntConstTagCounter() {
        return this.intConstTagCounter;
    }

    private Integer getAndIncIntConstTagCounter() {
        this.intConstTagCounter += 1;
        return this.intConstTagCounter - 1;
    }

    private Integer obtainIntConstTag(Integer val) {
        Integer tag = this.intConstMap.get(val);

        if (tag == null) {
            Integer newTag = getAndIncIntConstTagCounter();
            tag = newTag;
            this.intConstMap.put(val, newTag);
        }

        return tag;
    }

    private Integer getStringConstTagCounter() {
        return this.stringConstTagCounter;
    }

    private Integer getAndIncStringConstTagCounter() {
        this.stringConstTagCounter += 1;
        return this.stringConstTagCounter - 1;
    }

    private Integer obtainStringConstTag(String val) {
        Integer tag = this.stringConstMap.get(val);

        if (tag == null) {
            Integer newTag = getAndIncStringConstTagCounter();
            tag = newTag;
            this.stringConstMap.put(val, newTag);
        }

        return tag;
    }

    /**
     * Construieste prototipul unui clase cool
     * @param className numele clasei
     * @param classTag tagul clasei
     * @param size dimensiunea clasei
     * @param attrTypes vector cu tipurile atributelor clasei
     * @return StringTemplate cu prototipul clasei
     */
    private ST getProtObj(String className, Integer classTag, Integer size, ArrayList<String> attrTypes) {
        ST result = this.group.getInstanceOf("prot_obj");
        result.add("className", className);
        result.add("classTag", classTag);
        result.add("size", size);

        switch (className) {
            case "Object":
            case "IO":
                break;

            case "Int":
            case "Bool":
                result.add("attr", ".word   0");
                break;

            case "String":
                result.add("attr", ".word   int_const" + obtainIntConstTag(0));
                result.add("attr", ".asciiz \"\"");
                result.add("attr", ".align  2");
                break;

            default:
                addAttributes(result, attrTypes);
                break;
        }

        return result;
    }

    /**
     * Add all attributes from the attrTypes arraylist to the result stringtemplate
     * @param result
     * @param attrTypes
     */
    private void addAttributes(ST result, ArrayList<String> attrTypes) {
        for (String attrType : attrTypes) {
            switch(attrType) {

                case "Int":
                    result.add("attr", ".word   int_const" + obtainIntConstTag(0));
                    break;

                case "Bool":
                    result.add("attr", ".word   bool_const0");
                    break;

                case "String":
                    result.add("attr", ".word   string_const" + obtainStringConstTag(""));
                    break;

                default:
                    result.add("attr", ".word   0");
                    break;
            }
        }
    }
}

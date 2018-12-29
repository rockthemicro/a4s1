package cool.visitors;

import cool.compiler.ASTMethodParamsNode;
import cool.parser.CoolParser;
import cool.structures.SymbolTable;

public class ASTSymbolsSolveVisitor2 extends ASTNopVisitor {

    @Override
    public void visit(ASTMethodParamsNode node) {
        if (node.methodNode.classSymbol == null)
            return;

        // nu mostenim pe nimeni
        if (node.methodNode.classSymbol.getClassParent() == null) {
            return;
        }

        var overridenMethodSymbol = node.methodNode.classSymbol.getClassParent().lookupMethod(node.methodNode.id);

        if (overridenMethodSymbol == null)
            return;

        ASTMethodParamsNode overridenMethodParams = overridenMethodSymbol.astMethodNode.params;

        String className = node.methodNode.classSymbol.getName();
        String methodName = node.methodNode.id;

        // nu permitem schimbarea tipului de return a unei metode suprascrise
        if (node.methodNode.retType.equals(overridenMethodParams.methodNode.retType) == false) {
            var ctx = (CoolParser.MethodContext) node.methodNode.ctx;
            SymbolTable.error(node.ctx, ctx.ret, "Class " + className + " overrides method " + methodName +
                    " but changes return type from " + overridenMethodParams.methodNode.retType + " to " +
                    node.methodNode.retType);
        }

        // nu permitem suprascrierea unei metode si modificarea numarului de parametri al acesteia
        if (node.ids.size() != overridenMethodParams.ids.size()) {
            var ctx = (CoolParser.MethodContext) node.methodNode.ctx;
            SymbolTable.error(node.ctx, ctx.ID().getSymbol(), "Class " + className + " overrides method " + methodName +
                    " with different number of formal parameters");

            return;
        }

        // nu permitem modificarea tipurilor parametrilor unei metode pe care o suprascriem
        for (int i = 0; i < node.ids.size(); i++) {
            if (node.types.get(i).equals(overridenMethodParams.types.get(i)) == false) {
                SymbolTable.error(node.ctx, node.typeTokens.get(i), "Class " + className + " overrides method " + methodName +
                        " but changes type of formal parameter " + node.ids.get(i) + " from " + overridenMethodParams.types.get(i) +
                        " to " + node.types.get(i));
            }
        }
    }
}

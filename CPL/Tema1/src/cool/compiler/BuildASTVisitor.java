package cool.compiler;

import cool.parser.CoolParserBaseVisitor;
import cool.parser.CoolParser.Attr_asgnContext;
import cool.parser.CoolParser.Attr_no_asgnContext;
import cool.parser.CoolParser.BooleanContext;
import cool.parser.CoolParser.Class_bodyContext;
import cool.parser.CoolParser.Class_nodeContext;
import cool.parser.CoolParser.Empty_m_argsContext;
import cool.parser.CoolParser.Expr_acoladesContext;
import cool.parser.CoolParser.Expr_assignContext;
import cool.parser.CoolParser.Expr_caseContext;
import cool.parser.CoolParser.Expr_compContext;
import cool.parser.CoolParser.Expr_constContext;
import cool.parser.CoolParser.Expr_expr_fcallContext;
import cool.parser.CoolParser.Expr_fcallContext;
import cool.parser.CoolParser.Expr_idContext;
import cool.parser.CoolParser.Expr_ifContext;
import cool.parser.CoolParser.Expr_isvoidContext;
import cool.parser.CoolParser.Expr_letContext;
import cool.parser.CoolParser.Expr_muldivContext;
import cool.parser.CoolParser.Expr_negContext;
import cool.parser.CoolParser.Expr_newContext;
import cool.parser.CoolParser.Expr_notContext;
import cool.parser.CoolParser.Expr_paranContext;
import cool.parser.CoolParser.Expr_plussubContext;
import cool.parser.CoolParser.Expr_whileContext;
import cool.parser.CoolParser.F_callContext;
import cool.parser.CoolParser.Inheriter_classContext;
import cool.parser.CoolParser.Let_bindContext;
import cool.parser.CoolParser.M_argsContext;
import cool.parser.CoolParser.MethodContext;
import cool.parser.CoolParser.Mlt_arg_functionContext;
import cool.parser.CoolParser.Mlt_expr_methContext;
import cool.parser.CoolParser.NrContext;
import cool.parser.CoolParser.ProgramContext;
import cool.parser.CoolParser.Sg_arg_functionContext;
import cool.parser.CoolParser.Sg_expr_methContext;
import cool.parser.CoolParser.Simple_classContext;
import cool.parser.CoolParser.StringContext;

public class BuildASTVisitor extends CoolParserBaseVisitor<ASTBaseNode> {

	@Override
	public ASTBaseNode visitProgram(ProgramContext ctx) {
		ASTProgramNode node = new ASTProgramNode();
		node.ctx = ctx;
		for (var child : ctx.children) {
			node.classes.add(visit(child));
		}
		
		return node;
	}
	
	@Override
	public ASTBaseNode visitClass_node(Class_nodeContext ctx) {
		ASTClassNode node = (ASTClassNode) visit(ctx.class_header());
        node.ctx = ctx;
		node.body = visit(ctx.class_body());
		
		return node;
	}
	
	@Override
	public ASTBaseNode visitSimple_class(Simple_classContext ctx) {
		ASTClassNode node = new ASTClassNode();
		node.name = ctx.name.getText();
		
		return node;
	}
	
	@Override
	public ASTBaseNode visitInheriter_class(Inheriter_classContext ctx) {
		ASTClassNode node = new ASTClassNode();
		node.name = ctx.name.getText();
		node.inherits = ctx.parent.getText();

		return node;
	}
	
	@Override
	public ASTBaseNode visitClass_body(Class_bodyContext ctx) {
		ASTClassBodyNode node = new ASTClassBodyNode();
        node.ctx = ctx;
		for (var child : ctx.attr_or_meth()) {
			node.children.add(visit(child));
		}
	
		return node;
	}
	
	@Override
	public ASTBaseNode visitAttr_no_asgn(Attr_no_asgnContext ctx) {
		ASTAttributeNode node = new ASTAttributeNode();
        node.ctx = ctx;
		node.id = ctx.ID().getText();
		node.type = ctx.type().getText();
		
		return node;
	}
	
	@Override
	public ASTBaseNode visitAttr_asgn(Attr_asgnContext ctx) {
		ASTAttributeNode node = new ASTAttributeNode();
        node.ctx = ctx;
		node.id = ctx.ID().getText();
		node.type = ctx.type().getText();
		node.expr = visit(ctx.expr());
		
		return node;
	}
	
	@Override
	public ASTBaseNode visitMethod(MethodContext ctx) {
		ASTMethodNode node = new ASTMethodNode();
        node.ctx = ctx;
		node.id = ctx.ID().getText();
		node.retType = ctx.ret.getText();
		node.params = (ASTMethodParamsNode) visit(ctx.method_params());
		node.body = (ASTManyExprNode) visit(ctx.method_body());
		
		return node;
	}
	
	@Override
	public ASTBaseNode visitEmpty_m_args(Empty_m_argsContext ctx) {
		ASTMethodParamsNode node = new ASTMethodParamsNode();
        node.ctx = ctx;
		
		return node;
	}
	
	@Override
	public ASTBaseNode visitM_args(M_argsContext ctx) {
		ASTMethodParamsNode node = new ASTMethodParamsNode();
        node.ctx = ctx;
		for (int i = 0; i < ctx.ID().size(); i++) {
			node.ids.add(ctx.ID(i).getText());
			node.idTokens.add(ctx.ID(i).getSymbol());

			node.types.add(ctx.type(i).getText());
			node.typeTokens.add(ctx.type(i).getStart());
		}
		
		return node;
	}
	
	@Override
	public ASTBaseNode visitSg_expr_meth(Sg_expr_methContext ctx) {
		ASTManyExprNode node = new ASTManyExprNode();
        node.ctx = ctx;
		node.children.add(visit(ctx.expr()));
		
		return node;
	}
	
	@Override
	public ASTBaseNode visitMlt_expr_meth(Mlt_expr_methContext ctx) {
		ASTManyExprNode node = new ASTManyExprNode();
        node.ctx = ctx;
		for (var child : ctx.expr()) {
			node.children.add(visit(child));
		}
		
		return node;
	}
	
	@Override
	public ASTBaseNode visitF_call(F_callContext ctx) {
		ASTFcallNode node = new ASTFcallNode();
        node.ctx = ctx;
		node.id = ctx.ID().getText();
		
		if (ctx.f_call_args() != null) {
			node.params = visit(ctx.f_call_args());
		}
		
		return node;
	}
	
	@Override
	public ASTBaseNode visitSg_arg_function(Sg_arg_functionContext ctx) {
		ASTManyExprNode node = new ASTManyExprNode();
        node.ctx = ctx;
		node.children.add(visit(ctx.expr()));
		
		return node;
	}
	
	@Override
	public ASTBaseNode visitMlt_arg_function(Mlt_arg_functionContext ctx) {
		ASTManyExprNode node = new ASTManyExprNode();
		for (var child : ctx.expr()) {
			node.children.add(visit(child));
		}
        node.ctx = ctx;
		
		return node;
	}
	
	@Override
	public ASTBaseNode visitLet_bind(Let_bindContext ctx) {
		ASTLetNode node = new ASTLetNode();
        node.ctx = ctx;
		node.id = ctx.ID().getText();
		node.type = ctx.type().getText();
		if (ctx.expr() != null) {
			node.expr = visit(ctx.expr());
		}
		
		return node;
	}
	
	@Override
	public ASTBaseNode visitNr(NrContext ctx) {
		ASTExprConstNode node = new ASTExprConstNode();
        node.ctx = ctx;
		node.value = ctx.NR().getText();
		node.setNumberType();
		
		return node;
	}
	
	@Override
	public ASTBaseNode visitBoolean(BooleanContext ctx) {
		ASTExprConstNode node = new ASTExprConstNode();
        node.ctx = ctx;
		node.value = ctx.BOOLEAN().getText();
		node.setBooleanType();
		
		return node;
	}
	
	@Override
	public ASTBaseNode visitString(StringContext ctx) {
		ASTExprConstNode node = new ASTExprConstNode();
        node.ctx = ctx;
		node.value = ctx.STRING().getText();
		node.setStringType();
		
		return node;
	}
	
	/* expr productions implementation */
	
	@Override
	public ASTBaseNode visitExpr_expr_fcall(Expr_expr_fcallContext ctx) {
		ASTExprExprFcallNode node = new ASTExprExprFcallNode();
        node.ctx = ctx;
		
		node.expr = visit(ctx.expr());
		if (ctx.type() != null) {
			node.type = ctx.type().getText();
		}
		node.fcall = visit(ctx.f_call());
		
		return node;
	}
	
	@Override
	public ASTBaseNode visitExpr_fcall(Expr_fcallContext ctx) {
		ASTExprFcallNode node = new ASTExprFcallNode();
        node.ctx = ctx;
		node.fcall = visit(ctx.f_call());
		
		return node;
	}
	
	@Override
	public ASTBaseNode visitExpr_acolades(Expr_acoladesContext ctx) {
		ASTExprBlockNode node = new ASTExprBlockNode();
        node.ctx = ctx;
		for (var child : ctx.expr()) {
			node.child.children.add(visit(child));
		}
		
		return node;
	}
	
	@Override
	public ASTBaseNode visitExpr_if(Expr_ifContext ctx) {
		ASTExprIfNode node = new ASTExprIfNode();
        node.ctx = ctx;
		node.ifExpr = visit(ctx.expr(0));
		node.thenExpr = visit(ctx.expr(1));
		node.elseExpr = visit(ctx.expr(2));

		return node;
	}
	
	@Override
	public ASTBaseNode visitExpr_while(Expr_whileContext ctx) {
		ASTExprWhileNode node = new ASTExprWhileNode();
        node.ctx = ctx;
		node.cond = visit(ctx.expr(0));
		node.body = visit(ctx.expr(1));
		
		return node;
	}
	
	@Override
	public ASTBaseNode visitExpr_let(Expr_letContext ctx) {
		ASTExprLetNode node = new ASTExprLetNode();
        node.ctx = ctx;
		for (var child : ctx.let_bind()) {
			node.letNodes.add((ASTLetNode)visit(child));
		}
		node.expr = visit(ctx.expr());
		
		return node;
	}
	
	@Override
	public ASTBaseNode visitExpr_case(Expr_caseContext ctx) {
		ASTExprCaseNode node = new ASTExprCaseNode();
        node.ctx = ctx;
		node.expr = visit(ctx.expr(0));
		
		for (int i = 0; i < ctx.ID().size(); i++) {
			node.ids.add(ctx.ID(i).getText());
			node.types.add(ctx.type(i).getText());
			node.exprs.add(visit(ctx.expr(i + 1)));
		}
		
		return node;
	}
	
	@Override
	public ASTBaseNode visitExpr_paran(Expr_paranContext ctx) {
		return visit(ctx.expr());
	}
	
	@Override
	public ASTBaseNode visitExpr_id(Expr_idContext ctx) {
		ASTExprIdNode node = new ASTExprIdNode();
        node.ctx = ctx;
		node.id = ctx.ID().getText();
		
		return node;
	}
	
	@Override
	public ASTBaseNode visitExpr_const(Expr_constContext ctx) {
		return visit(ctx.constant());
	}
	
	@Override
	public ASTBaseNode visitExpr_neg(Expr_negContext ctx) {
		ASTExprNegNode node = new ASTExprNegNode();
        node.ctx = ctx;
		node.expr = visit(ctx.expr());
		
		return node;
	}
	
	@Override
	public ASTBaseNode visitExpr_isvoid(Expr_isvoidContext ctx) {
		ASTExprVoidNode node = new ASTExprVoidNode();
        node.ctx = ctx;
		node.expr = visit(ctx.expr());
		
		return node;
	}
	
	@Override
	public ASTBaseNode visitExpr_muldiv(Expr_muldivContext ctx) {
		ASTExprArithmNode node = new ASTExprArithmNode();
        node.ctx = ctx;
		node.expr1 = visit(ctx.expr(0));
		node.expr2 = visit(ctx.expr(1));
		node.operation = ctx.op.getText();
		
		return node;
	}
	
	@Override
	public ASTBaseNode visitExpr_plussub(Expr_plussubContext ctx) {
		ASTExprArithmNode node = new ASTExprArithmNode();
		node.expr1 = visit(ctx.expr(0));
		node.expr2 = visit(ctx.expr(1));
		node.operation = ctx.op.getText();
        node.ctx = ctx;

		return node;
	}
	
	@Override
	public ASTBaseNode visitExpr_comp(Expr_compContext ctx) {
		ASTExprCompNode node = new ASTExprCompNode();
        node.ctx = ctx;
		node.expr1 = visit(ctx.expr(0));
		node.expr2 = visit(ctx.expr(1));
		node.comparator = ctx.op.getText();
		
		return node;
	}
	
	@Override
	public ASTBaseNode visitExpr_not(Expr_notContext ctx) {
		ASTExprNotNode node = new ASTExprNotNode();
        node.ctx = ctx;
		node.expr = visit(ctx.expr());
		
		return node;
	}
	
	@Override
	public ASTBaseNode visitExpr_assign(Expr_assignContext ctx) {
		ASTExprAssignNode node = new ASTExprAssignNode();
        node.ctx = ctx;
		node.id = ctx.ID().getText();
		node.expr = visit(ctx.expr());
		
		return node;
	}
	
	@Override
	public ASTBaseNode visitExpr_new(Expr_newContext ctx) {
		ASTExprNewNode node = new ASTExprNewNode();
        node.ctx = ctx;
		node.type = ctx.type().getText();
		
		return node;
	}
}

// Generated from src/cool/parser/CoolParser.g4 by ANTLR 4.7.1

    package cool.parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CoolParser}.
 */
public interface CoolParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CoolParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(CoolParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoolParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(CoolParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nr}
	 * labeled alternative in {@link CoolParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterNr(CoolParser.NrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nr}
	 * labeled alternative in {@link CoolParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitNr(CoolParser.NrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolean}
	 * labeled alternative in {@link CoolParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterBoolean(CoolParser.BooleanContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolean}
	 * labeled alternative in {@link CoolParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitBoolean(CoolParser.BooleanContext ctx);
	/**
	 * Enter a parse tree produced by the {@code string}
	 * labeled alternative in {@link CoolParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterString(CoolParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code string}
	 * labeled alternative in {@link CoolParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitString(CoolParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoolParser#let_bind}.
	 * @param ctx the parse tree
	 */
	void enterLet_bind(CoolParser.Let_bindContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoolParser#let_bind}.
	 * @param ctx the parse tree
	 */
	void exitLet_bind(CoolParser.Let_bindContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sg_arg_function}
	 * labeled alternative in {@link CoolParser#f_call_args}.
	 * @param ctx the parse tree
	 */
	void enterSg_arg_function(CoolParser.Sg_arg_functionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sg_arg_function}
	 * labeled alternative in {@link CoolParser#f_call_args}.
	 * @param ctx the parse tree
	 */
	void exitSg_arg_function(CoolParser.Sg_arg_functionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mlt_arg_function}
	 * labeled alternative in {@link CoolParser#f_call_args}.
	 * @param ctx the parse tree
	 */
	void enterMlt_arg_function(CoolParser.Mlt_arg_functionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mlt_arg_function}
	 * labeled alternative in {@link CoolParser#f_call_args}.
	 * @param ctx the parse tree
	 */
	void exitMlt_arg_function(CoolParser.Mlt_arg_functionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoolParser#f_call}.
	 * @param ctx the parse tree
	 */
	void enterF_call(CoolParser.F_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoolParser#f_call}.
	 * @param ctx the parse tree
	 */
	void exitF_call(CoolParser.F_callContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_comp}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_comp(CoolParser.Expr_compContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_comp}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_comp(CoolParser.Expr_compContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_case}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_case(CoolParser.Expr_caseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_case}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_case(CoolParser.Expr_caseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_const}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_const(CoolParser.Expr_constContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_const}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_const(CoolParser.Expr_constContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_new}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_new(CoolParser.Expr_newContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_new}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_new(CoolParser.Expr_newContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_fcall}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_fcall(CoolParser.Expr_fcallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_fcall}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_fcall(CoolParser.Expr_fcallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_let}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_let(CoolParser.Expr_letContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_let}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_let(CoolParser.Expr_letContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_muldiv}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_muldiv(CoolParser.Expr_muldivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_muldiv}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_muldiv(CoolParser.Expr_muldivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_assign}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_assign(CoolParser.Expr_assignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_assign}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_assign(CoolParser.Expr_assignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_paran}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_paran(CoolParser.Expr_paranContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_paran}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_paran(CoolParser.Expr_paranContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_isvoid}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_isvoid(CoolParser.Expr_isvoidContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_isvoid}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_isvoid(CoolParser.Expr_isvoidContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_expr_fcall}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_expr_fcall(CoolParser.Expr_expr_fcallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_expr_fcall}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_expr_fcall(CoolParser.Expr_expr_fcallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_neg}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_neg(CoolParser.Expr_negContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_neg}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_neg(CoolParser.Expr_negContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_while}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_while(CoolParser.Expr_whileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_while}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_while(CoolParser.Expr_whileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_acolades}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_acolades(CoolParser.Expr_acoladesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_acolades}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_acolades(CoolParser.Expr_acoladesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_plussub}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_plussub(CoolParser.Expr_plussubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_plussub}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_plussub(CoolParser.Expr_plussubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_if}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_if(CoolParser.Expr_ifContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_if}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_if(CoolParser.Expr_ifContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_not}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_not(CoolParser.Expr_notContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_not}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_not(CoolParser.Expr_notContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_id}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr_id(CoolParser.Expr_idContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_id}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr_id(CoolParser.Expr_idContext ctx);
	/**
	 * Enter a parse tree produced by the {@code attr_asgn}
	 * labeled alternative in {@link CoolParser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterAttr_asgn(CoolParser.Attr_asgnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code attr_asgn}
	 * labeled alternative in {@link CoolParser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitAttr_asgn(CoolParser.Attr_asgnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code attr_no_asgn}
	 * labeled alternative in {@link CoolParser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterAttr_no_asgn(CoolParser.Attr_no_asgnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code attr_no_asgn}
	 * labeled alternative in {@link CoolParser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitAttr_no_asgn(CoolParser.Attr_no_asgnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code m_args}
	 * labeled alternative in {@link CoolParser#method_params}.
	 * @param ctx the parse tree
	 */
	void enterM_args(CoolParser.M_argsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code m_args}
	 * labeled alternative in {@link CoolParser#method_params}.
	 * @param ctx the parse tree
	 */
	void exitM_args(CoolParser.M_argsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code empty_m_args}
	 * labeled alternative in {@link CoolParser#method_params}.
	 * @param ctx the parse tree
	 */
	void enterEmpty_m_args(CoolParser.Empty_m_argsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code empty_m_args}
	 * labeled alternative in {@link CoolParser#method_params}.
	 * @param ctx the parse tree
	 */
	void exitEmpty_m_args(CoolParser.Empty_m_argsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sg_expr_meth}
	 * labeled alternative in {@link CoolParser#method_body}.
	 * @param ctx the parse tree
	 */
	void enterSg_expr_meth(CoolParser.Sg_expr_methContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sg_expr_meth}
	 * labeled alternative in {@link CoolParser#method_body}.
	 * @param ctx the parse tree
	 */
	void exitSg_expr_meth(CoolParser.Sg_expr_methContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mlt_expr_meth}
	 * labeled alternative in {@link CoolParser#method_body}.
	 * @param ctx the parse tree
	 */
	void enterMlt_expr_meth(CoolParser.Mlt_expr_methContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mlt_expr_meth}
	 * labeled alternative in {@link CoolParser#method_body}.
	 * @param ctx the parse tree
	 */
	void exitMlt_expr_meth(CoolParser.Mlt_expr_methContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoolParser#method}.
	 * @param ctx the parse tree
	 */
	void enterMethod(CoolParser.MethodContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoolParser#method}.
	 * @param ctx the parse tree
	 */
	void exitMethod(CoolParser.MethodContext ctx);
	/**
	 * Enter a parse tree produced by the {@code attr}
	 * labeled alternative in {@link CoolParser#attr_or_meth}.
	 * @param ctx the parse tree
	 */
	void enterAttr(CoolParser.AttrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code attr}
	 * labeled alternative in {@link CoolParser#attr_or_meth}.
	 * @param ctx the parse tree
	 */
	void exitAttr(CoolParser.AttrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code meth}
	 * labeled alternative in {@link CoolParser#attr_or_meth}.
	 * @param ctx the parse tree
	 */
	void enterMeth(CoolParser.MethContext ctx);
	/**
	 * Exit a parse tree produced by the {@code meth}
	 * labeled alternative in {@link CoolParser#attr_or_meth}.
	 * @param ctx the parse tree
	 */
	void exitMeth(CoolParser.MethContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoolParser#class_body}.
	 * @param ctx the parse tree
	 */
	void enterClass_body(CoolParser.Class_bodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoolParser#class_body}.
	 * @param ctx the parse tree
	 */
	void exitClass_body(CoolParser.Class_bodyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code inheriter_class}
	 * labeled alternative in {@link CoolParser#class_header}.
	 * @param ctx the parse tree
	 */
	void enterInheriter_class(CoolParser.Inheriter_classContext ctx);
	/**
	 * Exit a parse tree produced by the {@code inheriter_class}
	 * labeled alternative in {@link CoolParser#class_header}.
	 * @param ctx the parse tree
	 */
	void exitInheriter_class(CoolParser.Inheriter_classContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simple_class}
	 * labeled alternative in {@link CoolParser#class_header}.
	 * @param ctx the parse tree
	 */
	void enterSimple_class(CoolParser.Simple_classContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simple_class}
	 * labeled alternative in {@link CoolParser#class_header}.
	 * @param ctx the parse tree
	 */
	void exitSimple_class(CoolParser.Simple_classContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoolParser#class_node}.
	 * @param ctx the parse tree
	 */
	void enterClass_node(CoolParser.Class_nodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoolParser#class_node}.
	 * @param ctx the parse tree
	 */
	void exitClass_node(CoolParser.Class_nodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoolParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(CoolParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoolParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(CoolParser.ProgramContext ctx);
}
// Generated from src/cool/parser/CoolParser.g4 by ANTLR 4.7.1

    package cool.parser;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CoolParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CoolParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CoolParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(CoolParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nr}
	 * labeled alternative in {@link CoolParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNr(CoolParser.NrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolean}
	 * labeled alternative in {@link CoolParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolean(CoolParser.BooleanContext ctx);
	/**
	 * Visit a parse tree produced by the {@code string}
	 * labeled alternative in {@link CoolParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(CoolParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#let_bind}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLet_bind(CoolParser.Let_bindContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sg_arg_function}
	 * labeled alternative in {@link CoolParser#f_call_args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSg_arg_function(CoolParser.Sg_arg_functionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mlt_arg_function}
	 * labeled alternative in {@link CoolParser#f_call_args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMlt_arg_function(CoolParser.Mlt_arg_functionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#f_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitF_call(CoolParser.F_callContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_comp}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_comp(CoolParser.Expr_compContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_case}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_case(CoolParser.Expr_caseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_const}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_const(CoolParser.Expr_constContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_new}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_new(CoolParser.Expr_newContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_fcall}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_fcall(CoolParser.Expr_fcallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_let}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_let(CoolParser.Expr_letContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_muldiv}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_muldiv(CoolParser.Expr_muldivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_assign}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_assign(CoolParser.Expr_assignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_paran}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_paran(CoolParser.Expr_paranContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_isvoid}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_isvoid(CoolParser.Expr_isvoidContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_expr_fcall}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_expr_fcall(CoolParser.Expr_expr_fcallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_neg}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_neg(CoolParser.Expr_negContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_while}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_while(CoolParser.Expr_whileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_acolades}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_acolades(CoolParser.Expr_acoladesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_plussub}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_plussub(CoolParser.Expr_plussubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_if}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_if(CoolParser.Expr_ifContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_not}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_not(CoolParser.Expr_notContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_id}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_id(CoolParser.Expr_idContext ctx);
	/**
	 * Visit a parse tree produced by the {@code attr_asgn}
	 * labeled alternative in {@link CoolParser#attribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttr_asgn(CoolParser.Attr_asgnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code attr_no_asgn}
	 * labeled alternative in {@link CoolParser#attribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttr_no_asgn(CoolParser.Attr_no_asgnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code m_args}
	 * labeled alternative in {@link CoolParser#method_params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitM_args(CoolParser.M_argsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code empty_m_args}
	 * labeled alternative in {@link CoolParser#method_params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmpty_m_args(CoolParser.Empty_m_argsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sg_expr_meth}
	 * labeled alternative in {@link CoolParser#method_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSg_expr_meth(CoolParser.Sg_expr_methContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mlt_expr_meth}
	 * labeled alternative in {@link CoolParser#method_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMlt_expr_meth(CoolParser.Mlt_expr_methContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#method}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethod(CoolParser.MethodContext ctx);
	/**
	 * Visit a parse tree produced by the {@code attr}
	 * labeled alternative in {@link CoolParser#attr_or_meth}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttr(CoolParser.AttrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code meth}
	 * labeled alternative in {@link CoolParser#attr_or_meth}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMeth(CoolParser.MethContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#class_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass_body(CoolParser.Class_bodyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code inheriter_class}
	 * labeled alternative in {@link CoolParser#class_header}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInheriter_class(CoolParser.Inheriter_classContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simple_class}
	 * labeled alternative in {@link CoolParser#class_header}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimple_class(CoolParser.Simple_classContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#class_node}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass_node(CoolParser.Class_nodeContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(CoolParser.ProgramContext ctx);
}
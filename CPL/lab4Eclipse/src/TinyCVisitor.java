// Generated from TinyC.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TinyCParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TinyCVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TinyCParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(TinyCParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by the {@code if_then_statement}
	 * labeled alternative in {@link TinyCParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_then_statement(TinyCParser.If_then_statementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code if_then_else_statement}
	 * labeled alternative in {@link TinyCParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_then_else_statement(TinyCParser.If_then_else_statementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code while_statement}
	 * labeled alternative in {@link TinyCParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_statement(TinyCParser.While_statementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code do_while_statement}
	 * labeled alternative in {@link TinyCParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDo_while_statement(TinyCParser.Do_while_statementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code block_statement}
	 * labeled alternative in {@link TinyCParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock_statement(TinyCParser.Block_statementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expr_statement}
	 * labeled alternative in {@link TinyCParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_statement(TinyCParser.Expr_statementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code null_statement}
	 * labeled alternative in {@link TinyCParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNull_statement(TinyCParser.Null_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyCParser#paren_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParen_expr(TinyCParser.Paren_exprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bare_test}
	 * labeled alternative in {@link TinyCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBare_test(TinyCParser.Bare_testContext ctx);
	/**
	 * Visit a parse tree produced by the {@code attr_expr}
	 * labeled alternative in {@link TinyCParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttr_expr(TinyCParser.Attr_exprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bare_sum}
	 * labeled alternative in {@link TinyCParser#test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBare_sum(TinyCParser.Bare_sumContext ctx);
	/**
	 * Visit a parse tree produced by the {@code comp_test}
	 * labeled alternative in {@link TinyCParser#test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComp_test(TinyCParser.Comp_testContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bare_term}
	 * labeled alternative in {@link TinyCParser#sum}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBare_term(TinyCParser.Bare_termContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addition}
	 * labeled alternative in {@link TinyCParser#sum}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddition(TinyCParser.AdditionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code substraction}
	 * labeled alternative in {@link TinyCParser#sum}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubstraction(TinyCParser.SubstractionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bare_id}
	 * labeled alternative in {@link TinyCParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBare_id(TinyCParser.Bare_idContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bare_int}
	 * labeled alternative in {@link TinyCParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBare_int(TinyCParser.Bare_intContext ctx);
	/**
	 * Visit a parse tree produced by the {@code paren_term}
	 * labeled alternative in {@link TinyCParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParen_term(TinyCParser.Paren_termContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyCParser#id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(TinyCParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyCParser#integer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteger(TinyCParser.IntegerContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyCParser#decInteger}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecInteger(TinyCParser.DecIntegerContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyCParser#hexInteger}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHexInteger(TinyCParser.HexIntegerContext ctx);
	/**
	 * Visit a parse tree produced by {@link TinyCParser#binInteger}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinInteger(TinyCParser.BinIntegerContext ctx);
}
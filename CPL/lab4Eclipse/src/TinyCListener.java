// Generated from TinyC.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TinyCParser}.
 */
public interface TinyCListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TinyCParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(TinyCParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyCParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(TinyCParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by the {@code if_then_statement}
	 * labeled alternative in {@link TinyCParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIf_then_statement(TinyCParser.If_then_statementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code if_then_statement}
	 * labeled alternative in {@link TinyCParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIf_then_statement(TinyCParser.If_then_statementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code if_then_else_statement}
	 * labeled alternative in {@link TinyCParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIf_then_else_statement(TinyCParser.If_then_else_statementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code if_then_else_statement}
	 * labeled alternative in {@link TinyCParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIf_then_else_statement(TinyCParser.If_then_else_statementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code while_statement}
	 * labeled alternative in {@link TinyCParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterWhile_statement(TinyCParser.While_statementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code while_statement}
	 * labeled alternative in {@link TinyCParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitWhile_statement(TinyCParser.While_statementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code do_while_statement}
	 * labeled alternative in {@link TinyCParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterDo_while_statement(TinyCParser.Do_while_statementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code do_while_statement}
	 * labeled alternative in {@link TinyCParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitDo_while_statement(TinyCParser.Do_while_statementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code block_statement}
	 * labeled alternative in {@link TinyCParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBlock_statement(TinyCParser.Block_statementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code block_statement}
	 * labeled alternative in {@link TinyCParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBlock_statement(TinyCParser.Block_statementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expr_statement}
	 * labeled alternative in {@link TinyCParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterExpr_statement(TinyCParser.Expr_statementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expr_statement}
	 * labeled alternative in {@link TinyCParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitExpr_statement(TinyCParser.Expr_statementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code null_statement}
	 * labeled alternative in {@link TinyCParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterNull_statement(TinyCParser.Null_statementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code null_statement}
	 * labeled alternative in {@link TinyCParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitNull_statement(TinyCParser.Null_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyCParser#paren_expr}.
	 * @param ctx the parse tree
	 */
	void enterParen_expr(TinyCParser.Paren_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyCParser#paren_expr}.
	 * @param ctx the parse tree
	 */
	void exitParen_expr(TinyCParser.Paren_exprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bare_test}
	 * labeled alternative in {@link TinyCParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBare_test(TinyCParser.Bare_testContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bare_test}
	 * labeled alternative in {@link TinyCParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBare_test(TinyCParser.Bare_testContext ctx);
	/**
	 * Enter a parse tree produced by the {@code attr_expr}
	 * labeled alternative in {@link TinyCParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAttr_expr(TinyCParser.Attr_exprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code attr_expr}
	 * labeled alternative in {@link TinyCParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAttr_expr(TinyCParser.Attr_exprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bare_sum}
	 * labeled alternative in {@link TinyCParser#test}.
	 * @param ctx the parse tree
	 */
	void enterBare_sum(TinyCParser.Bare_sumContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bare_sum}
	 * labeled alternative in {@link TinyCParser#test}.
	 * @param ctx the parse tree
	 */
	void exitBare_sum(TinyCParser.Bare_sumContext ctx);
	/**
	 * Enter a parse tree produced by the {@code comp_test}
	 * labeled alternative in {@link TinyCParser#test}.
	 * @param ctx the parse tree
	 */
	void enterComp_test(TinyCParser.Comp_testContext ctx);
	/**
	 * Exit a parse tree produced by the {@code comp_test}
	 * labeled alternative in {@link TinyCParser#test}.
	 * @param ctx the parse tree
	 */
	void exitComp_test(TinyCParser.Comp_testContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bare_term}
	 * labeled alternative in {@link TinyCParser#sum}.
	 * @param ctx the parse tree
	 */
	void enterBare_term(TinyCParser.Bare_termContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bare_term}
	 * labeled alternative in {@link TinyCParser#sum}.
	 * @param ctx the parse tree
	 */
	void exitBare_term(TinyCParser.Bare_termContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addition}
	 * labeled alternative in {@link TinyCParser#sum}.
	 * @param ctx the parse tree
	 */
	void enterAddition(TinyCParser.AdditionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addition}
	 * labeled alternative in {@link TinyCParser#sum}.
	 * @param ctx the parse tree
	 */
	void exitAddition(TinyCParser.AdditionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code substraction}
	 * labeled alternative in {@link TinyCParser#sum}.
	 * @param ctx the parse tree
	 */
	void enterSubstraction(TinyCParser.SubstractionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code substraction}
	 * labeled alternative in {@link TinyCParser#sum}.
	 * @param ctx the parse tree
	 */
	void exitSubstraction(TinyCParser.SubstractionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bare_id}
	 * labeled alternative in {@link TinyCParser#term}.
	 * @param ctx the parse tree
	 */
	void enterBare_id(TinyCParser.Bare_idContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bare_id}
	 * labeled alternative in {@link TinyCParser#term}.
	 * @param ctx the parse tree
	 */
	void exitBare_id(TinyCParser.Bare_idContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bare_int}
	 * labeled alternative in {@link TinyCParser#term}.
	 * @param ctx the parse tree
	 */
	void enterBare_int(TinyCParser.Bare_intContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bare_int}
	 * labeled alternative in {@link TinyCParser#term}.
	 * @param ctx the parse tree
	 */
	void exitBare_int(TinyCParser.Bare_intContext ctx);
	/**
	 * Enter a parse tree produced by the {@code paren_term}
	 * labeled alternative in {@link TinyCParser#term}.
	 * @param ctx the parse tree
	 */
	void enterParen_term(TinyCParser.Paren_termContext ctx);
	/**
	 * Exit a parse tree produced by the {@code paren_term}
	 * labeled alternative in {@link TinyCParser#term}.
	 * @param ctx the parse tree
	 */
	void exitParen_term(TinyCParser.Paren_termContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyCParser#id}.
	 * @param ctx the parse tree
	 */
	void enterId(TinyCParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyCParser#id}.
	 * @param ctx the parse tree
	 */
	void exitId(TinyCParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyCParser#integer}.
	 * @param ctx the parse tree
	 */
	void enterInteger(TinyCParser.IntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyCParser#integer}.
	 * @param ctx the parse tree
	 */
	void exitInteger(TinyCParser.IntegerContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyCParser#decInteger}.
	 * @param ctx the parse tree
	 */
	void enterDecInteger(TinyCParser.DecIntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyCParser#decInteger}.
	 * @param ctx the parse tree
	 */
	void exitDecInteger(TinyCParser.DecIntegerContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyCParser#hexInteger}.
	 * @param ctx the parse tree
	 */
	void enterHexInteger(TinyCParser.HexIntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyCParser#hexInteger}.
	 * @param ctx the parse tree
	 */
	void exitHexInteger(TinyCParser.HexIntegerContext ctx);
	/**
	 * Enter a parse tree produced by {@link TinyCParser#binInteger}.
	 * @param ctx the parse tree
	 */
	void enterBinInteger(TinyCParser.BinIntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link TinyCParser#binInteger}.
	 * @param ctx the parse tree
	 */
	void exitBinInteger(TinyCParser.BinIntegerContext ctx);
}
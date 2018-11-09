import java.io.IOException;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;


public class Test {
	public static void main(String[] args) throws IOException {
		var input = CharStreams.fromFileName("samples/prog5.tinyc");

		var lexer = new TinyCLexer(input);
		var tokenStream = new CommonTokenStream(lexer);

		var parser = new TinyCParser(tokenStream);
		var tree = parser.program();

		System.out.println("Arborele generat de parser:");
		System.out.println(tree.toStringTree(parser));

		var visitor = new TinyCBaseVisitor<ASTBaseNode>() {
			@Override
			public ASTBaseNode visitProgram(TinyCParser.ProgramContext ctx) {
				ASTProgramNode node = new ASTProgramNode();

				for (var child : ctx.children) {
					node.statements.add(visit(child));
				}

				return node;
			}

			@Override
			public ASTBaseNode visitIf_then_statement(TinyCParser.If_then_statementContext ctx) {
				ASTIfNode node = new ASTIfNode();
				node.paren_statement = visit(ctx.paren_expr());
				node.if_statement = visit(ctx.statement());

				return node;
			}

			@Override
			public ASTBaseNode visitIf_then_else_statement(TinyCParser.If_then_else_statementContext ctx) {
				ASTIfNode node = new ASTIfNode();
				node.paren_statement = visit(ctx.paren_expr());
				node.if_statement = visit(ctx.statement(0));
				node.else_statement = visit(ctx.statement(1));

				return node;
			}

			@Override
			public ASTBaseNode visitParen_expr(TinyCParser.Paren_exprContext ctx) {
				return visit(ctx.expr());
			}

			@Override
			public ASTBaseNode visitAttr_expr(TinyCParser.Attr_exprContext ctx) {
				ASTAttrNode node = new ASTAttrNode();
				node.id = ctx.id().getText();
				node.expr = visit(ctx.expr());

				return node;
			}

			@Override
			public ASTBaseNode visitComp_test(TinyCParser.Comp_testContext ctx) {
				ASTCompNode node = new ASTCompNode();
				node.leftSum = visit(ctx.sum(0));
				node.rightSum = visit(ctx.sum(1));

				return node;
			}

			@Override
			public ASTBaseNode visitAddition(TinyCParser.AdditionContext ctx) {
				ASTAddNode node = new ASTAddNode();
				node.left = visit(ctx.term());
				node.right = visit(ctx.sum());

				return node;
			}

			@Override
			public ASTBaseNode visitSubstraction(TinyCParser.SubstractionContext ctx) {
				ASTSubNode node = new ASTSubNode();
				node.left = visit(ctx.term());
				node.right = visit(ctx.sum());

				return node;
			}
			
			@Override
			public ASTBaseNode visitBare_id(TinyCParser.Bare_idContext ctx) {
				ASTTermNode node = new ASTTermNode();
				node.value = ctx.id().getText();
				
				return node;
			}
			
			@Override
			public ASTBaseNode visitBare_int(TinyCParser.Bare_intContext ctx) {
				ASTTermNode node = new ASTTermNode();
				node.value = ctx.integer().getText();
				
				return node;
			}
			
			@Override
			public ASTBaseNode visitParen_term(TinyCParser.Paren_termContext ctx) {
				ASTTermNode node = new ASTTermNode();
				node.expr= visit(ctx.paren_expr());
				
				return node;
			}
			
			@Override
			public ASTBaseNode visitWhile_statement(TinyCParser.While_statementContext ctx) {
				ASTWhileNode node = new ASTWhileNode();
				node.expr = visit(ctx.paren_expr());
				node.statement = visit(ctx.statement());
				
				return node;
			}
			
			@Override
			public ASTBaseNode visitDo_while_statement(TinyCParser.Do_while_statementContext ctx) {
				ASTDoNode node = new ASTDoNode();
				node.statement = visit(ctx.statement());
				node.expr = visit(ctx.paren_expr());
				
				return node;
			}
			
			@Override
			public ASTBaseNode visitBlock_statement(TinyCParser.Block_statementContext ctx) {
				ASTBlockNode node = new ASTBlockNode();
				
				for (var statement : ctx.statement()) {
					node.statements.add(visit(statement));
				}
				
				return node;
			}
			
			@Override
			public ASTBaseNode visitExpr_statement(TinyCParser.Expr_statementContext ctx) {
				return visit(ctx.expr());
			}
			
			@Override
			public ASTBaseNode visitNull_statement(TinyCParser.Null_statementContext ctx) {
				return new ASTNullNode();
			}
		};

		ASTBaseNode ast = visitor.visit(tree);
		ast.printTree(0);
	}
}

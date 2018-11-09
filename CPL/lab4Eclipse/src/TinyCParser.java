// Generated from TinyC.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TinyCParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, DECINTEGER=14, HEXINTEGER=15, BININTEGER=16, 
		STRING=17, WS=18;
	public static final int
		RULE_program = 0, RULE_statement = 1, RULE_paren_expr = 2, RULE_expr = 3, 
		RULE_test = 4, RULE_sum = 5, RULE_term = 6, RULE_id = 7, RULE_integer = 8, 
		RULE_decInteger = 9, RULE_hexInteger = 10, RULE_binInteger = 11;
	public static final String[] ruleNames = {
		"program", "statement", "paren_expr", "expr", "test", "sum", "term", "id", 
		"integer", "decInteger", "hexInteger", "binInteger"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'if'", "'else'", "'while'", "'do'", "';'", "'{'", "'}'", "'('", 
		"')'", "'='", "'<'", "'+'", "'-'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, "DECINTEGER", "HEXINTEGER", "BININTEGER", "STRING", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "TinyC.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TinyCParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyCVisitor ) return ((TinyCVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(24);
				statement();
				}
				}
				setState(27); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__7) | (1L << DECINTEGER) | (1L << HEXINTEGER) | (1L << BININTEGER) | (1L << STRING))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Do_while_statementContext extends StatementContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public Paren_exprContext paren_expr() {
			return getRuleContext(Paren_exprContext.class,0);
		}
		public Do_while_statementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).enterDo_while_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).exitDo_while_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyCVisitor ) return ((TinyCVisitor<? extends T>)visitor).visitDo_while_statement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Null_statementContext extends StatementContext {
		public Null_statementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).enterNull_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).exitNull_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyCVisitor ) return ((TinyCVisitor<? extends T>)visitor).visitNull_statement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Expr_statementContext extends StatementContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Expr_statementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).enterExpr_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).exitExpr_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyCVisitor ) return ((TinyCVisitor<? extends T>)visitor).visitExpr_statement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class If_then_statementContext extends StatementContext {
		public Paren_exprContext paren_expr() {
			return getRuleContext(Paren_exprContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public If_then_statementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).enterIf_then_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).exitIf_then_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyCVisitor ) return ((TinyCVisitor<? extends T>)visitor).visitIf_then_statement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class While_statementContext extends StatementContext {
		public Paren_exprContext paren_expr() {
			return getRuleContext(Paren_exprContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public While_statementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).enterWhile_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).exitWhile_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyCVisitor ) return ((TinyCVisitor<? extends T>)visitor).visitWhile_statement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Block_statementContext extends StatementContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public Block_statementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).enterBlock_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).exitBlock_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyCVisitor ) return ((TinyCVisitor<? extends T>)visitor).visitBlock_statement(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class If_then_else_statementContext extends StatementContext {
		public Paren_exprContext paren_expr() {
			return getRuleContext(Paren_exprContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public If_then_else_statementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).enterIf_then_else_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).exitIf_then_else_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyCVisitor ) return ((TinyCVisitor<? extends T>)visitor).visitIf_then_else_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		int _la;
		try {
			setState(61);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				_localctx = new If_then_statementContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(29);
				match(T__0);
				setState(30);
				paren_expr();
				setState(31);
				statement();
				}
				break;
			case 2:
				_localctx = new If_then_else_statementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(33);
				match(T__0);
				setState(34);
				paren_expr();
				setState(35);
				statement();
				setState(36);
				match(T__1);
				setState(37);
				statement();
				}
				break;
			case 3:
				_localctx = new While_statementContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(39);
				match(T__2);
				setState(40);
				paren_expr();
				setState(41);
				statement();
				}
				break;
			case 4:
				_localctx = new Do_while_statementContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(43);
				match(T__3);
				setState(44);
				statement();
				setState(45);
				match(T__2);
				setState(46);
				paren_expr();
				setState(47);
				match(T__4);
				}
				break;
			case 5:
				_localctx = new Block_statementContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(49);
				match(T__5);
				setState(53);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__7) | (1L << DECINTEGER) | (1L << HEXINTEGER) | (1L << BININTEGER) | (1L << STRING))) != 0)) {
					{
					{
					setState(50);
					statement();
					}
					}
					setState(55);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(56);
				match(T__6);
				}
				break;
			case 6:
				_localctx = new Expr_statementContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(57);
				expr();
				setState(58);
				match(T__4);
				}
				break;
			case 7:
				_localctx = new Null_statementContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(60);
				match(T__4);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Paren_exprContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Paren_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paren_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).enterParen_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).exitParen_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyCVisitor ) return ((TinyCVisitor<? extends T>)visitor).visitParen_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Paren_exprContext paren_expr() throws RecognitionException {
		Paren_exprContext _localctx = new Paren_exprContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_paren_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			match(T__7);
			setState(64);
			expr();
			setState(65);
			match(T__8);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Bare_testContext extends ExprContext {
		public TestContext test() {
			return getRuleContext(TestContext.class,0);
		}
		public Bare_testContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).enterBare_test(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).exitBare_test(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyCVisitor ) return ((TinyCVisitor<? extends T>)visitor).visitBare_test(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Attr_exprContext extends ExprContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Attr_exprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).enterAttr_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).exitAttr_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyCVisitor ) return ((TinyCVisitor<? extends T>)visitor).visitAttr_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_expr);
		try {
			setState(72);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				_localctx = new Bare_testContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(67);
				test();
				}
				break;
			case 2:
				_localctx = new Attr_exprContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(68);
				id();
				setState(69);
				match(T__9);
				setState(70);
				expr();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TestContext extends ParserRuleContext {
		public TestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_test; }
	 
		public TestContext() { }
		public void copyFrom(TestContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Comp_testContext extends TestContext {
		public List<SumContext> sum() {
			return getRuleContexts(SumContext.class);
		}
		public SumContext sum(int i) {
			return getRuleContext(SumContext.class,i);
		}
		public Comp_testContext(TestContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).enterComp_test(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).exitComp_test(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyCVisitor ) return ((TinyCVisitor<? extends T>)visitor).visitComp_test(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Bare_sumContext extends TestContext {
		public SumContext sum() {
			return getRuleContext(SumContext.class,0);
		}
		public Bare_sumContext(TestContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).enterBare_sum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).exitBare_sum(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyCVisitor ) return ((TinyCVisitor<? extends T>)visitor).visitBare_sum(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TestContext test() throws RecognitionException {
		TestContext _localctx = new TestContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_test);
		try {
			setState(79);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				_localctx = new Bare_sumContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(74);
				sum();
				}
				break;
			case 2:
				_localctx = new Comp_testContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(75);
				sum();
				setState(76);
				match(T__10);
				setState(77);
				sum();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SumContext extends ParserRuleContext {
		public SumContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sum; }
	 
		public SumContext() { }
		public void copyFrom(SumContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SubstractionContext extends SumContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public SumContext sum() {
			return getRuleContext(SumContext.class,0);
		}
		public SubstractionContext(SumContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).enterSubstraction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).exitSubstraction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyCVisitor ) return ((TinyCVisitor<? extends T>)visitor).visitSubstraction(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Bare_termContext extends SumContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public Bare_termContext(SumContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).enterBare_term(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).exitBare_term(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyCVisitor ) return ((TinyCVisitor<? extends T>)visitor).visitBare_term(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AdditionContext extends SumContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public SumContext sum() {
			return getRuleContext(SumContext.class,0);
		}
		public AdditionContext(SumContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).enterAddition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).exitAddition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyCVisitor ) return ((TinyCVisitor<? extends T>)visitor).visitAddition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SumContext sum() throws RecognitionException {
		SumContext _localctx = new SumContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_sum);
		try {
			setState(90);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				_localctx = new Bare_termContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(81);
				term();
				}
				break;
			case 2:
				_localctx = new AdditionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(82);
				term();
				setState(83);
				match(T__11);
				setState(84);
				sum();
				}
				break;
			case 3:
				_localctx = new SubstractionContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(86);
				term();
				setState(87);
				match(T__12);
				setState(88);
				sum();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
	 
		public TermContext() { }
		public void copyFrom(TermContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Paren_termContext extends TermContext {
		public Paren_exprContext paren_expr() {
			return getRuleContext(Paren_exprContext.class,0);
		}
		public Paren_termContext(TermContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).enterParen_term(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).exitParen_term(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyCVisitor ) return ((TinyCVisitor<? extends T>)visitor).visitParen_term(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Bare_idContext extends TermContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public Bare_idContext(TermContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).enterBare_id(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).exitBare_id(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyCVisitor ) return ((TinyCVisitor<? extends T>)visitor).visitBare_id(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Bare_intContext extends TermContext {
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
		}
		public Bare_intContext(TermContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).enterBare_int(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).exitBare_int(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyCVisitor ) return ((TinyCVisitor<? extends T>)visitor).visitBare_int(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_term);
		try {
			setState(95);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				_localctx = new Bare_idContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(92);
				id();
				}
				break;
			case DECINTEGER:
			case HEXINTEGER:
			case BININTEGER:
				_localctx = new Bare_intContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(93);
				integer();
				}
				break;
			case T__7:
				_localctx = new Paren_termContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(94);
				paren_expr();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(TinyCParser.STRING, 0); }
		public IdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).exitId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyCVisitor ) return ((TinyCVisitor<? extends T>)visitor).visitId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdContext id() throws RecognitionException {
		IdContext _localctx = new IdContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_id);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntegerContext extends ParserRuleContext {
		public DecIntegerContext decInteger() {
			return getRuleContext(DecIntegerContext.class,0);
		}
		public HexIntegerContext hexInteger() {
			return getRuleContext(HexIntegerContext.class,0);
		}
		public BinIntegerContext binInteger() {
			return getRuleContext(BinIntegerContext.class,0);
		}
		public IntegerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).enterInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).exitInteger(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyCVisitor ) return ((TinyCVisitor<? extends T>)visitor).visitInteger(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntegerContext integer() throws RecognitionException {
		IntegerContext _localctx = new IntegerContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_integer);
		try {
			setState(102);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DECINTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(99);
				decInteger();
				}
				break;
			case HEXINTEGER:
				enterOuterAlt(_localctx, 2);
				{
				setState(100);
				hexInteger();
				}
				break;
			case BININTEGER:
				enterOuterAlt(_localctx, 3);
				{
				setState(101);
				binInteger();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DecIntegerContext extends ParserRuleContext {
		public TerminalNode DECINTEGER() { return getToken(TinyCParser.DECINTEGER, 0); }
		public DecIntegerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decInteger; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).enterDecInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).exitDecInteger(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyCVisitor ) return ((TinyCVisitor<? extends T>)visitor).visitDecInteger(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DecIntegerContext decInteger() throws RecognitionException {
		DecIntegerContext _localctx = new DecIntegerContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_decInteger);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(DECINTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HexIntegerContext extends ParserRuleContext {
		public TerminalNode HEXINTEGER() { return getToken(TinyCParser.HEXINTEGER, 0); }
		public HexIntegerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hexInteger; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).enterHexInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).exitHexInteger(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyCVisitor ) return ((TinyCVisitor<? extends T>)visitor).visitHexInteger(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HexIntegerContext hexInteger() throws RecognitionException {
		HexIntegerContext _localctx = new HexIntegerContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_hexInteger);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			match(HEXINTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BinIntegerContext extends ParserRuleContext {
		public TerminalNode BININTEGER() { return getToken(TinyCParser.BININTEGER, 0); }
		public BinIntegerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binInteger; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).enterBinInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TinyCListener ) ((TinyCListener)listener).exitBinInteger(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TinyCVisitor ) return ((TinyCVisitor<? extends T>)visitor).visitBinInteger(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinIntegerContext binInteger() throws RecognitionException {
		BinIntegerContext _localctx = new BinIntegerContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_binInteger);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			match(BININTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\24q\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\3\2\6\2\34\n\2\r\2\16\2\35\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3\66\n"+
		"\3\f\3\16\39\13\3\3\3\3\3\3\3\3\3\3\3\5\3@\n\3\3\4\3\4\3\4\3\4\3\5\3\5"+
		"\3\5\3\5\3\5\5\5K\n\5\3\6\3\6\3\6\3\6\3\6\5\6R\n\6\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\5\7]\n\7\3\b\3\b\3\b\5\bb\n\b\3\t\3\t\3\n\3\n\3\n\5\n"+
		"i\n\n\3\13\3\13\3\f\3\f\3\r\3\r\3\r\2\2\16\2\4\6\b\n\f\16\20\22\24\26"+
		"\30\2\2\2t\2\33\3\2\2\2\4?\3\2\2\2\6A\3\2\2\2\bJ\3\2\2\2\nQ\3\2\2\2\f"+
		"\\\3\2\2\2\16a\3\2\2\2\20c\3\2\2\2\22h\3\2\2\2\24j\3\2\2\2\26l\3\2\2\2"+
		"\30n\3\2\2\2\32\34\5\4\3\2\33\32\3\2\2\2\34\35\3\2\2\2\35\33\3\2\2\2\35"+
		"\36\3\2\2\2\36\3\3\2\2\2\37 \7\3\2\2 !\5\6\4\2!\"\5\4\3\2\"@\3\2\2\2#"+
		"$\7\3\2\2$%\5\6\4\2%&\5\4\3\2&\'\7\4\2\2\'(\5\4\3\2(@\3\2\2\2)*\7\5\2"+
		"\2*+\5\6\4\2+,\5\4\3\2,@\3\2\2\2-.\7\6\2\2./\5\4\3\2/\60\7\5\2\2\60\61"+
		"\5\6\4\2\61\62\7\7\2\2\62@\3\2\2\2\63\67\7\b\2\2\64\66\5\4\3\2\65\64\3"+
		"\2\2\2\669\3\2\2\2\67\65\3\2\2\2\678\3\2\2\28:\3\2\2\29\67\3\2\2\2:@\7"+
		"\t\2\2;<\5\b\5\2<=\7\7\2\2=@\3\2\2\2>@\7\7\2\2?\37\3\2\2\2?#\3\2\2\2?"+
		")\3\2\2\2?-\3\2\2\2?\63\3\2\2\2?;\3\2\2\2?>\3\2\2\2@\5\3\2\2\2AB\7\n\2"+
		"\2BC\5\b\5\2CD\7\13\2\2D\7\3\2\2\2EK\5\n\6\2FG\5\20\t\2GH\7\f\2\2HI\5"+
		"\b\5\2IK\3\2\2\2JE\3\2\2\2JF\3\2\2\2K\t\3\2\2\2LR\5\f\7\2MN\5\f\7\2NO"+
		"\7\r\2\2OP\5\f\7\2PR\3\2\2\2QL\3\2\2\2QM\3\2\2\2R\13\3\2\2\2S]\5\16\b"+
		"\2TU\5\16\b\2UV\7\16\2\2VW\5\f\7\2W]\3\2\2\2XY\5\16\b\2YZ\7\17\2\2Z[\5"+
		"\f\7\2[]\3\2\2\2\\S\3\2\2\2\\T\3\2\2\2\\X\3\2\2\2]\r\3\2\2\2^b\5\20\t"+
		"\2_b\5\22\n\2`b\5\6\4\2a^\3\2\2\2a_\3\2\2\2a`\3\2\2\2b\17\3\2\2\2cd\7"+
		"\23\2\2d\21\3\2\2\2ei\5\24\13\2fi\5\26\f\2gi\5\30\r\2he\3\2\2\2hf\3\2"+
		"\2\2hg\3\2\2\2i\23\3\2\2\2jk\7\20\2\2k\25\3\2\2\2lm\7\21\2\2m\27\3\2\2"+
		"\2no\7\22\2\2o\31\3\2\2\2\n\35\67?JQ\\ah";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
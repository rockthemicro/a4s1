// Generated from CoolParser.g4 by ANTLR 4.7.1

    package cool.parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CoolParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ERROR=1, WS=2, SG_LINE_COMM=3, UNMATCHED_COMM=4, EOI=5, DBL_DOT=6, COMMA=7, 
		DOT=8, AT=9, OPEN_AC=10, CLOSED_AC=11, OPEN_PAR=12, CLOSED_PAR=13, NEG=14, 
		PLUS=15, SUB=16, MUL=17, DIV=18, ASSIGN=19, LESS_OR_EQ=20, TO=21, LESS=22, 
		EQ=23, INHERITS=24, SELF=25, ISVOID=26, NOT=27, CLASS=28, NEW=29, IF=30, 
		THEN=31, ELSE=32, FI=33, WHILE=34, LOOP=35, POOL=36, LET=37, IN=38, CASE=39, 
		OF=40, ESAC=41, BOOLEAN=42, CLASS_NAME=43, ID=44, NR=45, PROBLEMATIC_CHAR=46, 
		ERROR_STRING=47, STRING=48, NULL_CHAR=49, NON_ESCAPED_NEWLINE=50, UNEXPETED_END=51, 
		REST_OF_STRING=52, ERROR_MULTICOMM=53, MLT_LINE_COMM=54, ESC=55;
	public static final int
		RULE_type = 0, RULE_constant = 1, RULE_let_bind = 2, RULE_f_call_args = 3, 
		RULE_f_call = 4, RULE_expr = 5, RULE_attribute = 6, RULE_method_params = 7, 
		RULE_method_body = 8, RULE_method = 9, RULE_attr_or_meth = 10, RULE_class_body = 11, 
		RULE_class_header = 12, RULE_class_node = 13, RULE_program = 14;
	public static final String[] ruleNames = {
		"type", "constant", "let_bind", "f_call_args", "f_call", "expr", "attribute", 
		"method_params", "method_body", "method", "attr_or_meth", "class_body", 
		"class_header", "class_node", "program"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, "';'", "':'", "','", "'.'", "'@'", "'{'", 
		"'}'", "'('", "')'", "'~'", "'+'", "'-'", "'*'", "'/'", "'<-'", "'<='", 
		"'=>'", "'<'", "'='", "'inherits'", "'SELF_TYPE'", "'isvoid'", "'not'", 
		"'class'", "'new'", "'if'", "'then'", "'else'", "'fi'", "'while'", "'loop'", 
		"'pool'", "'let'", "'in'", "'case'", "'of'", "'esac'", null, null, null, 
		null, null, null, null, null, null, null, null, null, null, "'\\'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "ERROR", "WS", "SG_LINE_COMM", "UNMATCHED_COMM", "EOI", "DBL_DOT", 
		"COMMA", "DOT", "AT", "OPEN_AC", "CLOSED_AC", "OPEN_PAR", "CLOSED_PAR", 
		"NEG", "PLUS", "SUB", "MUL", "DIV", "ASSIGN", "LESS_OR_EQ", "TO", "LESS", 
		"EQ", "INHERITS", "SELF", "ISVOID", "NOT", "CLASS", "NEW", "IF", "THEN", 
		"ELSE", "FI", "WHILE", "LOOP", "POOL", "LET", "IN", "CASE", "OF", "ESAC", 
		"BOOLEAN", "CLASS_NAME", "ID", "NR", "PROBLEMATIC_CHAR", "ERROR_STRING", 
		"STRING", "NULL_CHAR", "NON_ESCAPED_NEWLINE", "UNEXPETED_END", "REST_OF_STRING", 
		"ERROR_MULTICOMM", "MLT_LINE_COMM", "ESC"
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
	public String getGrammarFileName() { return "CoolParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CoolParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class TypeContext extends ParserRuleContext {
		public TerminalNode CLASS_NAME() { return getToken(CoolParser.CLASS_NAME, 0); }
		public TerminalNode SELF() { return getToken(CoolParser.SELF, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			_la = _input.LA(1);
			if ( !(_la==SELF || _la==CLASS_NAME) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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

	public static class ConstantContext extends ParserRuleContext {
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
	 
		public ConstantContext() { }
		public void copyFrom(ConstantContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BooleanContext extends ConstantContext {
		public TerminalNode BOOLEAN() { return getToken(CoolParser.BOOLEAN, 0); }
		public BooleanContext(ConstantContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterBoolean(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitBoolean(this);
		}
	}
	public static class NrContext extends ConstantContext {
		public TerminalNode NR() { return getToken(CoolParser.NR, 0); }
		public NrContext(ConstantContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterNr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitNr(this);
		}
	}
	public static class StringContext extends ConstantContext {
		public TerminalNode STRING() { return getToken(CoolParser.STRING, 0); }
		public StringContext(ConstantContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitString(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_constant);
		try {
			setState(35);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NR:
				_localctx = new NrContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(32);
				match(NR);
				}
				break;
			case BOOLEAN:
				_localctx = new BooleanContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(33);
				match(BOOLEAN);
				}
				break;
			case STRING:
				_localctx = new StringContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(34);
				match(STRING);
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

	public static class Let_bindContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public TerminalNode DBL_DOT() { return getToken(CoolParser.DBL_DOT, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(CoolParser.ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Let_bindContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_let_bind; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterLet_bind(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitLet_bind(this);
		}
	}

	public final Let_bindContext let_bind() throws RecognitionException {
		Let_bindContext _localctx = new Let_bindContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_let_bind);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37);
			match(ID);
			setState(38);
			match(DBL_DOT);
			setState(39);
			type();
			setState(42);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(40);
				match(ASSIGN);
				setState(41);
				expr(0);
				}
			}

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

	public static class F_call_argsContext extends ParserRuleContext {
		public F_call_argsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_f_call_args; }
	 
		public F_call_argsContext() { }
		public void copyFrom(F_call_argsContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Sg_arg_functionContext extends F_call_argsContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Sg_arg_functionContext(F_call_argsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterSg_arg_function(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitSg_arg_function(this);
		}
	}
	public static class Mlt_arg_functionContext extends F_call_argsContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CoolParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CoolParser.COMMA, i);
		}
		public Mlt_arg_functionContext(F_call_argsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterMlt_arg_function(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitMlt_arg_function(this);
		}
	}

	public final F_call_argsContext f_call_args() throws RecognitionException {
		F_call_argsContext _localctx = new F_call_argsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_f_call_args);
		int _la;
		try {
			setState(52);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				_localctx = new Sg_arg_functionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(44);
				expr(0);
				}
				break;
			case 2:
				_localctx = new Mlt_arg_functionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(45);
				expr(0);
				setState(48); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(46);
					match(COMMA);
					setState(47);
					expr(0);
					}
					}
					setState(50); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==COMMA );
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

	public static class F_callContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public TerminalNode OPEN_PAR() { return getToken(CoolParser.OPEN_PAR, 0); }
		public TerminalNode CLOSED_PAR() { return getToken(CoolParser.CLOSED_PAR, 0); }
		public F_call_argsContext f_call_args() {
			return getRuleContext(F_call_argsContext.class,0);
		}
		public F_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_f_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterF_call(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitF_call(this);
		}
	}

	public final F_callContext f_call() throws RecognitionException {
		F_callContext _localctx = new F_callContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_f_call);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			match(ID);
			setState(55);
			match(OPEN_PAR);
			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_AC) | (1L << OPEN_PAR) | (1L << NEG) | (1L << ISVOID) | (1L << NOT) | (1L << NEW) | (1L << IF) | (1L << WHILE) | (1L << LET) | (1L << CASE) | (1L << BOOLEAN) | (1L << ID) | (1L << NR) | (1L << STRING))) != 0)) {
				{
				setState(56);
				f_call_args();
				}
			}

			setState(59);
			match(CLOSED_PAR);
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
	public static class Expr_compContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode LESS() { return getToken(CoolParser.LESS, 0); }
		public TerminalNode LESS_OR_EQ() { return getToken(CoolParser.LESS_OR_EQ, 0); }
		public TerminalNode EQ() { return getToken(CoolParser.EQ, 0); }
		public Expr_compContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterExpr_comp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitExpr_comp(this);
		}
	}
	public static class Expr_caseContext extends ExprContext {
		public TerminalNode CASE() { return getToken(CoolParser.CASE, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OF() { return getToken(CoolParser.OF, 0); }
		public TerminalNode ESAC() { return getToken(CoolParser.ESAC, 0); }
		public List<TerminalNode> ID() { return getTokens(CoolParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(CoolParser.ID, i);
		}
		public List<TerminalNode> DBL_DOT() { return getTokens(CoolParser.DBL_DOT); }
		public TerminalNode DBL_DOT(int i) {
			return getToken(CoolParser.DBL_DOT, i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> TO() { return getTokens(CoolParser.TO); }
		public TerminalNode TO(int i) {
			return getToken(CoolParser.TO, i);
		}
		public List<TerminalNode> EOI() { return getTokens(CoolParser.EOI); }
		public TerminalNode EOI(int i) {
			return getToken(CoolParser.EOI, i);
		}
		public Expr_caseContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterExpr_case(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitExpr_case(this);
		}
	}
	public static class Expr_constContext extends ExprContext {
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public Expr_constContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterExpr_const(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitExpr_const(this);
		}
	}
	public static class Expr_newContext extends ExprContext {
		public TerminalNode NEW() { return getToken(CoolParser.NEW, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public Expr_newContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterExpr_new(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitExpr_new(this);
		}
	}
	public static class Expr_fcallContext extends ExprContext {
		public F_callContext f_call() {
			return getRuleContext(F_callContext.class,0);
		}
		public Expr_fcallContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterExpr_fcall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitExpr_fcall(this);
		}
	}
	public static class Expr_letContext extends ExprContext {
		public TerminalNode LET() { return getToken(CoolParser.LET, 0); }
		public List<Let_bindContext> let_bind() {
			return getRuleContexts(Let_bindContext.class);
		}
		public Let_bindContext let_bind(int i) {
			return getRuleContext(Let_bindContext.class,i);
		}
		public TerminalNode IN() { return getToken(CoolParser.IN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(CoolParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CoolParser.COMMA, i);
		}
		public Expr_letContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterExpr_let(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitExpr_let(this);
		}
	}
	public static class Expr_muldivContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode MUL() { return getToken(CoolParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(CoolParser.DIV, 0); }
		public Expr_muldivContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterExpr_muldiv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitExpr_muldiv(this);
		}
	}
	public static class Expr_assignContext extends ExprContext {
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public TerminalNode ASSIGN() { return getToken(CoolParser.ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Expr_assignContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterExpr_assign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitExpr_assign(this);
		}
	}
	public static class Expr_paranContext extends ExprContext {
		public TerminalNode OPEN_PAR() { return getToken(CoolParser.OPEN_PAR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode CLOSED_PAR() { return getToken(CoolParser.CLOSED_PAR, 0); }
		public Expr_paranContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterExpr_paran(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitExpr_paran(this);
		}
	}
	public static class Expr_isvoidContext extends ExprContext {
		public TerminalNode ISVOID() { return getToken(CoolParser.ISVOID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Expr_isvoidContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterExpr_isvoid(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitExpr_isvoid(this);
		}
	}
	public static class Expr_expr_fcallContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode DOT() { return getToken(CoolParser.DOT, 0); }
		public F_callContext f_call() {
			return getRuleContext(F_callContext.class,0);
		}
		public TerminalNode AT() { return getToken(CoolParser.AT, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public Expr_expr_fcallContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterExpr_expr_fcall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitExpr_expr_fcall(this);
		}
	}
	public static class Expr_negContext extends ExprContext {
		public TerminalNode NEG() { return getToken(CoolParser.NEG, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Expr_negContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterExpr_neg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitExpr_neg(this);
		}
	}
	public static class Expr_whileContext extends ExprContext {
		public TerminalNode WHILE() { return getToken(CoolParser.WHILE, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode LOOP() { return getToken(CoolParser.LOOP, 0); }
		public TerminalNode POOL() { return getToken(CoolParser.POOL, 0); }
		public Expr_whileContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterExpr_while(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitExpr_while(this);
		}
	}
	public static class Expr_acoladesContext extends ExprContext {
		public TerminalNode OPEN_AC() { return getToken(CoolParser.OPEN_AC, 0); }
		public TerminalNode CLOSED_AC() { return getToken(CoolParser.CLOSED_AC, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> EOI() { return getTokens(CoolParser.EOI); }
		public TerminalNode EOI(int i) {
			return getToken(CoolParser.EOI, i);
		}
		public Expr_acoladesContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterExpr_acolades(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitExpr_acolades(this);
		}
	}
	public static class Expr_plussubContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(CoolParser.PLUS, 0); }
		public TerminalNode SUB() { return getToken(CoolParser.SUB, 0); }
		public Expr_plussubContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterExpr_plussub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitExpr_plussub(this);
		}
	}
	public static class Expr_ifContext extends ExprContext {
		public TerminalNode IF() { return getToken(CoolParser.IF, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode THEN() { return getToken(CoolParser.THEN, 0); }
		public TerminalNode ELSE() { return getToken(CoolParser.ELSE, 0); }
		public TerminalNode FI() { return getToken(CoolParser.FI, 0); }
		public Expr_ifContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterExpr_if(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitExpr_if(this);
		}
	}
	public static class Expr_notContext extends ExprContext {
		public TerminalNode NOT() { return getToken(CoolParser.NOT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Expr_notContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterExpr_not(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitExpr_not(this);
		}
	}
	public static class Expr_idContext extends ExprContext {
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public Expr_idContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterExpr_id(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitExpr_id(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				_localctx = new Expr_fcallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(62);
				f_call();
				}
				break;
			case 2:
				{
				_localctx = new Expr_acoladesContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(63);
				match(OPEN_AC);
				setState(67); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(64);
					expr(0);
					setState(65);
					match(EOI);
					}
					}
					setState(69); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_AC) | (1L << OPEN_PAR) | (1L << NEG) | (1L << ISVOID) | (1L << NOT) | (1L << NEW) | (1L << IF) | (1L << WHILE) | (1L << LET) | (1L << CASE) | (1L << BOOLEAN) | (1L << ID) | (1L << NR) | (1L << STRING))) != 0) );
				setState(71);
				match(CLOSED_AC);
				}
				break;
			case 3:
				{
				_localctx = new Expr_ifContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(73);
				match(IF);
				setState(74);
				expr(0);
				setState(75);
				match(THEN);
				setState(76);
				expr(0);
				setState(77);
				match(ELSE);
				setState(78);
				expr(0);
				setState(79);
				match(FI);
				}
				break;
			case 4:
				{
				_localctx = new Expr_whileContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(81);
				match(WHILE);
				setState(82);
				expr(0);
				setState(83);
				match(LOOP);
				setState(84);
				expr(0);
				setState(85);
				match(POOL);
				}
				break;
			case 5:
				{
				_localctx = new Expr_letContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(87);
				match(LET);
				setState(88);
				let_bind();
				setState(93);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(89);
					match(COMMA);
					setState(90);
					let_bind();
					}
					}
					setState(95);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(96);
				match(IN);
				setState(97);
				expr(13);
				}
				break;
			case 6:
				{
				_localctx = new Expr_caseContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(99);
				match(CASE);
				setState(100);
				expr(0);
				setState(101);
				match(OF);
				setState(109); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(102);
					match(ID);
					setState(103);
					match(DBL_DOT);
					setState(104);
					type();
					setState(105);
					match(TO);
					setState(106);
					expr(0);
					setState(107);
					match(EOI);
					}
					}
					setState(111); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==ID );
				setState(113);
				match(ESAC);
				}
				break;
			case 7:
				{
				_localctx = new Expr_paranContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(115);
				match(OPEN_PAR);
				setState(116);
				expr(0);
				setState(117);
				match(CLOSED_PAR);
				}
				break;
			case 8:
				{
				_localctx = new Expr_idContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(119);
				match(ID);
				}
				break;
			case 9:
				{
				_localctx = new Expr_constContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(120);
				constant();
				}
				break;
			case 10:
				{
				_localctx = new Expr_negContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(121);
				match(NEG);
				setState(122);
				expr(8);
				}
				break;
			case 11:
				{
				_localctx = new Expr_isvoidContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(123);
				match(ISVOID);
				setState(124);
				expr(7);
				}
				break;
			case 12:
				{
				_localctx = new Expr_notContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(125);
				match(NOT);
				setState(126);
				expr(3);
				}
				break;
			case 13:
				{
				_localctx = new Expr_assignContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(127);
				match(ID);
				setState(128);
				match(ASSIGN);
				setState(129);
				expr(2);
				}
				break;
			case 14:
				{
				_localctx = new Expr_newContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(130);
				match(NEW);
				setState(131);
				type();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(152);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(150);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
					case 1:
						{
						_localctx = new Expr_muldivContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(134);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(135);
						((Expr_muldivContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==MUL || _la==DIV) ) {
							((Expr_muldivContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(136);
						expr(7);
						}
						break;
					case 2:
						{
						_localctx = new Expr_plussubContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(137);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(138);
						((Expr_plussubContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==SUB) ) {
							((Expr_plussubContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(139);
						expr(6);
						}
						break;
					case 3:
						{
						_localctx = new Expr_compContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(140);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(141);
						((Expr_compContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LESS_OR_EQ) | (1L << LESS) | (1L << EQ))) != 0)) ) {
							((Expr_compContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(142);
						expr(5);
						}
						break;
					case 4:
						{
						_localctx = new Expr_expr_fcallContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(143);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(146);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==AT) {
							{
							setState(144);
							match(AT);
							setState(145);
							type();
							}
						}

						setState(148);
						match(DOT);
						setState(149);
						f_call();
						}
						break;
					}
					} 
				}
				setState(154);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AttributeContext extends ParserRuleContext {
		public AttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute; }
	 
		public AttributeContext() { }
		public void copyFrom(AttributeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Attr_no_asgnContext extends AttributeContext {
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public TerminalNode DBL_DOT() { return getToken(CoolParser.DBL_DOT, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public Attr_no_asgnContext(AttributeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterAttr_no_asgn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitAttr_no_asgn(this);
		}
	}
	public static class Attr_asgnContext extends AttributeContext {
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public TerminalNode DBL_DOT() { return getToken(CoolParser.DBL_DOT, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(CoolParser.ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Attr_asgnContext(AttributeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterAttr_asgn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitAttr_asgn(this);
		}
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_attribute);
		try {
			setState(164);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				_localctx = new Attr_asgnContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(155);
				match(ID);
				setState(156);
				match(DBL_DOT);
				setState(157);
				type();
				setState(158);
				match(ASSIGN);
				setState(159);
				expr(0);
				}
				break;
			case 2:
				_localctx = new Attr_no_asgnContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(161);
				match(ID);
				setState(162);
				match(DBL_DOT);
				setState(163);
				type();
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

	public static class Method_paramsContext extends ParserRuleContext {
		public Method_paramsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_method_params; }
	 
		public Method_paramsContext() { }
		public void copyFrom(Method_paramsContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Empty_m_argsContext extends Method_paramsContext {
		public Empty_m_argsContext(Method_paramsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterEmpty_m_args(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitEmpty_m_args(this);
		}
	}
	public static class M_argsContext extends Method_paramsContext {
		public List<TerminalNode> ID() { return getTokens(CoolParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(CoolParser.ID, i);
		}
		public List<TerminalNode> DBL_DOT() { return getTokens(CoolParser.DBL_DOT); }
		public TerminalNode DBL_DOT(int i) {
			return getToken(CoolParser.DBL_DOT, i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CoolParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CoolParser.COMMA, i);
		}
		public M_argsContext(Method_paramsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterM_args(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitM_args(this);
		}
	}

	public final Method_paramsContext method_params() throws RecognitionException {
		Method_paramsContext _localctx = new Method_paramsContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_method_params);
		int _la;
		try {
			setState(179);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				_localctx = new M_argsContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(166);
				match(ID);
				setState(167);
				match(DBL_DOT);
				setState(168);
				type();
				setState(175);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(169);
					match(COMMA);
					setState(170);
					match(ID);
					setState(171);
					match(DBL_DOT);
					setState(172);
					type();
					}
					}
					setState(177);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case CLOSED_PAR:
				_localctx = new Empty_m_argsContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
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

	public static class Method_bodyContext extends ParserRuleContext {
		public Method_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_method_body; }
	 
		public Method_bodyContext() { }
		public void copyFrom(Method_bodyContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Sg_expr_methContext extends Method_bodyContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Sg_expr_methContext(Method_bodyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterSg_expr_meth(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitSg_expr_meth(this);
		}
	}
	public static class Mlt_expr_methContext extends Method_bodyContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> EOI() { return getTokens(CoolParser.EOI); }
		public TerminalNode EOI(int i) {
			return getToken(CoolParser.EOI, i);
		}
		public Mlt_expr_methContext(Method_bodyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterMlt_expr_meth(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitMlt_expr_meth(this);
		}
	}

	public final Method_bodyContext method_body() throws RecognitionException {
		Method_bodyContext _localctx = new Method_bodyContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_method_body);
		int _la;
		try {
			setState(190);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				_localctx = new Sg_expr_methContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(181);
				expr(0);
				}
				break;
			case 2:
				_localctx = new Mlt_expr_methContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(187);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_AC) | (1L << OPEN_PAR) | (1L << NEG) | (1L << ISVOID) | (1L << NOT) | (1L << NEW) | (1L << IF) | (1L << WHILE) | (1L << LET) | (1L << CASE) | (1L << BOOLEAN) | (1L << ID) | (1L << NR) | (1L << STRING))) != 0)) {
					{
					{
					setState(182);
					expr(0);
					setState(183);
					match(EOI);
					}
					}
					setState(189);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
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

	public static class MethodContext extends ParserRuleContext {
		public Token ret;
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public TerminalNode OPEN_PAR() { return getToken(CoolParser.OPEN_PAR, 0); }
		public Method_paramsContext method_params() {
			return getRuleContext(Method_paramsContext.class,0);
		}
		public TerminalNode CLOSED_PAR() { return getToken(CoolParser.CLOSED_PAR, 0); }
		public TerminalNode DBL_DOT() { return getToken(CoolParser.DBL_DOT, 0); }
		public TerminalNode OPEN_AC() { return getToken(CoolParser.OPEN_AC, 0); }
		public Method_bodyContext method_body() {
			return getRuleContext(Method_bodyContext.class,0);
		}
		public TerminalNode CLOSED_AC() { return getToken(CoolParser.CLOSED_AC, 0); }
		public TerminalNode CLASS_NAME() { return getToken(CoolParser.CLASS_NAME, 0); }
		public TerminalNode SELF() { return getToken(CoolParser.SELF, 0); }
		public MethodContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_method; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterMethod(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitMethod(this);
		}
	}

	public final MethodContext method() throws RecognitionException {
		MethodContext _localctx = new MethodContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_method);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			match(ID);
			setState(193);
			match(OPEN_PAR);
			setState(194);
			method_params();
			setState(195);
			match(CLOSED_PAR);
			setState(196);
			match(DBL_DOT);
			setState(197);
			((MethodContext)_localctx).ret = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==SELF || _la==CLASS_NAME) ) {
				((MethodContext)_localctx).ret = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(198);
			match(OPEN_AC);
			setState(199);
			method_body();
			setState(200);
			match(CLOSED_AC);
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

	public static class Attr_or_methContext extends ParserRuleContext {
		public Attr_or_methContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attr_or_meth; }
	 
		public Attr_or_methContext() { }
		public void copyFrom(Attr_or_methContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AttrContext extends Attr_or_methContext {
		public AttributeContext attribute() {
			return getRuleContext(AttributeContext.class,0);
		}
		public AttrContext(Attr_or_methContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterAttr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitAttr(this);
		}
	}
	public static class MethContext extends Attr_or_methContext {
		public MethodContext method() {
			return getRuleContext(MethodContext.class,0);
		}
		public MethContext(Attr_or_methContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterMeth(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitMeth(this);
		}
	}

	public final Attr_or_methContext attr_or_meth() throws RecognitionException {
		Attr_or_methContext _localctx = new Attr_or_methContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_attr_or_meth);
		try {
			setState(204);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				_localctx = new AttrContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(202);
				attribute();
				}
				break;
			case 2:
				_localctx = new MethContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(203);
				method();
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

	public static class Class_bodyContext extends ParserRuleContext {
		public List<Attr_or_methContext> attr_or_meth() {
			return getRuleContexts(Attr_or_methContext.class);
		}
		public Attr_or_methContext attr_or_meth(int i) {
			return getRuleContext(Attr_or_methContext.class,i);
		}
		public List<TerminalNode> EOI() { return getTokens(CoolParser.EOI); }
		public TerminalNode EOI(int i) {
			return getToken(CoolParser.EOI, i);
		}
		public Class_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterClass_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitClass_body(this);
		}
	}

	public final Class_bodyContext class_body() throws RecognitionException {
		Class_bodyContext _localctx = new Class_bodyContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_class_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(206);
				attr_or_meth();
				setState(207);
				match(EOI);
				}
				}
				setState(213);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class Class_headerContext extends ParserRuleContext {
		public Class_headerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_header; }
	 
		public Class_headerContext() { }
		public void copyFrom(Class_headerContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Inheriter_classContext extends Class_headerContext {
		public TerminalNode CLASS() { return getToken(CoolParser.CLASS, 0); }
		public List<TerminalNode> CLASS_NAME() { return getTokens(CoolParser.CLASS_NAME); }
		public TerminalNode CLASS_NAME(int i) {
			return getToken(CoolParser.CLASS_NAME, i);
		}
		public TerminalNode INHERITS() { return getToken(CoolParser.INHERITS, 0); }
		public Inheriter_classContext(Class_headerContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterInheriter_class(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitInheriter_class(this);
		}
	}
	public static class Simple_classContext extends Class_headerContext {
		public TerminalNode CLASS() { return getToken(CoolParser.CLASS, 0); }
		public TerminalNode CLASS_NAME() { return getToken(CoolParser.CLASS_NAME, 0); }
		public Simple_classContext(Class_headerContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterSimple_class(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitSimple_class(this);
		}
	}

	public final Class_headerContext class_header() throws RecognitionException {
		Class_headerContext _localctx = new Class_headerContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_class_header);
		try {
			setState(220);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				_localctx = new Inheriter_classContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(214);
				match(CLASS);
				setState(215);
				match(CLASS_NAME);
				setState(216);
				match(INHERITS);
				setState(217);
				match(CLASS_NAME);
				}
				break;
			case 2:
				_localctx = new Simple_classContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(218);
				match(CLASS);
				setState(219);
				match(CLASS_NAME);
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

	public static class Class_nodeContext extends ParserRuleContext {
		public Class_headerContext class_header() {
			return getRuleContext(Class_headerContext.class,0);
		}
		public TerminalNode OPEN_AC() { return getToken(CoolParser.OPEN_AC, 0); }
		public Class_bodyContext class_body() {
			return getRuleContext(Class_bodyContext.class,0);
		}
		public TerminalNode CLOSED_AC() { return getToken(CoolParser.CLOSED_AC, 0); }
		public TerminalNode EOI() { return getToken(CoolParser.EOI, 0); }
		public Class_nodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_node; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterClass_node(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitClass_node(this);
		}
	}

	public final Class_nodeContext class_node() throws RecognitionException {
		Class_nodeContext _localctx = new Class_nodeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_class_node);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(222);
			class_header();
			setState(223);
			match(OPEN_AC);
			setState(224);
			class_body();
			setState(225);
			match(CLOSED_AC);
			setState(226);
			match(EOI);
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

	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(CoolParser.EOF, 0); }
		public List<Class_nodeContext> class_node() {
			return getRuleContexts(Class_nodeContext.class);
		}
		public Class_nodeContext class_node(int i) {
			return getRuleContext(Class_nodeContext.class,i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<MethodContext> method() {
			return getRuleContexts(MethodContext.class);
		}
		public MethodContext method(int i) {
			return getRuleContext(MethodContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(246);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(231);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==CLASS) {
					{
					{
					setState(228);
					class_node();
					}
					}
					setState(233);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				{
				setState(237);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPEN_AC) | (1L << OPEN_PAR) | (1L << NEG) | (1L << ISVOID) | (1L << NOT) | (1L << NEW) | (1L << IF) | (1L << WHILE) | (1L << LET) | (1L << CASE) | (1L << BOOLEAN) | (1L << ID) | (1L << NR) | (1L << STRING))) != 0)) {
					{
					{
					setState(234);
					expr(0);
					}
					}
					setState(239);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 3:
				{
				setState(243);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ID) {
					{
					{
					setState(240);
					method();
					}
					}
					setState(245);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			}
			setState(248);
			match(EOF);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 5:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		case 1:
			return precpred(_ctx, 5);
		case 2:
			return precpred(_ctx, 4);
		case 3:
			return precpred(_ctx, 18);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\39\u00fd\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\3\3\3\3\3"+
		"\5\3&\n\3\3\4\3\4\3\4\3\4\3\4\5\4-\n\4\3\5\3\5\3\5\3\5\6\5\63\n\5\r\5"+
		"\16\5\64\5\5\67\n\5\3\6\3\6\3\6\5\6<\n\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\6\7F\n\7\r\7\16\7G\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7^\n\7\f\7\16\7a\13\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\6\7p\n\7\r\7\16\7q\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u0087"+
		"\n\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u0095\n\7\3\7"+
		"\3\7\7\7\u0099\n\7\f\7\16\7\u009c\13\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\5\b\u00a7\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u00b0\n\t\f\t\16\t"+
		"\u00b3\13\t\3\t\5\t\u00b6\n\t\3\n\3\n\3\n\3\n\7\n\u00bc\n\n\f\n\16\n\u00bf"+
		"\13\n\5\n\u00c1\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\f\3\f\5\f\u00cf\n\f\3\r\3\r\3\r\7\r\u00d4\n\r\f\r\16\r\u00d7\13\r\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\5\16\u00df\n\16\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\20\7\20\u00e8\n\20\f\20\16\20\u00eb\13\20\3\20\7\20\u00ee\n\20"+
		"\f\20\16\20\u00f1\13\20\3\20\7\20\u00f4\n\20\f\20\16\20\u00f7\13\20\5"+
		"\20\u00f9\n\20\3\20\3\20\3\20\2\3\f\21\2\4\6\b\n\f\16\20\22\24\26\30\32"+
		"\34\36\2\6\4\2\33\33--\3\2\23\24\3\2\21\22\4\2\26\26\30\31\2\u0115\2 "+
		"\3\2\2\2\4%\3\2\2\2\6\'\3\2\2\2\b\66\3\2\2\2\n8\3\2\2\2\f\u0086\3\2\2"+
		"\2\16\u00a6\3\2\2\2\20\u00b5\3\2\2\2\22\u00c0\3\2\2\2\24\u00c2\3\2\2\2"+
		"\26\u00ce\3\2\2\2\30\u00d5\3\2\2\2\32\u00de\3\2\2\2\34\u00e0\3\2\2\2\36"+
		"\u00f8\3\2\2\2 !\t\2\2\2!\3\3\2\2\2\"&\7/\2\2#&\7,\2\2$&\7\62\2\2%\"\3"+
		"\2\2\2%#\3\2\2\2%$\3\2\2\2&\5\3\2\2\2\'(\7.\2\2()\7\b\2\2),\5\2\2\2*+"+
		"\7\25\2\2+-\5\f\7\2,*\3\2\2\2,-\3\2\2\2-\7\3\2\2\2.\67\5\f\7\2/\62\5\f"+
		"\7\2\60\61\7\t\2\2\61\63\5\f\7\2\62\60\3\2\2\2\63\64\3\2\2\2\64\62\3\2"+
		"\2\2\64\65\3\2\2\2\65\67\3\2\2\2\66.\3\2\2\2\66/\3\2\2\2\67\t\3\2\2\2"+
		"89\7.\2\29;\7\16\2\2:<\5\b\5\2;:\3\2\2\2;<\3\2\2\2<=\3\2\2\2=>\7\17\2"+
		"\2>\13\3\2\2\2?@\b\7\1\2@\u0087\5\n\6\2AE\7\f\2\2BC\5\f\7\2CD\7\7\2\2"+
		"DF\3\2\2\2EB\3\2\2\2FG\3\2\2\2GE\3\2\2\2GH\3\2\2\2HI\3\2\2\2IJ\7\r\2\2"+
		"J\u0087\3\2\2\2KL\7 \2\2LM\5\f\7\2MN\7!\2\2NO\5\f\7\2OP\7\"\2\2PQ\5\f"+
		"\7\2QR\7#\2\2R\u0087\3\2\2\2ST\7$\2\2TU\5\f\7\2UV\7%\2\2VW\5\f\7\2WX\7"+
		"&\2\2X\u0087\3\2\2\2YZ\7\'\2\2Z_\5\6\4\2[\\\7\t\2\2\\^\5\6\4\2][\3\2\2"+
		"\2^a\3\2\2\2_]\3\2\2\2_`\3\2\2\2`b\3\2\2\2a_\3\2\2\2bc\7(\2\2cd\5\f\7"+
		"\17d\u0087\3\2\2\2ef\7)\2\2fg\5\f\7\2go\7*\2\2hi\7.\2\2ij\7\b\2\2jk\5"+
		"\2\2\2kl\7\27\2\2lm\5\f\7\2mn\7\7\2\2np\3\2\2\2oh\3\2\2\2pq\3\2\2\2qo"+
		"\3\2\2\2qr\3\2\2\2rs\3\2\2\2st\7+\2\2t\u0087\3\2\2\2uv\7\16\2\2vw\5\f"+
		"\7\2wx\7\17\2\2x\u0087\3\2\2\2y\u0087\7.\2\2z\u0087\5\4\3\2{|\7\20\2\2"+
		"|\u0087\5\f\7\n}~\7\34\2\2~\u0087\5\f\7\t\177\u0080\7\35\2\2\u0080\u0087"+
		"\5\f\7\5\u0081\u0082\7.\2\2\u0082\u0083\7\25\2\2\u0083\u0087\5\f\7\4\u0084"+
		"\u0085\7\37\2\2\u0085\u0087\5\2\2\2\u0086?\3\2\2\2\u0086A\3\2\2\2\u0086"+
		"K\3\2\2\2\u0086S\3\2\2\2\u0086Y\3\2\2\2\u0086e\3\2\2\2\u0086u\3\2\2\2"+
		"\u0086y\3\2\2\2\u0086z\3\2\2\2\u0086{\3\2\2\2\u0086}\3\2\2\2\u0086\177"+
		"\3\2\2\2\u0086\u0081\3\2\2\2\u0086\u0084\3\2\2\2\u0087\u009a\3\2\2\2\u0088"+
		"\u0089\f\b\2\2\u0089\u008a\t\3\2\2\u008a\u0099\5\f\7\t\u008b\u008c\f\7"+
		"\2\2\u008c\u008d\t\4\2\2\u008d\u0099\5\f\7\b\u008e\u008f\f\6\2\2\u008f"+
		"\u0090\t\5\2\2\u0090\u0099\5\f\7\7\u0091\u0094\f\24\2\2\u0092\u0093\7"+
		"\13\2\2\u0093\u0095\5\2\2\2\u0094\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095"+
		"\u0096\3\2\2\2\u0096\u0097\7\n\2\2\u0097\u0099\5\n\6\2\u0098\u0088\3\2"+
		"\2\2\u0098\u008b\3\2\2\2\u0098\u008e\3\2\2\2\u0098\u0091\3\2\2\2\u0099"+
		"\u009c\3\2\2\2\u009a\u0098\3\2\2\2\u009a\u009b\3\2\2\2\u009b\r\3\2\2\2"+
		"\u009c\u009a\3\2\2\2\u009d\u009e\7.\2\2\u009e\u009f\7\b\2\2\u009f\u00a0"+
		"\5\2\2\2\u00a0\u00a1\7\25\2\2\u00a1\u00a2\5\f\7\2\u00a2\u00a7\3\2\2\2"+
		"\u00a3\u00a4\7.\2\2\u00a4\u00a5\7\b\2\2\u00a5\u00a7\5\2\2\2\u00a6\u009d"+
		"\3\2\2\2\u00a6\u00a3\3\2\2\2\u00a7\17\3\2\2\2\u00a8\u00a9\7.\2\2\u00a9"+
		"\u00aa\7\b\2\2\u00aa\u00b1\5\2\2\2\u00ab\u00ac\7\t\2\2\u00ac\u00ad\7."+
		"\2\2\u00ad\u00ae\7\b\2\2\u00ae\u00b0\5\2\2\2\u00af\u00ab\3\2\2\2\u00b0"+
		"\u00b3\3\2\2\2\u00b1\u00af\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2\u00b6\3\2"+
		"\2\2\u00b3\u00b1\3\2\2\2\u00b4\u00b6\3\2\2\2\u00b5\u00a8\3\2\2\2\u00b5"+
		"\u00b4\3\2\2\2\u00b6\21\3\2\2\2\u00b7\u00c1\5\f\7\2\u00b8\u00b9\5\f\7"+
		"\2\u00b9\u00ba\7\7\2\2\u00ba\u00bc\3\2\2\2\u00bb\u00b8\3\2\2\2\u00bc\u00bf"+
		"\3\2\2\2\u00bd\u00bb\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00c1\3\2\2\2\u00bf"+
		"\u00bd\3\2\2\2\u00c0\u00b7\3\2\2\2\u00c0\u00bd\3\2\2\2\u00c1\23\3\2\2"+
		"\2\u00c2\u00c3\7.\2\2\u00c3\u00c4\7\16\2\2\u00c4\u00c5\5\20\t\2\u00c5"+
		"\u00c6\7\17\2\2\u00c6\u00c7\7\b\2\2\u00c7\u00c8\t\2\2\2\u00c8\u00c9\7"+
		"\f\2\2\u00c9\u00ca\5\22\n\2\u00ca\u00cb\7\r\2\2\u00cb\25\3\2\2\2\u00cc"+
		"\u00cf\5\16\b\2\u00cd\u00cf\5\24\13\2\u00ce\u00cc\3\2\2\2\u00ce\u00cd"+
		"\3\2\2\2\u00cf\27\3\2\2\2\u00d0\u00d1\5\26\f\2\u00d1\u00d2\7\7\2\2\u00d2"+
		"\u00d4\3\2\2\2\u00d3\u00d0\3\2\2\2\u00d4\u00d7\3\2\2\2\u00d5\u00d3\3\2"+
		"\2\2\u00d5\u00d6\3\2\2\2\u00d6\31\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d8\u00d9"+
		"\7\36\2\2\u00d9\u00da\7-\2\2\u00da\u00db\7\32\2\2\u00db\u00df\7-\2\2\u00dc"+
		"\u00dd\7\36\2\2\u00dd\u00df\7-\2\2\u00de\u00d8\3\2\2\2\u00de\u00dc\3\2"+
		"\2\2\u00df\33\3\2\2\2\u00e0\u00e1\5\32\16\2\u00e1\u00e2\7\f\2\2\u00e2"+
		"\u00e3\5\30\r\2\u00e3\u00e4\7\r\2\2\u00e4\u00e5\7\7\2\2\u00e5\35\3\2\2"+
		"\2\u00e6\u00e8\5\34\17\2\u00e7\u00e6\3\2\2\2\u00e8\u00eb\3\2\2\2\u00e9"+
		"\u00e7\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\u00f9\3\2\2\2\u00eb\u00e9\3\2"+
		"\2\2\u00ec\u00ee\5\f\7\2\u00ed\u00ec\3\2\2\2\u00ee\u00f1\3\2\2\2\u00ef"+
		"\u00ed\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\u00f9\3\2\2\2\u00f1\u00ef\3\2"+
		"\2\2\u00f2\u00f4\5\24\13\2\u00f3\u00f2\3\2\2\2\u00f4\u00f7\3\2\2\2\u00f5"+
		"\u00f3\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\u00f9\3\2\2\2\u00f7\u00f5\3\2"+
		"\2\2\u00f8\u00e9\3\2\2\2\u00f8\u00ef\3\2\2\2\u00f8\u00f5\3\2\2\2\u00f9"+
		"\u00fa\3\2\2\2\u00fa\u00fb\7\2\2\3\u00fb\37\3\2\2\2\32%,\64\66;G_q\u0086"+
		"\u0094\u0098\u009a\u00a6\u00b1\u00b5\u00bd\u00c0\u00ce\u00d5\u00de\u00e9"+
		"\u00ef\u00f5\u00f8";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
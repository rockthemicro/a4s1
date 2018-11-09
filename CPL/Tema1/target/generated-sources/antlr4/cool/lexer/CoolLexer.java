// Generated from CoolLexer.g4 by ANTLR 4.7.1

    package cool.lexer;	

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CoolLexer extends Lexer {
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
		STRING_MODE=1, CONSUME_TILL_EOS=2, MULTICOMM=3;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE", "STRING_MODE", "CONSUME_TILL_EOS", "MULTICOMM"
	};

	public static final String[] ruleNames = {
		"WS", "SG_LINE_COMM", "MLT_LINE_COMM", "UNMATCHED_COMM", "EOI", "DBL_DOT", 
		"COMMA", "DOT", "AT", "OPEN_AC", "CLOSED_AC", "OPEN_PAR", "CLOSED_PAR", 
		"NEG", "PLUS", "SUB", "MUL", "DIV", "ASSIGN", "LESS_OR_EQ", "TO", "LESS", 
		"EQ", "INHERITS", "SELF", "ISVOID", "NOT", "CLASS", "NEW", "IF", "THEN", 
		"ELSE", "FI", "WHILE", "LOOP", "POOL", "LET", "IN", "CASE", "OF", "ESAC", 
		"BOOLEAN", "CLASS_NAME", "ID", "NR", "STRING2", "PROBLEMATIC_CHAR", "ERROR_STRING", 
		"ESCAPED_NEWLINE", "TAB_TEXT", "NEWLINE_TEXT", "BACKSPACE_TEXT", "FORM_TEXT", 
		"BACKSLASH", "ESC", "STRING", "NULL_CHAR", "NON_ESCAPED_NEWLINE", "ANYTHING_STRING", 
		"UNEXPETED_END", "REST_OF_STRING", "IMBR_MLT_LINE_COMM", "END_OF_MLT_LINE_COMM", 
		"ERROR_MULTICOMM", "ANYTHING"
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

	    
	    private void raiseError(String msg) {
	        setText(msg);
	        setType(ERROR);
	    }
	    
	    static int stringCnt = 0;
	    static StringBuffer buffer = null;


	public CoolLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "CoolLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 3:
			UNMATCHED_COMM_action((RuleContext)_localctx, actionIndex);
			break;
		case 45:
			STRING2_action((RuleContext)_localctx, actionIndex);
			break;
		case 46:
			PROBLEMATIC_CHAR_action((RuleContext)_localctx, actionIndex);
			break;
		case 47:
			ERROR_STRING_action((RuleContext)_localctx, actionIndex);
			break;
		case 48:
			ESCAPED_NEWLINE_action((RuleContext)_localctx, actionIndex);
			break;
		case 49:
			TAB_TEXT_action((RuleContext)_localctx, actionIndex);
			break;
		case 50:
			NEWLINE_TEXT_action((RuleContext)_localctx, actionIndex);
			break;
		case 51:
			BACKSPACE_TEXT_action((RuleContext)_localctx, actionIndex);
			break;
		case 52:
			FORM_TEXT_action((RuleContext)_localctx, actionIndex);
			break;
		case 53:
			BACKSLASH_action((RuleContext)_localctx, actionIndex);
			break;
		case 55:
			STRING_action((RuleContext)_localctx, actionIndex);
			break;
		case 56:
			NULL_CHAR_action((RuleContext)_localctx, actionIndex);
			break;
		case 57:
			NON_ESCAPED_NEWLINE_action((RuleContext)_localctx, actionIndex);
			break;
		case 58:
			ANYTHING_STRING_action((RuleContext)_localctx, actionIndex);
			break;
		case 63:
			ERROR_MULTICOMM_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void UNMATCHED_COMM_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			 raiseError("Unmatched *)"); 
			break;
		}
	}
	private void STRING2_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:
			 stringCnt = 0; buffer = new StringBuffer(); 
			break;
		}
	}
	private void PROBLEMATIC_CHAR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2:
			 raiseError("Invalid character: " + getText().toString()); 
			break;
		}
	}
	private void ERROR_STRING_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3:
			 raiseError("EOF in string constant"); 
			break;
		}
	}
	private void ESCAPED_NEWLINE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 4:

				stringCnt++;
				buffer.append("\n");

			break;
		}
	}
	private void TAB_TEXT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 5:

				stringCnt++;
				buffer.append("\t");

			break;
		}
	}
	private void NEWLINE_TEXT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 6:

				stringCnt++;
				buffer.append("\n");

			break;
		}
	}
	private void BACKSPACE_TEXT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 7:

				stringCnt++;
				buffer.append("\b");

			break;
		}
	}
	private void FORM_TEXT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 8:

				stringCnt++;
				buffer.append("\f");

			break;
		}
	}
	private void BACKSLASH_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 9:

				stringCnt++;
				buffer.append("\\");

			break;
		}
	}
	private void STRING_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 10:

				if (stringCnt > 1024) {
					raiseError("String constant too long");
				} else if (buffer != null) {
					setText(new String(buffer));
					buffer = null;
				}
				
				stringCnt = 0;

			break;
		}
	}
	private void NULL_CHAR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 11:
			 raiseError("String contains null character"); 
			break;
		}
	}
	private void NON_ESCAPED_NEWLINE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 12:
			 raiseError("Unterminated string constant"); 
			break;
		}
	}
	private void ANYTHING_STRING_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 13:
			 stringCnt++; buffer.append(getText()); 
			break;
		}
	}
	private void ERROR_MULTICOMM_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 14:
			 raiseError("EOF in comment"); 
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\29\u01c8\b\1\b\1\b"+
		"\1\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t"+
		"\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21"+
		"\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30"+
		"\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37"+
		"\t\37\4 \t \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)"+
		"\4*\t*\4+\t+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63"+
		"\t\63\4\64\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;"+
		"\4<\t<\4=\t=\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\3\2\6\2\u008a\n\2\r\2\16\2"+
		"\u008b\3\2\3\2\3\3\3\3\3\3\3\3\7\3\u0094\n\3\f\3\16\3\u0097\13\3\3\3\3"+
		"\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b"+
		"\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3"+
		"\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3"+
		"\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3"+
		"\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3"+
		"\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3"+
		"\36\3\36\3\36\3\36\3\37\3\37\3\37\3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3\"\3"+
		"\"\3\"\3#\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3&\3&\3&\3&\3\'"+
		"\3\'\3\'\3(\3(\3(\3(\3(\3)\3)\3)\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3+\3+\3"+
		"+\3+\5+\u0134\n+\3,\3,\7,\u0138\n,\f,\16,\u013b\13,\3-\3-\7-\u013f\n-"+
		"\f-\16-\u0142\13-\3.\6.\u0145\n.\r.\16.\u0146\3/\3/\3/\3/\3/\3/\3\60\3"+
		"\60\3\60\3\61\3\61\3\61\3\61\3\61\3\61\3\62\3\62\3\62\3\62\3\62\5\62\u015d"+
		"\n\62\3\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\64\3\64"+
		"\3\64\3\64\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\66\3\66"+
		"\3\66\3\66\3\66\3\66\3\66\3\67\3\67\3\67\3\67\3\67\3\67\3\67\38\38\38"+
		"\38\39\39\39\39\39\3:\3:\3:\3:\3:\3;\3;\3;\5;\u0197\n;\3;\3;\3;\3;\3<"+
		"\3<\3<\3<\3<\3=\3=\3=\3=\3=\3=\3>\7>\u01a9\n>\f>\16>\u01ac\13>\3>\3>\3"+
		">\3>\3>\3?\3?\3?\3?\3?\3?\3@\3@\3@\3@\3@\3@\3A\3A\3A\3A\3A\3A\3B\3B\3"+
		"B\3B\3\u01aa\2C\6\4\b\5\n8\f\6\16\7\20\b\22\t\24\n\26\13\30\f\32\r\34"+
		"\16\36\17 \20\"\21$\22&\23(\24*\25,\26.\27\60\30\62\31\64\32\66\338\34"+
		":\35<\36>\37@ B!D\"F#H$J%L&N\'P(R)T*V+X,Z-\\.^/`\2b\60d\61f\2h\2j\2l\2"+
		"n\2p\2r9t\62v\63x\64z\2|\65~\66\u0080\2\u0082\2\u0084\67\u0086\2\6\2\3"+
		"\4\5\t\5\2\13\f\16\17\"\"\4\2\f\f\17\17\3\2C\\\5\2\62;C\\c|\3\2c|\6\2"+
		"\62;C\\aac|\3\2\62;\2\u01cd\2\6\3\2\2\2\2\b\3\2\2\2\2\n\3\2\2\2\2\f\3"+
		"\2\2\2\2\16\3\2\2\2\2\20\3\2\2\2\2\22\3\2\2\2\2\24\3\2\2\2\2\26\3\2\2"+
		"\2\2\30\3\2\2\2\2\32\3\2\2\2\2\34\3\2\2\2\2\36\3\2\2\2\2 \3\2\2\2\2\""+
		"\3\2\2\2\2$\3\2\2\2\2&\3\2\2\2\2(\3\2\2\2\2*\3\2\2\2\2,\3\2\2\2\2.\3\2"+
		"\2\2\2\60\3\2\2\2\2\62\3\2\2\2\2\64\3\2\2\2\2\66\3\2\2\2\28\3\2\2\2\2"+
		":\3\2\2\2\2<\3\2\2\2\2>\3\2\2\2\2@\3\2\2\2\2B\3\2\2\2\2D\3\2\2\2\2F\3"+
		"\2\2\2\2H\3\2\2\2\2J\3\2\2\2\2L\3\2\2\2\2N\3\2\2\2\2P\3\2\2\2\2R\3\2\2"+
		"\2\2T\3\2\2\2\2V\3\2\2\2\2X\3\2\2\2\2Z\3\2\2\2\2\\\3\2\2\2\2^\3\2\2\2"+
		"\2`\3\2\2\2\2b\3\2\2\2\3d\3\2\2\2\3f\3\2\2\2\3h\3\2\2\2\3j\3\2\2\2\3l"+
		"\3\2\2\2\3n\3\2\2\2\3p\3\2\2\2\3r\3\2\2\2\3t\3\2\2\2\3v\3\2\2\2\3x\3\2"+
		"\2\2\3z\3\2\2\2\4|\3\2\2\2\4~\3\2\2\2\5\u0080\3\2\2\2\5\u0082\3\2\2\2"+
		"\5\u0084\3\2\2\2\5\u0086\3\2\2\2\6\u0089\3\2\2\2\b\u008f\3\2\2\2\n\u009a"+
		"\3\2\2\2\f\u00a0\3\2\2\2\16\u00a5\3\2\2\2\20\u00a7\3\2\2\2\22\u00a9\3"+
		"\2\2\2\24\u00ab\3\2\2\2\26\u00ad\3\2\2\2\30\u00af\3\2\2\2\32\u00b1\3\2"+
		"\2\2\34\u00b3\3\2\2\2\36\u00b5\3\2\2\2 \u00b7\3\2\2\2\"\u00b9\3\2\2\2"+
		"$\u00bb\3\2\2\2&\u00bd\3\2\2\2(\u00bf\3\2\2\2*\u00c1\3\2\2\2,\u00c4\3"+
		"\2\2\2.\u00c7\3\2\2\2\60\u00ca\3\2\2\2\62\u00cc\3\2\2\2\64\u00ce\3\2\2"+
		"\2\66\u00d7\3\2\2\28\u00e1\3\2\2\2:\u00e8\3\2\2\2<\u00ec\3\2\2\2>\u00f2"+
		"\3\2\2\2@\u00f6\3\2\2\2B\u00f9\3\2\2\2D\u00fe\3\2\2\2F\u0103\3\2\2\2H"+
		"\u0106\3\2\2\2J\u010c\3\2\2\2L\u0111\3\2\2\2N\u0116\3\2\2\2P\u011a\3\2"+
		"\2\2R\u011d\3\2\2\2T\u0122\3\2\2\2V\u0125\3\2\2\2X\u0133\3\2\2\2Z\u0135"+
		"\3\2\2\2\\\u013c\3\2\2\2^\u0144\3\2\2\2`\u0148\3\2\2\2b\u014e\3\2\2\2"+
		"d\u0151\3\2\2\2f\u015c\3\2\2\2h\u0162\3\2\2\2j\u0169\3\2\2\2l\u0170\3"+
		"\2\2\2n\u0177\3\2\2\2p\u017e\3\2\2\2r\u0185\3\2\2\2t\u0189\3\2\2\2v\u018e"+
		"\3\2\2\2x\u0196\3\2\2\2z\u019c\3\2\2\2|\u01a1\3\2\2\2~\u01aa\3\2\2\2\u0080"+
		"\u01b2\3\2\2\2\u0082\u01b8\3\2\2\2\u0084\u01be\3\2\2\2\u0086\u01c4\3\2"+
		"\2\2\u0088\u008a\t\2\2\2\u0089\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b"+
		"\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008e\b\2"+
		"\2\2\u008e\7\3\2\2\2\u008f\u0090\7/\2\2\u0090\u0091\7/\2\2\u0091\u0095"+
		"\3\2\2\2\u0092\u0094\n\3\2\2\u0093\u0092\3\2\2\2\u0094\u0097\3\2\2\2\u0095"+
		"\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0098\3\2\2\2\u0097\u0095\3\2"+
		"\2\2\u0098\u0099\b\3\2\2\u0099\t\3\2\2\2\u009a\u009b\7*\2\2\u009b\u009c"+
		"\7,\2\2\u009c\u009d\3\2\2\2\u009d\u009e\b\4\3\2\u009e\u009f\b\4\4\2\u009f"+
		"\13\3\2\2\2\u00a0\u00a1\7,\2\2\u00a1\u00a2\7+\2\2\u00a2\u00a3\3\2\2\2"+
		"\u00a3\u00a4\b\5\5\2\u00a4\r\3\2\2\2\u00a5\u00a6\7=\2\2\u00a6\17\3\2\2"+
		"\2\u00a7\u00a8\7<\2\2\u00a8\21\3\2\2\2\u00a9\u00aa\7.\2\2\u00aa\23\3\2"+
		"\2\2\u00ab\u00ac\7\60\2\2\u00ac\25\3\2\2\2\u00ad\u00ae\7B\2\2\u00ae\27"+
		"\3\2\2\2\u00af\u00b0\7}\2\2\u00b0\31\3\2\2\2\u00b1\u00b2\7\177\2\2\u00b2"+
		"\33\3\2\2\2\u00b3\u00b4\7*\2\2\u00b4\35\3\2\2\2\u00b5\u00b6\7+\2\2\u00b6"+
		"\37\3\2\2\2\u00b7\u00b8\7\u0080\2\2\u00b8!\3\2\2\2\u00b9\u00ba\7-\2\2"+
		"\u00ba#\3\2\2\2\u00bb\u00bc\7/\2\2\u00bc%\3\2\2\2\u00bd\u00be\7,\2\2\u00be"+
		"\'\3\2\2\2\u00bf\u00c0\7\61\2\2\u00c0)\3\2\2\2\u00c1\u00c2\7>\2\2\u00c2"+
		"\u00c3\7/\2\2\u00c3+\3\2\2\2\u00c4\u00c5\7>\2\2\u00c5\u00c6\7?\2\2\u00c6"+
		"-\3\2\2\2\u00c7\u00c8\7?\2\2\u00c8\u00c9\7@\2\2\u00c9/\3\2\2\2\u00ca\u00cb"+
		"\7>\2\2\u00cb\61\3\2\2\2\u00cc\u00cd\7?\2\2\u00cd\63\3\2\2\2\u00ce\u00cf"+
		"\7k\2\2\u00cf\u00d0\7p\2\2\u00d0\u00d1\7j\2\2\u00d1\u00d2\7g\2\2\u00d2"+
		"\u00d3\7t\2\2\u00d3\u00d4\7k\2\2\u00d4\u00d5\7v\2\2\u00d5\u00d6\7u\2\2"+
		"\u00d6\65\3\2\2\2\u00d7\u00d8\7U\2\2\u00d8\u00d9\7G\2\2\u00d9\u00da\7"+
		"N\2\2\u00da\u00db\7H\2\2\u00db\u00dc\7a\2\2\u00dc\u00dd\7V\2\2\u00dd\u00de"+
		"\7[\2\2\u00de\u00df\7R\2\2\u00df\u00e0\7G\2\2\u00e0\67\3\2\2\2\u00e1\u00e2"+
		"\7k\2\2\u00e2\u00e3\7u\2\2\u00e3\u00e4\7x\2\2\u00e4\u00e5\7q\2\2\u00e5"+
		"\u00e6\7k\2\2\u00e6\u00e7\7f\2\2\u00e79\3\2\2\2\u00e8\u00e9\7p\2\2\u00e9"+
		"\u00ea\7q\2\2\u00ea\u00eb\7v\2\2\u00eb;\3\2\2\2\u00ec\u00ed\7e\2\2\u00ed"+
		"\u00ee\7n\2\2\u00ee\u00ef\7c\2\2\u00ef\u00f0\7u\2\2\u00f0\u00f1\7u\2\2"+
		"\u00f1=\3\2\2\2\u00f2\u00f3\7p\2\2\u00f3\u00f4\7g\2\2\u00f4\u00f5\7y\2"+
		"\2\u00f5?\3\2\2\2\u00f6\u00f7\7k\2\2\u00f7\u00f8\7h\2\2\u00f8A\3\2\2\2"+
		"\u00f9\u00fa\7v\2\2\u00fa\u00fb\7j\2\2\u00fb\u00fc\7g\2\2\u00fc\u00fd"+
		"\7p\2\2\u00fdC\3\2\2\2\u00fe\u00ff\7g\2\2\u00ff\u0100\7n\2\2\u0100\u0101"+
		"\7u\2\2\u0101\u0102\7g\2\2\u0102E\3\2\2\2\u0103\u0104\7h\2\2\u0104\u0105"+
		"\7k\2\2\u0105G\3\2\2\2\u0106\u0107\7y\2\2\u0107\u0108\7j\2\2\u0108\u0109"+
		"\7k\2\2\u0109\u010a\7n\2\2\u010a\u010b\7g\2\2\u010bI\3\2\2\2\u010c\u010d"+
		"\7n\2\2\u010d\u010e\7q\2\2\u010e\u010f\7q\2\2\u010f\u0110\7r\2\2\u0110"+
		"K\3\2\2\2\u0111\u0112\7r\2\2\u0112\u0113\7q\2\2\u0113\u0114\7q\2\2\u0114"+
		"\u0115\7n\2\2\u0115M\3\2\2\2\u0116\u0117\7n\2\2\u0117\u0118\7g\2\2\u0118"+
		"\u0119\7v\2\2\u0119O\3\2\2\2\u011a\u011b\7k\2\2\u011b\u011c\7p\2\2\u011c"+
		"Q\3\2\2\2\u011d\u011e\7e\2\2\u011e\u011f\7c\2\2\u011f\u0120\7u\2\2\u0120"+
		"\u0121\7g\2\2\u0121S\3\2\2\2\u0122\u0123\7q\2\2\u0123\u0124\7h\2\2\u0124"+
		"U\3\2\2\2\u0125\u0126\7g\2\2\u0126\u0127\7u\2\2\u0127\u0128\7c\2\2\u0128"+
		"\u0129\7e\2\2\u0129W\3\2\2\2\u012a\u012b\7v\2\2\u012b\u012c\7t\2\2\u012c"+
		"\u012d\7w\2\2\u012d\u0134\7g\2\2\u012e\u012f\7h\2\2\u012f\u0130\7c\2\2"+
		"\u0130\u0131\7n\2\2\u0131\u0132\7u\2\2\u0132\u0134\7g\2\2\u0133\u012a"+
		"\3\2\2\2\u0133\u012e\3\2\2\2\u0134Y\3\2\2\2\u0135\u0139\t\4\2\2\u0136"+
		"\u0138\t\5\2\2\u0137\u0136\3\2\2\2\u0138\u013b\3\2\2\2\u0139\u0137\3\2"+
		"\2\2\u0139\u013a\3\2\2\2\u013a[\3\2\2\2\u013b\u0139\3\2\2\2\u013c\u0140"+
		"\t\6\2\2\u013d\u013f\t\7\2\2\u013e\u013d\3\2\2\2\u013f\u0142\3\2\2\2\u0140"+
		"\u013e\3\2\2\2\u0140\u0141\3\2\2\2\u0141]\3\2\2\2\u0142\u0140\3\2\2\2"+
		"\u0143\u0145\t\b\2\2\u0144\u0143\3\2\2\2\u0145\u0146\3\2\2\2\u0146\u0144"+
		"\3\2\2\2\u0146\u0147\3\2\2\2\u0147_\3\2\2\2\u0148\u0149\7$\2\2\u0149\u014a"+
		"\b/\6\2\u014a\u014b\3\2\2\2\u014b\u014c\b/\3\2\u014c\u014d\b/\7\2\u014d"+
		"a\3\2\2\2\u014e\u014f\13\2\2\2\u014f\u0150\b\60\b\2\u0150c\3\2\2\2\u0151"+
		"\u0152\13\2\2\2\u0152\u0153\7\2\2\3\u0153\u0154\b\61\t\2\u0154\u0155\3"+
		"\2\2\2\u0155\u0156\b\61\n\2\u0156e\3\2\2\2\u0157\u0158\7^\2\2\u0158\u015d"+
		"\7\f\2\2\u0159\u015a\7^\2\2\u015a\u015b\7\17\2\2\u015b\u015d\7\f\2\2\u015c"+
		"\u0157\3\2\2\2\u015c\u0159\3\2\2\2\u015d\u015e\3\2\2\2\u015e\u015f\b\62"+
		"\13\2\u015f\u0160\3\2\2\2\u0160\u0161\b\62\3\2\u0161g\3\2\2\2\u0162\u0163"+
		"\7^\2\2\u0163\u0164\7v\2\2\u0164\u0165\3\2\2\2\u0165\u0166\b\63\f\2\u0166"+
		"\u0167\3\2\2\2\u0167\u0168\b\63\3\2\u0168i\3\2\2\2\u0169\u016a\7^\2\2"+
		"\u016a\u016b\7p\2\2\u016b\u016c\3\2\2\2\u016c\u016d\b\64\r\2\u016d\u016e"+
		"\3\2\2\2\u016e\u016f\b\64\3\2\u016fk\3\2\2\2\u0170\u0171\7^\2\2\u0171"+
		"\u0172\7d\2\2\u0172\u0173\3\2\2\2\u0173\u0174\b\65\16\2\u0174\u0175\3"+
		"\2\2\2\u0175\u0176\b\65\3\2\u0176m\3\2\2\2\u0177\u0178\7^\2\2\u0178\u0179"+
		"\7h\2\2\u0179\u017a\3\2\2\2\u017a\u017b\b\66\17\2\u017b\u017c\3\2\2\2"+
		"\u017c\u017d\b\66\3\2\u017do\3\2\2\2\u017e\u017f\7^\2\2\u017f\u0180\7"+
		"^\2\2\u0180\u0181\3\2\2\2\u0181\u0182\b\67\20\2\u0182\u0183\3\2\2\2\u0183"+
		"\u0184\b\67\3\2\u0184q\3\2\2\2\u0185\u0186\7^\2\2\u0186\u0187\3\2\2\2"+
		"\u0187\u0188\b8\3\2\u0188s\3\2\2\2\u0189\u018a\7$\2\2\u018a\u018b\b9\21"+
		"\2\u018b\u018c\3\2\2\2\u018c\u018d\b9\n\2\u018du\3\2\2\2\u018e\u018f\7"+
		"\2\2\2\u018f\u0190\b:\22\2\u0190\u0191\3\2\2\2\u0191\u0192\b:\23\2\u0192"+
		"w\3\2\2\2\u0193\u0197\7\f\2\2\u0194\u0195\7\17\2\2\u0195\u0197\7\f\2\2"+
		"\u0196\u0193\3\2\2\2\u0196\u0194\3\2\2\2\u0197\u0198\3\2\2\2\u0198\u0199"+
		"\b;\24\2\u0199\u019a\3\2\2\2\u019a\u019b\b;\n\2\u019by\3\2\2\2\u019c\u019d"+
		"\13\2\2\2\u019d\u019e\b<\25\2\u019e\u019f\3\2\2\2\u019f\u01a0\b<\3\2\u01a0"+
		"{\3\2\2\2\u01a1\u01a2\13\2\2\2\u01a2\u01a3\7\2\2\3\u01a3\u01a4\3\2\2\2"+
		"\u01a4\u01a5\b=\2\2\u01a5\u01a6\b=\n\2\u01a6}\3\2\2\2\u01a7\u01a9\13\2"+
		"\2\2\u01a8\u01a7\3\2\2\2\u01a9\u01ac\3\2\2\2\u01aa\u01ab\3\2\2\2\u01aa"+
		"\u01a8\3\2\2\2\u01ab\u01ad\3\2\2\2\u01ac\u01aa\3\2\2\2\u01ad\u01ae\7$"+
		"\2\2\u01ae\u01af\3\2\2\2\u01af\u01b0\b>\2\2\u01b0\u01b1\b>\n\2\u01b1\177"+
		"\3\2\2\2\u01b2\u01b3\7*\2\2\u01b3\u01b4\7,\2\2\u01b4\u01b5\3\2\2\2\u01b5"+
		"\u01b6\b?\3\2\u01b6\u01b7\b?\4\2\u01b7\u0081\3\2\2\2\u01b8\u01b9\7,\2"+
		"\2\u01b9\u01ba\7+\2\2\u01ba\u01bb\3\2\2\2\u01bb\u01bc\b@\3\2\u01bc\u01bd"+
		"\b@\n\2\u01bd\u0083\3\2\2\2\u01be\u01bf\13\2\2\2\u01bf\u01c0\7\2\2\3\u01c0"+
		"\u01c1\bA\26\2\u01c1\u01c2\3\2\2\2\u01c2\u01c3\bA\27\2\u01c3\u0085\3\2"+
		"\2\2\u01c4\u01c5\13\2\2\2\u01c5\u01c6\3\2\2\2\u01c6\u01c7\bB\3\2\u01c7"+
		"\u0087\3\2\2\2\17\2\3\4\5\u008b\u0095\u0133\u0139\u0140\u0146\u015c\u0196"+
		"\u01aa\30\b\2\2\5\2\2\7\5\2\3\5\2\3/\3\7\3\2\3\60\4\3\61\5\6\2\2\3\62"+
		"\6\3\63\7\3\64\b\3\65\t\3\66\n\3\67\13\39\f\3:\r\4\4\2\3;\16\3<\17\3A"+
		"\20\4\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
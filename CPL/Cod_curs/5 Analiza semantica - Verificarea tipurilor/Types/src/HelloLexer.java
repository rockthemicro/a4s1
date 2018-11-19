// Generated from HelloLexer.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class HelloLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IF=1, THEN=2, ELSE=3, FI=4, COLON=5, SEMI=6, LBRACE=7, RBRACE=8, TYPE=9, 
		PLUS=10, INT=11, ID=12, REAL=13, STRING=14, BLOCK_COMMENT=15, WS=16;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"IF", "THEN", "ELSE", "FI", "COLON", "SEMI", "LBRACE", "RBRACE", "TYPE", 
		"PLUS", "DIGIT", "INT", "LETTER", "ID", "DIGITS", "FRACTION", "EXPONENT", 
		"REAL", "STRING", "BLOCK_COMMENT", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'if'", "'then'", "'else'", "'fi'", "':'", "';'", "'{'", "'}'", 
		null, "'+'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "IF", "THEN", "ELSE", "FI", "COLON", "SEMI", "LBRACE", "RBRACE", 
		"TYPE", "PLUS", "INT", "ID", "REAL", "STRING", "BLOCK_COMMENT", "WS"
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


	public HelloLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "HelloLexer.g4"; }

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
		case 19:
			BLOCK_COMMENT_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void BLOCK_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			System.err.println("EOF in comment");
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\22\u009d\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\2\3\3\3\3\3\3"+
		"\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3"+
		"\t\3\n\3\n\7\nH\n\n\f\n\16\nK\13\n\3\13\3\13\3\f\3\f\3\r\6\rR\n\r\r\r"+
		"\16\rS\3\16\3\16\3\17\3\17\5\17Z\n\17\3\17\3\17\3\17\7\17_\n\17\f\17\16"+
		"\17b\13\17\3\20\6\20e\n\20\r\20\16\20f\3\21\3\21\5\21k\n\21\5\21m\n\21"+
		"\3\22\3\22\5\22q\n\22\3\22\5\22t\n\22\3\23\3\23\3\23\3\23\3\24\3\24\3"+
		"\24\3\24\7\24~\n\24\f\24\16\24\u0081\13\24\3\24\3\24\3\25\3\25\3\25\3"+
		"\25\3\25\7\25\u008a\n\25\f\25\16\25\u008d\13\25\3\25\3\25\3\25\3\25\5"+
		"\25\u0093\n\25\3\25\3\25\3\26\6\26\u0098\n\26\r\26\16\26\u0099\3\26\3"+
		"\26\4\177\u008b\2\27\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27"+
		"\2\31\r\33\2\35\16\37\2!\2#\2%\17\'\20)\21+\22\3\2\7\3\2C\\\3\2\62;\3"+
		"\2c|\4\2--//\5\2\13\f\17\17\"\"\2\u00a8\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3"+
		"\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2"+
		"\2\23\3\2\2\2\2\25\3\2\2\2\2\31\3\2\2\2\2\35\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\3-\3\2\2\2\5\60\3\2\2\2\7\65\3\2\2\2\t:\3"+
		"\2\2\2\13=\3\2\2\2\r?\3\2\2\2\17A\3\2\2\2\21C\3\2\2\2\23E\3\2\2\2\25L"+
		"\3\2\2\2\27N\3\2\2\2\31Q\3\2\2\2\33U\3\2\2\2\35Y\3\2\2\2\37d\3\2\2\2!"+
		"l\3\2\2\2#s\3\2\2\2%u\3\2\2\2\'y\3\2\2\2)\u0084\3\2\2\2+\u0097\3\2\2\2"+
		"-.\7k\2\2./\7h\2\2/\4\3\2\2\2\60\61\7v\2\2\61\62\7j\2\2\62\63\7g\2\2\63"+
		"\64\7p\2\2\64\6\3\2\2\2\65\66\7g\2\2\66\67\7n\2\2\678\7u\2\289\7g\2\2"+
		"9\b\3\2\2\2:;\7h\2\2;<\7k\2\2<\n\3\2\2\2=>\7<\2\2>\f\3\2\2\2?@\7=\2\2"+
		"@\16\3\2\2\2AB\7}\2\2B\20\3\2\2\2CD\7\177\2\2D\22\3\2\2\2EI\t\2\2\2FH"+
		"\5\33\16\2GF\3\2\2\2HK\3\2\2\2IG\3\2\2\2IJ\3\2\2\2J\24\3\2\2\2KI\3\2\2"+
		"\2LM\7-\2\2M\26\3\2\2\2NO\t\3\2\2O\30\3\2\2\2PR\5\27\f\2QP\3\2\2\2RS\3"+
		"\2\2\2SQ\3\2\2\2ST\3\2\2\2T\32\3\2\2\2UV\t\4\2\2V\34\3\2\2\2WZ\5\33\16"+
		"\2XZ\7a\2\2YW\3\2\2\2YX\3\2\2\2Z`\3\2\2\2[_\5\33\16\2\\_\7a\2\2]_\5\27"+
		"\f\2^[\3\2\2\2^\\\3\2\2\2^]\3\2\2\2_b\3\2\2\2`^\3\2\2\2`a\3\2\2\2a\36"+
		"\3\2\2\2b`\3\2\2\2ce\5\27\f\2dc\3\2\2\2ef\3\2\2\2fd\3\2\2\2fg\3\2\2\2"+
		"g \3\2\2\2hj\7\60\2\2ik\5\37\20\2ji\3\2\2\2jk\3\2\2\2km\3\2\2\2lh\3\2"+
		"\2\2lm\3\2\2\2m\"\3\2\2\2np\7g\2\2oq\t\5\2\2po\3\2\2\2pq\3\2\2\2qr\3\2"+
		"\2\2rt\5\37\20\2sn\3\2\2\2st\3\2\2\2t$\3\2\2\2uv\5\37\20\2vw\5!\21\2w"+
		"x\5#\22\2x&\3\2\2\2y\177\7$\2\2z{\7^\2\2{~\7$\2\2|~\13\2\2\2}z\3\2\2\2"+
		"}|\3\2\2\2~\u0081\3\2\2\2\177\u0080\3\2\2\2\177}\3\2\2\2\u0080\u0082\3"+
		"\2\2\2\u0081\177\3\2\2\2\u0082\u0083\7$\2\2\u0083(\3\2\2\2\u0084\u0085"+
		"\7*\2\2\u0085\u0086\7,\2\2\u0086\u008b\3\2\2\2\u0087\u008a\5)\25\2\u0088"+
		"\u008a\13\2\2\2\u0089\u0087\3\2\2\2\u0089\u0088\3\2\2\2\u008a\u008d\3"+
		"\2\2\2\u008b\u008c\3\2\2\2\u008b\u0089\3\2\2\2\u008c\u0092\3\2\2\2\u008d"+
		"\u008b\3\2\2\2\u008e\u008f\7,\2\2\u008f\u0093\7+\2\2\u0090\u0091\7\2\2"+
		"\3\u0091\u0093\b\25\2\2\u0092\u008e\3\2\2\2\u0092\u0090\3\2\2\2\u0093"+
		"\u0094\3\2\2\2\u0094\u0095\b\25\3\2\u0095*\3\2\2\2\u0096\u0098\t\6\2\2"+
		"\u0097\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u0097\3\2\2\2\u0099\u009a"+
		"\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u009c\b\26\3\2\u009c,\3\2\2\2\23\2"+
		"ISY^`fjlps}\177\u0089\u008b\u0092\u0099\4\3\25\2\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
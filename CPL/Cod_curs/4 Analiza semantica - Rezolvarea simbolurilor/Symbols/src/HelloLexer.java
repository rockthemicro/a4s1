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
		"'Int'", "'+'"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\22\u009a\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\2\3\3\3\3\3\3"+
		"\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3"+
		"\t\3\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\r\6\rO\n\r\r\r\16\rP\3\16\3\16"+
		"\3\17\3\17\5\17W\n\17\3\17\3\17\3\17\7\17\\\n\17\f\17\16\17_\13\17\3\20"+
		"\6\20b\n\20\r\20\16\20c\3\21\3\21\5\21h\n\21\5\21j\n\21\3\22\3\22\5\22"+
		"n\n\22\3\22\5\22q\n\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\7\24{\n"+
		"\24\f\24\16\24~\13\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\7\25\u0087\n"+
		"\25\f\25\16\25\u008a\13\25\3\25\3\25\3\25\3\25\5\25\u0090\n\25\3\25\3"+
		"\25\3\26\6\26\u0095\n\26\r\26\16\26\u0096\3\26\3\26\4|\u0088\2\27\3\3"+
		"\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\2\31\r\33\2\35\16\37\2!"+
		"\2#\2%\17\'\20)\21+\22\3\2\6\3\2\62;\4\2C\\c|\4\2--//\5\2\13\f\17\17\""+
		"\"\2\u00a4\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2"+
		"\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\31"+
		"\3\2\2\2\2\35\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\3-"+
		"\3\2\2\2\5\60\3\2\2\2\7\65\3\2\2\2\t:\3\2\2\2\13=\3\2\2\2\r?\3\2\2\2\17"+
		"A\3\2\2\2\21C\3\2\2\2\23E\3\2\2\2\25I\3\2\2\2\27K\3\2\2\2\31N\3\2\2\2"+
		"\33R\3\2\2\2\35V\3\2\2\2\37a\3\2\2\2!i\3\2\2\2#p\3\2\2\2%r\3\2\2\2\'v"+
		"\3\2\2\2)\u0081\3\2\2\2+\u0094\3\2\2\2-.\7k\2\2./\7h\2\2/\4\3\2\2\2\60"+
		"\61\7v\2\2\61\62\7j\2\2\62\63\7g\2\2\63\64\7p\2\2\64\6\3\2\2\2\65\66\7"+
		"g\2\2\66\67\7n\2\2\678\7u\2\289\7g\2\29\b\3\2\2\2:;\7h\2\2;<\7k\2\2<\n"+
		"\3\2\2\2=>\7<\2\2>\f\3\2\2\2?@\7=\2\2@\16\3\2\2\2AB\7}\2\2B\20\3\2\2\2"+
		"CD\7\177\2\2D\22\3\2\2\2EF\7K\2\2FG\7p\2\2GH\7v\2\2H\24\3\2\2\2IJ\7-\2"+
		"\2J\26\3\2\2\2KL\t\2\2\2L\30\3\2\2\2MO\5\27\f\2NM\3\2\2\2OP\3\2\2\2PN"+
		"\3\2\2\2PQ\3\2\2\2Q\32\3\2\2\2RS\t\3\2\2S\34\3\2\2\2TW\5\33\16\2UW\7a"+
		"\2\2VT\3\2\2\2VU\3\2\2\2W]\3\2\2\2X\\\5\33\16\2Y\\\7a\2\2Z\\\5\27\f\2"+
		"[X\3\2\2\2[Y\3\2\2\2[Z\3\2\2\2\\_\3\2\2\2][\3\2\2\2]^\3\2\2\2^\36\3\2"+
		"\2\2_]\3\2\2\2`b\5\27\f\2a`\3\2\2\2bc\3\2\2\2ca\3\2\2\2cd\3\2\2\2d \3"+
		"\2\2\2eg\7\60\2\2fh\5\37\20\2gf\3\2\2\2gh\3\2\2\2hj\3\2\2\2ie\3\2\2\2"+
		"ij\3\2\2\2j\"\3\2\2\2km\7g\2\2ln\t\4\2\2ml\3\2\2\2mn\3\2\2\2no\3\2\2\2"+
		"oq\5\37\20\2pk\3\2\2\2pq\3\2\2\2q$\3\2\2\2rs\5\37\20\2st\5!\21\2tu\5#"+
		"\22\2u&\3\2\2\2v|\7$\2\2wx\7^\2\2x{\7$\2\2y{\13\2\2\2zw\3\2\2\2zy\3\2"+
		"\2\2{~\3\2\2\2|}\3\2\2\2|z\3\2\2\2}\177\3\2\2\2~|\3\2\2\2\177\u0080\7"+
		"$\2\2\u0080(\3\2\2\2\u0081\u0082\7*\2\2\u0082\u0083\7,\2\2\u0083\u0088"+
		"\3\2\2\2\u0084\u0087\5)\25\2\u0085\u0087\13\2\2\2\u0086\u0084\3\2\2\2"+
		"\u0086\u0085\3\2\2\2\u0087\u008a\3\2\2\2\u0088\u0089\3\2\2\2\u0088\u0086"+
		"\3\2\2\2\u0089\u008f\3\2\2\2\u008a\u0088\3\2\2\2\u008b\u008c\7,\2\2\u008c"+
		"\u0090\7+\2\2\u008d\u008e\7\2\2\3\u008e\u0090\b\25\2\2\u008f\u008b\3\2"+
		"\2\2\u008f\u008d\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0092\b\25\3\2\u0092"+
		"*\3\2\2\2\u0093\u0095\t\5\2\2\u0094\u0093\3\2\2\2\u0095\u0096\3\2\2\2"+
		"\u0096\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u0099"+
		"\b\26\3\2\u0099,\3\2\2\2\22\2PV[]cgimpz|\u0086\u0088\u008f\u0096\4\3\25"+
		"\2\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
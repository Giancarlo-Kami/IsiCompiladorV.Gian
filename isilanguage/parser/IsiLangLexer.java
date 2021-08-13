// Generated from IsiLang.g4 by ANTLR 4.4

	package parser;
	
	import datastructures.IsiSymbol;
	import datastructures.IsiVariable;
	import datastructures.IsiSymbolTable;
	import exceptions.IsiSemanticException;
	import ast.IsiProgram;
	import ast.AbstractCommand;
	import ast.CommandLeitura;
	import ast.CommandEscrita;
	import ast.CommandAtribuicao;
	import ast.CommandDecisao;
	import java.util.ArrayList;
	import java.util.Stack;
	import ast.CommandRepeticao;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IsiLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__10=1, T__9=2, T__8=3, T__7=4, T__6=5, T__5=6, T__4=7, T__3=8, T__2=9, 
		T__1=10, T__0=11, II=12, AP=13, FP=14, SC=15, OP=16, ATTR=17, VIR=18, 
		ACH=19, FCH=20, OPREL=21, ID=22, NUMBER=23, BOOLEAN=24, WS=25;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'", "'\\u0017'", "'\\u0018'", 
		"'\\u0019'"
	};
	public static final String[] ruleNames = {
		"T__10", "T__9", "T__8", "T__7", "T__6", "T__5", "T__4", "T__3", "T__2", 
		"T__1", "T__0", "II", "AP", "FP", "SC", "OP", "ATTR", "VIR", "ACH", "FCH", 
		"OPREL", "ID", "NUMBER", "BOOLEAN", "WS"
	};


		private int _tipo;
		private String _varName;
		private String _varValue;
		private IsiSymbolTable symbolTable = new IsiSymbolTable();
		private IsiSymbol symbol;
		private IsiProgram program = new IsiProgram();
		private ArrayList<AbstractCommand> curThread;
		private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>();
		private String _readID;
		private String _writeID;
		private String _exprID;
		private String _exprContent;
		private String _exprDecision;
		private String _exprRepeticao;
		private ArrayList<AbstractCommand> listaTrue;
		private ArrayList<AbstractCommand> listaFalse;
		private ArrayList<AbstractCommand> blocoFaca;
		
		public boolean verificaVarSemUso(ArrayList<AbstractCommand> lista, ArrayList<IsiSymbol> variaveis){
				Object[] lista2 = lista.toArray();
				int flag = 0;
				 
				for(IsiSymbol var : variaveis){
					for(Object com : lista2){
						if(var.getName() == ((AbstractCommand) com).getId()) flag = 1;
					}
					if(flag == 0) return true;
					flag=0;
				}
				return false;
			}
		
		
		public void verificaID(String id){
			if (!symbolTable.exists(id)){
				throw new IsiSemanticException("Symbol "+id+" not declared");
			}
		}
		
		public void exibeComandos(){
			for (AbstractCommand c: program.getComandos()){
				System.out.println(c);
			}
		}
		
		public void generateCode(){
			program.generateTarget();
		}


	public IsiLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "IsiLang.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\33\u00b9\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3"+
		"\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\17"+
		"\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u009d\n\26\3\27\3\27\7\27"+
		"\u00a1\n\27\f\27\16\27\u00a4\13\27\3\30\6\30\u00a7\n\30\r\30\16\30\u00a8"+
		"\3\30\3\30\6\30\u00ad\n\30\r\30\16\30\u00ae\5\30\u00b1\n\30\3\31\5\31"+
		"\u00b4\n\31\3\32\3\32\3\32\3\32\2\2\33\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21"+
		"\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30"+
		"/\31\61\32\63\33\3\2\t\5\2,-//\61\61\4\2>>@@\3\2c|\5\2\62;C\\c|\3\2\62"+
		";\t\2ccfhkknnqqtuxx\5\2\13\f\17\17\"\"\u00c0\2\3\3\2\2\2\2\5\3\2\2\2\2"+
		"\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2"+
		"\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2"+
		"\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2"+
		"\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2"+
		"\2\3\65\3\2\2\2\5=\3\2\2\2\7@\3\2\2\2\tH\3\2\2\2\13Q\3\2\2\2\rZ\3\2\2"+
		"\2\17`\3\2\2\2\21g\3\2\2\2\23p\3\2\2\2\25u\3\2\2\2\27z\3\2\2\2\31\u0080"+
		"\3\2\2\2\33\u0083\3\2\2\2\35\u0085\3\2\2\2\37\u0087\3\2\2\2!\u0089\3\2"+
		"\2\2#\u008b\3\2\2\2%\u008d\3\2\2\2\'\u008f\3\2\2\2)\u0091\3\2\2\2+\u009c"+
		"\3\2\2\2-\u009e\3\2\2\2/\u00a6\3\2\2\2\61\u00b3\3\2\2\2\63\u00b5\3\2\2"+
		"\2\65\66\7d\2\2\66\67\7q\2\2\678\7n\2\289\7g\2\29:\7c\2\2:;\7p\2\2;<\7"+
		"q\2\2<\4\3\2\2\2=>\7u\2\2>?\7g\2\2?\6\3\2\2\2@A\7g\2\2AB\7u\2\2BC\7e\2"+
		"\2CD\7t\2\2DE\7g\2\2EF\7x\2\2FG\7c\2\2G\b\3\2\2\2HI\7h\2\2IJ\7k\2\2JK"+
		"\7o\2\2KL\7r\2\2LM\7t\2\2MN\7q\2\2NO\7i\2\2OP\7=\2\2P\n\3\2\2\2QR\7g\2"+
		"\2RS\7p\2\2ST\7s\2\2TU\7w\2\2UV\7c\2\2VW\7p\2\2WX\7v\2\2XY\7q\2\2Y\f\3"+
		"\2\2\2Z[\7u\2\2[\\\7g\2\2\\]\7p\2\2]^\7c\2\2^_\7q\2\2_\16\3\2\2\2`a\7"+
		"p\2\2ab\7w\2\2bc\7o\2\2cd\7g\2\2de\7t\2\2ef\7q\2\2f\20\3\2\2\2gh\7r\2"+
		"\2hi\7t\2\2ij\7q\2\2jk\7i\2\2kl\7t\2\2lm\7c\2\2mn\7o\2\2no\7c\2\2o\22"+
		"\3\2\2\2pq\7n\2\2qr\7g\2\2rs\7k\2\2st\7c\2\2t\24\3\2\2\2uv\7h\2\2vw\7"+
		"c\2\2wx\7e\2\2xy\7c\2\2y\26\3\2\2\2z{\7v\2\2{|\7g\2\2|}\7z\2\2}~\7v\2"+
		"\2~\177\7q\2\2\177\30\3\2\2\2\u0080\u0081\7?\2\2\u0081\u0082\7?\2\2\u0082"+
		"\32\3\2\2\2\u0083\u0084\7*\2\2\u0084\34\3\2\2\2\u0085\u0086\7+\2\2\u0086"+
		"\36\3\2\2\2\u0087\u0088\7=\2\2\u0088 \3\2\2\2\u0089\u008a\t\2\2\2\u008a"+
		"\"\3\2\2\2\u008b\u008c\7?\2\2\u008c$\3\2\2\2\u008d\u008e\7.\2\2\u008e"+
		"&\3\2\2\2\u008f\u0090\7}\2\2\u0090(\3\2\2\2\u0091\u0092\7\177\2\2\u0092"+
		"*\3\2\2\2\u0093\u009d\t\3\2\2\u0094\u0095\7@\2\2\u0095\u009d\7?\2\2\u0096"+
		"\u0097\7>\2\2\u0097\u009d\7?\2\2\u0098\u0099\7?\2\2\u0099\u009d\7?\2\2"+
		"\u009a\u009b\7#\2\2\u009b\u009d\7?\2\2\u009c\u0093\3\2\2\2\u009c\u0094"+
		"\3\2\2\2\u009c\u0096\3\2\2\2\u009c\u0098\3\2\2\2\u009c\u009a\3\2\2\2\u009d"+
		",\3\2\2\2\u009e\u00a2\t\4\2\2\u009f\u00a1\t\5\2\2\u00a0\u009f\3\2\2\2"+
		"\u00a1\u00a4\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3.\3"+
		"\2\2\2\u00a4\u00a2\3\2\2\2\u00a5\u00a7\t\6\2\2\u00a6\u00a5\3\2\2\2\u00a7"+
		"\u00a8\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00b0\3\2"+
		"\2\2\u00aa\u00ac\7\60\2\2\u00ab\u00ad\t\6\2\2\u00ac\u00ab\3\2\2\2\u00ad"+
		"\u00ae\3\2\2\2\u00ae\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00b1\3\2"+
		"\2\2\u00b0\u00aa\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\60\3\2\2\2\u00b2\u00b4"+
		"\t\7\2\2\u00b3\u00b2\3\2\2\2\u00b4\62\3\2\2\2\u00b5\u00b6\t\b\2\2\u00b6"+
		"\u00b7\3\2\2\2\u00b7\u00b8\b\32\2\2\u00b8\64\3\2\2\2\n\2\u009c\u00a0\u00a2"+
		"\u00a8\u00ae\u00b0\u00b3\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
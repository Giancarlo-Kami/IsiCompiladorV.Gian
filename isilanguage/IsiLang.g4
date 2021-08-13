grammar IsiLang;

@header{
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
}

@members{
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
}

prog	: 'programa' decl bloco  'fimprog;'
           {  program.setVarTable(symbolTable);
           	  program.setComandos(stack.pop());
           	 
           } 
		;
		
decl    :  (declaravar)+
        ;
        
        
declaravar :  tipo ID  {
	                  _varName = _input.LT(-1).getText();
	                  _varValue = null;
	                  symbol = new IsiVariable(_varName, _tipo, _varValue);
	                  if (!symbolTable.exists(_varName)){
	                     symbolTable.add(symbol);	
	                  }
	                  else{
	                  	 throw new IsiSemanticException("Symbol "+_varName+" already declared");
	                  }
                    } 
              (  VIR 
              	 ID {
	                  _varName = _input.LT(-1).getText();
	                  _varValue = null;
	                  symbol = new IsiVariable(_varName, _tipo, _varValue);
	                  if (!symbolTable.exists(_varName)){
	                     symbolTable.add(symbol);	
	                  }
	                  else{
	                  	 throw new IsiSemanticException("Symbol "+_varName+" already declared");
	                  }
                    }
              )* 
               SC
           ;
           
tipo       : 'numero' { _tipo = IsiVariable.NUMBER;  }
           | 'texto'  { _tipo = IsiVariable.TEXT;  }
           | 'boleano'{ _tipo = IsiVariable.BOOLEAN;}
           ;
        
bloco	: { curThread = new ArrayList<AbstractCommand>(); 
	        stack.push(curThread);  
          }
          (cmd)+
          {
          	if(verificaVarSemUso(stack.peek(),symbolTable.getAll())){
          		throw new IsiSemanticException("There are variables not being used");
          	}
          }
		;
		

cmd		:  cmdleitura  
 		|  cmdescrita 
 		|  cmdattrib
 		|  cmdselecao
 		|  cmdrepeticao  
		;
		

		
cmdleitura	: 'leia' AP
                     ID { verificaID(_input.LT(-1).getText());
                     	  _readID = _input.LT(-1).getText();
                        } 
                     FP 
                     SC 
                     
              {
              	IsiVariable var = (IsiVariable)symbolTable.get(_readID);
              	CommandLeitura cmd = new CommandLeitura(_readID, var);
              	stack.peek().add(cmd);
              }   
			;
			
cmdescrita	: 'escreva' 
                 AP 
                 ID { verificaID(_input.LT(-1).getText());
	                  _writeID = _input.LT(-1).getText();
                     } 
                 FP 
                 SC
               		{
               	  		CommandEscrita cmd = new CommandEscrita(_writeID);
               	  		stack.peek().add(cmd);
               		}
			;
			
cmdattrib	:  ID { verificaID(_input.LT(-1).getText());
                    _exprID = _input.LT(-1).getText();
                   } 
               ATTR { _exprContent = ""; } 
               expr 
               SC
               {
               	 CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
               	 if((((IsiVariable)symbolTable.get(_exprID)).getType() == 0 && (Character.isLetter(_exprContent.charAt(0)))==false) ||((((IsiVariable)symbolTable.get(_exprID)).getType() == 1 && (Character.isLetter(_exprContent.charAt(0)))==true)) || (((IsiVariable)symbolTable.get(_exprID)).getType()==2) && (_exprContent == "verdadeiro"||(_exprContent == "falso"))){
               	 	stack.peek().add(cmd);
               	 }
               	 else throw new IsiSemanticException ("types of "+_exprID+" and "+_exprContent+" do not match");
				}
			;
			
			
cmdselecao  :  'se' AP
                    ID    { verificaID(_input.LT(-1).getText());
                    	    _exprDecision = _input.LT(-1).getText();
                    	  }
                    ((OPREL { _exprDecision += _input.LT(-1).getText(); }
                    (ID | NUMBER) {_exprDecision += _input.LT(-1).getText(); } )|
                    (II { _exprDecision += _input.LT(-1).getText(); }
                     BOOLEAN {_exprDecision += _input.LT(-1).getText(); }
                    ))
                    FP 
                    ACH 
                    { curThread = new ArrayList<AbstractCommand>(); 
                      stack.push(curThread);
                    }
                    (cmd)+ 
                    
                    FCH 
                    {
                       listaTrue = stack.pop();	
                    } 
                   ('senao' 
                   	 ACH
                   	 {
                   	 	curThread = new ArrayList<AbstractCommand>();
                   	 	stack.push(curThread);
                   	 } 
                   	(cmd+) 
                   	FCH
                   	{
                   		listaFalse = stack.pop();
                   		CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
                   		stack.peek().add(cmd);
                   	}
                   )?
            ;
            
cmdrepeticao : 'enquanto' AP
						  ID    { verificaID(_input.LT(-1).getText());
						  		  _exprRepeticao = _input.LT(-1).getText();
						  		}
						((OPREL { _exprDecision += _input.LT(-1).getText(); }
                    	 (ID | NUMBER) {_exprDecision += _input.LT(-1).getText(); } )|
                    	 (II { _exprDecision += _input.LT(-1).getText(); }
                    	  BOOLEAN {_exprDecision += _input.LT(-1).getText(); }
                    	))
                    	  FP
                    	  
               'faca'
                    	  ACH 
						  { curThread = new ArrayList<AbstractCommand>();
                   	 		stack.push(curThread);
                   	      }
                   	      (cmd)+
                   	      FCH
                   	      {
                    	  	blocoFaca = stack.pop();
                    	  	CommandRepeticao cmd = new CommandRepeticao(_exprRepeticao, blocoFaca);
                   			stack.peek().add(cmd);
                    	  } 

			;
			
expr		:  termo ( 
	             OP  { _exprContent += _input.LT(-1).getText();}
	            termo
	            )*
			;
			
termo		: ID { verificaID(_input.LT(-1).getText());
	               _exprContent += _input.LT(-1).getText();
                 } 
            | 
              NUMBER
              {
              	_exprContent += _input.LT(-1).getText();
              }
            |
              BOOLEAN
              {
              	_exprContent += _input.LT(-1).getText();
              }
			;
			
II  : '=='
	;

	
AP	: '('
	;
	
FP	: ')'
	;
	
SC	: ';'
	;
	
OP	: '+' | '-' | '*' | '/'
	;
	
ATTR : '='
	 ;
	 
VIR  : ','
     ;
     
ACH  : '{'
     ;
     
FCH  : '}'
     ;
	 
	 
OPREL : '>' | '<' | '>=' | '<=' | '==' | '!='
      ;
      
ID	: [a-z] ([a-z] | [A-Z] | [0-9])*
	;
	
NUMBER	: [0-9]+ ('.' [0-9]+)?
		;
		
BOOLEAN : [verdadeiro] | [falso]
		;
		
WS	: (' ' | '\t' | '\n' | '\r') -> skip;
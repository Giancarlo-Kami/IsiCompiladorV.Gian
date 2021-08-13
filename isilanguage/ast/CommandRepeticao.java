package ast;

import java.util.ArrayList;

public class CommandRepeticao extends AbstractCommand {
	
	private String condition;
	private ArrayList<AbstractCommand> blocoFaca;
	
	public String getId() {
		return this.condition;
	}
	
	public CommandRepeticao(String condition, ArrayList<AbstractCommand> blocoFaca) {
		this.condition = condition;
		this.blocoFaca = blocoFaca;
	}
	
	@Override
	public String generateJavaCode() {
		StringBuilder str = new StringBuilder();
		str.append("while("+condition+") do{/n");
		for(AbstractCommand cmd:blocoFaca) {
			str.append(cmd.generateJavaCode());
		}
		str.append("}");
		return str.toString();
	}
	
	@Override
	public String toString() {
		return "CommandRepeticao [condition=" + condition + ", listaCommand=" + blocoFaca+ "]";
	}

}

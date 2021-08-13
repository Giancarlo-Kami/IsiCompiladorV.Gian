package ast;

public class CommandEscrita extends AbstractCommand {

	private String id;
	
	public CommandEscrita(String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
	
	@Override
	public String generateJavaCode() {
		// TODO Auto-generated method stub
		return "System.out.println("+id+");";
	}
	@Override
	public String toString() {
		return "CommandEscrita [id=" + id + "]";
	}
	

}

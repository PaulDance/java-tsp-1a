package interpreter.structures;
import java.util.Hashtable;


public class Function implements Node {
	private String name;
	private String[] args;
	private Node body;
	private Hashtable<String, Node> __locals__;
	
	public Function(String name, String[] args, Node body) {
		this.name = name;
		this.args = args;
		this.body = body;
		this.__locals__ = new Hashtable<String, Node>();
	}

	public double execute() {
		return this.body.execute();
	}
	
	public String toString() {
		return this.name;
	}
	
	public String[] getArgsNames() {
		return this.args;
	}
	
	public Hashtable<String, Node> getLocals() {
		return this.__locals__;
	}
}

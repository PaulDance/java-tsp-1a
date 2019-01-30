package interpreter.operations;
import interpreter.structures.Node;
import interpreter.structures.Operation;


public class Echo extends Operation {
	
	public Echo(Node node) {
		super(node);
	}
	
	public double execute() {
		double result = this.getOpAt(0).execute();
		System.out.println(result);
		return result;
	}

	public String opString() {
		return "echo";
	}

}

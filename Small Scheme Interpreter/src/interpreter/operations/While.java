package interpreter.operations;
import interpreter.structures.Node;
import interpreter.structures.Operation;


public class While extends Operation {
	
	public While(Node condition, Node body) {
		super(condition, body);
	}

	public double execute() {
		double result = 0;
		
		while (this.getOpAt(0).execute() != 0) {
			result = this.getOpAt(1).execute();
		}
		
		return result;
	}

	public String opString() {
		return "while";
	}
}

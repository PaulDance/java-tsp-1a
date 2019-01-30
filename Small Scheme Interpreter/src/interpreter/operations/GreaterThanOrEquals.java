package interpreter.operations;
import interpreter.structures.Node;
import interpreter.structures.Operation;


public class GreaterThanOrEquals extends Operation {
	
	public GreaterThanOrEquals(Node node1, Node node2) {
		super(node1, node2);
	}

	public double execute() {
		if (this.getOpAt(0).execute() >= this.getOpAt(1).execute()) {
			return 1;
		}
		else {
			return 0;
		}
	}

	public String opString() {
		return ">=";
	}
	
	
}
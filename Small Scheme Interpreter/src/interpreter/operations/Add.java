package interpreter.operations;
import interpreter.structures.Node;
import interpreter.structures.Operation;


public class Add extends Operation {
	
	public Add(Node... nodes) {
		super(nodes);
	}
	
	public double execute() {
		double sum = 0;
		
		for (int i = 0; i < this.numOps(); i++) {
			sum += this.getOpAt(i).execute();
		}
		
		return sum;
	}
	
	public String opString() {
		return "+";
	}
}

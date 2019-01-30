package interpreter.operations;
import interpreter.structures.Node;
import interpreter.structures.Operation;


public class Divide extends Operation {

	public Divide(Node... nodes) {
		super(nodes);
	}
	
	public double execute() {
		if (this.numOps() == 0) {
			return 1;
		}
		else if (this.numOps() == 1) {
			return 1 / this.getOpAt(0).execute();
		}
		else {
			double div = this.getOpAt(0).execute();
			
			for (int i = 1; i < this.numOps(); i++) {
				div /= this.getOpAt(i).execute();
			}
			
			return div;
		}
	}

	public String opString() {
		return "/";
	}

}

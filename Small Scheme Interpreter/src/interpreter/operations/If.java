package interpreter.operations;
import interpreter.structures.Node;
import interpreter.structures.Operation;


public class If extends Operation {
	
	public If(Node conditionNode, Node thenNode, Node elseNode) {
		super(conditionNode, thenNode, elseNode);
	}
	
	public double execute() {
		if (this.getOpAt(0).execute() == 1) {
			return this.getOpAt(1).execute();
		}
		else {
			return this.getOpAt(2).execute();
		}
	}

	public String opString() {
		return "if";
	}

}

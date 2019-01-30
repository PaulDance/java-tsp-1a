package interpreter.operations;
import interpreter.structures.Node;
import interpreter.structures.Operation;
import interpreter.structures.Variable;


public class Set extends Operation {
	
	public Set(Variable variable, Node value) {
		super(variable, value);
	}

	public double execute() {
		Variable var = (Variable) this.getOpAt(0);
		var.setValue(this.getOpAt(1).execute());
		return var.execute();
	}

	public String opString() {
		return "set!";
	}
}

package interpreter.operations;
import interpreter.structures.Operation;


public class Exit extends Operation {
	
	public Exit() {
		super();
	}
	
	public double execute() {
		return 0.0;
	}

	public String opString() {
		return "exit";
	}

}

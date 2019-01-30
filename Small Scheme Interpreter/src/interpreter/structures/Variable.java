package interpreter.structures;


public class Variable extends Scalar {
	String name;
	
	public Variable(String name) {
		super(0);
		this.name = name;
	}
	
	public String toString() {
		return this.name;
	}
}

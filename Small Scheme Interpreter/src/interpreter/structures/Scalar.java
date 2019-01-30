package interpreter.structures;


public class Scalar implements Node {
	private double value;
	
	public Scalar(double value) {
		this.value = value;
	}
	
	public double execute() {
		return this.value;
	}
	
	public String toString() {
		return Double.toString(this.value);
	}
	
	public void setValue(double value) {
		this.value = value;
	}
}

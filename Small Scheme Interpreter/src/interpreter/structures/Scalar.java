package interpreter.structures;


public class Scalar implements Node {
    private double value;

    public Scalar(final double value) {
        this.value = value;
    }

    @Override
    public double execute() {
        return this.value;
    }

    @Override
    public String toString() {
        return Double.toString(this.value);
    }

    public void setValue(final double value) {
        this.value = value;
    }
}

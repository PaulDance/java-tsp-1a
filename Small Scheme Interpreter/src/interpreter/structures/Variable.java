package interpreter.structures;


public class Variable extends Scalar {
    private final String name;

    public Variable(final String name) {
        super(0);
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

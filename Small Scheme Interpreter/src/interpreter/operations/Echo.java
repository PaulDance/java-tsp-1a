package interpreter.operations;

import interpreter.structures.Node;
import interpreter.structures.Operation;


public class Echo extends Operation {
    public Echo(final Node node) {
        super(node);
    }

    @Override
    public double execute() {
        final double result = this.getOpAt(0).execute();
        System.out.println(result);
        return result;
    }

    @Override
    public String opString() {
        return "echo";
    }
}

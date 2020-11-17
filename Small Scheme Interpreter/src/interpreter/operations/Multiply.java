package interpreter.operations;

import interpreter.structures.Node;
import interpreter.structures.Operation;


public class Multiply extends Operation {
    public Multiply(final Node... nodes) {
        super(nodes);
    }

    @Override
    public double execute() {
        double mul = 1;

        for (int i = 0; i < this.numOps(); i++) {
            mul *= this.getOpAt(i).execute();
        }

        return mul;
    }

    @Override
    public String opString() {
        return "*";
    }
}

package interpreter.operations;

import interpreter.structures.Node;
import interpreter.structures.Operation;


public class LessThanOrEquals extends Operation {
    public LessThanOrEquals(final Node node1, final Node node2) {
        super(node1, node2);
    }

    @Override
    public double execute() {
        if (this.getOpAt(0).execute() <= this.getOpAt(1).execute()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String opString() {
        return "<=";
    }
}

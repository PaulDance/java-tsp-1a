package interpreter.operations;

import interpreter.structures.Node;
import interpreter.structures.Operation;


public class Begin extends Operation {
    public Begin(final Node... nodes) {
        super(nodes);
    }

    @Override
    public double execute() {
        for (int i = 0; i < this.numOps() - 1; i++) {
            this.getOpAt(i).execute();
        }

        return this.getOpAt(this.numOps() - 1).execute();
    }

    @Override
    public String opString() {
        return "begin";
    }
}

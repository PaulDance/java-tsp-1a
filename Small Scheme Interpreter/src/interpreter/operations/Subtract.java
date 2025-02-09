package interpreter.operations;

import interpreter.structures.Node;
import interpreter.structures.Operation;


public class Subtract extends Operation {
    public Subtract(final Node... nodes) {
        super(nodes);
    }

    @Override
    public double execute() {
        if (this.numOps() == 0) {
            return 0;
        }
        if (this.numOps() == 1) {
            return -this.getOpAt(0).execute();
        } else {
            double sSum = this.getOpAt(0).execute();

            for (int i = 1; i < this.numOps(); i++) {
                sSum -= this.getOpAt(i).execute();
            }

            return sSum;
        }
    }

    @Override
    public String opString() {
        return "-";
    }
}

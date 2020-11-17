package interpreter.operations;

import interpreter.structures.Node;
import interpreter.structures.Operation;


public class While extends Operation {
    public While(final Node condition, final Node body) {
        super(condition, body);
    }

    @Override
    public double execute() {
        double result = 0;

        while (this.getOpAt(0).execute() != 0) {
            result = this.getOpAt(1).execute();
        }

        return result;
    }

    @Override
    public String opString() {
        return "while";
    }
}

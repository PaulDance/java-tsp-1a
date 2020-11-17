package interpreter.operations;

import interpreter.structures.Node;
import interpreter.structures.Operation;
import interpreter.structures.Variable;


public class Set extends Operation {
    public Set(final Variable variable, final Node value) {
        super(variable, value);
    }

    @Override
    public double execute() {
        final Variable var = (Variable) this.getOpAt(0);
        var.setValue(this.getOpAt(1).execute());
        return var.execute();
    }

    @Override
    public String opString() {
        return "set!";
    }
}

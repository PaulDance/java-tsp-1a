package interpreter.operations;

import interpreter.structures.Node;
import interpreter.structures.Operation;


public class If extends Operation {
    public If(final Node conditionNode, final Node thenNode, final Node elseNode) {
        super(conditionNode, thenNode, elseNode);
    }

    @Override
    public double execute() {
        if (this.getOpAt(0).execute() == 1) {
            return this.getOpAt(1).execute();
        } else {
            return this.getOpAt(2).execute();
        }
    }

    @Override
    public String opString() {
        return "if";
    }
}

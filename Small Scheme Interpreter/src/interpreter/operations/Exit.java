package interpreter.operations;

import interpreter.structures.Operation;


public class Exit extends Operation {
    public Exit() {
        super();
    }

    @Override
    public double execute() {
        return 0.0;
    }

    @Override
    public String opString() {
        return "exit";
    }
}

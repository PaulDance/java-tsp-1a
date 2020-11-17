package interpreter.structures;


public abstract class Operation implements Node {
    private final Node[] operands;

    public Operation(final Node... nodes) {
        this.operands = nodes;
    }

    public Node getOpAt(final int i) {
        return this.operands[i];
    }

    public int numOps() {
        return this.operands.length;
    }

    public abstract String opString();

    @Override
    public String toString() {
        String str = "(" + this.opString();

        for (int i = 0; i < this.numOps(); i++) {
            str += " " + this.operands[i].toString();
        }

        return str + ")";
    }
}

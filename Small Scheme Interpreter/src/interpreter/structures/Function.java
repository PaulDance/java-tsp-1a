package interpreter.structures;

import java.util.Hashtable;


public class Function implements Node {
    private final String name;
    private final String[] args;
    private final Node body;
    private final Hashtable<String, Node> __locals__;

    public Function(final String name, final String[] args, final Node body) {
        this.name = name;
        this.args = args;
        this.body = body;
        this.__locals__ = new Hashtable<String, Node>();
    }

    @Override
    public double execute() {
        return this.body.execute();
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String[] getArgsNames() {
        return this.args;
    }

    public Hashtable<String, Node> getLocals() {
        return this.__locals__;
    }
}

package interpreter;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;

import interpreter.operations.Add;
import interpreter.operations.Begin;
import interpreter.operations.Divide;
import interpreter.operations.Echo;
import interpreter.operations.Exit;
import interpreter.operations.GreaterThan;
import interpreter.operations.GreaterThanOrEquals;
import interpreter.operations.If;
import interpreter.operations.LessThan;
import interpreter.operations.LessThanOrEquals;
import interpreter.operations.Multiply;
import interpreter.operations.Set;
import interpreter.operations.Subtract;
import interpreter.operations.While;
import interpreter.structures.Node;
import interpreter.structures.Scalar;
import interpreter.structures.Variable;


/**
 * A wrapper for the opening of a text console on System input to compile and
 * execute Scheme instructions. No arguments is needed upon initialization. Use
 * <code>interpObj.startConsole()</code> to start the Scheme console.
 * 
 * @see    Node
 * @author Paul Mabileau
 */
public class SchemeInterpreter {
    private final Hashtable<String, Node> __globals__;
    private final Scanner __scanner__;

    public SchemeInterpreter() {
        this.__globals__ = new Hashtable<String, Node>();
        this.__scanner__ = new Scanner(System.in);
    }

    /**
     * Takes no argument. Starts an interactive Scheme interpreter on the
     * <code>System</code> console. The session will remain active and running
     * until <code>(exit)</code> is called.
     */
    public void startConsole() {
        String cmd = "";

        while (!cmd.equals("(exit)")) {
            System.out.print("> ");
            cmd = this.__scanner__.nextLine();

            if (!cmd.equals("")) {
                System.out.println(this.execute(cmd.trim()));
            }
        }

        System.out.println("\n ---  Exit  ---");
    }

    public double execute(final String string) {
        return this.compile(string).execute();
    }

    public Node compile(final String string) {
        final String[] strs = SchemeInterpreter.splitCmd(string);
        return this.beginCompileRec(strs, 0, strs.length - 1);
    }

    private Node beginCompileRec(final String[] strs, final int start, final int end) {
        Node resultNode = null;
        final Node[] argsArray = this.getArgs(strs, start + 2, end);

        switch (strs[start + 1]) {
            case "exit":
                resultNode = new Exit();
                break;

            case "echo":
                resultNode = new Echo(argsArray[0]);
                break;

            case "+":
                resultNode = new Add(argsArray);
                break;

            case "-":
                resultNode = new Subtract(argsArray);
                break;

            case "*":
                resultNode = new Multiply(argsArray);
                break;

            case "/":
                resultNode = new Divide(argsArray);
                break;

            case "<":
                resultNode = new LessThan(argsArray[0], argsArray[1]);
                break;

            case "<=":
                resultNode = new LessThanOrEquals(argsArray[0], argsArray[1]);
                break;

            case ">":
                resultNode = new GreaterThan(argsArray[0], argsArray[1]);
                break;

            case ">=":
                resultNode = new GreaterThanOrEquals(argsArray[0], argsArray[1]);
                break;

            case "begin":
                resultNode = new Begin(argsArray);
                break;

            case "if":
                resultNode = new If(argsArray[0], argsArray[1], argsArray[2]);
                break;

            case "while":
                resultNode = new While(argsArray[0], argsArray[1]);
                break;

            case "set!":
                if (argsArray[0] == null) {
                    argsArray[0] = new Variable(strs[start + 2]);
                    this.__globals__.put(strs[start + 2], argsArray[0]);
                }
                resultNode = new Set((Variable) argsArray[0], argsArray[1]);
                break;

            default:
                if (!this.__globals__.containsKey(strs[start + 1])) {
                    System.out.println("Not an operation: " + strs[start + 1]);
                    resultNode = new Scalar(0);
                } else {
                    resultNode = this.__globals__.get(strs[start + 1]);
                }

        }

        return resultNode;
    }

    // TODO: put a context Function var to get its __local__ variables if in
    // function body.
    private Node[] getArgs(final String[] strs, final int start, final int end) {
        final LinkedList<Node> args = new LinkedList<Node>();
        int recEnd = 0;

        for (int i = start; i < end; i++) {
            if (strs[i].equals("(")) {
                recEnd = SchemeInterpreter.detectEnd(strs, i);
                args.addLast(this.beginCompileRec(strs, i, recEnd));
                i = recEnd;
            } else if (strs[i].matches("-?[0-9]*\\.?[0-9]*")) {
                args.addLast(new Scalar(Double.parseDouble(strs[i])));
            } else if (strs[i].matches("[a-zA-Z0-9!_]*")) {
                args.addLast(this.__globals__.get(strs[i]));
            } else {
                System.out.println("Syntax error: " + strs[i]);
            }
        }

        final Node[] argsArray = new Node[args.size()];

        for (int i = 0; i < argsArray.length; i++) {
            argsArray[i] = args.removeFirst();
        }

        return argsArray;
    }

    private static int detectEnd(final String[] strs, final int start) {
        int pCount = 0;

        for (int i = start; i < strs.length; i++) {
            if (strs[i].equals("(")) {
                pCount += 1;
            } else if (strs[i].equals(")")) {
                pCount -= 1;
            }

            if (pCount == 0) {
                return i;
            }
        }

        return -1;
    }

    private static String[] splitCmd(final String string) {
        final String[] strs = string.split("\\s+");
        final LinkedList<String> strStack = new LinkedList<String>();
        String tmp = "";
        int j = 0;

        for (final String str: strs) {
            j = 0;

            while (j < str.length()) {
                if (str.charAt(j) == '(' || str.charAt(j) == ')') {
                    if (!tmp.equals("")) {
                        strStack.addLast(tmp);
                        tmp = "";
                    }
                    strStack.addLast(String.valueOf(str.charAt(j)));
                } else {
                    tmp += str.charAt(j);
                }

                j++;
            }

            if (!tmp.equals("")) {
                strStack.addLast(tmp);
                tmp = "";
            }
        }

        final String[] result = new String[strStack.size()];

        for (int i = 0; i < result.length; i++) {
            result[i] = strStack.removeFirst();
        }

        return result;
    }
}

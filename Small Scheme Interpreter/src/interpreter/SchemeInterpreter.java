package interpreter;
import java.util.LinkedList;
import java.util.Hashtable;
import java.util.Scanner;
import interpreter.structures.Node;
import interpreter.structures.Scalar;
import interpreter.structures.Variable;
import interpreter.operations.*;


/**
 * A wrapper for the opening of a text console on System input to compile and execute Scheme instructions.
 * No arguments is needed upon initialization. Use <code>interpObj.startConsole()</code> to start the Scheme console.
 * @see Node
 * @author Paul Mabileau
 */
public class SchemeInterpreter {
	private Hashtable<String, Node> __globals__;
	private Scanner __scanner__;
	
	public SchemeInterpreter() {
		this.__globals__ = new Hashtable<String, Node>();
		this.__scanner__ = new Scanner(System.in);
	}
	
	/**
	 * Takes no argument. Starts an interactive Scheme interpreter on the <code>System</code> console.
	 * The session will remain active and running until <code>(exit)</code> is called.
	 * @author Paul Mabileau
	 */
	public void startConsole() {
		String cmd = "";
		
		while (!cmd.equals("(exit)")) {
			System.out.print("> ");
			cmd = this.__scanner__.nextLine();
			//System.out.println("cmd: " + cmd);
			
			if (!cmd.equals("")) {
				System.out.println(this.execute(cmd.trim()));
			}
		}
		
		System.out.println("\n ---  Exit  ---");
	}
	
	public double execute(String string) {
		return this.compile(string).execute();
	}
	
	public Node compile(String string) {
		String[] strs = splitCmd(string);
		return this.beginCompileRec(strs, 0, strs.length - 1);
	}
	
	private Node beginCompileRec(String[] strs, int start, int end) {
		//System.out.println("rec strs: " + Arrays.toString(strs));
		Node resultNode = null;
		//System.out.println("strs[start+2]: " + strs[start+2]);
		Node[] argsArray = this.getArgs(strs, start + 2, end);
		//System.out.println("rec args: " + Arrays.toString(argsArray));
		
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
	
	private Node[] getArgs(String[] strs, int start, int end) {		// TODO: put a context Function var to get its __local__ variables if in function body.
		LinkedList<Node> args = new LinkedList<Node>();
		int recEnd = 0;
		
		for (int i = start; i < end; i++) {
			//System.out.println(strs[i]);
			
			if (strs[i].equals("(")) {
				recEnd = detectEnd(strs, i);
				args.addLast(this.beginCompileRec(strs, i, recEnd));
				i = recEnd;
			}
			else if (strs[i].matches("-?[0-9]*\\.?[0-9]*")) {
				args.addLast(new Scalar(Double.parseDouble(strs[i])));
			}
			else if (strs[i].matches("[a-zA-Z0-9!_]*")) {
				args.addLast(this.__globals__.get(strs[i]));
			}
			else {
				System.out.println("Syntax error: " + strs[i]);
			}
		}
		
		Node[] argsArray = new Node[args.size()];
		
		for (int i = 0; i < argsArray.length; i++) {
			argsArray[i] = args.removeFirst();
		}
		
		return argsArray;
	}
	
	private static int detectEnd(String[] strs, int start) {
		int pCount = 0;
		
		for (int i = start; i < strs.length; i++) {
			//System.out.print(strs[i] + " ");
			if (strs[i].equals("(")) {
				pCount += 1;
			}
			else if (strs[i].equals(")")) {
				pCount -= 1;
			}
			
			if (pCount == 0) {
				return i;
			}
		}
		
		return -1;
	}
	
	private static String[] splitCmd(String string) {
		String[] strs = string.split("\\s+");
		//System.out.println("split cmd: " + string);
		//System.out.println("split strs: " + Arrays.toString(strs));
		LinkedList<String> strStack = new LinkedList<String>();
		String tmp = "";
		int j = 0;
		
		for (int i = 0; i < strs.length; i++) {
			j = 0;
			
			while (j < strs[i].length()) {
				if (strs[i].charAt(j) == '(' || strs[i].charAt(j) == ')') {
					if (!tmp.equals("")) {
						strStack.addLast(tmp);
						tmp = "";
					}
					strStack.addLast(String.valueOf(strs[i].charAt(j)));
				}
				else {
					tmp += strs[i].charAt(j);
				}
				j++;
			}
			
			if (!tmp.equals("")) {
				strStack.addLast(tmp);
				tmp = "";
			}
		}
		
		String[] result = new String[strStack.size()];
		
		for (int i = 0; i < result.length; i++) {
			result[i] = strStack.removeFirst();
		}
		
		return result;
	}
}

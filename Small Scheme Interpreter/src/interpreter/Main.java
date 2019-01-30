package interpreter;

public class Main {
	
	public static void main(String[] args) {
		//	(begin (set! x 0) (while (< x 10) (begin (echo x) (set! x (+ x 1)))))
		
		SchemeInterpreter scInt = new SchemeInterpreter();
		scInt.startConsole();
		//System.out.println(interp.execute("(+ (+ 1 1) 2)"));
		
	}
}

package interpreter;


public class Main {
    public static void main(final String[] args) {
        // Example: (begin (set! x 0) (while (< x 10) (begin (echo x) (set! x (+ x 1)))))
        new SchemeInterpreter().startConsole();
    }
}

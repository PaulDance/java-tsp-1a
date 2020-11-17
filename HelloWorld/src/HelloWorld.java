public class HelloWorld {
    public static void main(final String[] args) {
        System.out.println("Hello, world!!!");

        for (int i = 0; i < args.length; i++) {
            System.out.println("args[" + i + "]: " + args[i]);
        }
    }
}

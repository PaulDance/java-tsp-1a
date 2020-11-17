public class PGCD {
    public static void main(final String[] args) {
        if (args.length != 2) {
            System.out.println("Il faut deux arguments.");
            System.exit(1);
        }

        int a = Integer.parseInt(args[0]), b = Integer.parseInt(args[1]), tmp = 0;

        if (a < b) {
            tmp = a;
            a = b;
            b = tmp;
        }

        while (b != 0) {
            tmp = b;
            b = a - b * (a / b);
            a = tmp;
        }

        System.out.println(a);
    }
}

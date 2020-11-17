public class Histogramme {
    public static void main(final String[] args) {
        final int[] tab = new int[args.length];
        int max = 0, min = 0;

        for (int i = 0; i < tab.length; i++) {
            tab[i] = Integer.parseInt(args[i]);

            if (tab[i] > max) {
                max = tab[i];
            }

            if (tab[i] < min) {
                min = tab[i];
            }
        }

        for (int i = max; i >= min; i--) {
            for (final int element: tab) {
                if (i == 0 && element == 0 || i > 0 && element > 0 && element >= i
                        || i < 0 && element < 0 && element <= i) {
                    System.out.print('*');
                } else if (i == 0 && element != 0) {
                    System.out.print('+');
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }
}

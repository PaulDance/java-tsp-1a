
public class QuickSort {

    public static void main(final String[] args) {
        final int[] array = QuickSort.randomIntArray(10, -10, 10);
        QuickSort.display(array);
        QuickSort.quicksort(array);
        QuickSort.display(array);
    }

    /**
     * Returns an int[<length>] array where the elements are randomly
     * distributed between <start> and <end>.
     */
    private static int[] randomIntArray(final int length, final int start, final int end) {
        final int[] array = new int[length];

        for (int i = 0; i < length; i++) {
            array[i] = (int) Math.rint(start + Math.random() * (end - start));
        }

        return array;
    }

    /**
     * Prints the given <array> to standard output, in an code-like format.
     */
    private static void display(final int[] array) {
        if (array.length == 0) {
            System.out.println("{}");
        } else {
            System.out.print('{');

            for (int i = 0; i < array.length - 1; i++) {
                System.out.print(array[i] + "; ");
            }

            System.out.println(array[array.length - 1] + "}");
        }
    }

    /**
     * Swaps out the elements at indices <i> and <j> in <array>.
     */
    private static void swap(final int i, final int j, final int[] array) {
        final int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    /**
     * Puts every element lesser than <array>[<end>] to its left and returns its
     * final index.
     */
    private static int partition(final int[] array, final int start, final int end) {
        int finalPivot = start;

        for (int i = start; i < end; i++) {
            if (array[i] < array[end]) {
                QuickSort.swap(i, finalPivot, array);
                finalPivot += 1;
            }
        }

        QuickSort.swap(end, finalPivot, array);
        return finalPivot;
    }

    /**
     * Implements the quicksort algorithm to the sub-array of <array> from
     * indices <start> to <end>.
     */
    private static void quicksort(final int[] array, final int start, final int end) {
        if (end > start) {
            final int pivot = QuickSort.partition(array, start, end);
            QuickSort.quicksort(array, start, pivot - 1);
            QuickSort.quicksort(array, pivot + 1, end);
        }
    }

    /**
     * Uses above quicksort to sort the entire <array>.
     */
    private static void quicksort(final int[] array) {
        QuickSort.quicksort(array, 0, array.length - 1);
    }
}

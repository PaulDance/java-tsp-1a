public class MergeSort {
    /** Demonstration. */
    public static void main(final String[] args) {
        final int[] array = MergeSort.randomIntArray(10, -10, 10);
        MergeSort.display(array);
        MergeSort.display(MergeSort.mergeSort(array));
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
     * Different signature, same idea but for matrices.
     */
    private static void display(final int[][] matrix) {
        for (final int[] element: matrix) {
            MergeSort.display(element);
        }
    }

    /**
     * Returns a 2-partition of the given <array> where the parts are as close
     * to equal lengths as possible.
     */
    private static int[][] partition(final int[] array) {
        // int used to distinguish whether the <array> has an odd (value: 0) or
        // even (value: 1) length.
        final int parity = array.length - 2 * (array.length / 2);
        final int[][] ret = {new int[array.length / 2 + parity], new int[array.length / 2]};

        for (int i = 0; i < array.length / 2 + parity; i++) {
            // The first "half" is put in the first part,
            ret[0][i] = array[i];

            if (i >= parity) {
                // the second in the second.
                ret[1][i - parity] = array[array.length / 2 + i];
            }
        }

        return ret;
    }

    /**
     * Merges two ascendantly sorted arrays and returns the sorted resulting
     * array.
     */
    private static int[] merge(final int[] array1, final int[] array2) {
        final int[] merged = new int[array1.length + array2.length];
        int i = 0, j = 0;

        // The merged array is set in a linear fashion,
        while (i < array1.length || j < array2.length) {
            // and the two given sorted arrays are traversed linearly also:
            if (j < array2.length && (i == array1.length || array2[j] < array1[i])) {
                // their beginnings are not accessed every time an element has
                // to be compared with the other array's,
                merged[i + j] = array2[j];
                // as we can take advantage of the fact that the arrays are
                // sorted.
                j++;
            } else if (i < array1.length || j == array2.length) {
                merged[i + j] = array1[i];
                i++;
            }
        }

        return merged;
    }

    /**
     * Implements the merge sort algorithm to return the sorted version of the
     * given <array>.
     */
    private static int[] mergeSort(final int[] array) {
        // The array is merged only if it contains at least two elements,
        if (array.length > 1) {
            // otherwise the partitioning does always the same, and an infinite
            // recursive loop is created.
            final int[][] parts = MergeSort.partition(array);
            return MergeSort.merge(MergeSort.mergeSort(parts[0]), MergeSort.mergeSort(parts[1]));
        } else {
            return array;
        }
    }
}

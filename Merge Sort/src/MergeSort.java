
public class MergeSort {

	public static void main(String[] args) {
		int[] array = randomIntArray(10, -10, 10);								// Demonstration.
		display(array);
		display(mergeSort(array));
	}
	
	private static int[] randomIntArray(int length, int start, int end) {		// Returns an int[<length>] array where the elements are randomly distributed between <start> and <end>.
		int[] array = new int[length];
		
		for (int i = 0; i < length; i++) {
			array[i] = (int) Math.rint(start + Math.random() * (end - start));
		}
		
		return array;
	}
	
	private static void display(int[] array) {									// Prints the given <array> to standard output, in an code-like format.
		if (array.length == 0) {
			System.out.println("{}");
		}
		else {
			System.out.print('{');
			
			for (int i = 0; i < array.length - 1; i++) {
				System.out.print(array[i] + "; ");
			}
			
			System.out.println(array[array.length - 1] + "}");
		}
	}
	
	private static void display(int[][] matrix) {								// Different signature, same idea but for matrices.
		for (int i = 0; i < matrix.length; i++) {
			display(matrix[i]);
		}
	}
	
	private static int[][] partition(int[] array) {								// Returns a 2-partition of the given <array> where the parts are as close to equal lengths as possible.
		int parity = array.length - 2 * (array.length / 2);						// int used to distinguish whether the <array> has an odd (value: 0) or even (value: 1) length.
		int[][] ret = {new int[array.length / 2 + parity], new int[array.length / 2]};
			
			for (int i = 0; i < array.length / 2 + parity; i++) {
				ret[0][i] = array[i];											// The first "half" is put in the first part,
				
				if (i >= parity) {
					ret[1][i - parity] = array[array.length / 2 + i];			// the second in the second.
				}
			}
			
			return ret;
	}
		
	private static int[] merge(int[] array1, int[] array2) {					// Merges two ascendantly sorted arrays and returns the sorted resulting array.
		int[] merged = new int[array1.length + array2.length];
		int i = 0, j = 0;
		
		while (i < array1.length || j < array2.length) {						// The merged array is set in a linear fashion,
			if (j < array2.length && (i == array1.length || array2[j] < array1[i])) {	// and the two given sorted arrays are traversed linearly also:
				merged[i + j] = array2[j];										// their beginnings are not accessed every time an element has to be compared with the other array's, 
				j++;															// as we can take advantage of the fact that the arrays are sorted.
			}
			else if (i < array1.length || j == array2.length){
				merged[i + j] = array1[i];
				i++;
			}
		}
		
		return merged;
	}
	
	private static int[] mergeSort(int[] array) {								// Implements the merge sort algorithm to return the sorted version of the given <array>.
		if (array.length > 1) {													// The array is merged only if it contains at least two elements,
			int[][] parts = partition(array);									// otherwise the partitioning does always the same, and an infinite recursive loop is created.
			return merge(mergeSort(parts[0]), mergeSort(parts[1]));
		}
		else {
			return array;
		}
	}
}

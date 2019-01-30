
public class QuickSort {

	public static void main(String[] args) {
		int[] array = randomIntArray(10, -10, 10);
		display(array);
		quicksort(array);
		display(array);
	}
	
	
	private static int[] randomIntArray(int length, int start, int end) {			// Returns an int[<length>] array where the elements are randomly distributed between <start> and <end>.
		int[] array = new int[length];
		
		for (int i = 0; i < length; i++) {
			array[i] = (int) Math.rint(start + Math.random() * (end - start));
		}
		
		return array;
	}
	
	private static void display(int[] array) {										// Prints the given <array> to standard output, in an code-like format.
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
	
	private static void swap(int i, int j, int[] array) {							// Swaps out the elements at indices <i> and <j> in <array>.
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
	
	private static int partition(int[] array, int start, int end) {					// Puts every element lesser than <array>[<end>] to its left and returns its final index.
		int finalPivot = start;
		
		for (int i = start; i < end; i++) {
			if (array[i] < array[end]) {
				swap(i, finalPivot, array);
				finalPivot += 1;
			}
		}
		
		swap(end, finalPivot, array);
		return finalPivot;
	}
	
	private static void quicksort(int[] array, int start, int end) {				// Implements the quicksort algorithm to the sub-array of <array> from indices <start> to <end>.
		if (end > start) {
			int pivot = partition(array, start, end);
			quicksort(array, start, pivot - 1);
			quicksort(array, pivot + 1, end);
		}
	}
	
	private static void quicksort(int[] array) {									// Uses above quicksort to sort the entire <array>.
		quicksort(array, 0, array.length - 1);
	}
}

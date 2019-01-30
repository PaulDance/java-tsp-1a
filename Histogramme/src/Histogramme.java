
public class Histogramme {

	public static void main(String[] args) {
		
		int[] tab = new int[args.length];
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
			for (int j = 0; j < tab.length; j++) {
				if ((i == 0 && tab[j] == 0) || (i > 0 && tab[j] > 0 && tab[j] >= i) || (i < 0 && tab[j] < 0 && tab[j] <= i)) {
					System.out.print('*');
				}
				else if (i == 0 && tab[j] != 0) {
					System.out.print('+');
				}
				else {
					System.out.print(' ');
				}
			}
			System.out.println();
		}
		
	}

}

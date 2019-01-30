
public class Calculette {

	public static void main(String[] args) {
		
		int[] tab = new int[args.length];
		int somme = 0;
		
		for (int i = 0; i < tab.length; i++) {
			tab[i] = Integer.parseInt(args[i]);
			System.out.println("args[" + i +"] : " + tab[i]);
			somme += tab[i];
		}
		
		System.out.println("Somme : " + somme);

	}

}

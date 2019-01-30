/*
 * Si une variable doit être désignée dans un commentaire, elle sera entre <>.
 * Les tableaux utilisés ici sont générés aléatoirement.
 * Si vous le souhaitez, l'exemple du sujet est disponible en commentaire.
*/


public class BubbleSort {

	public static void main(String[] args) {
		double ampli = 10, tmp = 0;								// Amplitude de la génération et variable temporaire pour réaliser les échanges.
		double[] tab = new double[5];							// Tableau de doubles nuls.
		//double[] tab = {2.3, 17.0, 3.14, 8.83, 7.26};			// Exemple du sujet.
		
		System.out.println("Tableau initial :");
		for (int i = 0; i < tab.length; i++) {
			tab[i] = 2 * ampli * (Math.random() - 0.5);			// Initialisation du tableau dont les éléments sont aléatoires entre -<ampli> et <ampli>. A commenter pour avoir l'exemple du sujet.
			System.out.print(tab[i] + " ");						// Affichage des éléments initiaux.
		}
		System.out.println("\n\nTableau trié :");
		
		
		// Tri à bulles.
		for (int i = tab.length - 1; i >= 1; i--) {				// On parcourt les éléments du tableau dans le sens inverse.
			for (int j = 0; j <= i - 1; j++) {					// Chaque élément d'indice inférieur ou égal à <i - 1>
				if (tab[j] > tab[j + 1]) {						// est comparé à son suivant
					tmp = tab[j];								// pour être échangés s'il est plus grand.
					tab[j] = tab[j + 1];
					tab[j + 1] = tmp;
				}
			}
		}
		
		
		for (int i = 0; i < tab.length; i++) {
			System.out.print(tab[i] + " ");						// Affichage des éléments du tableau après le tri.
		}
	}
}

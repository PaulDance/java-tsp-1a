/*
 * Si une variable doit être désignée dans un commentaire, elle sera entre <>.
 * Les tableaux utilisés ici sont générés aléatoirement.
 * Si vous le souhaitez, l'exemple du sujet est disponible en commentaire.
*/


public class InsertionSort {

	public static void main(String[] args) {
		int len = 5, dest = 0;											// Longueur du tableau et indice de tri.
		double ampli = 10;												// Amplitude de la génération.
		double[] tab = new double[len];									// Tableau d'entrée, non initialisé pour l'instant.
		double[] sorted = new double[len];								// Tableaux de sortie, gardé à zéro pour l'instant.
		//double[] tab = {8, 6, 1, 3, 5, 2}; len = tab.length;			// Exemple du sujet.
		
		System.out.println("Tableau initial :");
		for (int i = 0; i < tab.length; i++) {
			tab[i] = 2 * ampli * (Math.random() - 0.5);					// Initialisation du tableau dont les éléments sont aléatoires entre <-ampli> et <ampli>.
			System.out.print(tab[i] + " ");								// Affichage des éléments initiaux.
		}
		System.out.println("\n\nTableau trié :");
		
		//Tri par insertion :
		for (int i = 0; i < len; i++) {									// On traverse tous les éléments du tableau initial.
			dest = 0;													// L'indice de destination est remis à zéro à chaque nouvel élément.
			
			while (dest < i && tab[i] > sorted[dest]) {					// On cherche l'indice de destination jusqu'au nombre d'éléments insérés jusqu'ici, c'est-à-dire <i>.
				dest += 1;
			}
			
			for (int j = len - 1; j > dest; j--) {						// Une fois l'indice de destination trouvé, on décale tous les éléments à sa droite,
				sorted[j] = sorted[j - 1];
			}
			
			sorted[dest] = tab[i];										// et on met l'élément d'indide i à la destination.
		}
		
		
		for (int i = 0; i < len; i++) {
			System.out.print(sorted[i] + " ");							// Affichage des éléments du tableau après le tri.
		}
	}
}

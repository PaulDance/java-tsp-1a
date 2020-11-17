/*
 * Si une variable doit être désignée dans un commentaire, elle sera entre <>.
 * Les tableaux utilisés ici sont générés aléatoirement. Si vous le souhaitez,
 * l'exemple du sujet est disponible en commentaire.
 */

public class InsertionSort {
    public static void main(final String[] args) {
        // Longueur du tableau et indice de tri.
        final int len = 5;
        int dest = 0;
        // Amplitude de la génération.
        final double ampli = 10;
        // Tableau d'entrée, non initialisé pour l'instant.
        final double[] tab = new double[len];
        // Tableaux de sortie, gardé à zéro pour l'instant.
        final double[] sorted = new double[len];
        // double[] tab = {8, 6, 1, 3, 5, 2}; len = tab.length;

        // Initialisation du tableau dont les éléments sont aléatoires entre
        // <-ampli> et <ampli>.
        System.out.println("Tableau initial :");
        for (int i = 0; i < tab.length; i++) {
            tab[i] = 2 * ampli * (Math.random() - 0.5);
            System.out.print(tab[i] + " ");
        }
        System.out.println("\n\nTableau trié :");

        // Tri par insertion :
        // On traverse tous les éléments du tableau initial.
        for (int i = 0; i < len; i++) {
            // L'indice de destination est remis à zéro à chaque nouvel élément.
            dest = 0;

            // On cherche l'indice de destination jusqu'au nombre d'éléments
            // insérés jusqu'ici, c'est-à-dire <i>.
            while (dest < i && tab[i] > sorted[dest]) {
                dest += 1;
            }

            // Une fois l'indice de destination trouvé, on décale tous les
            // éléments à sa droite,
            for (int j = len - 1; j > dest; j--) {
                sorted[j] = sorted[j - 1];
            }

            // et on met l'élément d'indide i à la destination.
            sorted[dest] = tab[i];
        }

        // Affichage des éléments du tableau après le tri.
        for (int i = 0; i < len; i++) {
            System.out.print(sorted[i] + " ");
        }
    }
}

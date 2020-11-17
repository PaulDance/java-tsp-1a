/*
 * Si une variable doit être désignée dans un commentaire, elle sera entre <>.
 * Les tableaux utilisés ici sont générés aléatoirement. Si vous le souhaitez,
 * l'exemple du sujet est disponible en commentaire.
 */

public class BubbleSort {
    public static void main(final String[] args) {
        // Amplitude de la génération et variable temporaire pour réaliser les
        // échanges.
        final double ampli = 10;
        double tmp = 0;
        // Tableau de doubles nuls.
        final double[] tab = new double[5];
        // double[] tab = {2.3, 17.0, 3.14, 8.83, 7.26};

        // Initialisation du tableau dont les éléments sont aléatoires entre
        // -<ampli> et <ampli>. A commenter pour avoir l'exemple du sujet.
        System.out.println("Tableau initial :");
        for (int i = 0; i < tab.length; i++) {
            tab[i] = 2 * ampli * (Math.random() - 0.5);
            System.out.print(tab[i] + " ");
        }
        System.out.println("\n\nTableau trié :");

        // Tri à bulles.
        // On parcourt les éléments du tableau dans le sens inverse.
        for (int i = tab.length - 1; i >= 1; i--) {
            // Chaque élément d'indice inférieur ou égal à <i - 1>
            for (int j = 0; j <= i - 1; j++) {
                // est comparé à son suivant pour être échangés si plus grand.
                if (tab[j] > tab[j + 1]) {
                    tmp = tab[j];
                    tab[j] = tab[j + 1];
                    tab[j + 1] = tmp;
                }
            }
        }

        // Affichage des éléments du tableau après le tri.
        for (final double element: tab) {
            System.out.print(element + " ");
        }
    }
}

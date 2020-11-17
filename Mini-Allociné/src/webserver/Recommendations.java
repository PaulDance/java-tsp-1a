package webserver;

import java.util.LinkedList;


/**
 * Defines a wrapper class around a {@link LinkedList} of {@link String} to
 * limit its uses to the current application's.
 * 
 * @author Paul Mabileau
 * @see    Film
 */
public class Recommendations {
    private final LinkedList<String> recommendations;

    public Recommendations() {
        this.recommendations = new LinkedList<String>();
    }

    /**
     * Takes the given {@code recommendation} parameter and adds it at the end
     * of the internal {@link #recommendations} {@link LinkedList} of
     * {@link String}.
     * 
     * @param recommendation : a {@link String} to be added to the collection.
     */
    public void addRecommendation(final String recommendation) {
        this.recommendations.addLast(recommendation);
    }

    /**
     * @return The internal {@link LinkedList} of {@link String} that contains
     *         the recommendations.
     */
    public LinkedList<String> getRecommendations() {
        return this.recommendations;
    }

    /**
     * Uses the {@link LinkedList} of {@link String} {@code toString} method to
     * return a string representation of the {@link #recommendations} object.
     */
    @Override
    public String toString() {
        return this.recommendations.toString();
    }
}

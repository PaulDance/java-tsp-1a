package webserver;

import java.util.Hashtable;
import java.util.Set;


/**
 * Uses an internal {@link java.util.Hashtable} to create and manage a database
 * containing movies. The table associates a {@link Film} object (title and
 * year) to a {@link Recommendations} object. This class provides ways to add a
 * new {@link Film} to the database with {@link #create(String, int)} and
 * retrieve its {@link Recommendations} with {@link #lookup(String, int)}
 * 
 * @author Paul Mabileau
 * @see    Film
 * @see    Recommendations
 */
public class FilmDB {
    private final Hashtable<Film, Recommendations> dbTable;

    public FilmDB() {
        this.dbTable = new Hashtable<Film, Recommendations>();
    }

    /**
     * Uses the hashtable's toString() method to return a string representation
     * of the database object.
     */
    @Override
    public String toString() {
        return this.dbTable.toString();
    }

    /**
     * Takes in a title and a year as parameters to create the corresponding new
     * {@link Film} object and associate it with a new empty
     * {@link Recommendations} object in the {@link #dbTable} hashtable.
     * 
     * @param  title                      : the {@link String} that will be the
     *                                    new {@link Film}'s title.
     * @param  year                       : the {@code int} that will be the new
     *                                    {@link Film}'s year.
     * @throws FilmAlreadyExistsException in case the movie is already contained
     *                                    int the hashtable as a key.
     * @see                               Hashtable
     */
    public void create(final String title, final int year) throws FilmAlreadyExistsException {
        final Film newFilm = new Film(title, year);

        if (this.dbTable.containsKey(newFilm)) {
            throw new FilmAlreadyExistsException(
                    "The movie " + newFilm.toString() + " already exists in the database.");
        } else {
            this.dbTable.put(newFilm, new Recommendations());
        }
    }

    /**
     * Looks for the corresponding {@link Film} in the {@link #dbTable}
     * hashtable and returns its {@link Recommendations} if found, throws an
     * exception otherwise.
     * 
     * @param  title                     : the {@link String} that is the sought
     *                                   {@link Film}'s title.
     * @param  year                      : the {@link String} that is the sought
     *                                   {@link Film}'s year.
     * @return                           The {@link Recommendations} object of
     *                                   the found {@link Film} object.
     * @throws FilmDoesNotExistException in case the {@link Film} does not exist
     *                                   in the database.
     * @see                              Hashtable
     */
    public Recommendations lookup(final String title, final int year)
            throws FilmDoesNotExistException {
        final Film film = new Film(title, year);

        if (this.dbTable.containsKey(film)) {
            return this.dbTable.get(film);
        } else {
            throw new FilmDoesNotExistException();
        }
    }

    /**
     * Used only in the {@link Server} class to display every movie at once.
     * 
     * @return The {@link #dbTable}'s key {@link Set}.
     * @see    Server
     */
    public Set<Film> getFilms() {
        return this.dbTable.keySet();
    }
}

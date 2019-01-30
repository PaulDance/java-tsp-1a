package webserver;

/**
 * {@link Exception} class thrown when a {@link Film} is being looked up in a database, but is not in it.
 * @author Paul Mabileau
 */
public class FilmDoesNotExistException extends Exception {
	private static final long serialVersionUID = -1026829147210059516L;

	public FilmDoesNotExistException(String msg) {
		super(msg);
	}
	
	public FilmDoesNotExistException() {
		super();
	}
}

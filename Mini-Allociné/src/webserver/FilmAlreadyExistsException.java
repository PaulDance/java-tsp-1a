package webserver;

/**
 * {@link Exception} class thrown when a {@link Film} is meant to be added in a database, but is already in it.
 * @author Paul Mabileau
 */
public class FilmAlreadyExistsException extends Exception {
	private static final long serialVersionUID = -846232556559120811L;

	public FilmAlreadyExistsException(String msg) {
		super(msg);
	}
	
	public FilmAlreadyExistsException() {
		super();
	}
}

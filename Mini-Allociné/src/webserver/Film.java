package webserver;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Defines a tuple class of String * int describing a movie in a {@link FilmDB} database.
 * Overrides the {@link java.lang.Object#toString()} and {@link java.lang.Object#equals(Object)} methods to adapt them
 * to the current application.
 * @see #toString()
 * @see #equals(Object)
 * @author Paul Mabileau
 */
public class Film {
	private String title;
	private int year;
	
	public Film(String title, int year) {
		this.title = title;
		this.year = year;
	}
	
	/**
	 * @return The {@link String} representing this {@link Film} object by following the format {@code (title, year)}.
	 * @author Paul Mabileau
	 */
	public String toString() {
		return "('" + this.title + "', " + this.year + ")";
	}
	
	/**
	 * @return The {@link String} that is the HTML code for a link pointing to {@code this} in the {@link Server}. The title is encoded in UTF-8 web encoding.
	 * @throws UnsupportedEncodingException thrown by {@link URLEncoder#encode(String, String)} if the {@link #title} format is not UTF-8 compatible.
	 * @author Paul Mabileau
	 */
	public String asHTML() throws UnsupportedEncodingException {
		return "<a href='/" + URLEncoder.encode(this.title, "UTF-8") + "/" + this.year + "'>" + this.title + " (" + this.year + ")</a>";
	}
	
	/**
	 * @param obj : an instance of {@link Object}
	 * @return true if and only if the obj paramater is an instance of {@link Film} and it has the same title and year as {@code this}.
	 * @author Paul Mabileau
	 */
	public boolean equals(Object obj) {
		if (obj instanceof Film) {
			return this.title.equals(((Film) obj).title) && this.year == ((Film) obj).year;
		}
		else {
			return false;
		}
	}
	
	/**
	 * @return The hash code of {@code this}' title's.
	 * @author Paul Mabileau
	 */
	public int hashCode() {
		return this.title.hashCode();
	}
}

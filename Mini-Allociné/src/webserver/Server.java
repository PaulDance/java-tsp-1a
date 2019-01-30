package webserver;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * This class builds a server infrastructure on the local machine, meant to host a tiny web site
 * that displays the content of a {@link FilmDB} database on the server document root {@code /},
 * and each {@link Film}'s recommendations on a other page respecting the URL format {@code /utf_8_webencode(title)/year}.
 * It provides really only two methods: the constructor {@link #Server(FilmDB, int)} and {@link #start()}.
 * @author Paul Mabileau
 * @see Film
 * @see FilmDB
 * @see Recommendations
 */
public class Server {
	private ServerSocket connSocket;
	private Socket commSocket;
	private BufferedReader inputReader;
	private PrintStream outputStream;
	private FilmDB db;
	
	/**
	 * Constructs a {@link Server} instance that creates a TCP/IP connection socket on the given {@code port} parameter, which it later listens to.
	 * It also remembers the given {@code db} parameter in a private field.
	 * @param db : a {@link FilmDB} database.
	 * @param port : an {@code int} representing the server's listening port.
	 * @throws IOException in case there was a mistake in connecting or communicating with the server.
	 * @author Paul Mabileau
	 * @see #start()
	 */
	public Server(FilmDB db, int port) throws IOException {
		this.db = db;
		this.connSocket = new ServerSocket(port);
	}
	
	/**
	 * Starts the server on the port given at construction. Indefinitely, it builds a communication socket on any free port when receiving a connection request
	 * on the connection socket, then plugs in an {@link InputStreamReader} to receive on the communication socket and a {@link PrintStream} for the output to the client.
	 * It passes every TCP/IP request to {@link #handleInput()}.
	 * @throws IOException in case there was a mistake in connecting or communicating with the server, but tries to catch it and display debug information first.
	 * @author Paul Mabileau
	 * @see #handleInput()
	 */
	public void start() throws IOException {						// Debug server information:
		System.out.println("Server started on local port " + this.connSocket.getLocalPort());
		
		while (true) {
			try {
				this.commSocket = this.connSocket.accept();																// Wait for a connection,
				System.out.println("Server received connection from " + this.commSocket.getInetAddress()				// print to console who asked for it,
					+ ":" + this.commSocket.getPort() + " on local port " + this.commSocket.getLocalPort() + ".");
				
				this.inputReader = new BufferedReader(new InputStreamReader(this.commSocket.getInputStream()));			// start the input and
				this.outputStream = new PrintStream(this.commSocket.getOutputStream());									// output streams,
				this.handleInput();																						// handle the received input sequence,
				
				this.inputReader.close();																				// close all a'dem hoes,
				this.outputStream.close();
				this.commSocket.close();
			}																											// start again.
			catch (IOException exc) {						// In case of a communication or connection mistake,
				System.out.println(exc.getMessage());		// the error message is passed to the console.
			}
		}
	}
	
	/**
	 * Uses the created input and output streams to interpret the received TCP/IP request and return (through the network) the HTML code meant to create the web site. 
	 * @throws IOException
	 * @author Paul Mabileau
	 * @see Server
	 */
	private void handleInput() throws IOException {
		String input = null;
		
		if (this.inputReader.ready()) {							// Read the input,
			input = this.inputReader.readLine();				// if it's not an empty (ping ?) request.
		}
		
		if (input != null) {									// If there is a request to potentially grant,
			System.out.println("Got request: " + input);
			this.outputStream.println("HTTP/1.1 200 OK\n\n<!doctype html>\n\n<html>\n<head>\n<meta charset=\"utf-8\"/>\n</head>\n\n<body>");
			
			if (input.equals("GET / HTTP/1.1")) {				// if only the root is requested,
				this.outputStream.println("<title>Films</title>");
				this.outputStream.println("<h1>Films</h1>");
				this.outputStream.println("<ul>");
				
				for (Film film: this.db.getFilms()) {			// then display the list of links to the {@link Film} objects contained in the {@link FilmDB} database;
					this.outputStream.println("<li>" + film.asHTML() + "</li>");
				}
				
				this.outputStream.println("</ul>\n");
				System.out.println("Request granted.\n");
			}													// else if a movie is requested,
			else if (input.matches("GET\\s\\/[a-zA-Z0-9_\\-\\%\\(\\)\\+]*\\/[0-9]*\\sHTTP/1.1")) {
				String[] args = input.split(" ")[1].split("/");
				
				if (!args[1].contains(".")) {					// and not a file (like favicon.ico that is just ignored here),
					try {										// then display the movie's title, year and recommendations;
						this.outputStream.println("<title>" + URLDecoder.decode(args[1], "UTF-8") + " (" + args[2] + ")</title>");
						this.outputStream.println("<h1>" + URLDecoder.decode(args[1], "UTF-8") + " (" + args[2] + ")</h1>");
						this.outputStream.println("\n<h3>Recommandations :</h3>\n<ul>");
						
						for (String reco: this.db.lookup(URLDecoder.decode(args[1], "UTF-8"), Integer.parseInt(args[2])).getRecommendations()) {
							this.outputStream.println("<li>" + reco + "</li>");
						}
						
						this.outputStream.println("</ul>");
					}
					catch (NumberFormatException | FilmDoesNotExistException e) {
						this.outputStream.println("<h2>404 Not Found</h2>\nThe requested URL /" + args[1] + "/" + args[2] + " was not found on this server.");
						System.out.println("Absent resource request: /" + args[1] + "/" + args[2] + "\n");
						e.printStackTrace();					// a 404 message is answered if an inexistent movie is requested;
					}
					
					this.outputStream.println("\n<a href='/'>Retour</a>");
					System.out.println("Request granted.\n");
				}
			}
			else {												// a bad request message is answered if an unsupported protocol is requested;
				this.outputStream.println("<h2>Bad request</h2>\n The request '" + input + "' is not supported by this server.");
				System.out.println("Unhandled request: " + input + "\n");
			}
			
			this.outputStream.println("</body>\n</html>");
		}
		else {													// otherwise, ignore and display debug information.
			System.out.println("No request.\n");
		}
	}
}

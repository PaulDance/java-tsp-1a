package webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;


/**
 * This class builds a server infrastructure on the local machine, meant to host
 * a tiny web site that displays the content of a {@link FilmDB} database on the
 * server document root {@code /}, and each {@link Film}'s recommendations on a
 * other page respecting the URL format {@code /utf_8_webencode(title)/year}. It
 * provides really only two methods: the constructor
 * {@link #Server(FilmDB, int)} and {@link #start()}.
 * 
 * @author Paul Mabileau
 * @see    Film
 * @see    FilmDB
 * @see    Recommendations
 */
public class Server {
    private final ServerSocket connSocket;
    private Socket commSocket;
    private BufferedReader inputReader;
    private PrintStream outputStream;
    private final FilmDB db;

    /**
     * Constructs a {@link Server} instance that creates a TCP/IP connection
     * socket on the given {@code port} parameter, which it later listens to. It
     * also remembers the given {@code db} parameter in a private field.
     * 
     * @param  db          : a {@link FilmDB} database.
     * @param  port        : an {@code int} representing the server's listening
     *                     port.
     * @throws IOException in case there was a mistake in connecting or
     *                     communicating with the server.
     * @author             Paul Mabileau
     * @see                #start()
     */
    public Server(final FilmDB db, final int port) throws IOException {
        this.db = db;
        this.connSocket = new ServerSocket(port);
    }

    /**
     * Starts the server on the port given at construction. Indefinitely, it
     * builds a communication socket on any free port when receiving a
     * connection request on the connection socket, then plugs in an
     * {@link InputStreamReader} to receive on the communication socket and a
     * {@link PrintStream} for the output to the client. It passes every TCP/IP
     * request to {@link #handleInput()}.
     * 
     * @throws IOException in case there was a mistake in connecting or
     *                     communicating with the server, but tries to catch it
     *                     and display debug information first.
     * @author             Paul Mabileau
     * @see                #handleInput()
     */
    public void start() throws IOException {
        System.out.println("Server started on local port " + this.connSocket.getLocalPort());

        while (true) {
            try {
                // Wait for a connection,
                this.commSocket = this.connSocket.accept();
                System.out.println("Server received connection from "
                        + this.commSocket.getInetAddress() + ":" + this.commSocket.getPort()
                        + " on local port " + this.commSocket.getLocalPort() + ".");

                // start the input and output streams,
                this.inputReader = new BufferedReader(
                        new InputStreamReader(this.commSocket.getInputStream()));
                this.outputStream = new PrintStream(this.commSocket.getOutputStream());
                // handle the received input sequence,
                this.handleInput();

                // close all a'dem hoes,
                this.inputReader.close();
                this.outputStream.close();
                this.commSocket.close();
                // start again.
            } catch (final IOException exc) {
                // In case of a communication or connection mistake,
                // the error message is passed to the console.
                System.out.println(exc.getMessage());
            }
        }
    }

    /**
     * Uses the created input and output streams to interpret the received
     * TCP/IP request and return (through the network) the HTML code meant to
     * create the web site.
     * 
     * @throws IOException
     * @author             Paul Mabileau
     * @see                Server
     */
    private void handleInput() throws IOException {
        String input = null;

        // Read the input if it's not an empty (ping ?) request.
        if (this.inputReader.ready()) {
            input = this.inputReader.readLine();
        }

        // If there is a request to potentially grant,
        if (input != null) {
            System.out.println("Got request: " + input);
            this.outputStream.println(
                    "HTTP/1.1 200 OK\n\n<!doctype html>\n\n<html>\n<head>\n<meta charset=\"utf-8\"/>\n</head>\n\n<body>");

            // if only the root is requested,
            if (input.equals("GET / HTTP/1.1")) {
                this.outputStream.println("<title>Films</title>");
                this.outputStream.println("<h1>Films</h1>");
                this.outputStream.println("<ul>");

                // then display the list of links to the {@link Film} objects
                // contained in the {@link FilmDB} database;
                for (final Film film: this.db.getFilms()) {
                    this.outputStream.println("<li>" + film.asHTML() + "</li>");
                }

                this.outputStream.println("</ul>\n");
                System.out.println("Request granted.\n");
            } else if (input.matches("GET\\s\\/[a-zA-Z0-9_\\-\\%\\(\\)\\+]*\\/[0-9]*\\sHTTP/1.1")) {
                // else if a movie is requested,
                final String[] args = input.split(" ")[1].split("/");

                // and not a file (like favicon.ico that is just ignored here),
                if (!args[1].contains(".")) {
                    // then display the movie's title, year and recommendations;
                    try {
                        this.outputStream.println("<title>" + URLDecoder.decode(args[1], "UTF-8")
                                + " (" + args[2] + ")</title>");
                        this.outputStream.println("<h1>" + URLDecoder.decode(args[1], "UTF-8")
                                + " (" + args[2] + ")</h1>");
                        this.outputStream.println("\n<h3>Recommandations :</h3>\n<ul>");

                        for (final String reco: this.db.lookup(URLDecoder.decode(args[1], "UTF-8"),
                                Integer.parseInt(args[2])).getRecommendations()) {
                            this.outputStream.println("<li>" + reco + "</li>");
                        }

                        this.outputStream.println("</ul>");
                    } catch (NumberFormatException | FilmDoesNotExistException e) {
                        // a 404 message is answered if an inexistent movie is
                        // requested;
                        this.outputStream.println("<h2>404 Not Found</h2>\nThe requested URL /"
                                + args[1] + "/" + args[2] + " was not found on this server.");
                        System.out.println(
                                "Absent resource request: /" + args[1] + "/" + args[2] + "\n");
                        e.printStackTrace();
                    }

                    this.outputStream.println("\n<a href='/'>Retour</a>");
                    System.out.println("Request granted.\n");
                }
            } else {
                // a bad request message is answered if an unsupported protocol
                // is requested;
                this.outputStream.println("<h2>Bad request</h2>\n The request '" + input
                        + "' is not supported by this server.");
                System.out.println("Unhandled request: " + input + "\n");
            }

            this.outputStream.println("</body>\n</html>");
        } else {
            // otherwise, ignore and display debug information.
            System.out.println("No request.\n");
        }
    }
}

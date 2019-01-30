package webserver;
import java.io.IOException;


public class Main {

	public static void main(String[] args) throws FilmAlreadyExistsException, FilmDoesNotExistException, IOException {
		FilmDB db = new FilmDB();
		
		db.create("Evil Dead",			1981);
		db.create("Evil Dead",			2013);
		db.create("Fanfan la Tulipe",	1952);
		db.create("Fanfan la Tulipe",	2003);
		db.create("Mary a tout prix",	1998);
		db.create("Black Sheep",		2008);

		db.lookup("Evil Dead",			1981)	.addRecommendation("Ouh ! Mais ça fait peur !"				);
		db.lookup("Evil Dead",			2013)	.addRecommendation("Même pas peur !"						);
		db.lookup("Evil Dead",			2013)	.addRecommendation("Insipide et sans saveur"				);
		db.lookup("Fanfan la Tulipe",	1952)	.addRecommendation("Manque de couleurs"						);
		db.lookup("Fanfan la Tulipe",	1952)	.addRecommendation("Supers scènes de combat"				);
		db.lookup("Fanfan la Tulipe",	2003)	.addRecommendation("Mais pourquoi ???"						);
		db.lookup("Mary a tout prix",	1998)	.addRecommendation("Le meilleur film de tous les temps"		);
		db.lookup("Black Sheep",		2008)	.addRecommendation("Un scénario de génie"					);
		db.lookup("Black Sheep",		2008)	.addRecommendation("Une réalisation parfaite"				);
		db.lookup("Black Sheep",		2008)	.addRecommendation("A quand Black Goat ?"					);
		
		Server srv = new Server(db, 8080);
		srv.start();
	}
}

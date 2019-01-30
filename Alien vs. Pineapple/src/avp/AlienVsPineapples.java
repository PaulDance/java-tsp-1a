package avp;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Iterator;
import avp.Settings;


public class AlienVsPineapples extends Application {
	private int score = 0;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) {
		Group group = new Group();
		Scene scene = new Scene(group);
		stage.setWidth(Settings.winWidth);
		stage.setHeight(Settings.winHeight);
		stage.setResizable(false);
		stage.setTitle("Alien vs. Pineapples");
		stage.setScene(scene);
		
		Canvas canvas = new Canvas(Settings.winWidth, Settings.winHeight);
		group.getChildren().add(canvas);
		
		GraphicsContext canGc = canvas.getGraphicsContext2D();
		canGc.setFont(Font.font("Helvetica", FontWeight.BOLD, 24));
		canGc.setFill(Color.BISQUE);
		canGc.setStroke(Color.RED);
		canGc.setLineWidth(1);
		
		Image background = new Image("space.jpg");
		ArrayList<Sprite> pineArmy = new ArrayList<Sprite>(Settings.numPines);
		Sprite alien = new Sprite("alien.png", 62, 36);
		alien.setPos(Settings.winWidth / 2, Settings.winHeight / 2);
		
		for (int i = 0; i < Settings.numPines; i++) {
			pineArmy.add(new Sprite("pineapple.png", 19, 36));
			pineArmy.get(i).setPos(	Math.random() * (Settings.winWidth - pineArmy.get(i).getWidth() - Settings.xFix),
									Math.random() * (Settings.winHeight - pineArmy.get(i).getHeight() - Settings.yFix));
			pineArmy.get(i).setSpeed(10 * Math.random() - 5, 10 * Math.random() - 5);
		}
		
		new AnimationTimer() {
			public void handle(long now) {
				canGc.drawImage(background, 0, 0);
				canGc.fillText("Score: " + AlienVsPineapples.this.score, Settings.winWidth - 160, 40);
				alien.render(canGc);
				alien.update();
				
				Iterator<Sprite> pinesItor = pineArmy.iterator();
				Sprite tmp;
				
				while (pinesItor.hasNext()) {
					tmp = pinesItor.next();
					
					if (alien.intersects(tmp)) {
						pinesItor.remove();
						AlienVsPineapples.this.score += 1;
					}
					else {
						tmp.render(canGc);
						tmp.update();
					}
				}
			}
		}.start();
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent> () {
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
					case LEFT:
						if (Math.abs(alien.getxSpeed()) < Settings.maxAlienSpeed || alien.getxSpeed() >= 0) {
							alien.setxSpeed(alien.getxSpeed() - Settings.ctrlAcc);
						}
						break;
					case RIGHT:
						if (Math.abs(alien.getxSpeed()) < Settings.maxAlienSpeed || alien.getxSpeed() <= 0) {
							alien.setxSpeed(alien.getxSpeed() + Settings.ctrlAcc);
						}
						break;
					case UP:
						if (Math.abs(alien.getySpeed()) < Settings.maxAlienSpeed || alien.getySpeed() >= 0) {
							alien.setySpeed(alien.getySpeed() - Settings.ctrlAcc);
						}
						break;
					case DOWN:
						if (Math.abs(alien.getySpeed()) < Settings.maxAlienSpeed || alien.getySpeed() <= 0) {
							alien.setySpeed(alien.getySpeed() + Settings.ctrlAcc);
						}
						break;
					case ESCAPE:
						try {
							AlienVsPineapples.this.stop();
						} catch (Exception e) {}
						break;
					default:
						break;
				}
			}
		});
		
		scene.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				alien.setPos(event.getX() - alien.getWidth() / 2, event.getY() - alien.getHeight() / 2);
				alien.setSpeed(0, 0);
			}
		});
		
		scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				alien.setPos(event.getX() - alien.getWidth() / 2, event.getY() - alien.getHeight() / 2);
				alien.setSpeed(0, 0);
			}
		});
		
		stage.show();
	}
}
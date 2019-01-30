package avp;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class Sprite {
	private double x = 0, y = 0, xSpeed = 0, ySpeed = 0, width = 0, height = 0;
	private final Image image;
	
	public Sprite(String imageName, double width, double height) {
		this.image = new Image(imageName, width, height, true, true);
		this.width = width;
		this.height = height;
	}
	
	public void render(GraphicsContext gc) {
		gc.drawImage(this.image, this.x, this.y, this.width, this.height);
	}
	
	public void renderAt(GraphicsContext gc, double x, double y) {
		gc.drawImage(this.image, x, y, this.width, this.height);
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getxSpeed() {
		return xSpeed;
	}

	public void setxSpeed(double xSpeed) {
		this.xSpeed = xSpeed;
	}

	public double getySpeed() {
		return ySpeed;
	}

	public void setySpeed(double ySpeed) {
		this.ySpeed = ySpeed;
	}

	public double getWidth() {
		return this.width;
	}
	
	public double getHeight() {
		return this.height;
	}
	
	public void setPos(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getSpeed() {
		return Math.sqrt(this.xSpeed * this.xSpeed + this.ySpeed * this.ySpeed);
	}
	
	public void setSpeed(double xSpeed, double ySpeed) {
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}
	
	public void update() {
		this.validatePosition();
		this.x += this.xSpeed;
		this.y += this.ySpeed;
	}
	
	public boolean intersects(Sprite otherSprite) {
//		if (this.x >= otherSprite.x + otherSprite.width || otherSprite.x >= this.x + this.width) {
//			return false;
//		}
//		
//		if (this.y >= otherSprite.y + otherSprite.height || otherSprite.y >= this.y + this.height) {
//			return false;
//		}
//		
//		return true;
		return 	!(	this.x			>=	otherSprite.x	+	otherSprite.width
				|| 	otherSprite.x	>=	this.x			+	this.width
				||	this.y			>=	otherSprite.y	+	otherSprite.height
				|| 	otherSprite.y	>=	this.y			+	this.height
				);
	}
	
	private void validatePosition() {
		if (this.x <= 0 || this.x + this.width + Settings.xFix >= Settings.winWidth) {
			this.xSpeed *= -1;
		}
		if (this.y <= 0 || this.y + this.height + Settings.yFix >= Settings.winHeight) {
			this.ySpeed *= -1;
		}
	}
}
package avp;

import java.awt.Image;

import javafx.scene.canvas.GraphicsContext;


public class Sprite {
    private double x = 0, y = 0, xSpeed = 0, ySpeed = 0, width = 0, height = 0;
    private final Image image;

    public Sprite(final String imageName, final double width, final double height) {
        this.image = new Image(imageName, width, height, true, true);
        this.width = width;
        this.height = height;
    }

    public void render(final GraphicsContext gc) {
        gc.drawImage(this.image, this.x, this.y, this.width, this.height);
    }

    public void renderAt(final GraphicsContext gc, final double x, final double y) {
        gc.drawImage(this.image, x, y, this.width, this.height);
    }

    public double getX() {
        return this.x;
    }

    public void setX(final double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(final double y) {
        this.y = y;
    }

    public double getxSpeed() {
        return this.xSpeed;
    }

    public void setxSpeed(final double xSpeed) {
        this.xSpeed = xSpeed;
    }

    public double getySpeed() {
        return this.ySpeed;
    }

    public void setySpeed(final double ySpeed) {
        this.ySpeed = ySpeed;
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    public void setPos(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double getSpeed() {
        return Math.sqrt(this.xSpeed * this.xSpeed + this.ySpeed * this.ySpeed);
    }

    public void setSpeed(final double xSpeed, final double ySpeed) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public void update() {
        this.validatePosition();
        this.x += this.xSpeed;
        this.y += this.ySpeed;
    }

    public boolean intersects(final Sprite otherSprite) {
        return !(this.x >= otherSprite.x + otherSprite.width || otherSprite.x >= this.x + this.width
                || this.y >= otherSprite.y + otherSprite.height
                || otherSprite.y >= this.y + this.height);
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

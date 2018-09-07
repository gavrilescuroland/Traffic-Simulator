import java.awt.*;

public class Vehicle {

    // Attributes
    private int x;
    private int y;
    private int width = 0;
    private int height = 0;
    private int speed;

    // Constructor
    public Vehicle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void paintMe(Graphics g) {

    }

    // Getters and Setters
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }

    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }



}

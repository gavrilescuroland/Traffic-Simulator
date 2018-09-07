import java.awt.*;

public class Car extends Vehicle {

    // Constructor
    public Car(int x, int y) {
        super(x, y);
        setWidth(50);
        setHeight(30);
        setSpeed(8);
    }

    @Override
    public void paintMe(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}

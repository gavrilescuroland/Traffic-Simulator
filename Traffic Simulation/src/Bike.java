import java.awt.*;

public class Bike extends Vehicle {

    // Constructor
    public Bike(int x, int y) {
        super(x, y);
        setWidth(40);
        setHeight(20);
        setSpeed(12);
    }

    @Override
    public void paintMe(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }

}

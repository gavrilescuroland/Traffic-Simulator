import java.awt.*;

public class Truck extends Vehicle {

    // Constructor
    public Truck(int x, int y) {
        super(x, y);
        setWidth(110);
        setHeight(50);
        setSpeed(5);
    }

    @Override
    public void paintMe(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }



}

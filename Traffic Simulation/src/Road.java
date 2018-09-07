import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class Road extends JPanel {

    // Variables
    public static final int LANE_HEIGHT = 120;
    public static final int ROAD_WIDTH = 1000;
    private ArrayList<Vehicle> vehicles = new ArrayList<>(); // Truck is-a Vehicle
    private int carCount = 0;


    //Constructor
    public Road() {
        super();
    }

    public void addVehicle (Vehicle v) {
        vehicles.add(v);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Display road
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Display road lines
        g.setColor(Color.WHITE);
        for (int i = LANE_HEIGHT; i < LANE_HEIGHT * 4; i += LANE_HEIGHT) {
            for (int j = 0; j < getWidth(); j += 40) {
                g.fillRect(j, i, 30, 5);
            }
        }
        // Display cars
        for (Vehicle v : vehicles) {
            v.paintMe(g);
        }
    }

    public void step() {
        // move vehicle (only if it doesn't collide with another)
        for (Vehicle v : vehicles) {
            if (!collision(v.getX() + v.getSpeed(), v.getY(), v)) {
                v.setX(v.getX() + v.getSpeed());

                // if reach end (without collision), start again
                if (v.getX() > ROAD_WIDTH) {
                    if (!collision(0, v.getY(), v)) {
                        v.setX(-v.getWidth());
                        carCount++;

                    }
                }
            } else { // switch lanes
                // not in leftmost lane and doesn't collide
                if (v.getY() > 40 && !collision(v.getX(), v.getY() - LANE_HEIGHT, v)) {
                        v.setY(v.getY() - LANE_HEIGHT);
                }
                // in leftmost lane
                else if ((v.getY() < 40 + 3*LANE_HEIGHT) &&
                        !collision(v.getX(), v.getY() - LANE_HEIGHT, v)) {
                    v.setY(v.getY() + LANE_HEIGHT);

                }
            }

        }
    }


    public boolean collision(int x, int y, Vehicle v1) {

        for (Vehicle v2 : vehicles) {
            // if in the same lane, different vehicles and they collide
            if (y == v2.getY() && !v2.equals(v1) &&
                x < v2.getX() + v2.getWidth() &&
                x + v1.getWidth() > v2.getX()) {
                return true;
            }
        }
        return false;
    }

    public int getCarCount() {
        return carCount;
    }
    public void resetCarCount() { carCount = 0; }

}

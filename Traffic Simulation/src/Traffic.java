import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Traffic implements ActionListener, Runnable {

    // Simulation init
    private JFrame frame = new JFrame("Traffic Simulation");
    private boolean running = false;
    private long startTime = 0;
    private int vehicleCount;

    // Road init
    private Road road = new Road();

    // Button init
    private JButton start = new JButton("Start");
    private JButton stop = new JButton("Stop");
    // cars off the end / time
    private JLabel throughput = new JLabel("Throughput: 0");

    private JButton truck = new JButton("Add Truck");
    private JButton car = new JButton("Add Car");
    private JButton bike = new JButton("Add Bike");

    // Containers
    private Container south = new Container();
    private Container west = new Container();



    public Traffic() {
        // Window setup
        frame.setSize(Road.ROAD_WIDTH, 550);
        frame.setLayout(new BorderLayout());
        frame.add(road, BorderLayout.CENTER);

        // South container setup
        south.setLayout(new GridLayout(1,2));
        south.add(start);
        south.add(stop);
        south.add(throughput);
        start.addActionListener(this);
        stop.addActionListener(this);
        frame.add(south, BorderLayout.SOUTH);

        // West container setup
        west.setLayout(new GridLayout(3,1));
        west.add(truck);
        west.add(car);
        west.add(bike);
        truck.addActionListener(this);
        car.addActionListener(this);
        bike.addActionListener(this);
        frame.add(west, BorderLayout.WEST);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new Traffic();
    }


    @Override
    public void actionPerformed(ActionEvent event) {
        // start
        if (event.getSource().equals(start)) {
            if (!running) {
                running = true;
                road.resetCarCount();
                startTime = System.currentTimeMillis();
                Thread t = new Thread(this);
                t.start();
            }
        }
        // stop
        if (event.getSource().equals(stop)) {
            running = false;
        }
        // add truck
        if (event.getSource().equals(truck)) {
            Vehicle truck = new Truck(0, 20);
            road.addVehicle(truck);

            // find spot in the lane
            for (int x = 0; x < Road.ROAD_WIDTH; x += 20) {
                // find lane
                for (int y = 40; y < 480; y += 120) {
                    truck.setX(x);
                    truck.setY(y);
                    // if doesn't collide, you're done
                    if (!road.collision(x, y, truck)) {
                        frame.repaint();
                        return;
                    }
                }
            }
        }
        // add car
        if (event.getSource().equals(car)) {
            Vehicle car = new Car(0, 20);
            road.addVehicle(car);

            // find spot in the lane
            for (int x = 0; x < Road.ROAD_WIDTH; x += 20) {
                // find lane
                for (int y = 40; y < 480; y += 120) {
                    car.setX(x);
                    car.setY(y);
                    // if doesn't collide, you're done
                    if (!road.collision(x, y, car)) {
                        frame.repaint();
                        return;
                    }
                }
            }
        }
        // add bike
        if (event.getSource().equals(bike)) {
            Vehicle bike = new Bike(0, 20);
            road.addVehicle(bike);

            // find spot in the lane
            for (int x = 0; x < Road.ROAD_WIDTH; x += 20) {
                // find lane
                for (int y = 40; y < 480; y += 120) {
                    bike.setX(x);
                    bike.setY(y);
                    // if doesn't collide, you're done
                    if (!road.collision(x, y, bike)) {
                        frame.repaint();
                        return;
                    }
                }
            }
        }

    }

    @Override
    public void run() {
        while (running) {
            road.step(); // move vehicles
            vehicleCount = road.getCarCount();
            double currentThroughput = vehicleCount / (double)(System.currentTimeMillis() - startTime);
            throughput.setText("Throughput: " + currentThroughput * 1000);
            frame.repaint(); // update screen

            try {
                Thread.sleep(15);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}

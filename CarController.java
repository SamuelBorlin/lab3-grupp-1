import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
 * This class represents the Controller part in the MVC pattern.
 * Its responsibilities are to listen to the View and responds in an appropriate manner by
 * modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private final Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Cars> cars = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        // Instances of cars
        Volvo240 volvo240 = new Volvo240();
        Saab95 saab95 = new Saab95();
        Scania scania = new Scania();

        cc.cars.add(volvo240);
        cc.cars.add(saab95);
        cc.cars.add(scania);

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Cars car : cars) {
                car.move();
                int x = (int) Math.round(car.getPosition().getX());
                int y = (int) Math.round(car.getPosition().getY());

                if (x > frame.getWidth() - 100 || x < 0) {
                    car.stopEngine();
                    car.turnLeft();
                    car.turnLeft(); // Custom method to reverse direction
                    car.startEngine();
                }

                frame.drawPanel.moveCar(car, x, y);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Cars car : cars) {
            car.gas(gas);
        }
    }

    void startAllCars() {
        for (Cars car : cars) {
            car.startEngine();
        }
    }

    void stopAllCars() {
        for (Cars car : cars) {
            car.stopEngine();
        }
    }

    void enableTurbo() {
        for (Cars car : cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOn();
            }
        }
    }

    void disableTurbo() {
        for (Cars car : cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOff();
            }
        }
    }

    void raiseBed() {
        for (Cars car : cars) {
            if (car instanceof Scania) {
                ((Scania) car).raiseRamp();
            }
        }
    }

    void lowerBed() {
        for (Cars car : cars) {
            if (car instanceof Scania) {
                ((Scania) car).lowerRamp();
            }
        }
    }

}
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel {
    // To keep track of a several cars and their positions
    private final ArrayList<Cars> cars = new ArrayList<>();
    private final ArrayList<Point> carPositions = new ArrayList<>();


    // All images
    BufferedImage volvoImage;
    BufferedImage saabImage;
    BufferedImage scaniaImage;
    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(300, 300);

    void moveCar(Cars car, int x, int y) {
        int index = cars.indexOf(car);
        if (index != -1) {
            carPositions.get(index).setLocation(x, y);
        }
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {
            // Remember to right-click src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
            volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
            saabImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
            scaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));
            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < cars.size(); i++) {
            BufferedImage carImage = getCarImage(cars.get(i));
            Point position = carPositions.get(i);
            g.drawImage(carImage, position.x, position.y, null);
        }
    }

    private BufferedImage getCarImage(Cars car) {
        if (car instanceof Volvo240) {
            return volvoImage;
        } else if (car instanceof Saab95) {
            return saabImage;
        } else if (car instanceof Scania) {
            return scaniaImage;
        }
        return null;
    }
}

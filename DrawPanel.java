import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel {

    // Just a single image, TODO: Generalize
    BufferedImage[] carImages;
    Point[] carPoints;

    BufferedImage volvoImage;
    BufferedImage saabImage;
    BufferedImage scaniaImage;
    /*
     * // To keep track of a single car's position
     * Point volvoPoint = new Point();
     */

    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(300, 300);

    // TODO: Make this general for all cars
    void moveit(int x, int y) {

        for (int i = 0; i < carPoints.length; i++) {
            carPoints[i].x = x;
            carPoints[i].y = y;
        }
        // volvoPoint.x = x;
        // volvoPoint.y = y;
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to
            // pics.
            // if you are starting in IntelliJ.
            volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
            saabImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
            scaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));

            carImages = new BufferedImage[] { volvoImage, saabImage, scaniaImage };
            carPoints = new Point[] {
                    new Point(0, 0),
                    new Point(0, 100),
                    new Point(0, 200)
            };

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
        for (int i = 0; i < carImages.length; i++) {
            g.drawImage(carImages[i], carPoints[i].x, carPoints[i].y + i * 100, null);
        }
        // g.drawImage(volvoImage, volvoPoint.x, volvoPoint.y, null); // see javadoc for
        // more info on the parameters
        g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);
    }
}

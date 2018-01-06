import pixelart.PixelArtConverter;
import ui.NonogramGenerator;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class Main {

    public static void main(String[] args) {
//        BufferedImage image = new PixelArtConverter("filepath")
//            .width(10)
//            .height(10)
//            .convert();


        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Nonogram Generator");
                frame.setContentPane(new NonogramGenerator().getContentPane());
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });


    }
}

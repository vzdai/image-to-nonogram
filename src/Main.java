import pixelart.PixelArtConverter;

import java.awt.image.BufferedImage;

public class Main {

    public static void main(String[] args) {
        BufferedImage image = new PixelArtConverter("filepath")
            .width(10)
            .height(10)
            .convert();
    }
}

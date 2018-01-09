package pixelart;


import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class PixelColor {

    private static final List<PixelColor> allColors = Arrays.asList(
        new PixelColor(255, 255, 255), // white
        new PixelColor(200, 200, 200), // light grey
        new PixelColor(136, 136, 136), // dark grey
        new PixelColor(0, 0, 0) // black
    );

    private final Color mColor;

    public PixelColor(int r, int g, int b) {
        mColor = new Color(r, g, b);
    }

    public Color getColor() {
        return mColor;
    }

    public static List<PixelColor> getAllColors() {
        return allColors;
    }
}

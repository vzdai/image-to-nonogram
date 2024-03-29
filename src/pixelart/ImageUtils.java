package pixelart;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class ImageUtils {

    public static BufferedImage resize(BufferedImage source, int width, int height) {
        final BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        final Graphics2D g = out.createGraphics();

        final AffineTransform at = AffineTransform.getScaleInstance((double) width / source.getWidth(), (double) height / source.getHeight());
        g.drawRenderedImage(source, at);
        g.dispose();

        return out;
    }

    public static BufferedImage toPixelImage(BufferedImage source) {
        final BufferedImage out = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < out.getWidth(); x++) {
            for (int y = 0; y < out.getHeight(); y++) {
                final PixelColor color = getNearestColor(new Color(source.getRGB(x, y)));
                out.setRGB(x, y, color.getColor().getRGB());
            }
        }

        return out;
    }

    public static PixelColor[][] toPixelColors(BufferedImage source) {
        PixelColor[][] output = new PixelColor[source.getHeight()][source.getWidth()];
        for (int x = 0; x < source.getWidth(); x++) {
            for (int y = 0; y < source.getHeight(); y++) {
                final PixelColor color = getNearestColor(new Color(source.getRGB(x, y)));
                output[y][x] = color;
            }
        }

        return output;
    }

    private static PixelColor getNearestColor(Color color) {
        PixelColor bestMatch = null;
        double minDistance = Double.MAX_VALUE;

        for (PixelColor pixelColor : PixelColor.getAllColors()) {

            double distance = getDistanceBetweenColors(color, pixelColor.getColor());
            if (bestMatch == null || distance < minDistance) {
                bestMatch = pixelColor;
                minDistance = distance;
            }
        }

        return bestMatch;
    }

    private static double getDistanceBetweenColors(Color first, Color second) {
        return Math.sqrt(Math.pow(first.getRed() - second.getRed(), 2)
            + Math.pow(first.getGreen() - second.getGreen(), 2)
            + Math.pow(first.getBlue() - second.getBlue(), 2));
    }

}

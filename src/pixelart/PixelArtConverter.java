package pixelart;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PixelArtConverter {

    private BufferedImage mSourceImage;
    private int mWidth;
    private int mHeight;

    public PixelArtConverter(String filepath) {
        try {
            mSourceImage = ImageIO.read(new File(filepath));
        } catch (IOException e) {
            System.out.println("Could not read file: " + filepath);
        }
    }

    public PixelArtConverter width(int width) {
        mWidth = width;
        return this;
    }

    public PixelArtConverter height(int height) {
        mHeight = height;
        return this;
    }

    public BufferedImage convert() {
        BufferedImage output = mSourceImage;
        if (mWidth > 0 && mHeight > 0) {
            output = ImageUtils.resize(output, mWidth, mHeight);
        }

        return ImageUtils.toPixelImage(output);
    }
}

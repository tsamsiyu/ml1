package ts.conv;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class ImageReader {
    public static RGBMatrix read(String path, int height, int width) throws Exception {
        RGBMatrix rgb = new RGBMatrix(height, width);

        File file = new File(path);
        BufferedImage image = ImageIO.read(file);
        image = resize(image, height, width);

        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                int pixel = image.getRGB(w, h);
                Color color = new Color(pixel);
                rgb.getR().set(h, w, color.getRed());
                rgb.getG().set(h, w, color.getGreen());
                rgb.getB().set(h, w, color.getBlue());
            }
        }

        return rgb;
    }

    public static BufferedImage resize(BufferedImage image, int height, int width) {
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = newImage.createGraphics();
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();
        graphics2D.setComposite(AlphaComposite.Src);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        return newImage;
    }
}
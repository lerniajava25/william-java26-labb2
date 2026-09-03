package org.example.renderer;

import org.example.math.Color;
import org.example.scene.Camera;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public final class ImageWriter {
    private final Color[][] colorData;

    private final int viewportWidth;
    private final int viewportHeight;

    public ImageWriter(Color[][] colorData, Camera camera) {
        this.colorData = colorData;
        this.viewportWidth = camera.getViewportWidth();
        this.viewportHeight = camera.getViewportHeight();
    }

    public void writeSceneToImage(String path) {
        BufferedImage image = new BufferedImage(viewportWidth, viewportHeight, BufferedImage.TYPE_INT_ARGB);
        for(int x = 0; x < viewportWidth; x++) {
            for(int y = 0; y < viewportHeight; y++) {
                int rgb = colorData[x][y].toRGB();
                image.setRGB(x ,y, rgb);
            }
        }
        try {
            File file = new File(path);
            ImageIO.write(image, "PNG", file);
        } catch(IOException e) {
            IO.println("Kunde inte skriva ut bild: " + e.getMessage());
        }
    }
}

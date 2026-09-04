package org.example.renderer;

import org.example.geometry.Intersection;
import org.example.math.Color;
import org.example.math.Ray;
import org.example.scene.Camera;
import org.example.scene.Scene;

import java.util.Arrays;
import java.util.Optional;

public class Renderer {
    private final Camera camera;
    private final Scene scene;
    private final Color backgroundColor;

    private final Color[][] colorData;

    private final int viewportWidth;
    private final int viewportHeight;

    public Renderer(Camera camera, Scene scene, Color backgroundColor) {
        this.camera = camera;
        this.scene = scene;
        this.backgroundColor = backgroundColor;
        this.viewportWidth = camera.getViewportWidth();
        this.viewportHeight = camera.getViewportHeight();
        colorData = new Color[viewportWidth][viewportHeight];
    }

    public Color[][] getColorData() {
        return Arrays.stream(colorData).map(Color[]::clone).toArray(Color[][]::new);
    }

    public void renderScene() {
        Ray[][] rays = camera.getViewportRays();
        for(int x = 0; x < viewportWidth; x++) {
            for(int y = 0; y < viewportHeight; y++) {
                Optional<Intersection> hit = scene.findClosestHit(rays[x][y]);
                Color pixelColor = hit
                        .map(intersection -> intersection.intersectedShape().color())
                        .orElse(backgroundColor);
                colorData[x][y] = pixelColor;
            }
        }
    }
}

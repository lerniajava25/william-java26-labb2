package org.example;

import org.example.geometry.Shape;
import org.example.geometry.Sphere;
import org.example.math.Color;
import org.example.math.Vector3D;
import org.example.renderer.ImageWriter;
import org.example.renderer.Renderer;
import org.example.scene.Camera;
import org.example.scene.Scene;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int VIEWPORT_WIDTH = 100;
    private static final int VIEWPORT_HEIGHT = 100;

    private static final String IMAGE_OUTPUT_PATH = "./output.png";

    void main() {
        List<Shape> shapes = new ArrayList<>();
        Sphere sphere1 = new Sphere(new Vector3D(0, 0, 80), 30, new Color(1, 0, 0));
        Sphere sphere2 = new Sphere(new Vector3D(40, 0, 90), 20, new Color(0, 1, 0));
        shapes.add(sphere1);
        shapes.add(sphere2);

        Scene scene = new Scene(shapes);
        Camera camera = new Camera(new Vector3D(0, 0, 0), new Vector3D(0, 0, 1), VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        Renderer renderer = new Renderer(camera, scene, new Color(1, 1, 1));
        renderer.renderScene();
        ImageWriter imageWriter = new ImageWriter(renderer.getColorData(), camera);
        imageWriter.writeSceneToImage(IMAGE_OUTPUT_PATH);
    }
}

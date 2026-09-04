package org.example;

import org.example.geometry.Sphere;
import org.example.geometry.Triangle;
import org.example.math.Color;
import org.example.math.Vector3D;
import org.example.renderer.ImageWriter;
import org.example.renderer.Renderer;
import org.example.scene.Camera;
import org.example.scene.Scene;

public class Main {
    private static final int VIEWPORT_WIDTH = 100;
    private static final int VIEWPORT_HEIGHT = 100;

    private static final String IMAGE_OUTPUT_PATH = "./output.png";

    void main() {
        Scene scene = new Scene();
        scene.addShape(new Sphere(new Vector3D(0, 0, 80), 10, new Color(1, 0, 0)));
        scene.addShape(new Sphere(new Vector3D(40, 0, 90), 20, new Color(0, 1, 0)));
        scene.addShape(new Triangle(new Vector3D(0, 0, 180), new Vector3D(-20, 20, 135), new Vector3D(-40, 0, 90), new Color(0, 0, 1)));

        Camera camera = new Camera(new Vector3D(0, 0, 0), new Vector3D(0, 0, 1), VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        Renderer renderer = new Renderer(camera, scene, new Color(1, 1, 1));
        renderer.renderScene();
        ImageWriter imageWriter = new ImageWriter(renderer.getColorData(), camera);
        imageWriter.writeSceneToImage(IMAGE_OUTPUT_PATH);
    }
}

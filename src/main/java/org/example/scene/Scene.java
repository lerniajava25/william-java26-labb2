package org.example.scene;

import org.example.geometry.Intersection;
import org.example.geometry.Shape;
import org.example.math.Ray;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Scene {
    private final List<Shape> shapes;

    public Scene(List<Shape> shapes) {
        this.shapes = new ArrayList<>(shapes);
    }

    public Optional<Intersection> findClosestHit(Ray ray) {
        Optional<Intersection> closest = Optional.empty();
        double closestT = Double.MAX_VALUE;

        for(Shape shape : shapes) {
            Optional<Intersection> hit = shape.hit(ray);
            if(hit.isPresent() && hit.get().distance() < closestT) {
                closest = hit;
                closestT = hit.get().distance();
            }
        }
        return closest;
    }
}

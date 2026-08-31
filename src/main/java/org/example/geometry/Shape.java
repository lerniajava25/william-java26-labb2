package org.example.geometry;

import org.example.math.Ray;

import java.util.Optional;

public abstract class Shape {
    public abstract Optional<Intersection> hit(Ray ray);
}

package org.example.geometry;

import org.example.math.Color;
import org.example.math.Ray;

import java.util.Optional;

public abstract class Shape {
    protected Color color;

    public abstract Optional<Intersection> hit(Ray ray);
}

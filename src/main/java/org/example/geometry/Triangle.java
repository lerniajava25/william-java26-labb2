package org.example.geometry;

import org.example.math.Color;
import org.example.math.Ray;
import org.example.math.Vector3D;

import java.util.Optional;

public class Triangle extends Shape {
    private final Vector3D v0;
    private final Vector3D v1;
    private final Vector3D v2;

    public Triangle(Vector3D v0, Vector3D v1, Vector3D v2, Color color) {
        this.v0 = v0;
        this.v1 = v1;
        this.v2 = v2;
        this.color = color;
    }

    @Override
    public Optional<Intersection> hit(Ray ray) {
        final double EPSILON = 0.0000001;
        Vector3D edge1 = v1.subtract(v0);
        Vector3D edge2 = v2.subtract(v0);

        Vector3D h = ray.direction().cross(edge2);
        double a = edge1.dot(h);

        if(Math.abs(a) < EPSILON) {
            return Optional.empty();
        }

        double f = 1 / a;
        Vector3D s = ray.origin().subtract(v0);
        double u = f * s.dot(h);

        if(u < 0 || u > 1) {
            return Optional.empty();
        }

        Vector3D q = s.cross(edge1);
        double v = f * ray.direction().dot(q);

        if(v < 0 || u + v > 1) {
            return Optional.empty();
        }

        double t = f * edge2.dot(q);

        if(t > EPSILON) {
            Vector3D hitPos = ray.pointAt(t);
            Intersection intersection = new Intersection(hitPos, t, this);
            return Optional.of(intersection);
        }
        return Optional.empty();
    }
}

package org.example.geometry;

import org.example.math.Color;
import org.example.math.Ray;
import org.example.math.Vector3D;

import java.util.Objects;
import java.util.Optional;

public final class Sphere extends Shape {
    private final Vector3D center;
    private final double radius;

    public Sphere(Vector3D center, double radius, Color color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
    }

    public Vector3D center() {
        return center;
    }

    public double radius() {
        return radius;
    }

    public Color color() {
        return color;
    }

    @Override
    public Optional<Intersection> hit(Ray ray) {
        Vector3D oc = ray.origin().subtract(center);
        double a = ray.direction().dot(ray.direction());
        double b = 2 * oc.dot(ray.direction());
        double c = oc.dot(oc) - radius * radius;

        double discriminant = b * b - 4 * a * c;
        if(discriminant < 0) {
            return Optional.empty();
        }

        double sqrtDiscriminant = Math.sqrt(discriminant);
        double t1 = (-b - sqrtDiscriminant) / (2 * a);
        double t2 = (-b + sqrtDiscriminant) / (2 * a);

        double t = -1;
        if(t1 > 0.0001) {
            t = t1;
        } else if(t2 > 0.0001) {
            t = t2;
        }

        if(t < 0) {
            return Optional.empty();
        }

        Vector3D hitPos = ray.pointAt(t);
        Intersection intersection = new Intersection(hitPos, t, this);
        return Optional.of(intersection);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Sphere) obj;
        return Objects.equals(this.center, that.center) &&
                Double.doubleToLongBits(this.radius) == Double.doubleToLongBits(that.radius);
    }

    @Override
    public int hashCode() {
        return Objects.hash(center, radius);
    }

    @Override
    public String toString() {
        return "Sphere[" +
                "center=" + center + ", " +
                "radius=" + radius + ']';
    }

}

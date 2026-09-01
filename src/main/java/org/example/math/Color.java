package org.example.math;

public record Color(double r, double g, double b) {
    public Color(double r, double g, double b) {
        this.r = clamp01(r);
        this.g = clamp01(g);
        this.b = clamp01(b);
    }

    private double clamp01(double v) {
        return Math.clamp(v, 0.0, 1.0);
    }

    public Color add(Color other) {
        return new Color(this.r + other.r, this.g + other.g, this.b + other.b);
    }

    public Color subtract(Color other) {
        return new Color(this.r - other.r, this.g - other.g, this.b - other.b);
    }

    public Color multiply(double scalar) {
        return new Color(this.r * scalar, this.g * scalar, this.b * scalar);
    }

    @Override
    public String toString() {
        return "Color[" +
                "r=" + r + ", " +
                "g=" + g + ", " +
                "b=" + b + ']';
    }

}

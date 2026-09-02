package org.example.math;

public record Ray(Vector3D origin, Vector3D direction) {
    public Ray(Vector3D origin, Vector3D direction) {
        this.origin = origin;
        if (direction.length() == 0) {
            throw new IllegalArgumentException("Riktningen på en Ray får inte vara noll!");
        }
        this.direction = direction.normalize();
    }

    public Vector3D pointAt(double t) {
        return origin.add(direction.multiply(t));
    }

    @Override
    public String toString() {
        return "Ray[" +
                "origin=" + origin + ", " +
                "direction=" + direction + ']';
    }
}

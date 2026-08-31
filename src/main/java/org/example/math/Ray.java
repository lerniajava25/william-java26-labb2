package org.example.math;

import java.util.Objects;

public class Ray {
    private final Vector3D origin;
    private final Vector3D direction;

    public Ray(Vector3D origin, Vector3D direction) {
        this.origin = origin;
        if(direction.length() == 0) {
            throw new IllegalArgumentException("Riktningen på en Ray får inte vara noll!");
        }
        this.direction = direction.normalize();
    }

    public Vector3D origin() {
        return origin;
    }

    public Vector3D direction() {
        return direction;
    }

    public Vector3D pointAt(double t) {
        return origin.add(direction.multiply(t));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Ray) obj;
        return Objects.equals(this.origin, that.origin) &&
                Objects.equals(this.direction, that.direction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origin, direction);
    }

    @Override
    public String toString() {
        return "Ray[" +
                "origin=" + origin + ", " +
                "direction=" + direction + ']';
    }
}

package org.example.scene;

import org.example.math.Ray;
import org.example.math.Vector3D;

import java.util.Arrays;

public class Camera {
    private final Vector3D position;
    private final Vector3D direction;

    private final int viewportWidth;
    private final int viewportHeight;

    private final Ray[][] viewportRays;

    public Camera(Vector3D position, Vector3D direction, int viewportWidth, int viewportHeight) {
        if (direction.length() == 0) {
            throw new IllegalArgumentException("Riktningen på en kamera får inte vara noll!");
        }

        if(viewportWidth < 1 || viewportHeight < 1) {
            throw new IllegalArgumentException("Kamerans viewport måste vara större än noll i både X- och Y-led");
        }

        this.position = position;
        this.direction = direction;
        this.viewportWidth = viewportWidth;
        this.viewportHeight = viewportHeight;
        viewportRays = new Ray[viewportWidth][viewportHeight];

        generateViewportRays();
    }

    public int getViewportWidth() {
        return viewportWidth;
    }

    public int getViewportHeight() {
        return viewportHeight;
    }

    public Ray[][] getViewportRays() {
        return Arrays.stream(viewportRays).map(Ray[]::clone).toArray(Ray[][]::new);
    }

    private void generateViewportRays() {
        Vector3D forward = direction.normalize();
        Vector3D tempUp = Math.abs(forward.y()) > 0.999 ? new Vector3D(0, 0, 1) : new Vector3D(0, 1, 0);
        Vector3D right = forward.cross(tempUp).normalize();
        Vector3D up = right.cross(forward).normalize();

        double vwHalf = (double) viewportWidth / 2;
        double vhHalf = (double) viewportHeight / 2;

        Vector3D viewportCenter = position.add(
                forward.multiply(
                        viewportHeight
                )
        );

        for(int x = 0; x < viewportWidth; x++) {
            for(int y = 0; y < viewportHeight; y++) {
                double u = (x + 0.5) - vwHalf;
                double v = vhHalf - (y + 0.5);

                Vector3D pixelPos = viewportCenter
                        .add(right.multiply(u))
                        .add(up.multiply(v));

                Vector3D rayDir = pixelPos.subtract(position).normalize();
                viewportRays[x][y] = new Ray(position, rayDir);
            }
        }
    }
}

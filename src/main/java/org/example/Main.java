package org.example;

import org.example.math.Ray;
import org.example.math.Vector3D;

public class Main {
    void main() {
        Vector3D pos1 = new Vector3D(1, 0, 0);
        Vector3D pos2 = new Vector3D(0, 1, 0);

        Ray ray = new Ray(pos1, pos2);

        IO.println("Cross product: " + pos1.cross(pos2));
    }
}

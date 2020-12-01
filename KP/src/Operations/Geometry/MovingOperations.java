package Operations.Geometry;

import Elements.Point;
import Operations.SystemOperations;
import org.opencv.core.Mat;

import java.util.ArrayList;

public class MovingOperations {

    public static void moving(ArrayList<Point> points, double dx, double dy, double dz) {
        for (Point point : points) {
            Mat mat = SystemOperations.createMatrix(new double[]{
                    1, 0, 0, 0,
                    0, 1, 0, 0,
                    0, 0, 1, 0,
                    dx, dy, dz, 1});
            point.setCoordinates(SystemOperations.matrixMultiplying(point.getCoordinates(), mat));
        }
    }

    public static Mat getMovingMat(double dx, double dy, double dz) {
        return SystemOperations.createMatrix(new double[]{
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                dx, dy, dz, 1});
    }
}

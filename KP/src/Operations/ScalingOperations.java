package Operations;

import Elements.Point;
import org.opencv.core.Mat;

import java.util.ArrayList;

public class ScalingOperations {
    public static void scaling(ArrayList<Point> points, double ix, double iy, double iz){
        for(Point point : points) {
            Mat mat = SystemOperations.createMatrix(new double[]{ix, 0, 0, 0, 0, iy, 0, 0,
                    0, 0, iz, 0, 0, 0, 0, 1});
            point.setCoordinates(SystemOperations.matrixMultiplying(point.getCoordinates(), mat));
        }
    }

    public static Mat getScalingMat(double ix, double iy, double iz){
        return SystemOperations.createMatrix(new double[]{ix, 0, 0, 0, 0, iy, 0, 0,
                0, 0, iz, 0, 0, 0, 0, 1});
    }
}

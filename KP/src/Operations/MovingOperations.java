package Operations;

import Elements.Point;
import org.opencv.core.Mat;

import java.util.ArrayList;

public class MovingOperations {

    public static void moving(ArrayList<Point> points, double dx, double dy, double dz){
        for(Point point : points) {
            Mat mat = SystemOperations.createMatrix(new double[]{1, 0, 0, dx, 0, 1, 0, dy,
                    0, 0, 1, dz, 0, 0, 0, 1});
            SystemOperations.matrixMultiplying(point, mat);
        }
    }
}

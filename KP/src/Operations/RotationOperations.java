package Operations;

import Elements.Point;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import java.util.ArrayList;

public class RotationOperations {

    public static void xRotation(ArrayList<Point> points, double angle){
        for(Point point : points) {
            Mat mat = SystemOperations.createMatrix(new double[]{1, 0, 0, 0, 0, Math.cos(angle), -Math.sin(angle), 0,
                    0, Math.sin(angle), Math.cos(angle), 0, 0, 0, 0, 1});
            SystemOperations.matrixMultiplying(point, mat);
        }
    }

    public static void yRotation(ArrayList<Point> points, double angle){
        for(Point point : points) {
            Mat mat = SystemOperations.createMatrix(new double[]{Math.cos(angle), 0, Math.sin(angle), 0, 0, 1, 0, 0,
                    -Math.sin(angle), 0, Math.cos(angle), 0, 0, 0, 0, 1});
            SystemOperations.matrixMultiplying(point, mat);
        }
    }

    public static void zRotation(ArrayList<Point> points, double angle){
        for(Point point : points) {
            Mat mat = SystemOperations.createMatrix(new double[]{Math.cos(angle), -Math.sin(angle),
                    0, 0, Math.sin(angle), Math.cos(angle), 0, 0, 0, 0, 1, 0, 0, 0, 0, 1});
            SystemOperations.matrixMultiplying(point, mat);
        }
    }
}

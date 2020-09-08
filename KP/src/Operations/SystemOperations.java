package Operations;

import Elements.Point;
import StaticValues.StaticValues;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;


public class SystemOperations {

    public static double toRadians(double grad) {
        return (Math.PI * grad / 180);
    }

    public static double[] correctCoordinates(double x, double y, double z) {
        return new double[]{(double) StaticValues.width / 2 + x, (double) StaticValues.height / 2 + y, (double) StaticValues.height / 2 + z, 1};
    }

    public static double correctX(double x){
        return (double) StaticValues.width / 2 + x;
    }

    public static double correctY(double y){
        return (double) StaticValues.height / 2 + y;
    }

    public static double correctZ(double z){
        return (double) StaticValues.width / 2 + z;
    }

    public static void matrixMultiplying(Point point, Mat matrix) {
        Mat returnable = new Mat(4, 4, CvType.CV_64F);
        Core.gemm(matrix, point.getCoordinates(), 1, new Mat(), 0, returnable);
        point.setCoordinates(returnable);
    }

    public static Mat createMatrix(double[] elements) {
        Mat mat = new Mat(4, 4, CvType.CV_64F);
        mat.put(0, 0, elements);
        return mat;
    }
}

package Operations.Geometry;

import Elements.Edge;
import Elements.Face;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class EquationOfPlane {

    public static Mat getEquationOfPlane(Face face) {
        double x1 = face.getPoints().get(0).getX();
        double y1 = face.getPoints().get(0).getY();
        double z1 = face.getPoints().get(0).getZ();

        double x2 = face.getPoints().get(1).getX();
        double y2 = face.getPoints().get(1).getY();
        double z2 = face.getPoints().get(1).getZ();

        double x3 = face.getPoints().get(2).getX();
        double y3 = face.getPoints().get(2).getY();
        double z3 = face.getPoints().get(2).getZ();
        double a1 = x2 - x1;

        double b1 = y2 - y1;
        double c1 = z2 - z1;
        double a2 = x3 - x1;
        double b2 = y3 - y1;
        double c2 = z3 - z1;
        double a = b1 * c2 - b2 * c1;
        double b = a2 * c1 - a1 * c2;
        double c = a1 * b2 - b1 * a2;
        double d = (- a * x1 - b * y1 - c * z1);
        Mat temp = new Mat(4,1, CvType.CV_64F);
        temp.put(0,0, a);
        temp.put(1,0, b);
        temp.put(2,0, c);
        temp.put(3,0, d);

        return temp;
    }
}


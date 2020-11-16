package Operations.Geometry;

import Elements.Face;
import Elements.Figures.Cone;
import Elements.Point;
import Operations.SystemOperations;
import StaticValues.StaticValues;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

public class InvisibleLines {

    private final Mat vMatrix;
    private boolean isHole;

    public InvisibleLines(Cone cone) {
        if (cone.isHole())
            vMatrix = new Mat(4, cone.getApproximationNumber(), CvType.CV_64F);
        else
            vMatrix = new Mat(4, cone.getApproximationNumber() + 1, CvType.CV_64F);
        createVMatrix(cone);
    }

    public void createVMatrix(Cone cone) {
        int counter = 0;
        isHole = cone.isHole();
        for (Face face : cone.getFaces()) {
            Mat mat = EquationOfPlane.getEquationOfPlane(face);
            vMatrix.put(0, counter, mat.get(0, 0)[0]);
            vMatrix.put(1, counter, mat.get(1, 0)[0]);
            vMatrix.put(2, counter, mat.get(2, 0)[0]);
            vMatrix.put(3, counter, mat.get(3, 0)[0]);
            counter++;
        }
    }

    public Mat getResult() {
        Mat point = new Mat(1, 4, CvType.CV_64F);
        double[] array = new double[]{0, 0, 1000000, 1};
        point.put(0, 0, array);
        Mat result = SystemOperations.matrixMultiplying(vMatrix, point);
        System.out.print(result.dump());
        if(isHole) {
            Core.multiply(result, new Scalar(-1), result);
            return result;
        }
        return result;
    }


}

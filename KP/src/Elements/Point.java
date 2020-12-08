package Elements;

import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class Point {
    private Mat coordinates;

    public Point(double[] coordinates){
        this.coordinates = new Mat(1,4, CvType.CV_64F);
        this.coordinates.put(0,0, coordinates);
    }

    public Mat getCoordinates(){ return coordinates; }

    public void setCoordinates(Mat coordinates){this.coordinates = coordinates;}

    public double getX(){
        return coordinates.get(0,0)[0];
    }

    public double getY(){
        return coordinates.get(0,1)[0];
    }

    public double getZ(){
        return coordinates.get(0,2)[0];
    }

    public double getD(){
        return coordinates.get(0,3)[0];
    }

    public Point getCopy(){
        return new Point(new double[]{getX(), getY(), getZ(), getD()});
    }
}

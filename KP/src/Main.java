import Elements.Figures.Cone;
import Graphics.GraphicsFrame;
import Operations.RotationOperations;
import StaticValues.StaticValues;

import java.util.Scanner;

public class Main {
    public double height;
    public double radius1;
    public double radius2;
    public double approximationNumber1 ;
    public double approximationNumber2;

    public static void getData(){
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the height: h = ");
        StaticValues.coneHeight = in.nextDouble();
        System.out.print("Enter the first radius: r1 = ");
        StaticValues.radius1 = in.nextDouble();
        System.out.print("Enter the second radius: r2 = ");
        StaticValues.radius2 = in.nextDouble();
        System.out.print("Enter the approximation number of the first figure: n1 = ");
        StaticValues.approximationNumber1 = in.nextDouble();
        System.out.print("Enter the approximation number of the second figure: n2 = ");
        StaticValues.approximationNumber2 = in.nextDouble();
    }

    public static void main(String[] args) {
        System.load("C:\\Users\\kiril\\opencv\\build\\java\\x64\\opencv_java430.dll");
        Main.getData();
        StaticValues.cone1 = new Cone(StaticValues.coneHeight, StaticValues.radius1, StaticValues.approximationNumber1);
        StaticValues.cone2 = new Cone(StaticValues.coneHeight, StaticValues.radius2, StaticValues.approximationNumber2);
        GraphicsFrame graphicsFrame = new GraphicsFrame();
    }
}

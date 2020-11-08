package Elements.Figures;


import Elements.Edge;
import Elements.Face;
import Elements.Figure;
import Elements.Point;
import Operations.SystemOperations;
import StaticValues.StaticValues;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cone implements Figure {

    double height;
    double radius;
    double approximationNumber;
    double alpha;
    ArrayList<Face> faces = new ArrayList<Face>();
    ArrayList<Point> points = new ArrayList<Point>();
    ArrayList<Edge> edges = new ArrayList<Edge>();

    public Cone(double height, double radius, double approximationNumber) {
        this.radius = radius;
        this.height = height;
        this.approximationNumber = approximationNumber;
        this.alpha = 360 / approximationNumber;
        pointLaw();
        edgeLaw();
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }
    

    public void pointLaw() {
        double alpha1 = 0;
        this.points.add(new Point(new double[]{0, 0, 0, 1}));
        for (int i = 0; i < approximationNumber; i++) {
            points.add(new Point(new double[]{radius * Math.cos(alpha1 * Math.PI / 180),
                    -height, radius * Math.sin(alpha1 * Math.PI / 180), 1}));
            alpha1 += alpha;
        }
    }

    public void edgeLaw() {
        for (int i = 1; i < points.size(); i++) {
            edges.add(new Edge(points.get(0), points.get(i)));
        }
        for (int i = 2; i < points.size(); i++) {
            edges.add(new Edge(points.get(i - 1), points.get(i)));
        }
        edges.add(new Edge(points.get(1), points.get(points.size() - 1)));
    }

    public void faceLaw() {
        //TODO: fill dat shiat
    }

    public double getHeight() {
        return height;
    }

    public double getRadius() {
        return radius;
    }

    public double getApproximationNumber() {
        return approximationNumber;
    }
}

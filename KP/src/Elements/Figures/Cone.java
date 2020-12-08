package Elements.Figures;


import Elements.Edge;
import Elements.Face;
import Elements.Figure;
import Elements.Point;

import java.awt.*;
import java.util.ArrayList;

public class Cone implements Figure {

    boolean isHole;
    double height;
    double radius;
    int approximationNumber;
    double alpha;
    int flag = 0;
    ArrayList<Face> faces = new ArrayList<Face>();
    ArrayList<Point> points = new ArrayList<Point>();
    ArrayList<Edge> edges = new ArrayList<Edge>();

    public Cone(double height, double radius, int approximationNumber, boolean isHole) {
        this.radius = radius;
        this.height = height;
        this.approximationNumber = approximationNumber;
        this.alpha = 360 / (double) approximationNumber;
        this.isHole = isHole;
        pointLaw();
        edgeLaw();
        faceLaw();
        setFacesPoints();
    }

    public Cone(Cone cone){
        this.isHole = cone.isHole();
        this.height = cone.height;
        this.radius = cone.radius;
        this.approximationNumber = cone.approximationNumber;
        this.alpha = cone.alpha;
        this.flag = cone.flag;
        this.points = new ArrayList<Point>();
        this.edges = new ArrayList<Edge>();
        this.faces = new ArrayList<Face>();
        for(Point point: cone.points){
            points.add(point.getCopy());
        }
        for(Edge edge: cone.edges){
            edges.add(edge.getCopy());
        }
        for(Face face: cone.faces){
            faces.add(face.getCopy());
        }
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public ArrayList<Face> getFaces() {
        return faces;
    }

    public int getApproximationNumber() {
        return approximationNumber;
    }

    public Cone getCopy(){
        return new Cone(this);
    }

    public boolean isHole() {
        return isHole;
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
            flag = i;
            System.out.println(i);
        }
        for (int i = 2; i < points.size(); i++) {
            edges.add(new Edge(points.get(i - 1), points.get(i)));
        }
        edges.add(new Edge(points.get(1), points.get(points.size() - 1)));
    }

    public void faceLaw() {
        for (int i = 0; i < flag; i++) {
            ArrayList<Edge> face = new ArrayList<Edge>();
            face.add(edges.get(i));
            if (i == flag - 1)
                face.add(edges.get(0));
            else
                face.add(edges.get(i + 1));
            face.add(edges.get(i + flag));
            faces.add(new Face(face));
        }
        if(!isHole()) {
            ArrayList<Edge> face = new ArrayList<Edge>();
            for (int i = flag; i < edges.size(); i++) {
                face.add(edges.get(i));
            }
            faces.add(new Face(face));
        }
    }

    public void setFacesPoints() {
        for (int i = 1; i < approximationNumber; i++) {
            faces.get(i - 1).setPoint(points.get(0));
            faces.get(i - 1).setPoint(points.get(i));
            faces.get(i - 1).setPoint(points.get(i + 1));
        }
        if(!isHole()) {
            faces.get(faces.size() - 2).setPoint(points.get(0));
            faces.get(faces.size() - 2).setPoint(points.get(points.size() - 1));
            faces.get(faces.size() - 2).setPoint(points.get(1));
            for (int i = approximationNumber - 1; i > 1; i--) {
                Face face = faces.get(faces.size() - 1);
                face.setPoint(points.get(i));
            }
        }
        else {
            faces.get(faces.size() - 1).setPoint(points.get(0));
            faces.get(faces.size() - 1).setPoint(points.get(points.size() - 1));
            faces.get(faces.size() - 1).setPoint(points.get(1));
        }
    }
}

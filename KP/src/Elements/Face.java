package Elements;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Face {
    private ArrayList<Edge> edges;
    private ArrayList<Point> points;

    public Face(ArrayList<Edge> edges){
        points = new ArrayList<>();
        this.edges = edges;
    }

    public Face(ArrayList<Edge> edges, ArrayList<Point> points){
        this.edges = edges;
        this.points = points;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void setPoint(Point point) {
        points.add(point);
    }

    public Face getCopy(){
        ArrayList<Edge> edges = new ArrayList<>();
        ArrayList<Point> points = new ArrayList<>();
        for(Edge edge: this.edges){
            Edge edge1 = edge.getCopy();
            edges.add(edge1);
        }
        for(Point point: this.points){
            Point point1 = point.getCopy();
            points.add(point1);
        }
        return new Face(edges, points);
    }
}

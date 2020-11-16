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

    public ArrayList<Point> getPoints() {
        return points;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void setPoint(Point point) {
        points.add(point);
    }
}

package Graphics;

import Elements.Edge;
import Elements.Face;
import Elements.Figures.Cone;
import Elements.Point;
import Operations.SystemOperations;
import StaticValues.StaticValues;
import org.opencv.core.Mat;

import java.awt.*;
import java.util.ArrayList;

public class DrawFigure {
    public static void drawFigure(Graphics2D g2d, ArrayList<Edge> edges) {
        if (StaticValues.isHorizontal)
            drawFigureHorizontal(g2d, edges);
        else if (StaticValues.isProfile)
            drawFigureProfile(g2d, edges);
        else
            drawFigureFrontal(g2d, edges);
    }

    public static void drawFigureFrontal(Graphics2D g2d, ArrayList<Edge> edges) {
        for (Edge edge : edges) {
            g2d.drawLine((int) SystemOperations.correctX(edge.getPoint1().getX()),
                    (int) SystemOperations.correctY(edge.getPoint1().getY()),
                    (int) SystemOperations.correctX(edge.getPoint2().getX()),
                    (int) SystemOperations.correctY(edge.getPoint2().getY()));
        }
    }

    public static void drawFigureHorizontal(Graphics2D g2d, ArrayList<Edge> edges) {
        for (Edge edge : edges) {
            g2d.drawLine((int) SystemOperations.correctX(edge.getPoint1().getX()),
                    (int) SystemOperations.correctY(edge.getPoint1().getZ()),
                    (int) SystemOperations.correctX(edge.getPoint2().getX()),
                    (int) SystemOperations.correctY(edge.getPoint2().getZ()));
        }
    }

    public static void drawFigureProfile(Graphics2D g2d, ArrayList<Edge> edges) {
        for (Edge edge : edges) {
            g2d.drawLine((int) SystemOperations.correctZ(edge.getPoint1().getZ()),
                    (int) SystemOperations.correctY(edge.getPoint1().getY()),
                    (int) SystemOperations.correctZ(edge.getPoint2().getZ()),
                    (int) SystemOperations.correctY(edge.getPoint2().getY()));
        }
    }

    public static void fillHole(Graphics2D g2d, ArrayList<Face> faces, Mat invisibleLines) {
        for (int i = 0; i < invisibleLines.cols(); i++) {
            if (invisibleLines.get(0, i)[0] > 0) {
                fillFace(g2d, faces.get(i).getPoints());
                drawFigure(g2d, faces.get(i).getEdges());
            }
        }
    }

    public static void fillCone(Graphics2D g2d, ArrayList<Face> faces, Mat invisibleLines) {
        if (invisibleLines.get(0, invisibleLines.cols()-1)[0] > 0)
            fillBigFace(g2d, StaticValues.cone1, StaticValues.cone2);
        for (int i = 0; i < invisibleLines.cols(); i++) {
            if (invisibleLines.get(0, i)[0] > 0) {
                if (i != invisibleLines.cols() - 1)
                    fillFace(g2d, faces.get(i).getPoints());
                drawFigure(g2d, faces.get(i).getEdges());
            }
        }
    }

    public static void fillFace(Graphics2D g2d, ArrayList<Point> points) {
        Polygon polygon = new Polygon();
        g2d.setColor(Color.YELLOW);
        if(StaticValues.isHorizontal) {
            for (Point point : points)
                polygon.addPoint((int) SystemOperations.correctX(point.getX()),
                        (int) SystemOperations.correctY(point.getZ()));
        }
        else if(StaticValues.isProfile){
            for (Point point : points)
                polygon.addPoint((int) SystemOperations.correctZ(point.getZ()),
                        (int) SystemOperations.correctY(point.getY()));
        }
        else {
            for (Point point : points)
                polygon.addPoint((int) SystemOperations.correctX(point.getX()),
                        (int) SystemOperations.correctY(point.getY()));
        }
        g2d.fillPolygon(polygon);
        g2d.setColor(Color.BLACK);
    }

    public static void fillBigFace(Graphics2D g2d, Cone cone, Cone coneHole) {
        g2d.setColor(Color.YELLOW);
        if(StaticValues.isHorizontal) {
            fillBigHorizontalFace(g2d, cone, coneHole);
        }
        else if(StaticValues.isProfile){
            fillBigProfileFace(g2d, cone, coneHole);
        }
        else {
            fillBigFrontalFace(g2d, cone, coneHole);
        }
    }

    public static void fillBigProfileFace(Graphics2D g2d, Cone cone, Cone coneHole){
        Polygon polygon = new Polygon();
        for (int i = 1; i < cone.getPoints().size(); i++) {
            polygon.addPoint((int) SystemOperations.correctX(cone.getPoints().get(i).getZ()),
                    (int) SystemOperations.correctY(cone.getPoints().get(i).getZ()));
        }
        polygon.addPoint((int) SystemOperations.correctX(cone.getPoints().get(1).getZ()),
                (int) SystemOperations.correctY(cone.getPoints().get(1).getZ()));
        for (int i = 1; i < coneHole.getPoints().size(); i++) {
            polygon.addPoint((int) SystemOperations.correctX(coneHole.getPoints().get(i).getZ()),
                    (int) SystemOperations.correctY(coneHole.getPoints().get(i).getZ()));
        }
        polygon.addPoint((int) SystemOperations.correctX(coneHole.getPoints().get(1).getZ()),
                (int) SystemOperations.correctY(coneHole.getPoints().get(1).getZ()));
        g2d.fillPolygon(polygon);
    }

    public static void fillBigHorizontalFace(Graphics2D g2d, Cone cone, Cone coneHole){
        Polygon polygon = new Polygon();
        for (int i = 1; i < cone.getPoints().size(); i++) {
            polygon.addPoint((int) SystemOperations.correctX(cone.getPoints().get(i).getX()),
                    (int) SystemOperations.correctY(cone.getPoints().get(i).getZ()));
        }
        polygon.addPoint((int) SystemOperations.correctX(cone.getPoints().get(1).getX()),
                (int) SystemOperations.correctY(cone.getPoints().get(1).getZ()));
        for (int i = 1; i < coneHole.getPoints().size(); i++) {
            polygon.addPoint((int) SystemOperations.correctX(coneHole.getPoints().get(i).getX()),
                    (int) SystemOperations.correctY(coneHole.getPoints().get(i).getZ()));
        }
        polygon.addPoint((int) SystemOperations.correctX(coneHole.getPoints().get(1).getX()),
                (int) SystemOperations.correctY(coneHole.getPoints().get(1).getZ()));
        g2d.fillPolygon(polygon);
    }

    public static void fillBigFrontalFace(Graphics2D g2d, Cone cone, Cone coneHole){
        Polygon polygon = new Polygon();
        for (int i = 1; i < cone.getPoints().size(); i++) {
            polygon.addPoint((int) SystemOperations.correctX(cone.getPoints().get(i).getX()),
                    (int) SystemOperations.correctY(cone.getPoints().get(i).getY()));
        }
        polygon.addPoint((int) SystemOperations.correctX(cone.getPoints().get(1).getX()),
                (int) SystemOperations.correctY(cone.getPoints().get(1).getY()));
        for (int i = 1; i < coneHole.getPoints().size(); i++) {
            polygon.addPoint((int) SystemOperations.correctX(coneHole.getPoints().get(i).getX()),
                    (int) SystemOperations.correctY(coneHole.getPoints().get(i).getY()));
        }
        polygon.addPoint((int) SystemOperations.correctX(coneHole.getPoints().get(1).getX()),
                (int) SystemOperations.correctY(coneHole.getPoints().get(1).getY()));
        g2d.fillPolygon(polygon);
    }
}

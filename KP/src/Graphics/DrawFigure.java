package Graphics;

import Elements.Edge;
import Operations.SystemOperations;
import StaticValues.StaticValues;

import java.awt.*;
import java.util.ArrayList;

public class DrawFigure {
    public static void drawFigure(Graphics2D g2d, ArrayList<Edge> edges){
        for (Edge edge : edges){
            g2d.drawLine((int) SystemOperations.correctX(edge.getPoint1().getX()),
                    (int) SystemOperations.correctY(edge.getPoint1().getY()),
                    (int) SystemOperations.correctX(edge.getPoint2().getX()),
                    (int) SystemOperations.correctY(edge.getPoint2().getY()));
        }
    }
}

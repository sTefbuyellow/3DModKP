package Graphics;

import StaticValues.StaticValues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GraphicsPanel extends JPanel {
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        drawCoordinateLines(g2d);
        drawFigures(g2d);
    }

    public void drawCoordinateLines(Graphics2D g2d){
        g2d.drawLine(0, StaticValues.height / 2, StaticValues.width/ 2, StaticValues.height / 2);
        g2d.drawLine(StaticValues.width / 4, 0, StaticValues.width / 4, StaticValues.height);
    }

    public void drawFigures(Graphics2D g2d){
        DrawFigure.drawFigure(g2d, StaticValues.cone1.getEdges());
        DrawFigure.drawFigure(g2d, StaticValues.cone2.getEdges());
    }
}

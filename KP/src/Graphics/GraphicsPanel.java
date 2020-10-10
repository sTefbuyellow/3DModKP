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
        g2d.drawLine(0, StaticValues.height / 2, StaticValues.width, StaticValues.height / 2);
        g2d.drawLine(StaticValues.width / 2, 0, StaticValues.width / 2, StaticValues.height);
        DrawFigure.drawFigure(g2d, StaticValues.cone1.getEdges());
        DrawFigure.drawFigure(g2d, StaticValues.cone2.getEdges());
    }


    public GraphicsPanel(){
    }


}

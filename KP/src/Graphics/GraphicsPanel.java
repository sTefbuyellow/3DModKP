package Graphics;

import StaticValues.StaticValues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GraphicsPanel extends JPanel {

    public GraphicsPanel(){
        setBackground(new Color(150, 150, 150));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        drawFigures(g2d);
    }


    public void drawFigures(Graphics2D g2d){
        DrawFigure.drawFigure(g2d, StaticValues.cone1.getEdges());
        DrawFigure.drawFigure(g2d, StaticValues.cone2.getEdges());
    }
}

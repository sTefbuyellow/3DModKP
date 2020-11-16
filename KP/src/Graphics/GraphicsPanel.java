package Graphics;

import Operations.Geometry.InvisibleLines;
import StaticValues.StaticValues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class GraphicsPanel extends JPanel {

    private boolean needsToBeFill = false;

    public GraphicsPanel(){
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        if(isNeedsToBeFill())
            fillFigures(g2d);
        else
            drawFigures(g2d);
    }


    public void drawFigures(Graphics2D g2d){
        DrawFigure.drawFigure(g2d, StaticValues.cone1.getEdges());
        DrawFigure.drawFigure(g2d, StaticValues.cone2.getEdges());
    }

    public void fillFigures(Graphics2D g2d){
        InvisibleLines invisibleLines1 = new InvisibleLines(StaticValues.cone1);
        InvisibleLines invisibleLines2 = new InvisibleLines(StaticValues.cone2);
        DrawFigure.fillHole(g2d, StaticValues.cone2.getFaces(), invisibleLines2.getResult());
        DrawFigure.fillCone(g2d, StaticValues.cone1.getFaces(), invisibleLines1.getResult());
    }

    public boolean isNeedsToBeFill() {
        return needsToBeFill;
    }

    public void setNeedsToBeFill(boolean needsToBeFill) {
        this.needsToBeFill = needsToBeFill;
    }
}

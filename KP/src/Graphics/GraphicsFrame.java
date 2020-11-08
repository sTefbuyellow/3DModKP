package Graphics;

import Operations.Geometry.MovingOperations;
import Operations.Geometry.RotationOperations;
import Operations.Geometry.ScalingOperations;
import Operations.SystemOperations;
import StaticValues.StaticValues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GraphicsFrame extends JFrame {

    public static GraphicsPanel graphicsPanel;

    public GraphicsFrame() {
        StaticValues.width = getToolkit().getScreenSize().width;
        StaticValues.height = getToolkit().getScreenSize().height;
        setSize(StaticValues.width, StaticValues.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        graphicsPanel = new GraphicsPanel();
        getContentPane().add(graphicsPanel);
        setVisible(true);

        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                StaticValues.width = getWidth();
                StaticValues.height = getHeight();
                setSize(StaticValues.width, StaticValues.height);
            }
        });
    }
}

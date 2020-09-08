package Graphics;

import Operations.MovingOperations;
import Operations.RotationOperations;
import Operations.ScalingOperations;
import Operations.SystemOperations;
import StaticValues.StaticValues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GraphicsFrame extends JFrame {
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawLine(0, StaticValues.height/2, StaticValues.width, StaticValues.height/2);
        g2d.drawLine(StaticValues.width/2, 0, StaticValues.width/2, StaticValues.height);
        DrawFigure.drawFigure(g2d, StaticValues.cone1.getEdges());
        DrawFigure.drawFigure(g2d, StaticValues.cone2.getEdges());
    }

    public GraphicsFrame() {
        StaticValues.width = getToolkit().getScreenSize().width;
        StaticValues.height = getToolkit().getScreenSize().height;
        setSize(StaticValues.width, StaticValues.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                System.out.println(e.getKeyCode());
                switch (e.getKeyCode()) {
                    case StaticValues.KEY_W: RotationOperations.xRotation(StaticValues.cone1.getPoints(),
                            SystemOperations.toRadians(5));
                         RotationOperations.xRotation(StaticValues.cone2.getPoints(),
                            SystemOperations.toRadians(5));
                        break;
                    case StaticValues.KEY_S : RotationOperations.xRotation(StaticValues.cone1.getPoints(),
                            SystemOperations.toRadians(-5));
                    RotationOperations.xRotation(StaticValues.cone2.getPoints(),
                            SystemOperations.toRadians(-5));
                    break;
                    case StaticValues.KEY_A : RotationOperations.yRotation(StaticValues.cone1.getPoints(),
                            SystemOperations.toRadians(5));
                        RotationOperations.yRotation(StaticValues.cone2.getPoints(),
                                SystemOperations.toRadians(5));
                        break;
                    case StaticValues.KEY_D : RotationOperations.yRotation(StaticValues.cone1.getPoints(),
                            SystemOperations.toRadians(-5));
                        RotationOperations.yRotation(StaticValues.cone2.getPoints(),
                                SystemOperations.toRadians(-5));
                        break;
                    case StaticValues.KEY_E : RotationOperations.zRotation(StaticValues.cone1.getPoints(),
                            SystemOperations.toRadians(5));
                        RotationOperations.zRotation(StaticValues.cone2.getPoints(),
                                SystemOperations.toRadians(5));
                        break;
                    case StaticValues.KEY_Q : RotationOperations.zRotation(StaticValues.cone1.getPoints(),
                            SystemOperations.toRadians(-5));
                        RotationOperations.zRotation(StaticValues.cone2.getPoints(),
                                SystemOperations.toRadians(-5));
                        break;
                    case StaticValues.KEY_RIGHT : MovingOperations.moving(StaticValues.cone1.getPoints(),
                            10, 0, 0);
                        MovingOperations.moving(StaticValues.cone2.getPoints(),
                                10, 0, 0);
                        break;
                    case StaticValues.KEY_LEFT : MovingOperations.moving(StaticValues.cone1.getPoints(),
                            -10, 0, 0);
                        MovingOperations.moving(StaticValues.cone2.getPoints(),
                                -10, 0, 0);
                        break;
                    case StaticValues.KEY_UP : MovingOperations.moving(StaticValues.cone1.getPoints(),
                            0, 10, 0);
                        MovingOperations.moving(StaticValues.cone2.getPoints(),
                                0, 10, 0);
                        break;
                    case StaticValues.KEY_DOWN : MovingOperations.moving(StaticValues.cone1.getPoints(),
                            0, -10, 0);
                        MovingOperations.moving(StaticValues.cone2.getPoints(),
                                0, -10, 0);
                        break;
                    case StaticValues.KEY_SCALING_PLUS : ScalingOperations.scaling(StaticValues.cone1.getPoints(),
                            1.1, 1.1, 1.1);
                        ScalingOperations.scaling(StaticValues.cone2.getPoints(),
                                1.1, 1.1, 1.1);
                        break;
                    case StaticValues.KEY_SCALING_MINUS : ScalingOperations.scaling(StaticValues.cone1.getPoints(),
                            0.9, 0.9, 0.9);
                        ScalingOperations.scaling(StaticValues.cone2.getPoints(),
                                0.9, 0.9, 0.9);
                        break;
                }
                repaint();

            }
        });

    }
}

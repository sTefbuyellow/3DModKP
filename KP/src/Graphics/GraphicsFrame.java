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

    GraphicsPanel panel;
    Button up = new Button();
    Button down = new Button();
    Button left = new Button();
    Button right = new Button();

    public GraphicsFrame() {
        StaticValues.width = getToolkit().getScreenSize().width;
        StaticValues.height = getToolkit().getScreenSize().height;
        setSize(StaticValues.width, StaticValues.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(10, 10));
        panel = new GraphicsPanel();
        add(up);
        add(down);
        add(left);
        add(right);
        add(panel);
        setVisible(true);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                System.out.println(e.getKeyCode());
                switch (e.getKeyCode()) {
                    case StaticValues.KEY_W:
                        RotationOperations.xRotation(StaticValues.cone1.getPoints(),
                                SystemOperations.toRadians(5));
                        RotationOperations.xRotation(StaticValues.cone2.getPoints(),
                                SystemOperations.toRadians(5));
                        break;
                    case StaticValues.KEY_S:
                        RotationOperations.xRotation(StaticValues.cone1.getPoints(),
                                SystemOperations.toRadians(-5));
                        RotationOperations.xRotation(StaticValues.cone2.getPoints(),
                                SystemOperations.toRadians(-5));
                        break;
                    case StaticValues.KEY_A:
                        RotationOperations.yRotation(StaticValues.cone1.getPoints(),
                                SystemOperations.toRadians(5));
                        RotationOperations.yRotation(StaticValues.cone2.getPoints(),
                                SystemOperations.toRadians(5));
                        break;
                    case StaticValues.KEY_D:
                        RotationOperations.yRotation(StaticValues.cone1.getPoints(),
                                SystemOperations.toRadians(-5));
                        RotationOperations.yRotation(StaticValues.cone2.getPoints(),
                                SystemOperations.toRadians(-5));
                        break;
                    case StaticValues.KEY_E:
                        RotationOperations.zRotation(StaticValues.cone1.getPoints(),
                                SystemOperations.toRadians(5));
                        RotationOperations.zRotation(StaticValues.cone2.getPoints(),
                                SystemOperations.toRadians(5));
                        break;
                    case StaticValues.KEY_Q:
                        RotationOperations.zRotation(StaticValues.cone1.getPoints(),
                                SystemOperations.toRadians(-5));
                        RotationOperations.zRotation(StaticValues.cone2.getPoints(),
                                SystemOperations.toRadians(-5));
                        break;
                    case StaticValues.KEY_RIGHT:
                        MovingOperations.moving(StaticValues.cone1.getPoints(),
                                10, 0, 0);
                        MovingOperations.moving(StaticValues.cone2.getPoints(),
                                10, 0, 0);
                        break;
                    case StaticValues.KEY_LEFT:
                        MovingOperations.moving(StaticValues.cone1.getPoints(),
                                -10, 0, 0);
                        MovingOperations.moving(StaticValues.cone2.getPoints(),
                                -10, 0, 0);
                        break;
                    case StaticValues.KEY_UP:
                        MovingOperations.moving(StaticValues.cone1.getPoints(),
                                0, 10, 0);
                        MovingOperations.moving(StaticValues.cone2.getPoints(),
                                0, 10, 0);
                        break;
                    case StaticValues.KEY_DOWN:
                        MovingOperations.moving(StaticValues.cone1.getPoints(),
                                0, -10, 0);
                        MovingOperations.moving(StaticValues.cone2.getPoints(),
                                0, -10, 0);
                        break;
                    case StaticValues.KEY_SCALING_PLUS:
                        ScalingOperations.scaling(StaticValues.cone1.getPoints(),
                                1.1, 1.1, 1.1);
                        ScalingOperations.scaling(StaticValues.cone2.getPoints(),
                                1.1, 1.1, 1.1);
                        break;
                    case StaticValues.KEY_SCALING_MINUS:
                        ScalingOperations.scaling(StaticValues.cone1.getPoints(),
                                0.9, 0.9, 0.9);
                        ScalingOperations.scaling(StaticValues.cone2.getPoints(),
                                0.9, 0.9, 0.9);
                        break;
                }
                panel.repaint();
            }
        });

    }
}

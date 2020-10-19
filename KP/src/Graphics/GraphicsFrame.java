package Graphics;

import Operations.MovingOperations;
import Operations.RotationOperations;
import Operations.ScalingOperations;
import Operations.SystemOperations;
import StaticValues.StaticValues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GraphicsFrame extends JFrame {

    public static GraphicsPanel graphicsPanel;
    MenuPanel menuPanel;


    public GraphicsFrame() {
        StaticValues.width = getToolkit().getScreenSize().width;
        StaticValues.height = getToolkit().getScreenSize().height;
        setSize(StaticValues.width, StaticValues.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1,2, 5,5));
        createPanels();
        setVisible(true);
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                StaticValues.width = getWidth();
                StaticValues.height = getHeight();
                setSize(StaticValues.width, StaticValues.height);
            }
        });

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
                graphicsPanel.repaint();
            }
        });

    }

    public void createPanels(){
        graphicsPanel = new GraphicsPanel();
        menuPanel = new MenuPanel();
        getContentPane().add(graphicsPanel);
        getContentPane().add(menuPanel);
    }
}

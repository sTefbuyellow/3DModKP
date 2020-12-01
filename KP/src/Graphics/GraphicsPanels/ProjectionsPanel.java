package Graphics.GraphicsPanels;

import Operations.SystemOperations;
import StaticValues.StaticValues;
import org.opencv.core.Mat;
import Graphics.GraphicsFrame;
import Graphics.DrawFigure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectionsPanel extends JPanel {

    JLabel titleLabel = new JLabel("Projections");
    JButton horizontalProjection = new JButton("Horizontal");
    JButton profileProjection = new JButton("Profile");
    JButton frontalProjection = new JButton("Frontal");

    public ProjectionsPanel(){
        setBackground(new Color(220, 220, 220));
        changeFont(new JComponent[]{horizontalProjection, profileProjection, frontalProjection, titleLabel});
        ActionListener horizontalListener = new HorizontalButtonListener();
        ActionListener frontalListener = new FrontalButtonListener();
        ActionListener profileListener = new ProfileButtonListener();
        horizontalProjection.addActionListener(horizontalListener);
        frontalProjection.addActionListener(frontalListener);
        profileProjection.addActionListener(profileListener);
        titleLabel.setHorizontalAlignment(JTextField.CENTER);

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        setConstrains(constraints);
    }

    public void changeFont(JComponent[] components) {
        Font font = new Font("Segoe UI", Font.PLAIN, 12);
        for (JComponent component : components) {
            component.setFont(font);
        }
    }

    public void setConstrains(GridBagConstraints constraints){
        constraints.weighty = 1;
        constraints.weightx = 0.5;

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        add(titleLabel, constraints);
        constraints.gridwidth = 1;

        constraints.ipadx = 50;
        constraints.gridy = 1;
        constraints.gridx = 0;
        add(horizontalProjection, constraints);

        constraints.gridx = 1;
        add(frontalProjection, constraints);

        constraints.gridx = 2;
        add(profileProjection, constraints);
    }

    public class HorizontalButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Mat horizontalMatrix =  SystemOperations.createMatrix(new double[]{
                    1, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 1, 0,
                    0, 0, 0, 1});
            ParamPanel.createFigures();
            SystemOperations.getMultipliedPoints(StaticValues.cone1.getPoints(), horizontalMatrix);
            SystemOperations.getMultipliedPoints(StaticValues.cone2.getPoints(), horizontalMatrix);
            StaticValues.isHorizontal = true;
            GraphicsFrame.graphicsPanel.repaint();
        }
    }

    public class FrontalButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Mat horizontalMatrix =  SystemOperations.createMatrix(new double[]{
                    1, 0, 0, 0,
                    0, 1, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 1});
            ParamPanel.createFigures();
            SystemOperations.getMultipliedPoints(StaticValues.cone1.getPoints(), horizontalMatrix);
            SystemOperations.getMultipliedPoints(StaticValues.cone2.getPoints(), horizontalMatrix);
            GraphicsFrame.graphicsPanel.repaint();
        }
    }

    public class ProfileButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Mat horizontalMatrix =  SystemOperations.createMatrix(new double[]{
                    0, 0, 0, 0,
                    0, 1, 0, 0,
                    0, 0, 1, 0,
                    0, 0, 0, 1});
            ParamPanel.createFigures();
            SystemOperations.getMultipliedPoints(StaticValues.cone1.getPoints(), horizontalMatrix);
            SystemOperations.getMultipliedPoints(StaticValues.cone2.getPoints(), horizontalMatrix);
            StaticValues.isProfile = true;
            GraphicsFrame.graphicsPanel.repaint();
        }
    }
}

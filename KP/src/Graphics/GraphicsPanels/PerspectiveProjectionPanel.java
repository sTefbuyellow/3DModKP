package Graphics.GraphicsPanels;


import Elements.Point;
import Operations.SystemOperations;
import StaticValues.StaticValues;

import org.opencv.core.Mat;
import Graphics.GraphicsFrame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PerspectiveProjectionPanel extends JPanel {


    JLabel titleLabel = new JLabel("Perspective");
    JLabel dLabel = new JLabel("Параметр d");
    JTextField dValue = new JTextField("0.01");
    JLabel roLabel = new JLabel("Параметр ρ");
    JTextField roValue = new JTextField("0");
    JLabel fiLabel = new JLabel("Угол φ (°)");
    JTextField fiValue = new JTextField("0");
    JLabel thetaLabel = new JLabel("Угол θ (°)");
    JTextField thetaValue = new JTextField("0");
    JButton refreshButton = new JButton("Построить");
    JLabel zLabel = new JLabel("Смещение по Z");
    JTextField zValue = new JTextField("0");
    JButton plusButton = new JButton("Плюс 100");
    JButton minusButton = new JButton("Минус 100");

    public PerspectiveProjectionPanel() {
        setBackground(new Color(220, 220, 220));

        ActionListener listener = new PerspectiveButtonListener();
        refreshButton.addActionListener(listener);
        ActionListener listener1 = new MinusButtonListener();
        minusButton.addActionListener(listener1);
        ActionListener listener2 = new PlusButtonListener();
        plusButton.addActionListener(listener2);

        setJLabelAlignment(new JLabel[]{titleLabel, dLabel, roLabel, fiLabel, thetaLabel, zLabel});
        setJTextFieldAlignment(new JTextField[]{dValue, roValue, fiValue, thetaValue, zValue});
        changeFont(new JComponent[]{titleLabel, dLabel, dValue, roLabel,
                roValue, fiLabel, fiValue, thetaLabel, thetaValue,
                zLabel, zValue, plusButton, minusButton});

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        createLabelConstrains(constraints);
        createTextConstrains(constraints);
        createButtonConstrains(constraints);
    }

    public void createLabelConstrains(GridBagConstraints constraints) {
        constraints.weighty = 1;
        constraints.weightx = 0.5;

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        add(titleLabel, constraints);

        constraints.gridwidth = 1;
        constraints.gridy = 1;
        add(fiLabel, constraints);
        constraints.gridy = 2;
        add(thetaLabel, constraints);
        constraints.gridy = 3;
        add(dLabel, constraints);
        constraints.gridy = 4;
        add(roLabel, constraints);
    }

    public void createTextConstrains(GridBagConstraints constraints) {
        constraints.ipadx = 40;
        constraints.gridx = 2;
        constraints.gridy = 1;
        add(fiValue, constraints);
        constraints.gridy = 2;
        add(thetaValue, constraints);
        constraints.gridy = 3;
        add(dValue, constraints);
        constraints.gridy = 4;
        add(roValue, constraints);
    }

    public void createButtonConstrains(GridBagConstraints constraints) {
        constraints.gridwidth = 3;
        constraints.gridx = 0;
        constraints.gridy = 5;
        add(zLabel, constraints);

        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 6;
        add(minusButton, constraints);

        constraints.gridwidth = 1;
        constraints.gridx = 1;
        constraints.gridy = 6;
        add(zValue, constraints);

        constraints.gridwidth = 1;
        constraints.gridx = 2;
        constraints.gridy = 6;
        add(plusButton, constraints);

        constraints.gridwidth = 3;
        constraints.gridx = 0;
        constraints.gridy = 7;
        add(refreshButton, constraints);
    }


    public void changeFont(JComponent[] components) {
        Font font = new Font("Segoe UI", Font.PLAIN, 12);
        for (JComponent component : components) {
            component.setFont(font);
        }
    }


    public void setJLabelAlignment(JLabel[] labels) {
        for (JLabel label : labels)
            label.setHorizontalAlignment(JTextField.CENTER);
    }

    public void setJTextFieldAlignment(JTextField[] fields) {
        for (JTextField field : fields)
            field.setHorizontalAlignment(JTextField.CENTER);
    }

    public double toRadians(double grad) {
        return grad * Math.PI / 180;
    }

    public class PerspectiveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            double d = Double.parseDouble(dValue.getText());
            double theta = toRadians(Double.parseDouble(thetaValue.getText()));
            double fi = toRadians(Double.parseDouble(fiValue.getText()));
            double ro = Double.parseDouble(roValue.getText());
            double z = Double.parseDouble(zValue.getText());
            Mat rotation = SystemOperations.createMatrix(new double[]{
                    Math.cos(Math.PI/2), -Math.sin(Math.PI/2), 0, 0,
                    Math.sin(Math.PI/2), Math.cos(Math.PI/2), 0, 0,
                    0, 0, 1, 0,
                    0, 0, 0, 1
            });
            Mat zMatrix = SystemOperations.createMatrix(new double[]{
                    1, 0, 0, 0,
                    0, 1, 0, 0,
                    0, 0, 1, 0,
                    0, 200, z, 1});
            Mat helpingMatrix = SystemOperations.createMatrix(new double[]{
                    1, 0, 0, 0,
                    0, 1, 0, 0,
                    0, 0, 1, 1 / d,
                    0, 0, 0, 0});
            Mat perspectiveMatrix = SystemOperations.createMatrix(new double[]{
                    -1*Math.sin(theta), -1*Math.cos(fi) * Math.cos(theta), -1*Math.sin(fi) * Math.cos(theta), 0,
                    Math.cos(theta), -1*Math.cos(fi) * Math.sin(theta), -1*Math.sin(fi) * Math.sin(theta), 0,
                    0, Math.sin(fi), -1*Math.cos(fi), 0,
                    0, 0, ro, 1});
            ParamPanel.createFigures();



            SystemOperations.getMultipliedPoints(StaticValues.cone1.getPoints(), zMatrix);
            SystemOperations.getMultipliedPoints(StaticValues.cone2.getPoints(), zMatrix);
            SystemOperations.getMultipliedPoints(StaticValues.cone1.getPoints(), perspectiveMatrix);
            SystemOperations.getMultipliedPoints(StaticValues.cone2.getPoints(), perspectiveMatrix);
            SystemOperations.getNormalizedZ(StaticValues.cone1.getPoints());
            SystemOperations.getNormalizedZ(StaticValues.cone2.getPoints());
            SystemOperations.getMultipliedPoints(StaticValues.cone1.getPoints(), helpingMatrix);
            SystemOperations.getMultipliedPoints(StaticValues.cone2.getPoints(), helpingMatrix);
            SystemOperations.getNormalizedPoints(StaticValues.cone1.getPoints());
            SystemOperations.getNormalizedPoints(StaticValues.cone2.getPoints());
            SystemOperations.getMultipliedPoints(StaticValues.cone1.getPoints(), rotation);
            SystemOperations.getMultipliedPoints(StaticValues.cone2.getPoints(), rotation);

            GraphicsFrame.graphicsPanel.repaint();
        }
    }

    public class MinusButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            zValue.setText(String.valueOf(Double.parseDouble(zValue.getText())-100));
        }
    }

    public class PlusButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            zValue.setText(String.valueOf(Double.parseDouble(zValue.getText())+100));
            GraphicsFrame.graphicsPanel.repaint();
        }
    }
}

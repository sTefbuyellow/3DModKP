package Graphics.GraphicsPanels;

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
    JTextField dValue = new JTextField("0");
    JLabel roLabel = new JLabel("Параметр ρ");
    JTextField roValue = new JTextField("0");
    JLabel fiLabel = new JLabel("Угол φ (°)");
    JTextField fiValue = new JTextField("0");
    JLabel thetaLabel = new JLabel("Угол θ (°)");
    JTextField thetaValue = new JTextField("0");
    JButton refreshButton = new JButton("Построить");

    public PerspectiveProjectionPanel(){
        setBackground(new Color(220,220,220));

        ActionListener listener = new PerspectiveButtonListener();
        refreshButton.addActionListener(listener);

        setJLabelAlignment(new JLabel[]{titleLabel, dLabel, roLabel, fiLabel, thetaLabel});
        setJTextFieldAlignment(new JTextField[]{dValue, roValue, fiValue, thetaValue});
        changeFont(new JComponent[]{titleLabel, dLabel, dValue, roLabel,
                roValue, fiLabel, fiValue, thetaLabel, thetaValue});

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        createLabelConstrains(constraints);
        createTextConstrains(constraints);
        createButtonConstrains(constraints);
    }

    public void createLabelConstrains(GridBagConstraints constraints){
        constraints.weighty = 1;
        constraints.weightx = 0.5;

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
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

    public void createTextConstrains(GridBagConstraints constraints){
        constraints.ipadx = 40;
        constraints.gridx = 1;
        constraints.gridy = 1;
        add(fiValue, constraints);
        constraints.gridy = 2;
        add(thetaValue, constraints);
        constraints.gridy = 3;
        add(dValue, constraints);
        constraints.gridy = 4;
        add(roValue, constraints);
    }

    public void createButtonConstrains(GridBagConstraints constraints){
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 5;
        add(refreshButton, constraints);
    }


    public void changeFont(JComponent[] components){
        Font font = new Font("Segoe UI", Font.PLAIN, 12);
        for (JComponent component: components){
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

    public double toRadians(double grad){
        return grad*Math.PI/180;
    }

    public class PerspectiveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            double d = toRadians(Double.parseDouble(dValue.getText()));
            double theta = toRadians(Double.parseDouble(thetaValue.getText()));
            double fi = Double.parseDouble(fiValue.getText());
            double ro = Double.parseDouble(roValue.getText());
            Mat helpingMatrix =  SystemOperations.createMatrix(new double[]{
                    1, 0, 0, 0,
                    0, 1, 0, 0,
                    0, 0, 1, 1/d,
                    0, 0, 0, 0});
            Mat perspectiveMatrix =  SystemOperations.createMatrix(new double[]{
                    -Math.sin(theta), -Math.cos(fi)*Math.cos(theta), -Math.sin(fi)*Math.cos(theta), 0,
                    Math.cos(theta), -Math.cos(fi)*Math.sin(theta), -Math.sin(fi)*Math.sin(theta), 0,
                    0, Math.sin(fi), Math.cos(fi), 0,
                    0, 0, ro, 0});
            Mat mat = SystemOperations.matrixMultiplying(helpingMatrix, perspectiveMatrix);
            SystemOperations.getMultipliedPoints(StaticValues.cone1.getPoints(), mat);
            SystemOperations.getMultipliedPoints(StaticValues.cone2.getPoints(), mat);
            GraphicsFrame.graphicsPanel.repaint();
        }
    }
}

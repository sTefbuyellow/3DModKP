package Graphics.GraphicsPanels;

import Operations.SystemOperations;
import StaticValues.StaticValues;
import org.opencv.core.Mat;
import Graphics.GraphicsFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SharpAngleProjectionPanel extends JPanel {

    JLabel titleLabel = new JLabel("Косоугольная проекция");
    JLabel angleLabel = new JLabel("Угол α (°)");
    JLabel lValueLabel = new JLabel("Параметр L");
    JTextField angle = new JTextField("0");
    JTextField lValue = new JTextField("0");
    JButton refreshButton = new JButton("Построить");

    public SharpAngleProjectionPanel() {
        setBackground(new Color(220, 220, 220));

        JComponent[] elements = new JComponent[]{titleLabel, angleLabel,
                angle, lValueLabel, lValue, refreshButton};
        ActionListener listener = new SharpAngleButtonListener();
        refreshButton.addActionListener(listener);

        setJLabelAlignment(new JLabel[]{titleLabel, angleLabel, lValueLabel});
        setJTextFieldAlignment(new JTextField[]{angle, lValue});
        changeFont(elements);

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

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(angleLabel, constraints);

        constraints.gridx = 1;
        add(lValueLabel, constraints);
    }

    public void createTextConstrains(GridBagConstraints constraints){
        constraints.ipadx = 40;
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(angle, constraints);

        constraints.gridx = 1;
        add(lValue, constraints);
    }

    public void createButtonConstrains(GridBagConstraints constraints){
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        add(refreshButton, constraints);
    }

    public void changeFont(JComponent[] components) {
        Font font = new Font("Segoe UI", Font.PLAIN, 12);
        for (JComponent component : components) {
            component.setFont(font);
        }
    }

    public void setJTextFieldAlignment(JTextField[] fields) {
        for (JTextField field : fields)
            field.setHorizontalAlignment(JTextField.CENTER);
    }

    public void setJLabelAlignment(JLabel[] labels) {
        for (JLabel label : labels)
            label.setHorizontalAlignment(JTextField.CENTER);
    }

    public double toRadians(double grad){
        return grad*Math.PI/180;
    }

    public class SharpAngleButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            double cosAngle = Math.cos(toRadians(Double.parseDouble(angle.getText())));
            double sinAngle = Math.sin(toRadians(Double.parseDouble(angle.getText())));
            double l = Double.parseDouble(lValue.getText());
            Mat sharpAngleMatrix = SystemOperations.createMatrix(new double[]{
                    1, 0, 0, 0,
                    0, 1, 0, 0,
                    l*cosAngle, l*sinAngle, 0, 0,
                    0, 0, 0, 1});
            SystemOperations.getMultipliedPoints(StaticValues.cone1.getPoints(), sharpAngleMatrix);
            SystemOperations.getMultipliedPoints(StaticValues.cone2.getPoints(), sharpAngleMatrix);
            GraphicsFrame.graphicsPanel.repaint();
        }
    }
}

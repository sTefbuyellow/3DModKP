package Graphics.GraphicsPanels;

import Elements.Point;
import Operations.Geometry.MovingOperations;
import Operations.Geometry.RotationOperations;
import Operations.Geometry.ScalingOperations;
import Operations.SystemOperations;
import StaticValues.StaticValues;
import Graphics.GraphicsFrame;
import org.opencv.core.Mat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovingScalingRotatingPanel extends JPanel{

    JLabel titleLabel = new JLabel("Операции над фигурой");

    JTextField movingX = new JTextField("0");
    JTextField movingY = new JTextField("0");
    JTextField movingZ = new JTextField("0");

    JTextField scalingX = new JTextField("1");
    JTextField scalingY = new JTextField("1");
    JTextField scalingZ = new JTextField("1");

    JTextField rotatingX = new JTextField("0");
    JTextField rotatingY = new JTextField("0");
    JTextField rotatingZ = new JTextField("0");

    JLabel movingLabel = new JLabel("Сдвиг");
    JLabel scalingLabel = new JLabel("Масштабирование");
    JLabel rotatingLabel = new JLabel("Вращение (°)");
    JLabel xLabel = new JLabel("       X:");
    JLabel yLabel = new JLabel("       Y:");
    JLabel zLabel = new JLabel("       Z:");

    JButton refreshButton = new JButton("построить");

    public MovingScalingRotatingPanel(){
        setBackground(new Color(220,220,220));

        ActionListener listener = new RefreshButtonListener();
        refreshButton.addActionListener(listener);

        setJLabelAlignment(new JLabel[]{movingLabel, scalingLabel,
                rotatingLabel, xLabel, yLabel, zLabel});
        setJTextFieldAlignment(new JTextField[]{movingX, movingY, movingZ,
                scalingX, scalingY, scalingZ, rotatingX, rotatingY, rotatingZ});

        JComponent[] components = new JComponent[]{ movingLabel, scalingLabel, rotatingLabel,
                titleLabel, xLabel, movingX, scalingX, rotatingX, yLabel, movingY,
                scalingY, rotatingY, zLabel, movingZ,  scalingZ, rotatingZ, refreshButton};
        changeFont(components);

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        createLabelConstrains(constraints);
        createLabelXYZConstrains(constraints);
        createTextXConstrains(constraints);
        createTextYConstrains(constraints);
        createTextZConstrains(constraints);
        createButtonConstrains(constraints);

    }

    public void createLabelConstrains(GridBagConstraints constraints){
        constraints.weighty = 1;
        constraints.weightx = 0.5;

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 4;
        add(titleLabel, constraints);

        constraints.gridwidth = 1;
        constraints.gridy = 1;
        constraints.gridx = 1;
        add(movingLabel, constraints);
        constraints.gridx = 2;
        add(scalingLabel, constraints);
        constraints.gridx = 3;
        add(rotatingLabel, constraints);
    }

    public void createLabelXYZConstrains(GridBagConstraints constraints){
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(xLabel, constraints);
        constraints.gridy = 3;
        add(yLabel, constraints);
        constraints.gridy = 4;
        add(zLabel, constraints);
    }

    public void createTextXConstrains(GridBagConstraints constraints){
        constraints.ipadx = 40;
        constraints.gridy = 2;
        constraints.gridx = 1;
        add(movingX, constraints);
        constraints.gridx = 2;
        add(scalingX, constraints);
        constraints.gridx = 3;
        add(rotatingX, constraints);
    }

    public void createTextYConstrains(GridBagConstraints constraints){
        constraints.ipadx = 40;
        constraints.gridy = 3;
        constraints.gridx = 1;
        add(movingY, constraints);
        constraints.gridx = 2;
        add(scalingY, constraints);
        constraints.gridx = 3;
        add(rotatingY, constraints);

    }

    public void createTextZConstrains(GridBagConstraints constraints){
        constraints.ipadx = 40;
        constraints.gridy = 4;
        constraints.gridx = 1;
        add(movingZ, constraints);
        constraints.gridx = 2;
        add(scalingZ, constraints);
        constraints.gridx = 3;
        add(rotatingZ, constraints);
    }

    public void createButtonConstrains(GridBagConstraints constraints){
        constraints.gridy = 5;
        constraints.gridx = 0;
        constraints.gridwidth = 4;
        add(refreshButton, constraints);
    }

    public void changeFont(JComponent[] components){
        Font font = new Font("Segoe UI", Font.PLAIN, 12);
        for (JComponent component: components){
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

    public class RefreshButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Mat xRotation = RotationOperations.getXRotationMatrix(toRadians(Double.parseDouble(rotatingX.getText())));
            Mat yRotation = RotationOperations.getYRotationMatrix(toRadians(Double.parseDouble(rotatingY.getText())));
            Mat zRotation = RotationOperations.getZRotationMatrix(toRadians(Double.parseDouble(rotatingZ.getText())));
            Mat moving = MovingOperations.getMovingMat(Double.parseDouble(movingX.getText()),
                    Double.parseDouble(movingY.getText()), Double.parseDouble(movingZ.getText()));
            Mat scaling = ScalingOperations.getScalingMat(Double.parseDouble(scalingX.getText()),
                    Double.parseDouble(scalingY.getText()), Double.parseDouble(scalingZ.getText()));
            Mat matrix = getMultipliedMatrix(new Mat[]{xRotation, yRotation, zRotation, moving, scaling});
            SystemOperations.getMultipliedPoints(StaticValues.cone1.getPoints(), matrix);
            SystemOperations.getMultipliedPoints(StaticValues.cone2.getPoints(), matrix);
            StaticValues.cone1Copy = StaticValues.cone1.getCopy();
            StaticValues.cone2Copy = StaticValues.cone2.getCopy();
            GraphicsFrame.graphicsPanel.repaint();
        }
    }

    public Mat getMultipliedMatrix(Mat[] mats){
        Mat returnable = mats[0];
        for(Mat mat: mats){
            if(mat == mats[0])
                continue;
            returnable = SystemOperations.matrixMultiplying(mat, returnable);
        }
        return returnable;
    }
}

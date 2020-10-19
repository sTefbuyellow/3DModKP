package Graphics.GraphicsPanels;

import Operations.MovingOperations;
import Operations.RotationOperations;
import Operations.ScalingOperations;
import Operations.SystemOperations;
import StaticValues.StaticValues;
import Graphics.GraphicsFrame;
import org.opencv.core.Mat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovingScalingRotatingPanel extends JPanel{
    private JTextArea movingX = new JTextArea("0");
    private JTextArea movingY = new JTextArea("0");
    private JTextArea movingZ = new JTextArea("0");

    private JTextArea scalingX = new JTextArea("1");
    private JTextArea scalingY = new JTextArea("1");
    private JTextArea scalingZ = new JTextArea("1");

    private JTextArea rotatingX = new JTextArea("0");
    private JTextArea rotatingY = new JTextArea("0");
    private JTextArea rotatingZ = new JTextArea("0");

    private JPanel leftPanel1 = new JPanel();
    private JPanel leftPanel2 = new JPanel();
    private JPanel rightPanel = new JPanel();
    private JPanel leftTopPanel = new JPanel();

    private JButton refreshButton = new JButton("Refresh");

    public MovingScalingRotatingPanel(){
        setBackground(new Color(220,220,220));
        setLayout(new GridLayout(5,4,20,20));
        JLabel moving = new JLabel("Moving");
        JLabel scaling = new JLabel("Scaling");
        JLabel rotating = new JLabel("Rotating");
        JLabel xLabel = new JLabel("          X:");
        JLabel yLabel = new JLabel("          Y:");
        JLabel zLabel = new JLabel("          Z:");
        ActionListener listener = new RefreshButtonListener();
        refreshButton.addActionListener(listener);
        setPanelsColorGrey(new JPanel[]{leftPanel1, leftPanel2, rightPanel, leftTopPanel});
        JComponent[] components = new JComponent[]{leftTopPanel, moving, scaling, rotating,
                xLabel, movingX, scalingX, rotatingX,
                yLabel, movingY, scalingY, rotatingY,
                zLabel, movingZ,  scalingZ, rotatingZ,
                leftPanel1, leftPanel2 ,refreshButton, rightPanel};
        changeElements(components);
        addElements(components);
    }

    public void addElements(JComponent[] components){
        for (JComponent component: components){
            add(component);
        }
    }

    public void setPanelsColorGrey(JPanel[] components){
        for(JPanel panel: components){
            panel.setBackground(new Color(220,220,220));
        }
    }

    public void changeElements(JComponent[] components){
        Font font = new Font("Arial", Font.PLAIN, 30);
        for (JComponent component: components){
            component.setFont(font);
        }
    }

    public class RefreshButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Mat xRotation = RotationOperations.getXRotationMatrix(Double.parseDouble(rotatingX.getText()));
            Mat yRotation = RotationOperations.getYRotationMatrix(Double.parseDouble(rotatingY.getText()));
            Mat zRotation = RotationOperations.getZRotationMatrix(Double.parseDouble(rotatingZ.getText()));
            Mat moving = MovingOperations.getMovingMat(Double.parseDouble(movingX.getText()),
                    Double.parseDouble(movingY.getText()), Double.parseDouble(movingZ.getText()));
            Mat scaling = ScalingOperations.getScalingMat(Double.parseDouble(scalingX.getText()),
                    Double.parseDouble(scalingY.getText()), Double.parseDouble(scalingZ.getText()));
            Mat matrix = getMultipliedMatrix(new Mat[]{xRotation, yRotation, zRotation, moving, scaling});
            SystemOperations.getMultipliedPoints(StaticValues.cone1.getPoints(), matrix);
            SystemOperations.getMultipliedPoints(StaticValues.cone2.getPoints(), matrix);
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

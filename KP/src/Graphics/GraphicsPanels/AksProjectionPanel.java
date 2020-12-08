package Graphics.GraphicsPanels;

import Elements.Figures.Cone;
import Operations.SystemOperations;
import StaticValues.StaticValues;
import org.opencv.core.Mat;
import Graphics.GraphicsFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AksProjectionPanel extends JPanel {

    JLabel titleLabel = new JLabel("Аксонометрическая проекция");
    JLabel firstAngleLabel = new JLabel("Угол ψ (°)");
    JLabel secondAngleLabel = new JLabel("Угол φ (°)");
    JTextField firstAngle = new JTextField("30");
    JTextField secondAngle = new JTextField("30");
    JButton refreshButton = new JButton("Построить");

    public AksProjectionPanel(){
        setBackground(new Color(220,220,220));

        JComponent[] elements = new JComponent[]{titleLabel, firstAngleLabel,
                firstAngle, secondAngleLabel, secondAngle, refreshButton};
        ActionListener listener = new AksButtonListener();
        refreshButton.addActionListener(listener);
        setJLabelAlignment(new JLabel[]{titleLabel, firstAngleLabel, secondAngleLabel});
        setJTextFieldAlignment(new JTextField[]{firstAngle, secondAngle});
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
       add(firstAngleLabel, constraints);

       constraints.gridx = 1;
       add(secondAngleLabel, constraints);
   }

   public void createTextConstrains(GridBagConstraints constraints){
       constraints.ipadx = 40;
       constraints.gridx = 0;
       constraints.gridy = 2;
       add(firstAngle, constraints);

       constraints.gridx = 1;
       add(secondAngle, constraints);
   }

    public void createButtonConstrains(GridBagConstraints constraints){
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
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

    public class AksButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            double cosFirstAngle = Math.cos(toRadians(Double.parseDouble(firstAngle.getText())));
            double sinFirstAngle = Math.sin(toRadians(Double.parseDouble(firstAngle.getText())));
            double cosSecondAngle = Math.cos(toRadians(Double.parseDouble(secondAngle.getText())));
            double sinSecondAngle = Math.sin(toRadians(Double.parseDouble(secondAngle.getText())));
            Mat aksMatrix =  SystemOperations.createMatrix(new double[]{
                    cosFirstAngle, sinFirstAngle*sinSecondAngle, 0, 0,
                    0, cosSecondAngle, 0, 0,
                    sinFirstAngle, sinSecondAngle*cosFirstAngle, 1, 0,
                    0, 0, 0, 1});
            ParamPanel.createFigures();
            SystemOperations.getMultipliedPoints(StaticValues.cone1.getPoints(), aksMatrix);
            SystemOperations.getMultipliedPoints(StaticValues.cone2.getPoints(), aksMatrix);
            GraphicsFrame.graphicsPanel.repaint();
        }
    }
}

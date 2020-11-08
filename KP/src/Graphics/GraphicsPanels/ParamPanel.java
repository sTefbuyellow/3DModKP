package Graphics.GraphicsPanels;

import Elements.Figures.Cone;
import StaticValues.StaticValues;
import Graphics.GraphicsFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParamPanel extends JPanel {


    JLabel titleLabel = new JLabel("Параметры");
    JLabel heightLabel = new JLabel("Высота");
    JLabel radius1Label = new JLabel("Радиус 1");
    JLabel radius2Label = new JLabel("Радиус 2");
    JLabel approximationNumber1Label = new JLabel("Степень аппроксимации 1");
    JLabel approximationNumber2Label = new JLabel("Степень аппроксимации 2");

    JTextField height = new JTextField("200");
    JTextField radius1 = new JTextField("150");
    JTextField radius2 = new JTextField("100");
    JTextField approximationNumber1 = new JTextField("20");
    JTextField approximationNumber2 = new JTextField("15");
    JRadioButton facesRadioButton = new JRadioButton("Отобразить грани");
    JRadioButton edgesRadioButton = new JRadioButton("Отобразить рёбра", true);
    JRadioButton invisibleEdgesRadioButton = new JRadioButton("Невидимые линии");

    JButton refreshButton = new JButton("Построить");


    public ParamPanel() {
        setBackground(new Color(220, 220, 220));
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.weighty = 1;
        constraints.weightx = 0.5;

        setLabelConstrains(constraints);
        setTextConstrains(constraints);
        setRadioButtonConstrains(constraints);

        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.gridwidth = 3;
        add(refreshButton, constraints);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(facesRadioButton);
        buttonGroup.add(edgesRadioButton);
        buttonGroup.add(invisibleEdgesRadioButton);

        ActionListener listener = new ParamPanel.RefreshButtonListener();
        refreshButton.addActionListener(listener);
        setJTextFieldAlignment(new JTextField[]{height, radius1, radius2,
                approximationNumber1, approximationNumber2});

        changeFont(new JComponent[]{titleLabel, heightLabel, radius2Label,
                radius1Label, approximationNumber1Label, approximationNumber2Label,
        height, radius1, radius2, approximationNumber1, approximationNumber2,
        edgesRadioButton, facesRadioButton, invisibleEdgesRadioButton, refreshButton});

        createFigures();
    }

    public void setLabelConstrains(GridBagConstraints constraints) {
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        add(titleLabel, constraints);
        constraints.gridwidth = 1;
        constraints.gridy = 1;
        add(heightLabel, constraints);
        constraints.gridy = 2;
        add(radius1Label, constraints);
        constraints.gridy = 3;
        add(radius2Label, constraints);
        constraints.gridy = 4;
        add(approximationNumber1Label, constraints);
        constraints.gridy = 5;
        add(approximationNumber2Label, constraints);
    }

    public void setTextConstrains(GridBagConstraints constraints) {
        constraints.ipadx = 40;
        constraints.gridx = 1;
        constraints.gridy = 1;
        add(height, constraints);
        constraints.gridy = 2;
        add(radius1, constraints);
        constraints.gridy = 3;
        add(radius2, constraints);
        constraints.gridy = 4;
        add(approximationNumber1, constraints);
        constraints.gridy = 5;
        add(approximationNumber2, constraints);
    }

    public void setRadioButtonConstrains(GridBagConstraints constraints) {
        constraints.gridx = 0;
        constraints.gridy = 6;
        add(edgesRadioButton, constraints);
        constraints.gridx = 1;
        add(facesRadioButton, constraints);
        constraints.gridx = 2;
        add(invisibleEdgesRadioButton, constraints);
    }


    public void changeFont(JComponent[] components) {
        Font font = new Font("Segoe UI", Font.PLAIN, 12);
        for (JComponent component : components) {
            component.setFont(font);
        }
    }

    public void createFigures() {
        StaticValues.cone1 = new Cone(Double.parseDouble(height.getText()), Double.parseDouble(radius1.getText()),
                Double.parseDouble(approximationNumber1.getText()));
        StaticValues.cone2 = new Cone(Double.parseDouble(height.getText()), Double.parseDouble(radius2.getText()),
                Double.parseDouble(approximationNumber2.getText()));
    }

    public void setJTextFieldAlignment(JTextField[] fields) {
        for (JTextField field : fields)
            field.setHorizontalAlignment(JTextField.CENTER);
    }

    public void setJLabelAlignment(JLabel[] labels) {
        for (JLabel label : labels)
            label.setHorizontalAlignment(JTextField.CENTER);
    }

    public class RefreshButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            createFigures();
            GraphicsFrame.graphicsPanel.repaint();
        }
    }
}

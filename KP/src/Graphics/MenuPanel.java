package Graphics;

import Graphics.GraphicsPanels.*;
import StaticValues.StaticValues;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    JPanel panel1;
    JPanel panel2;
    JPanel panel3;
    JPanel panel4;

    public MenuPanel() {
        setBackground(new Color(150, 150, 150));
        setLayout(new GridLayout(1, 1,50,50));
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        Font font = new Font("Impact", Font.PLAIN, 20);
        tabbedPane.setFont(font);
        createPanel1();
        createPanel2();
        createPanel3();
        createPanel4();
        tabbedPane.addTab("Panel1", panel1);
        tabbedPane.addTab("Panel2", panel2);
        tabbedPane.addTab("Panel3", panel3);
        tabbedPane.addTab("Panel4", panel4);
        add(tabbedPane);
    }

    public void createPanel1() {
        panel1 = createPanel();
        panel1.setLayout(new GridLayout(1,1,20,20));
        ParamPanel paramPanel = new ParamPanel();
        panel1.add(paramPanel);
    }


    public void createPanel2(){
        panel2 = createPanel();
        panel2.setLayout(new GridLayout(1,1,20,20));
        MovingScalingRotatingPanel panel = new MovingScalingRotatingPanel();
        panel2.add(panel);
    }

    public void createPanel3(){
        panel3 = createPanel();
        panel3.setLayout(new GridLayout(3,1,20,20));
        SharpAngleProjectionPanel sharpPanel = new SharpAngleProjectionPanel();
        AksProjectionPanel aksPanel = new AksProjectionPanel();
        ProjectionsPanel projectionsPanel = new ProjectionsPanel();
        panel3.add(sharpPanel);
        panel3.add(aksPanel);
        panel3.add(projectionsPanel);
    }

    public void createPanel4(){
        panel4 = createPanel();
        panel4.setLayout(new GridLayout(1,1,20,20));
        PerspectiveProjectionPanel panel = new PerspectiveProjectionPanel();
        panel4.add(panel);
    }


    public JPanel createPanel(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(StaticValues.width/4, StaticValues.height));
        panel.setBackground(new Color(150, 150, 150));
        return panel;
    }

}

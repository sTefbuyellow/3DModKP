package Graphics;

import Graphics.GraphicsPanels.MovingScalingRotatingPanel;
import StaticValues.StaticValues;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    JPanel panel1;
    JPanel panel2;

    public MenuPanel() {
        setLayout(new GridLayout(1, 1,50,50));
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        Font font = new Font("Arial", Font.PLAIN, 20);
        tabbedPane.setFont(font);
        createPanel1();
        tabbedPane.addTab("Panel1", panel1);
        tabbedPane.addTab("Panel2", panel2);
        add(tabbedPane);
    }

    public void createPanel1() {
        panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(StaticValues.width/2, StaticValues.height));
        panel1.setLayout(new GridLayout(3,1,20,20));
        MovingScalingRotatingPanel firstPanel = new MovingScalingRotatingPanel();
        panel1.add(firstPanel);

    }

    public void addElements(JComponent[] components, JPanel panel){
        for (JComponent component: components){
            panel.add(component);
        }
    }

}

package Graphics.GraphicsPanels;

import Graphics.GraphicsFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewPanel extends JPanel {

    JButton tempButton = new JButton("Button");

    public ViewPanel(){
        setBackground(new Color(220, 220, 220));
        ActionListener listener = new Listener();
        tempButton.addActionListener(listener);
        add(tempButton);
    }

    public class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            GraphicsFrame.graphicsPanel.setNeedsToBeFill(true);
            GraphicsFrame.graphicsPanel.repaint();
        }
    }
}

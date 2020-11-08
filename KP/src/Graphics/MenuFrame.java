package Graphics;

import StaticValues.StaticValues;

import javax.swing.*;

public class MenuFrame extends JFrame {

    public static MenuPanel menuPanel;

    public MenuFrame() {
        StaticValues.width = getToolkit().getScreenSize().width;
        StaticValues.height = getToolkit().getScreenSize().height;
        setSize(StaticValues.width / 4, StaticValues.width / 4);
        setLocation(StaticValues.width - getWidth(), StaticValues.height - getHeight()-25);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuPanel = new MenuPanel();
        getContentPane().add(menuPanel);
    }

}

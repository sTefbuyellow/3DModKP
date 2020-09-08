package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GraphicsPanel extends JPanel {

    public GraphicsPanel(){
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                // switch (e.getKeyCode()) {
                System.out.println(e.getKeyCode());
                // }

            }
        });
    }


}

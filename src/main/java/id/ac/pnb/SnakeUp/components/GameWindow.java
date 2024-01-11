package id.ac.pnb.SnakeUp.components;

import javax.swing.*;

public class GameWindow {

    private GamePanel panel;
    private JFrame frame;


    public GameWindow(GamePanel panel) {
        this.panel = panel;
        frame = new JFrame();
        _initialize();
        
        
    }
    public void setPanel(GamePanel newPanel) {
        frame.getContentPane().removeAll();
        frame.add(newPanel);
        _initialize();
        
    }

    private void _initialize() {

        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(this.panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

}

package id.ac.pnb.SnakeUp.components;

import javax.swing.*;

public class GameWindow {

  private GamePanel panel;
  private JFrame frame;

  public GameWindow(GamePanel panel) {
    this.panel = panel;
    frame = new JFrame();

    frame.setVisible(true);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.add(this.panel);
    frame.pack();
    frame.setLocationRelativeTo(null);
  }
}

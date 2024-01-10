package id.ac.pnb.SnakeUp;

import id.ac.pnb.SnakeUp.components.GameWindow;
import id.ac.pnb.SnakeUp.components.MainLoginPanel;
import javax.swing.SwingUtilities;


public class SnakeUp {

  public static void main(String[] args) {
      SwingUtilities.invokeLater(() -> {
      new Game();
    });
  }
}
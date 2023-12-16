package id.ac.pnb.SnakeUp.panels;

import id.ac.pnb.SnakeUp.components.GamePanel;
import id.ac.pnb.SnakeUp.models.Board;

import java.awt.*;

public class MainGame extends GamePanel {

  public MainGame() {
  }

  public final void paintComponent(Graphics g) {
    super.paintComponent(g);

    var board = new Board(this.w, this.h);
    board.create(g);
  }
}

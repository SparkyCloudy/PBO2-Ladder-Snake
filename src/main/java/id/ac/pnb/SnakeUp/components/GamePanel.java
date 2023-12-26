package id.ac.pnb.SnakeUp.components;

import id.ac.pnb.SnakeUp.helpers.PropertiesHelper;

import javax.swing.JPanel;
import java.awt.Dimension;

public abstract class GamePanel extends JPanel {

  protected int w, h;

  public GamePanel() {
    _setPanelSize();
  }

  private void _setPanelSize() {
    PropertiesHelper.load("configs/games.properties");
    this.w = Integer.parseInt(PropertiesHelper.get("WINDOW_WIDTH"));
    this.h = Integer.parseInt(PropertiesHelper.get("WINDOW_HEIGHT"));
    var size = new Dimension(w, h);

    setPreferredSize(size);
  }

  public abstract void updateGame();
}

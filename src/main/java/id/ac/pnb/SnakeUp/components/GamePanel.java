package id.ac.pnb.SnakeUp.components;

import id.ac.pnb.SnakeUp.helpers.PropertiesHelper;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;

public class GamePanel extends JPanel {

  protected int w, h;
  private final MouseInputs mouseInputs;

  public GamePanel() {
    this.mouseInputs = new MouseInputs();

    _setPanelSize();
    addMouseListener(this.mouseInputs);
    addMouseMotionListener(this.mouseInputs);
  }

  private void _setPanelSize() {
    this.w = Integer.parseInt(PropertiesHelper.get("WINDOW_WIDTH"));
    this.h = Integer.parseInt(PropertiesHelper.get("WINDOW_HEIGHT"));
    var size = new Dimension(w, h);

    setPreferredSize(size);
  }
}

package id.ac.pnb.SnakeUp.models;

import java.awt.*;

public interface Player {
  void update();

  Point getPosition();

  void create(Graphics g);
}

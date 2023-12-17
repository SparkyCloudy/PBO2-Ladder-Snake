package id.ac.pnb.SnakeUp.models;

import java.awt.*;

public class Tile {

  private final int x, y, resolution, next;
  private final Dimension size;

  public Tile(int x, int y, Dimension size, int next) {
    this.x = x;
    this.y = y;
    this.size = size;
    this.next = next;
    resolution = size.width / 10;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getNext() {
    return next;
  }

  public int getResolution() {
    return resolution;
  }

  public void spawn(Graphics g) {
    g.drawRect(x, y, resolution, resolution);
  }

  @Override
  public String toString() {
    return "Tile{" +
        "x=" + x +
        ", y=" + y +
        ", resolution=" + resolution +
        ", next=" + next +
        ", size=" + size +
        '}';
  }
}

package id.ac.pnb.SnakeUp.models;

import java.awt.*;

public class Tile {

  private final int resolution, next;
  private final Point position = new Point();
  private final Dimension size;

  public Tile(Point position, Dimension size, int next) {
    this.position.setLocation(position);
    this.size = size;
    this.next = next;
    resolution = size.width / 10;
  }

  public int getX() {
    return position.x;
  }

  public int getY() {
    return position.y;
  }

  public int getNext() {
    return next;
  }

  public Point getPosition() {
    return position;
  }

  public int getResolution() {
    return resolution;
  }

  public void spawn(Graphics g) {
    g.drawRect(position.x, position.y, resolution, resolution);
  }

  @Override
  public String toString() {
    return "Tile{" +
        "x=" + position.x +
        ", y=" + position.y +
        ", resolution=" + resolution +
        ", next=" + next +
        ", size=" + size +
        '}';
  }
}

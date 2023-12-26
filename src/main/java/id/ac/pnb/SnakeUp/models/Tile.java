package id.ac.pnb.SnakeUp.models;

import id.ac.pnb.SnakeUp.utils.Constants.TileType;

import java.awt.*;

public class Tile {

  public static int numberTile = 1;
  private int num;
  private final int resolution;
  private final Point position = new Point();
  private final Dimension size;
  private TileType type;
  private int next;

  public Tile(Point position, Dimension size, int next, TileType type) {
    this.position.setLocation(position);
    this.size = size;
    this.next = next;
    resolution = size.width / 10;
    num = numberTile;
    numberTile++;
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

  public TileType getType() {
    return type;
  }

  public int getResolution() {
    return resolution;
  }

  public void setType(TileType type) {
    this.type = type;
  }

  public void setNext(int next) {
    this.next = next;
  }

  public void spawn(Graphics g) {
    g.drawRect(position.x, position.y, resolution, resolution);
  }

  @Override
  public String toString() {
    return "Tile{" +
        "resolution=" + resolution +
        ", position=" + position +
        ", size=" + size +
        ", type=" + type +
        ", next=" + next +
        ", numberTile=" + num +
        '}';
  }
}

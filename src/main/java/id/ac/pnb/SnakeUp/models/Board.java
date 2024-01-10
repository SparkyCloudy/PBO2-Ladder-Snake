package id.ac.pnb.SnakeUp.models;

import id.ac.pnb.SnakeUp.SnakeUp;
import id.ac.pnb.SnakeUp.utils.Constants.TileType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Board {

  private final Dimension SIZE;
  private final List<Tile> TILES = new ArrayList<>();
  private BufferedImage bufferedImage;
  private final SecureRandom RANDOM = new SecureRandom();

  private final List<Tile> obstacleTiles = new ArrayList<>();

  public Board() {
    _importImage();
    this.SIZE = new Dimension(
        bufferedImage.getWidth(), bufferedImage.getHeight()
    );

    _createTiles();
  }

  public void create(Graphics g) {
    g.drawImage(bufferedImage, 0, 0, null);
    _drawObsTiles(g);
  }

  public List<Tile> getTiles() {
    return this.TILES;
  }

  public Point getStartPosition() {
    return TILES.get(0).getPosition();
  }

  public Point getEndPosition() {
    return TILES.get(99).getPosition();
  }

  private void _drawObsTiles(Graphics g) {
    var counter = 0;
    Graphics2D g2 = (Graphics2D) g;
    g2.setStroke(new BasicStroke(10));
    for (var tile : obstacleTiles) {
      var nextTile = TILES.get(tile.getNext()-1);

      if (tile.getType() == TileType.SNAKE) {
        g2.setColor(Color.RED);
        g2.drawLine(tile.getX()+64/2, tile.getY()+64/2,
            nextTile.getX()+64/2, nextTile.getY()+64/2);
      } else {
        g2.setColor(Color.BLUE);
        g2.drawLine(tile.getX() + 64 / 2, tile.getY() + 64 / 2,
            nextTile.getX() + 64 / 2, nextTile.getY() + 64 / 2);
      }
    }
  }

  private void _importImage() {
    try (var is = SnakeUp.class.getClassLoader()
        .getResourceAsStream("assets/mainboard.png")) {
      bufferedImage = ImageIO.read(Objects.requireNonNull(is));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void _createTiles() {
    var size = new Dimension(SIZE.width-80, SIZE.height-80);
    var resolution = size.width / 10;
    var columns = size.width / resolution;
    var rows = size.height / resolution;

    var startPoint = 40;
    var pos = new Point(startPoint, (rows * resolution) - resolution + startPoint);
    var dir = 1;

    for (var i = 0; i < columns * rows; i++) {
      var tile = new Tile(pos, size, i + 1, TileType.NORMAL);
      TILES.add(tile);
//      System.out.println(tile);

      pos.x += (resolution * dir);
      if (pos.x >= size.width || pos.x <= 0) {
        dir *= -1;
        pos.x += resolution * dir;
        pos.y -= resolution;
      }
    }

    var numOfSnake = RANDOM.nextInt(3, 5);
    var numOfLadder = RANDOM.nextInt(3, 5);

    // Ladder
    _generateSpecialTiles(numOfLadder, TileType.LADDER);

    // Snake
    _generateSpecialTiles(numOfSnake, TileType.SNAKE);

    for (var tile : obstacleTiles) {
      System.out.println(tile.toString());
    }
  }

  private void _generateSpecialTiles(int size, TileType type) {
    for (var i = 0; i < size; i++) {
      var next = 0;
      var index = RANDOM.nextInt(1, 90);

      switch (type) {
        case LADDER -> next = Math.min(index + RANDOM.nextInt(10, 31), 99);
        case SNAKE -> next = Math.max(index - RANDOM.nextInt(10, 31), 1);
      }

      var tile = TILES.get(index);
      tile.setType(type);
      tile.setNext(next);
      obstacleTiles.add(tile);
    }
  }
}

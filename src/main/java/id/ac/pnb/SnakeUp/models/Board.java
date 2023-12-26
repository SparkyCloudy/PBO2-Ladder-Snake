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
  private final List<Tile> tiles = new ArrayList<>();
  private BufferedImage bufferedImage;

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
    g.drawLine(488+64/2, 616+64/2, 616+64/2, 552+64/2);
  }

  public List<Tile> getTiles() {
    return this.tiles;
  }

  public Point getStartPosition() {
    return tiles.get(0).getPosition();
  }

  public Point getEndPosition() {
    return tiles.get(99).getPosition();
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
      tiles.add(tile);
//      System.out.println(tile);

      pos.x += (resolution * dir);
      if (pos.x >= size.width || pos.x <= 0) {
        dir *= -1;
        pos.x += resolution * dir;
        pos.y -= resolution;
      }
    }

    var random = new SecureRandom();
    var numOfSnake = random.nextInt(4, 9);
    var numOfLadder = random.nextInt(4, 9);

    // Ladder
    for (var i = 0; i < numOfLadder; i++) {
      var rand = new SecureRandom();
      var index = rand.nextInt(1, 90);
      var next = Math.min(index + rand.nextInt(1, 11), 99);

      var tile = tiles.get(index);
      tile.setType(TileType.LADDER);
      tile.setNext(next);
      obstacleTiles.add(tile);
    }

    // Snake
    for (var i = 0; i < numOfSnake; i++) {
      var rand = new SecureRandom();
      var index = rand.nextInt(1, 90);
      var next = Math.max(index - rand.nextInt(1, 11), 1);

      var tile = tiles.get(index);
      tile.setType(TileType.SNAKE);
      tile.setNext(next);
      obstacleTiles.add(tile);
    }

    for (var tile : tiles) {
      System.out.println(tile.toString());
    }
  }
}

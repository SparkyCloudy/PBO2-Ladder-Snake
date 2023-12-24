package id.ac.pnb.SnakeUp.models;

import id.ac.pnb.SnakeUp.SnakeUp;

import javax.imageio.ImageIO;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Board {

  private final Dimension SIZE;
  private final List<Tile> tiles = new ArrayList<>();
  private BufferedImage bufferedImage;

  public Board() {
    _importImage();
    this.SIZE = new Dimension(
        bufferedImage.getWidth(), bufferedImage.getHeight()
    );

    _createTiles();
  }

  public void create(Graphics g) {
    g.drawImage(bufferedImage, 0, 0, null);
  }

  public List<Tile> getTiles() {
    return this.tiles;
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
      var tile = new Tile(pos, size, i + 1);
      tiles.add(tile);
//      System.out.println(tile);

      pos.x += (resolution * dir);
      if (pos.x >= size.width || pos.x <= 0) {
        dir *= -1;
        pos.x += resolution * dir;
        pos.y -= resolution;
      }
    }
  }
}

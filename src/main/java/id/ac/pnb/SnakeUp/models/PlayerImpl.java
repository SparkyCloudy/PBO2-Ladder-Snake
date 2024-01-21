package id.ac.pnb.SnakeUp.models;

import id.ac.pnb.SnakeUp.SnakeUp;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlayerImpl implements Player {
  private static int id = 0, rank;
  private final Point position;
  private final List<BufferedImage> bufferedImageList;
  private BufferedImage image;
  private int currentDiceValue = 0;

  public PlayerImpl() {
    id++;
    position = new Point();
    bufferedImageList = new ArrayList<>();
    _importImage();
    _pickImage(id);
  }

  @Override
  public void update() {

  }

  @Override
  public Point getPosition() {
    return this.position;
  }

  @Override
  public void create(Graphics g) {
    g.drawImage(image, position.x, position.y, null);
  }

  public static void resetId() {
    id = 0;
  }

  public void addDiceValue(int value) {
    this.currentDiceValue += value;
  }

  public int getCurrentDiceValue() {
    return this.currentDiceValue;
  }

  public void setCurrentDiceValue(int currentDiceValue) {
    this.currentDiceValue = currentDiceValue;
  }

  private void _pickImage(int numImage) {
    this.image = bufferedImageList.get(numImage-1);
  }

  private void _importImage() {
    for (var i = 0; i < 4; i++) {
      try (var is = SnakeUp.class.getClassLoader()
          .getResourceAsStream("assets/piece" + (i + 1) + ".png")) {
        bufferedImageList.add(ImageIO.read(Objects.requireNonNull(is)));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}

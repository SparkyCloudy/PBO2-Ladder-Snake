package id.ac.pnb.SnakeUp.models;

import id.ac.pnb.SnakeUp.SnakeUp;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Dice {
  private static int value;
  private final Point position;
  private final List<BufferedImage> bufferedImageList;
  private BufferedImage image;

  public Dice() {
    bufferedImageList = new ArrayList<>();
    position = new Point();
    _importImage();
    randomizeValue();
  }

  public int getValue() {
    return value;
  }

  public void randomizeValue() {
    var random = new Random();
    value = random.nextInt(0, 6);
    _pickImage(value);
  }

  public void create(Graphics g) {
    g.drawImage(image, (640 + 64*3), 40, null);
  }

  private void _pickImage(int numImage) {
    image = bufferedImageList.get(numImage);
  }

  private void _importImage() {
    for (var i = 0; i < 6; i++) {
      try (var is = SnakeUp.class.getClassLoader()
          .getResourceAsStream("assets/dice" + (i + 1) + ".png")) {
        bufferedImageList.add(ImageIO.read(Objects.requireNonNull(is)));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}

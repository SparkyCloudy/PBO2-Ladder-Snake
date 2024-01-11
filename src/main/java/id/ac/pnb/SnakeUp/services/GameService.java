package id.ac.pnb.SnakeUp.services;

import id.ac.pnb.SnakeUp.utils.Constants.GamePlayer;
import java.awt.*;

public interface GameService {

  void draw(Graphics g);

  void update();

  void start();

  void stop(GamePlayer player);
}

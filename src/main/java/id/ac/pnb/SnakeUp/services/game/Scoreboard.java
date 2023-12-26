package id.ac.pnb.SnakeUp.services.game;

import id.ac.pnb.SnakeUp.services.GameService;

import java.awt.*;

public class Scoreboard implements GameService {

  private static Scoreboard instance;

  private Scoreboard() {
  }

  public static synchronized Scoreboard getInstance() {
    if (instance == null) {
      instance = new Scoreboard();
    }

    return instance;
  }

  @Override
  public void draw(Graphics g) {

  }

  @Override
  public void update() {

  }

  @Override
  public void start() {

  }

  @Override
  public void stop() {

  }
}

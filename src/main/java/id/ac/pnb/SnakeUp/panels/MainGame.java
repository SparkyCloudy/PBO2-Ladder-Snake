package id.ac.pnb.SnakeUp.panels;

import id.ac.pnb.SnakeUp.components.GamePanel;
import id.ac.pnb.SnakeUp.services.GameService;
import id.ac.pnb.SnakeUp.services.game.Scoreboard;
import id.ac.pnb.SnakeUp.services.game.SnakeLadderGame;
import id.ac.pnb.SnakeUp.services.game.SnakeLadderGameEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainGame extends GamePanel {

  private GamePanel currentPanel = null;
  private final List<GameService> services = new ArrayList<>();

  public MainGame() {
    services.add(Scoreboard.getInstance());
    services.add(SnakeLadderGame.getInstance());
    addMouseListener(SnakeLadderGameEvent
        .getInstance());
    _initialize();
  }

  public final void paintComponent(Graphics g) {
    super.paintComponent(g);
    services.forEach(s -> s.draw(g));
  }

  private void _initialize() {
    services.forEach(GameService::start);
  }

  @Override
  public void updateGame() {
    services.forEach(GameService::update);
  }


}

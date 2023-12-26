package id.ac.pnb.SnakeUp.panels;

import id.ac.pnb.SnakeUp.components.GamePanel;
import id.ac.pnb.SnakeUp.services.GameService;
import id.ac.pnb.SnakeUp.services.game.ScoreBoard;
import id.ac.pnb.SnakeUp.services.game.SnakeLadderGame;
import id.ac.pnb.SnakeUp.services.game.SnakeLadderGameEvent;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class MainGame extends GamePanel {

  private final GameService gameService;
  private final GameService scoreService;

  private final List<GameService> services = new ArrayList<>();

  public MainGame(int playerCount) {
    scoreService = new ScoreBoard();
    gameService = new SnakeLadderGame(playerCount, scoreService);
    _initialize();
  }

  public final void paintComponent(Graphics g) {
    super.paintComponent(g);
    scoreService.draw(g);
    gameService.draw(g);
  }

  private void _initialize() {
    services.add(gameService);
    services.add(scoreService);

    for (var service : services) {
      service.start();
    }

    var gameEvent = new SnakeLadderGameEvent(gameService, this);
    addMouseListener(gameEvent);
  }

  @Override
  public void updateGame() {
    services.forEach(GameService::update);
  }
}

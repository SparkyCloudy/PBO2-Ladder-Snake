package id.ac.pnb.SnakeUp.panels;

import id.ac.pnb.SnakeUp.components.GameEvent;
import id.ac.pnb.SnakeUp.components.GamePanel;
import id.ac.pnb.SnakeUp.models.*;
import id.ac.pnb.SnakeUp.services.GameService;
import id.ac.pnb.SnakeUp.services.game.ScoreBoard;
import id.ac.pnb.SnakeUp.services.game.SnakeLadderGame;
import id.ac.pnb.SnakeUp.services.game.SnakeLadderGameEvent;

import java.awt.*;

public class MainGame extends GamePanel {

  private Board board;
  private Player player;
  private Dice dice;
  private Snake snake;
  private Ladder ladder;

  private GameService gameService;
  private GameService scoreService;

  private GameEvent gameEvent;

  public MainGame() {
    _initialize();
    addMouseListener(gameEvent);
  }

  public final void paintComponent(Graphics g) {
    super.paintComponent(g);
    gameService.start(g);
  }

  private void _initialize() {
    board = new Board();
    player = new Player();
    dice = new Dice();
    scoreService = new ScoreBoard();
    gameService = new SnakeLadderGame(board, player, dice, scoreService);
    gameEvent = new SnakeLadderGameEvent(gameService);
  }
}

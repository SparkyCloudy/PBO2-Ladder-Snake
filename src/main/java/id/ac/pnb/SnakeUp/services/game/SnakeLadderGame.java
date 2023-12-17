package id.ac.pnb.SnakeUp.services.game;

import id.ac.pnb.SnakeUp.models.Board;
import id.ac.pnb.SnakeUp.models.Dice;
import id.ac.pnb.SnakeUp.models.Player;
import id.ac.pnb.SnakeUp.services.GameService;

import java.awt.*;

public class SnakeLadderGame implements GameService {
  private final Board BOARD;
  private final Player PLAYER;
  private final Dice DICE;
  private final ScoreBoard SCORE_SERVICE;

  public SnakeLadderGame(Board board, Player player, Dice dice, GameService scoreService) {
    this.BOARD = board;
    this.PLAYER = player;
    this.DICE = dice;
    this.SCORE_SERVICE = (ScoreBoard) scoreService;
  }

  public Board getBoard() {
    return BOARD;
  }

  public Player getPlayer() {
    return PLAYER;
  }

  public Dice getDice() {
    return DICE;
  }

  public ScoreBoard getScoreService() {
    return SCORE_SERVICE;
  }

  @Override
  public void start(Graphics g) {
    BOARD.create(g);
    SCORE_SERVICE.start(g);
  }

  @Override
  public void stop() {

  }
}

package id.ac.pnb.SnakeUp.services.game;

import id.ac.pnb.SnakeUp.models.Board;
import id.ac.pnb.SnakeUp.models.Dice;
import id.ac.pnb.SnakeUp.models.PlayerImpl;
import id.ac.pnb.SnakeUp.services.GameService;

import java.awt.*;

public class SnakeLadderGame implements GameService {
  private final Board BOARD;
  private final Dice DICE;
  private final ScoreBoard SCORE_SERVICE;

  private final int PLAYER_COUNT;
  private GamePlayer playerTurn = GamePlayer.ONE;
  private boolean status, isNextTurn;
  private final GamePlayer[] gamePlayers = GamePlayer.values();

  public SnakeLadderGame(int playerCount, GameService scoreService) {
    this.BOARD = new Board();
    this.PLAYER_COUNT = playerCount;
    this.DICE = new Dice();
    this.SCORE_SERVICE = (ScoreBoard) scoreService;
  }

  public Board getBoard() {
    return BOARD;
  }

  public ScoreBoard getScoreService() {
    return SCORE_SERVICE;
  }

  public Dice getDice() {
    return DICE;
  }

  public GamePlayer getPlayerTurn() {
    return playerTurn;
  }

  public void setIsNextTurn(boolean value) {
    isNextTurn = value;
  }

  public boolean isNextTurn() {
    return isNextTurn;
  }

  @Override
  public void draw(Graphics g) {
    BOARD.create(g);
    PlayerManager.drawPlayers(g);
    DICE.create(g);

    if (isNextTurn) {
      var nextPlayerIndex = playerTurn.ordinal() + 1;
      if (nextPlayerIndex < PLAYER_COUNT) {
        playerTurn = gamePlayers[nextPlayerIndex];
      } else {
        playerTurn = GamePlayer.ONE;
      }

      isNextTurn = false;
    }

    if (!status) {
      System.exit(0);
    }
  }

  @Override
  public void start() {
    status = true;
    var startPosition = BOARD.getStartPosition();

    for (var i = 0; i < PLAYER_COUNT; i++) {
      PlayerManager.add(new PlayerImpl());
    }

    PlayerManager.setStartPosition(startPosition);
  }

  @Override
  public void stop() {
    status = false;
  }
}

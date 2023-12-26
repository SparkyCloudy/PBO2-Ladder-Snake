package id.ac.pnb.SnakeUp.services.game;

import id.ac.pnb.SnakeUp.models.Board;
import id.ac.pnb.SnakeUp.models.Dice;
import id.ac.pnb.SnakeUp.models.PlayerImpl;
import id.ac.pnb.SnakeUp.services.GameService;
import id.ac.pnb.SnakeUp.utils.Constants.GamePlayer;
import id.ac.pnb.SnakeUp.utils.GlobalVars;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class SnakeLadderGame implements GameService {

  private static SnakeLadderGame instance;

  private final Board BOARD;
  private final Dice DICE;
  private final ScoreBoard SCORE_SERVICE;

  private final int PLAYER_COUNT;
  private GamePlayer playerTurn = GamePlayer.ONE;
  private boolean status, isNextTurn, isTileClicked;
  private final GamePlayer[] gamePlayers = GamePlayer.values();

  private SnakeLadderGame() {
    this.BOARD = new Board();
    this.PLAYER_COUNT = GlobalVars.playerCount;
    this.DICE = new Dice();
    this.SCORE_SERVICE = ScoreBoard.getInstance();
  }

  public static synchronized SnakeLadderGame getInstance() {
    if (instance == null) {
      instance = new SnakeLadderGame();
    }

    return instance;
  }

  @Override
  public void draw(Graphics g) {
    BOARD.create(g);
    PlayerManager.drawPlayers(g);
    DICE.create(g);
  }

  @Override
  public void update() {
    if (!status) {
      System.exit(0);
    }

    if (isTileClicked) {
      _roleDice();
    }

    if (isNextTurn) {
      _changeNextPlayer();
    }

    _playerFinish();
    _resetAllEvent();
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

  public void onTileClick(MouseEvent e) {
    var mouseX = e.getX();
    var mouseY = e.getY();

    var tiles = BOARD.getTiles();

    for (var tile : tiles) {
      var posX = tile.getX();
      var posY = tile.getY();
      var res = tile.getResolution();

      if (mouseX >= posX && mouseX <= posX + res &&
          mouseY >= posY && mouseY <= posY + res) {
        isTileClicked = true;
        break;
      }
    }
  }

  private void _changeNextPlayer() {
    var nextPlayerIndex = playerTurn.ordinal() + 1;
    if (nextPlayerIndex < PLAYER_COUNT) {
      playerTurn = gamePlayers[nextPlayerIndex];
    } else {
      playerTurn = GamePlayer.ONE;
    }
  }

  private void _playerFinish() {
    if (!PlayerManager.getPlayer(playerTurn).getPosition()
        .equals(BOARD.getEndPosition())) {
      return;
    }
    System.out.println("Player " + playerTurn + " is winning");
    stop();
  }


  private void _roleDice() {
    var tiles = BOARD.getTiles();
    var player = (PlayerImpl) PlayerManager.getPlayer(playerTurn);

    DICE.randomizeValue();
    player.addDiceValue(DICE.getValue() + 1);

    var currentDiceValue = player.getCurrentDiceValue();
    if (currentDiceValue > tiles.size()-1) {
      var remain = currentDiceValue - tiles.size();
      player.setCurrentDiceValue(tiles.size()-2 - remain);
      currentDiceValue = player.getCurrentDiceValue();
    }

    for (int i = 0; i < DICE.getValue() + 1; i++) {
      var currentPos = tiles.get(currentDiceValue).getPosition();
      player.getPosition().setLocation(currentPos);
    }

    System.out.println(tiles.get(currentDiceValue).getNext());

    isNextTurn = true;
  }

  private void _resetAllEvent() {
    isNextTurn = false;
    isTileClicked = false;
  }
}

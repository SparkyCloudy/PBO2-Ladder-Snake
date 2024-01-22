package id.ac.pnb.SnakeUp.services.game;

import id.ac.pnb.SnakeUp.Game;
import id.ac.pnb.SnakeUp.models.Board;
import id.ac.pnb.SnakeUp.models.Dice;
import id.ac.pnb.SnakeUp.models.PlayerImpl;
import id.ac.pnb.SnakeUp.services.GameService;
import id.ac.pnb.SnakeUp.utils.Constants.GamePlayer;
import id.ac.pnb.SnakeUp.utils.Constants.TileType;
import id.ac.pnb.SnakeUp.utils.GlobalVars;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class SnakeLadderGame implements GameService {

  private static SnakeLadderGame instance;

  private final Board BOARD;
  private final Dice DICE;
  private final Scoreboard SCORE_SERVICE;

  private final int PLAYER_COUNT;
  private GamePlayer playerTurn = GamePlayer.ONE;
  private boolean status, isNextTurn, isTileClicked, isPlayerMoving,
      isAnyClicked;
  private final GamePlayer[] gamePlayers = GamePlayer.values();
  private String idWin;


  private SnakeLadderGame() {
    this.BOARD = new Board();
    this.PLAYER_COUNT = GlobalVars.userID.size();
    this.DICE = new Dice();
    this.SCORE_SERVICE = Scoreboard.getInstance();
    System.out.println(GlobalVars.userID);

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
    if (isGameEnded()) {
      Game.leaderBoard();
    }

    onPlayerMoving();
    onTileClicked();
    onAnyClicked();

    if (isNextTurn) {
      _changeNextPlayer();
    }

    _resetAllEvent();
  }

  @Override
  public void start() {
    status = true;
    PlayerManager.removeAll();
    PlayerImpl.resetId();
    var startPosition = BOARD.getStartPosition();

    for (var i = 0; i < PLAYER_COUNT; i++) {
      PlayerManager.add(new PlayerImpl());
    }

    PlayerManager.setStartPosition(startPosition);
  }

  @Override
  public void stop() {
    var loginManager = new LoginManager();
    loginManager.cekplayer();
    loginManager.updateWinrate(playerTurn);
    status = false;
    showWinPopup(playerTurn);
  }

  public void onTileClicked(MouseEvent e) {
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

  public void onAnyClicked(MouseEvent e) {
    isAnyClicked = true;
  }

  public void onPlayerMoving() {
    if (!isPlayerMoving) {
      return;
    }

    // TODO need to implement on motion powerup
  }

  public void onAnyClicked() {
    if (!isAnyClicked) {
      return;
    }

    _roleDice();
    _movePlayer();
    _checkPlayerPosition();
  }

  public void onTileClicked() {
    if (!isTileClicked && !isAnyClicked) {
      return;
    }

    // TODO implement powerup here
  }

  public boolean isGameEnded() {
    return !status;
  }

  private void _checkPlayerPosition() {
    var player = (PlayerImpl) PlayerManager.getPlayer(playerTurn);
    var playerTileNum = player.getCurrentDiceValue();
    var tile = BOARD.getTiles().get(playerTileNum);

    if (tile.getType() != TileType.NORMAL) {
      var next = tile.getNext() - 1;
      var nextTile = BOARD.getTiles().get(next);
      var nextTilePos = nextTile.getPosition();
      player.getPosition().setLocation(nextTilePos);
      player.setCurrentDiceValue(tile.getNext() - 1);
      // TODO implement player event logging
    }

    if (player.getPosition().equals(BOARD.getEndPosition())) {
      System.out.println("Player " + playerTurn + " is winning");
      stop();
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

  private void _movePlayer() {
    var tiles = BOARD.getTiles();
    var player = (PlayerImpl) PlayerManager.getPlayer(playerTurn);

    player.addDiceValue(DICE.getValue() + 1);

    var currentDiceValue = player.getCurrentDiceValue();
    if (currentDiceValue > tiles.size() - 1) {
      var remain = currentDiceValue - tiles.size();
      player.setCurrentDiceValue(tiles.size() - 2 - remain);
      currentDiceValue = player.getCurrentDiceValue();
    }

    var currentPos = tiles.get(currentDiceValue).getPosition();
    player.getPosition().setLocation(currentPos);

    isNextTurn = true;
  }

  private void _roleDice() {
    DICE.randomizeValue();
  }

  private void _resetAllEvent() {
    isNextTurn = false;
    isTileClicked = false;
    isAnyClicked = false;
    isPlayerMoving = false;
  }

  private void showWinPopup(GamePlayer player) {
    String winnerName = "Player " + (player.ordinal() + 1);
    String message = "Congratulations!\n" + winnerName + " wins the game!";
    JOptionPane.showMessageDialog(null, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
  }
}

package id.ac.pnb.SnakeUp.services.game;

import id.ac.pnb.SnakeUp.components.GameEvent;
import id.ac.pnb.SnakeUp.components.GamePanel;
import id.ac.pnb.SnakeUp.models.PlayerImpl;
import id.ac.pnb.SnakeUp.services.GameService;

import java.awt.event.MouseEvent;

public class SnakeLadderGameEvent extends GameEvent {

  private final SnakeLadderGame GAME_SERVICE;
  private final GamePanel PANEL;

  public SnakeLadderGameEvent(GameService gameService, GamePanel panel) {
    super();
    this.PANEL = panel;
    this.GAME_SERVICE = (SnakeLadderGame) gameService;
  }

  @Override
  public void mousePressed(MouseEvent e) {
    super.mousePressed(e);
    if (GAME_SERVICE.isNextTurn()) {
      onTileClick(e);
    } else {
      onDiceRolled();
    }
  }

  private void onDiceRolled() {
    var dice = GAME_SERVICE.getDice();
    var tiles = GAME_SERVICE.getBoard().getTiles();
    var playerTurn = GAME_SERVICE.getPlayerTurn();
    var player = (PlayerImpl) PlayerManager.getPlayer(playerTurn);

    dice.randomizeValue();
    player.addDiceValue(dice.getValue() + 1);

    var currentDiceValue = player.getCurrentDiceValue();
    if (currentDiceValue > tiles.size()-1) {
      var remain = currentDiceValue - tiles.size();
      player.setCurrentDiceValue(tiles.size()-2 - remain);
      currentDiceValue = player.getCurrentDiceValue();
    }

    var currentPos = tiles.get(currentDiceValue).getPosition();
    PlayerManager.getPlayer(playerTurn).getPosition()
            .setLocation(currentPos);

    dice.create(PANEL.getGraphics());

    if (PlayerManager.getPlayer(playerTurn).getPosition()
        .equals(GAME_SERVICE.getBoard().getEndPosition())) {
      System.out.println("Player " + playerTurn + " is winning");
      GAME_SERVICE.stop();
    }

    GAME_SERVICE.setIsNextTurn(true);
  }

  private void onTileClick(MouseEvent e) {
    var mouseX = e.getX();
    var mouseY = e.getY();

    var tiles = GAME_SERVICE.getBoard().getTiles();

    for (var tile : tiles) {
      var posX = tile.getX();
      var posY = tile.getY();
      var res = tile.getResolution();

      if (mouseX > (posX + res) || mouseY > (posY + res)) {
        continue;
      }

      if (mouseX < posX) {
        continue;
      }

      if (mouseY < posY) {
        continue;
      }

      System.out.println(tile.getNext());
    }
  }
}

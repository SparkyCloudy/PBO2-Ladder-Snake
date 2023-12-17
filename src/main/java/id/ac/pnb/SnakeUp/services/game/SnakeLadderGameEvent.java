package id.ac.pnb.SnakeUp.services.game;

import id.ac.pnb.SnakeUp.components.GameEvent;
import id.ac.pnb.SnakeUp.services.GameService;

import java.awt.event.MouseEvent;

public class SnakeLadderGameEvent extends GameEvent {

  private final SnakeLadderGame GAME_SERVICE;

  public SnakeLadderGameEvent(GameService gameService) {
    super();
    this.GAME_SERVICE = (SnakeLadderGame) gameService;
  }

  @Override
  public void mousePressed(MouseEvent e) {
    super.mousePressed(e);
    onTileClick(e);
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

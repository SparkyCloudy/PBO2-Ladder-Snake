package id.ac.pnb.SnakeUp.services.game;

import id.ac.pnb.SnakeUp.components.GameEvent;

import java.awt.event.MouseEvent;

public class SnakeLadderGameEvent extends GameEvent {

  private static SnakeLadderGameEvent instance;
  private static final SnakeLadderGame GAME_SERVICE = SnakeLadderGame
      .getInstance();

  private SnakeLadderGameEvent() {
    super();
  }

  public static synchronized SnakeLadderGameEvent getInstance() {
    if (instance == null) {
      instance = new SnakeLadderGameEvent();
    }

    return instance;
  }

  @Override
  public void mousePressed(MouseEvent e) {
    super.mousePressed(e);
    GAME_SERVICE.onTileClicked(e);
    GAME_SERVICE.onAnyClicked(e);
  }

  public boolean isGameEnded() {
    return GAME_SERVICE.isGameEnded();
  }
}

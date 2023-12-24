package id.ac.pnb.SnakeUp.services.game;

import id.ac.pnb.SnakeUp.models.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerManager {

  private static final List<Player> _PLAYERS = new ArrayList<>();

  public static Player getPlayer(GamePlayer enumValue) {
    return _PLAYERS.get(enumValue.ordinal());
  }

  public static void add(Player player) {
    _PLAYERS.add(player);
  }

  public static void drawPlayers(Graphics g) {
    for (var player : _PLAYERS) {
      player.create(g);
    }
  }

  public static void setStartPosition(Point position) {
    for (var player : _PLAYERS) {
      player.getPosition().setLocation(position);
    }
  }
}

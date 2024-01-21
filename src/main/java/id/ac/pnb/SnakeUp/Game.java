package id.ac.pnb.SnakeUp;

import id.ac.pnb.SnakeUp.components.GamePanel;
import id.ac.pnb.SnakeUp.components.GameWindow;
import id.ac.pnb.SnakeUp.panels.MainLoginPanel;
import id.ac.pnb.SnakeUp.helpers.PropertiesHelper;
import id.ac.pnb.SnakeUp.panels.LeaderBoard;
import id.ac.pnb.SnakeUp.panels.MainGame;
import id.ac.pnb.SnakeUp.panels.MainMenu;
import id.ac.pnb.SnakeUp.utils.Constants.GamePlayer;

import javax.swing.*;

public class Game implements Runnable {

  private static GamePanel panel;
  private static GameWindow window;
  private Thread thread;

  public Game() {
    _initialize();
    _startGameLoop();
  }

  @Override
  public void run() {
    PropertiesHelper.load("configs/games.properties");

    var fps = Integer.parseInt(PropertiesHelper.get("FPS_GAME"));
    var ups = Integer.parseInt(PropertiesHelper.get("UPS_SET"));

    var timePerFrame = 1_000_000_000.0 / fps;
    var timePerUpdate = 1_000_000_000.0 / ups;

    var previousTime = System.nanoTime();
    var lastCheck = System.currentTimeMillis();

    var deltaFrame = 0.0;
    var deltaUpdate = 0.0;

    while (true) {
      var currentTime = System.nanoTime();

      deltaFrame += (currentTime - previousTime) / timePerFrame;
      deltaUpdate += (currentTime - previousTime) / timePerUpdate;

      previousTime = currentTime;

      if (deltaUpdate >= 1.0) {
        panel.updateGame();
        deltaUpdate--;
      }

      if (deltaFrame >= 1.0) {
        panel.repaint();
        deltaFrame--;
      }

      if (System.currentTimeMillis() - lastCheck >= 1000) {
        lastCheck = System.currentTimeMillis();
      }
    }
  }

  public static GamePanel getPanel() {
    return panel;
  }

  private void _initialize() {
    this.thread = new Thread(this);
    panel = new MainLoginPanel();
    window = new GameWindow(panel);
  }

  private void showWinPopup(GamePlayer player) {
    String winnerName = "Player " + (player.ordinal() + 1);
    String message = "Congratulations!\n" + winnerName + " wins the game!";
    JOptionPane.showMessageDialog(null, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);

  }

  public static void leaderBoard() {
    panel = new LeaderBoard();
    window.setPanel(panel);
  }


  public static void  mainGame() {
    panel = new MainGame();
    window.setPanel(panel);
  }

  public static void mainMenu() {
    panel = new MainMenu();
    window.setPanel(panel);

  }

  public static void login() {
    panel = new MainLoginPanel();
    window.setPanel(panel);
  }

  private void _startGameLoop() {
    thread.start();
  }
}

package id.ac.pnb.SnakeUp;

import id.ac.pnb.SnakeUp.components.GamePanel;
import id.ac.pnb.SnakeUp.components.GameWindow;
import id.ac.pnb.SnakeUp.helpers.PropertiesHelper;
import id.ac.pnb.SnakeUp.panels.MainGame;
import id.ac.pnb.SnakeUp.panels.leaderboard;
import id.ac.pnb.SnakeUp.utils.GlobalVars;

public class Game implements Runnable {

  private GamePanel panel;
  private GameWindow window;
  private Thread thread;

  public Game() {
    _leaderboard();
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

    var frame = 0;
    var update = 0;

    var deltaFrame = 0.0;
    var deltaUpdate = 0.0;

    while (true) {
      var currentTime = System.nanoTime();

      deltaFrame += (currentTime - previousTime) / timePerFrame;
      deltaUpdate += (currentTime - previousTime) / timePerUpdate;

      previousTime = currentTime;

      if (deltaUpdate >= 1.0) {
        panel.updateGame();
        update++;
        deltaUpdate--;
      }

      if (deltaFrame >= 1.0) {
        panel.repaint();
        frame++;
        deltaFrame--;
      }

      if (System.currentTimeMillis() - lastCheck >= 1000) {
        lastCheck = System.currentTimeMillis();
//        System.out.println("Frames: " + frame + " | UPS: " + update);
        frame = 0;
        update = 0;
      }
    }
  }

  private void _initialize() {
    GlobalVars.playerCount = 2;
    this.thread = new Thread(this);
    this.panel = new MainGame();
    this.window = new GameWindow(this.panel);
  }

  private void _leaderboard() {
      this.panel = new leaderboard();
      this.window = new GameWindow(panel);
  }
  private void _startGameLoop() {
    thread.start();
  }
}

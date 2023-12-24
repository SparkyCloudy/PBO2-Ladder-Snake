package id.ac.pnb.SnakeUp;

import id.ac.pnb.SnakeUp.components.GamePanel;
import id.ac.pnb.SnakeUp.components.GameWindow;
import id.ac.pnb.SnakeUp.helpers.PropertiesHelper;
import id.ac.pnb.SnakeUp.panels.MainGame;

public class Game implements Runnable {

  private GamePanel panel;
  private GameWindow window;
  private Thread thread;

  public Game() {
    _initialize();
    _startGameLoop();
  }

  @Override
  public void run() {
    PropertiesHelper.load("configs/games.properties");
    var fps = Integer.parseInt(PropertiesHelper.get("FPS_GAME"));
    var timePerFrame = 1_000_000_000.0 / fps;
    var lastFrame = System.nanoTime();
    var now = 0L;
    var frame = 0;
    var lastCheck = 0L;

    while (true) {
      now = System.nanoTime();
      if (now - lastFrame >= timePerFrame) {
        panel.repaint();
        lastFrame = now;
        frame++;
      }

      if (System.currentTimeMillis() - lastCheck >= 1000) {
        lastCheck = System.currentTimeMillis();
//        System.out.println("Frames: " + frame);
        frame = 0;
      }
    }
  }

  private void _initialize() {
    this.thread = new Thread(this);
    this.panel = new MainGame(2);
    this.window = new GameWindow(this.panel);
  }

  private void _startGameLoop() {
    thread.start();
  }
}

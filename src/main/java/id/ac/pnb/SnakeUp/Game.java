package id.ac.pnb.SnakeUp;

import id.ac.pnb.SnakeUp.components.GamePanel;
import id.ac.pnb.SnakeUp.components.GameWindow;
import id.ac.pnb.SnakeUp.components.MainLoginPanel;
import id.ac.pnb.SnakeUp.components.PanelLoginAndRegister;
import id.ac.pnb.SnakeUp.database.DatabaseConnection;
import id.ac.pnb.SnakeUp.helpers.PropertiesHelper;
import id.ac.pnb.SnakeUp.panels.LeaderBoard;
import id.ac.pnb.SnakeUp.panels.MainGame;
import id.ac.pnb.SnakeUp.panels.MainMenu;
import id.ac.pnb.SnakeUp.utils.Constants.GamePlayer;
import id.ac.pnb.SnakeUp.utils.GlobalVars;
import java.sql.SQLException;
import javax.swing.JOptionPane;

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
      
      if (panel instanceof MainLoginPanel && ((MainLoginPanel) panel).isLoggedIn()) {
     
                   _mainMenu();
                }
       if (panel instanceof MainMenu && ((MainMenu) panel).retrun()) {
     
                    _mainGame();
                }
       if (panel instanceof MainMenu && ((MainMenu) panel).leader()) {
     
                    _leaderboard();
                }
       if (panel instanceof LeaderBoard && ((LeaderBoard) panel).backMenu()) {
     
                    _mainMenu();
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
    
    this.thread = new Thread(this);
    _login();
  }
  
   private void showWinPopup(GamePlayer player) {
        String winnerName = "Player " + (player.ordinal() + 1);
        String message = "Congratulations!\n" + winnerName + " wins the game!";
        JOptionPane.showMessageDialog(null, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }

  private void _leaderboard() {
      this.panel = new LeaderBoard();
      this.window.setPanel(panel);
  }
  

  private void _mainGame() {
    this.panel = new MainGame();
    this.window.setPanel(panel);
  }
  private void _mainMenu() {
    this.panel = new MainMenu();
    this.window.setPanel(panel);
 
}

  private void _login() {
          
    this.panel = new MainLoginPanel();
    this.window = new GameWindow(panel);
  }
 

  private void _startGameLoop() {
    thread.start();
  }
}

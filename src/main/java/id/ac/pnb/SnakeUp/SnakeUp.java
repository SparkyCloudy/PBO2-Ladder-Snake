package id.ac.pnb.SnakeUp;

import id.ac.pnb.SnakeUp.components.GameWindow;
import id.ac.pnb.SnakeUp.components.MainLoginPanel;
import id.ac.pnb.SnakeUp.database.DatabaseConnection;
import java.sql.SQLException;
import javax.swing.SwingUtilities;


public class SnakeUp {

  public static void main(String[] args) {
           try {
            DatabaseConnection.getInstance().connectToDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
      SwingUtilities.invokeLater(() -> {
      new Game();
    });
  }
}
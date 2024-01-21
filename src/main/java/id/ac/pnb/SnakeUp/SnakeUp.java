package id.ac.pnb.SnakeUp;

import id.ac.pnb.SnakeUp.database.DatabaseConnection;

import javax.swing.*;
import java.sql.SQLException;


public class SnakeUp {

  public static void main(String[] args) {
    try {
      DatabaseConnection.getInstance().connectToDatabase();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    SwingUtilities.invokeLater(Game::new);
  }
}
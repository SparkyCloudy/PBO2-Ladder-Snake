package id.ac.pnb.SnakeUp.services.game;

import id.ac.pnb.SnakeUp.database.DatabaseConnection;
import id.ac.pnb.SnakeUp.models.ModelLogin;
import id.ac.pnb.SnakeUp.models.ModelUser;
import id.ac.pnb.SnakeUp.services.AuthService;
import id.ac.pnb.SnakeUp.utils.GlobalVars;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginManager implements AuthService {
    private final Connection con;
//    private GlobalVars player = new  GlobalVars();
   

    public LoginManager() {
        con = DatabaseConnection.getInstance().getConnection();
    }

    @Override
public ModelUser login(ModelLogin login) {
    ModelUser data = null;
    try (PreparedStatement p = con.prepareStatement(
            "SELECT id_user, username FROM `user` WHERE BINARY(username) = ? AND BINARY(`password`) = ?",
            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

        p.setString(1, login.getUsername());
        p.setString(2, login.getPassword());

        try (ResultSet r = p.executeQuery()) {
             
            if (r.first()) {
               
                int userID = r.getInt(1);
                String userName = r.getString(2);
                data = new ModelUser(userID, userName);
               
                
            }
            
        }
    } catch (SQLException e) {
        System.out.println("Error in login: " + e);
    }finally{
    
    }
    return data;
}


    public void insertUser(ModelUser user) {
        try {
            if (con != null) {
                String query = "insert into `user` (username, password) values (?,?)";
                try (PreparedStatement p = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
                    p.setString(1, user.getUserName());
                    p.setString(2, user.getPassword());
                    p.execute();

                    try (ResultSet r = p.getGeneratedKeys()) {
                        if (r.next()) {
                            int userID = r.getInt(1);
                            user.setUserID(userID);
                        }
                    }
                }
            } else {
                System.out.println("Connection is null");
            }
        } catch (SQLException e) {
            System.out.println("Error in insertUser: " + e);
        }
    }

    public void insertWinrate(ModelUser user) {
        try {
            if (con != null) {
                String query = "INSERT INTO `winrate` (`match`, gameWin, id_user) VALUES (?, ?, ?)";
                try (PreparedStatement p = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
                    p.setInt(1, 0);
                    p.setInt(2, 0);
                    p.setInt(3, user.getUserID());
                    p.execute();
                }
            } else {
                System.out.println("Connection is null");
            }
        } catch (SQLException e) {
            System.out.println("Error in insertWinrate: " + e);
        }
    }

    public boolean checkDuplicateUser(String user) {
        boolean duplicate = false;
        try {
            if (con != null) {
                String query = "select id_user from `user` where username=?";
                try (PreparedStatement p = con.prepareStatement(query)) {
                    p.setString(1, user);

                    try (ResultSet r = p.executeQuery()) {
                        if (r.first()) {
                            duplicate = true;
                        }
                    }
                }
            } else {
                System.out.println("Connection is null");
            }
        } catch (SQLException e) {
            System.out.println("Error in checkDuplicateUser: " + e);
        }
        return duplicate;
    }
}

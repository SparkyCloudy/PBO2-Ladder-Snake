package id.ac.pnb.SnakeUp.services;

import id.ac.pnb.SnakeUp.database.DatabaseConnection;
import id.ac.pnb.SnakeUp.models.ModelLogin;
import id.ac.pnb.SnakeUp.models.ModelUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceUser {

    private final Connection con;

    public ServiceUser() {
        con = DatabaseConnection.getInstance().getConnection();
    }

   public ModelUser login(ModelLogin login) throws SQLException {
    ModelUser data = null;
    PreparedStatement p = con.prepareStatement("SELECT id_user, username FROM `user` WHERE BINARY(username) = ? AND BINARY(`password`) = ? AND `Status` = 'Verified' LIMIT 1");
    p.setString(1, login.getUsername());
    p.setString(2, login.getPassword());
    
    ResultSet r = p.executeQuery();
    
    if (r.first()) {
        int userID = r.getInt(1);
        String userName = r.getString(2);
     
        data = new ModelUser(userID, userName);
    }
    
    r.close();
    p.close();
    
    return data;
}


    public void insertUser(ModelUser user) throws SQLException {
        PreparedStatement p = con.prepareStatement("insert into `user` (username, password) values (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
        
        p.setString(1, user.getUserName());
        p.setString(2, user.getPassword());
        p.execute();
         ResultSet r = p.getGeneratedKeys();
    if (r.next()) {
        int userID = r.getInt(1);
        user.setUserID(userID);
    }
       
        r.close();
        p.close();
       

    }
        public void insertWinrate(ModelUser user) throws SQLException {
    PreparedStatement p = con.prepareStatement("INSERT INTO `winrate` (`match`, gameWin, id_user) VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

        p.setInt(1, 0);
        p.setInt(2, 0);
        p.setInt(3, user.getUserID());
        p.execute();
p.close();
    }


    

   

   public boolean checkDuplicateUser(String user) throws SQLException {
    boolean duplicate = false;
    if (con == null) {
        throw new SQLException("Database connection is null");
    }

    PreparedStatement p = con.prepareStatement("select id_user from `user` where username=? and `Status`='Verified' limit 1");
    p.setString(1, user);
    ResultSet r = p.executeQuery();
    if (r.first()) {
        duplicate = true;
    }
    r.close();
    p.close();
    return duplicate;
}


    public boolean checkDuplicateEmail(String user) throws SQLException {
        boolean duplicate = false;
        PreparedStatement p = con.prepareStatement("select UserID from `user` where Email=? and `Status`='Verified' limit 1");
        p.setString(1, user);
        ResultSet r = p.executeQuery();
        if (r.first()) {
            duplicate = true;
        }
        r.close();
        p.close();
        return duplicate;
    }

   

    
}

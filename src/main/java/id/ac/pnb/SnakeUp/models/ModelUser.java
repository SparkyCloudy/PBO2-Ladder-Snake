package id.ac.pnb.SnakeUp.models;

public class ModelUser {

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public ModelUser( int userID ,String userName) {
    
         this.userID = userID;
        this.userName = userName;
       
       
        
    }

    public ModelUser(String username,  String password) {

        this.userName = username;

        this.password = password;
    }

    public ModelUser() {
    }

    private int userID;
    private String userName;

    private String password;

}

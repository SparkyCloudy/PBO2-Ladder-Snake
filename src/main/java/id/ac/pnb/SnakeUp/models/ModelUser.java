package id.ac.pnb.SnakeUp.models;

import id.ac.pnb.SnakeUp.utils.Constants.GamePlayer;
import id.ac.pnb.SnakeUp.utils.GlobalVars;

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

    public ModelUser(int userID, String userName) {

        this.userID = userID;
        this.userName = userName;
        GlobalVars.playerCount ++;
        playerID();

    }
    
    public void playerID(){
        switch (GlobalVars.playerCount) {
            case 1:
                GlobalVars.userID.put(GamePlayer.ONE,this.userID);
                break;
            case 2:
                GlobalVars.userID.put(GamePlayer.TWO,this.userID);
                break;
            case 3:
                GlobalVars.userID.put(GamePlayer.THREE,this.userID);
                break;
            case 4:
                GlobalVars.userID.put(GamePlayer.FOUR,this.userID);
                break;
            default:
                break;
        }
        }
//    public void playerUsername(){
//        if(GlobalVars.playerCount == 1){
//             GlobalVars.userName.put("player1",this.userName);
//        }else if (GlobalVars.playerCount == 2) {
//            GlobalVars.userName.put("player2",this.userName);
//        }else if (GlobalVars.playerCount == 3) {
//            GlobalVars.userName.put("player3",this.userName);
//        }else if (GlobalVars.playerCount == 4) {
//            GlobalVars.userName.put("player4",this.userName);
//        }
//        }

    public ModelUser(String username, String password) {

        this.userName = username;

        this.password = password;
    }

    public ModelUser() {
    }
    
    public void clear (){
        this.userID = 0;
        this.userName = null;
    }

    private int userID;
    private String userName;

    private String password;

}

package id.ac.pnb.SnakeUp.models;

import id.ac.pnb.SnakeUp.utils.Constants.GamePlayer;
import id.ac.pnb.SnakeUp.utils.GlobalVars;

public final class ModelUser {

    public int getUserID() {
        return userID;
    }
    
    public void cekPlayer (){
      
        
     
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
        
          
       
   
    }
    
    
    public void playerID(){
        switch (GlobalVars.userID.size()) {
            case 0 -> GlobalVars.userID.put(GamePlayer.ONE,this.userID);
            case 1 -> GlobalVars.userID.put(GamePlayer.TWO,this.userID);
            case 2 -> GlobalVars.userID.put(GamePlayer.THREE,this.userID);
            case 3 -> GlobalVars.userID.put(GamePlayer.FOUR,this.userID);
            default -> {
            }
            
        }
//        cek ++;
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

    private int userID;
//    int cek = 0;
    private String userName;

    private String password;

}

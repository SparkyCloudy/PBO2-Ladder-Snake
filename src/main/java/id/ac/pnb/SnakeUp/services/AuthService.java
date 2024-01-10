package id.ac.pnb.SnakeUp.services;

import id.ac.pnb.SnakeUp.models.ModelLogin;
import id.ac.pnb.SnakeUp.models.ModelUser;

public interface AuthService {
    ModelUser login(ModelLogin login);
    
    void insertUser(ModelUser user);
    
    boolean checkDuplicateUser(String user);
    
    void insertWinrate(ModelUser user);
}

package id.ac.pnb.SnakeUp.models;

public class ModelLogin {

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ModelLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public ModelLogin() {
    }

    private String username;
    private String password;
}

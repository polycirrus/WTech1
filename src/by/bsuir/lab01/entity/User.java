package by.bsuir.lab01.entity;

public class User {
    protected String login;
    protected String passwordHash;
    
    public User(String login, String passwordHash){
        this.login = login;
        this.passwordHash = passwordHash;
    }
    
    public String getLogin(){
        return login;
    }
    
    public String getPasswordHash(){
        return passwordHash;
    }
}

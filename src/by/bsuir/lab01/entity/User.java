package by.bsuir.lab01.entity;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {

    protected String login;
    protected String passwordHash;
    protected AccessLevel accessLevel;

    public User(String login, String password, AccessLevel accessLevel) {
        this(login, password, accessLevel, false);
    }

    public User(String login, String password, AccessLevel accessLevel, boolean hashPassword){
        this.login = login;
        this.passwordHash = hashPassword ? getHashString(password) : password;
        this.accessLevel = accessLevel;
    }

    public User(UserCredentials credentials, AccessLevel accessLevel) {
        this(credentials.login, credentials.password, accessLevel, true);
    }
    
    public String getLogin() { return login; }
    
    public String getPasswordHash() { return passwordHash; }

    public AccessLevel getAccessLevel() { return accessLevel; }

    public static String getHashString(String password) {
        MessageDigest digest;
        try
        {
            digest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            //can't happen
            return null;
        }

        digest.reset();
        return new String(digest.digest(password.getBytes()), Charset.defaultCharset());
    }
}

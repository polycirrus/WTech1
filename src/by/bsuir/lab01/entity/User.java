package by.bsuir.lab01.entity;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    private String emailAddress;
    private String passwordHash;
    private AccessLevel accessLevel;

    public User(String emailAddress, String password, AccessLevel accessLevel) throws IllegalArgumentException {
        this(emailAddress, password, accessLevel, false);
    }

    public User(String emailAddress, String password, AccessLevel accessLevel, boolean hashPassword) throws IllegalArgumentException {
        this.emailAddress = emailAddress;
        this.passwordHash = hashPassword ? getHashString(password) : password;
        this.accessLevel = accessLevel;
    }

    public User(UserCredentials credentials, AccessLevel accessLevel) {
        this(credentials.login, credentials.password, accessLevel, true);
    }

    public static String getHashString(String password) throws IllegalArgumentException {
        if (password == null)
            throw new IllegalArgumentException("Password string cannot be null");

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

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setEmailAddress(String emailAddress) throws IllegalArgumentException {
        if (emailAddress == null)
            throw new IllegalArgumentException("Email address cannot be null.");

        //verify?
        this.emailAddress = emailAddress;
    }

    public void setPasswordHash(String passwordHash) throws IllegalArgumentException {
        if (passwordHash == null)
            throw new IllegalArgumentException("Password hash string cannot be null.");

        this.passwordHash = passwordHash;
    }

    public void setAccessLevel(AccessLevel accessLevel) throws IllegalArgumentException {
        if (accessLevel == null)
            throw new IllegalArgumentException("Access level cannot be null.");

        this.accessLevel = accessLevel;
    }
}

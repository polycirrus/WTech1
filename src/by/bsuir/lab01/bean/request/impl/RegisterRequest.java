package by.bsuir.lab01.bean.request.impl;

import by.bsuir.lab01.bean.request.Request;
import by.bsuir.lab01.bean.UserCredentials;

public class RegisterRequest extends Request {
    private UserCredentials credentials;

    public UserCredentials getCredentials() {
        return credentials;
    }

    public void setCredentials(UserCredentials credentials) {
        this.credentials = credentials;
    }
}

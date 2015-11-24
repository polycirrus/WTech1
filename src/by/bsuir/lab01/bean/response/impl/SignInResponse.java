package by.bsuir.lab01.bean.response.impl;

import by.bsuir.lab01.bean.SessionInfo;
import by.bsuir.lab01.bean.response.Response;

public class SignInResponse extends Response {
    private SessionInfo sessionInfo;

    public SessionInfo getSessionInfo() {
        return sessionInfo;
    }

    public void setSessionInfo(SessionInfo sessionInfo) {
        this.sessionInfo = sessionInfo;
    }
}

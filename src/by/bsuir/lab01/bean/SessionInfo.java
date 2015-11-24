package by.bsuir.lab01.bean;

import by.bsuir.lab01.entity.AccessLevel;

public class SessionInfo {
    private String sessionId;
    private AccessLevel accessLevel;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }
}

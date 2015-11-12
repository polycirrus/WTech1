package by.bsuir.lab01.entity;

public class Session {

    public String sessionId;
    public AccessLevel accessLevel;

    public Session() {}

    public Session(String sessionId, AccessLevel accessLevel) {
        this.sessionId = sessionId;
        this.accessLevel = accessLevel;
    }

}

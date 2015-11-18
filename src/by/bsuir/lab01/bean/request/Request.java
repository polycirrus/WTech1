package by.bsuir.lab01.bean.request;

public class Request {
	private String commandName;
	private String sessionId;

	public String getCommandName() {
		return commandName;
	}

    public String getSessionId() {
        return sessionId;
    }

	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}

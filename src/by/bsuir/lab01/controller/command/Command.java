package by.bsuir.lab01.controller.command;

import by.bsuir.lab01.bean.request.Request;
import by.bsuir.lab01.bean.response.Response;

public interface Command {
	Response execute(Request request) throws CommandException;
}

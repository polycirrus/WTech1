package by.bsuir.lab01.controller;

import by.bsuir.lab01.bean.request.Request;
import by.bsuir.lab01.bean.response.Response;
import by.bsuir.lab01.controller.command.Command;
import by.bsuir.lab01.controller.command.CommandException;
import by.bsuir.lab01.controller.command.CommandHelper;

public class BookController {

	private CommandHelper commandList = new CommandHelper();

	public Response executeRequest(Request request){
		Response response = null;
		try {
			String commandName = request.getCommandName();
			Command command = commandList.getCommand(commandName);
			response = command.execute(request);
		} catch (CommandException ex) {
			response = new Response();
			response.setMessage(ex.getMessage());
		}
		return response;		
	}

}

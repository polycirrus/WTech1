package by.bsuir.lab01.controller.command.authorization;

import by.bsuir.lab01.bean.request.Request;
import by.bsuir.lab01.bean.response.Response;
import by.bsuir.lab01.controller.command.Command;
import by.bsuir.lab01.controller.command.CommandException;
import by.bsuir.lab01.service.AuthorizationService;
import by.bsuir.lab01.service.ServiceException;

public class SignOutCommand implements Command {
    @Override
    public Response execute(Request request) throws CommandException {
        try {
            AuthorizationService.SignOut(request.getSessionId());
        } catch (ServiceException exception) {
            throw new CommandException(exception.getMessage());
        }

        Response response = new Response();
        response.setMessage("Sign out successful.");

        return response;
    }
}

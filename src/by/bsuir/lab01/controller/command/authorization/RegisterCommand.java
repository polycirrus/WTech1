package by.bsuir.lab01.controller.command.authorization;

import by.bsuir.lab01.bean.request.Request;
import by.bsuir.lab01.bean.request.impl.RegisterRequest;
import by.bsuir.lab01.bean.response.Response;
import by.bsuir.lab01.controller.command.Command;
import by.bsuir.lab01.controller.command.CommandException;
import by.bsuir.lab01.service.AuthorizationService;
import by.bsuir.lab01.service.ServiceException;

public class RegisterCommand implements Command {
    @Override
    public Response execute(Request request) throws CommandException {
        RegisterRequest rRequest;
        try {
            rRequest = (RegisterRequest) request;
        } catch (ClassCastException exception) {
            throw new CommandException("Invalid request.");
        }

        try {
            AuthorizationService.Register(rRequest.getCredentials());
        } catch (ServiceException exception) {
            throw new CommandException(exception.getMessage());
        }

        Response response = new Response();
        response.setMessage("Registration successful.");

        return response;
    }
}

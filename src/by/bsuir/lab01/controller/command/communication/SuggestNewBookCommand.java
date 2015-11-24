package by.bsuir.lab01.controller.command.communication;

import by.bsuir.lab01.bean.request.Request;
import by.bsuir.lab01.bean.request.impl.SuggestNewBookRequest;
import by.bsuir.lab01.bean.response.Response;
import by.bsuir.lab01.controller.command.Command;
import by.bsuir.lab01.controller.command.CommandException;
import by.bsuir.lab01.service.CommunicationService;
import by.bsuir.lab01.service.ServiceException;

public class SuggestNewBookCommand implements Command {
    @Override
    public Response execute(Request request) throws CommandException {
        SuggestNewBookRequest snbRequest;
        try {
            snbRequest = (SuggestNewBookRequest) request;
        } catch (ClassCastException exception) {
            throw new CommandException("Invalid request.");
        }

        try {
            CommunicationService.SuggestNewBook(snbRequest.getSessionId(), snbRequest.getNewBook());
        } catch (ServiceException exception) {
            throw new CommandException(exception.getMessage());
        }

        Response response = new Response();
        response.setMessage("Successfully suggested book.");

        return response;
    }
}

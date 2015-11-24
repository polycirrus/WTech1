package by.bsuir.lab01.controller.command.library;

import by.bsuir.lab01.bean.request.Request;
import by.bsuir.lab01.bean.request.impl.RemoveBooksByTitleRequest;
import by.bsuir.lab01.bean.response.Response;
import by.bsuir.lab01.controller.command.Command;
import by.bsuir.lab01.controller.command.CommandException;
import by.bsuir.lab01.service.LibraryService;
import by.bsuir.lab01.service.ServiceException;

public class RemoveBooksByTitleCommand implements Command {
    @Override
    public Response execute(Request request) throws CommandException {
        RemoveBooksByTitleRequest rRequest;
        try {
            rRequest = (RemoveBooksByTitleRequest) request;
        } catch (ClassCastException exception) {
            throw new CommandException("Invalid request.");
        }

        int removedBooksCount;
        try {
            removedBooksCount = LibraryService.removeBooksByTitle(rRequest.getSessionId(), rRequest.getTitle());
        } catch (ServiceException exception) {
            throw new CommandException(exception.getMessage());
        }

        Response response = new Response();
        response.setMessage(String.format("Removed %d books.", removedBooksCount));

        return response;
    }
}

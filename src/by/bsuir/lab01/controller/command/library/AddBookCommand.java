package by.bsuir.lab01.controller.command.library;

import by.bsuir.lab01.bean.request.Request;
import by.bsuir.lab01.bean.request.impl.AddBookRequest;
import by.bsuir.lab01.bean.response.Response;
import by.bsuir.lab01.controller.command.Command;
import by.bsuir.lab01.controller.command.CommandException;
import by.bsuir.lab01.service.LibraryService;
import by.bsuir.lab01.service.ServiceException;

public class AddBookCommand implements Command {
    @Override
    public Response execute(Request request) throws CommandException {
        AddBookRequest abRequest;
        try {
            abRequest = (AddBookRequest) request;
        } catch (ClassCastException exception) {
            throw new CommandException("Invalid request.");
        }

        try {
            LibraryService.addBook(abRequest.getSessionId(), abRequest.getNewBook());
        } catch (ServiceException exception) {
            throw new CommandException(exception.getMessage());
        }

        Response response = new Response();
        response.setMessage("Successfully added book.");

        return response;
    }
}

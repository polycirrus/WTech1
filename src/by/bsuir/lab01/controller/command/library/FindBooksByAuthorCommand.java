package by.bsuir.lab01.controller.command.library;

import by.bsuir.lab01.bean.request.Request;
import by.bsuir.lab01.bean.request.impl.FindBooksByAuthorRequest;
import by.bsuir.lab01.bean.response.Response;
import by.bsuir.lab01.bean.response.impl.GetBooksResponse;
import by.bsuir.lab01.controller.command.Command;
import by.bsuir.lab01.controller.command.CommandException;
import by.bsuir.lab01.entity.Book;
import by.bsuir.lab01.service.LibraryService;
import by.bsuir.lab01.service.ServiceException;

import java.util.Collection;

public class FindBooksByAuthorCommand implements Command {
    @Override
    public Response execute(Request request) throws CommandException {
        FindBooksByAuthorRequest fRequest;
        try {
            fRequest = (FindBooksByAuthorRequest) request;
        } catch (ClassCastException exception) {
            throw new CommandException("Invalid request.");
        }

        Collection<Book> books;
        try {
            books = LibraryService.findBooksByAuthor(fRequest.getSessionId(), fRequest.getAuthor());
        } catch (ServiceException exception) {
            throw new CommandException(exception.getMessage());
        }

        GetBooksResponse response = new GetBooksResponse();
        response.setMessage(String.format("Found %d books.", books.size()));
        response.setBooks(books);

        return response;
    }
}

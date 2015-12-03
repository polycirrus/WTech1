package by.bsuir.lab01.controller.command.library;

import by.bsuir.lab01.bean.request.Request;
import by.bsuir.lab01.bean.request.impl.FindBookByIsbnRequest;
import by.bsuir.lab01.bean.response.Response;
import by.bsuir.lab01.bean.response.impl.GetBooksResponse;
import by.bsuir.lab01.controller.command.Command;
import by.bsuir.lab01.controller.command.CommandException;
import by.bsuir.lab01.entity.Book;
import by.bsuir.lab01.service.LibraryService;
import by.bsuir.lab01.service.ServiceException;

import java.util.ArrayList;
import java.util.Collection;

public class FindBooksByIsbnCommand implements Command {
    @Override
    public Response execute(Request request) throws CommandException {
        FindBookByIsbnRequest fRequest;
        try {
            fRequest = (FindBookByIsbnRequest) request;
        } catch (ClassCastException exception) {
            throw new CommandException("Invalid request.");
        }

        Book book;
        try {
            book = LibraryService.findBooksByIsbn(fRequest.getSessionId(), fRequest.getIsbn());
        } catch (ServiceException exception) {
            throw new CommandException(exception.getMessage());
        }

        Collection<Book> books = new ArrayList<>();
        if (book != null)
            books.add(book);

        GetBooksResponse response = new GetBooksResponse();
        response.setMessage(String.format("Found %d books.", books.size()));
        response.setBooks(books);

        return response;
    }
}

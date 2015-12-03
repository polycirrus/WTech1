package by.bsuir.lab01.view.console;

import by.bsuir.lab01.bean.UserCredentials;
import by.bsuir.lab01.bean.request.Request;
import by.bsuir.lab01.bean.request.impl.*;
import by.bsuir.lab01.bean.response.Response;
import by.bsuir.lab01.bean.response.impl.GetBooksResponse;
import by.bsuir.lab01.bean.response.impl.SignInResponse;
import by.bsuir.lab01.entity.AccessLevel;
import by.bsuir.lab01.entity.Book;
import by.bsuir.lab01.view.View;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleView extends View {
    private Map<String, MenuItem> menuItems;
    private Scanner scanner;

    public ConsoleView() {
        menuItems = new HashMap<>();

        menuItems.put("add", new MenuItem(this::addBook, "Add book", AccessLevel.ADMINISTRATOR));
        menuItems.put("find_a", new MenuItem(this::findBooksByAuthor, "Find books by author", AccessLevel.USER));
        menuItems.put("find_i", new MenuItem(this::findBooksByIsbn, "Find books by ISBN", AccessLevel.USER));
        menuItems.put("find_t", new MenuItem(this::findBooksByTitle, "Find books by title", AccessLevel.USER));
        menuItems.put("view", new MenuItem(this::getBooks, "View all books", AccessLevel.USER));
        menuItems.put("remove_t", new MenuItem(this::removeBooksByTitle, "Remove books by title", AccessLevel.ADMINISTRATOR));

        menuItems.put("register", new MenuItem(this::register, "Register", AccessLevel.NONE));
        menuItems.put("signin", new MenuItem(this::signIn, "Sign in", AccessLevel.NONE));
        menuItems.put("signout", new MenuItem(this::signOut, "Sign out", AccessLevel.USER));

        menuItems.put("suggest", new MenuItem(this::suggestNewBook, "Suggest new book", AccessLevel.USER));

        menuItems.put("exit", new MenuItem(this::exit, "Exit", AccessLevel.NONE));

        scanner = new Scanner(System.in);
    }

    @Override
    public void view() {
        String input = "";
        while (!input.equals("exit")) {
            printMenuItems();
            input = scanner.nextLine();

            MenuItem menuItem = menuItems.get(input);
            if (menuItem != null)
                menuItem.getMethod().getAsBoolean();
            else
                System.out.println("Command not found.");

            System.out.println();
        }
    }

    private void printMenuItems() {
        menuItems.entrySet().stream().filter(entry -> entry.getValue().getAccessLevel().ordinal() <= accessLevel.ordinal())
                .forEach(entry -> System.out.printf("%-10s %s\n", entry.getKey(), entry.getValue().getDescription()));
    }

    private boolean addBook() {
        Book newBook = readBook();

        AddBookRequest request = new AddBookRequest();
        request.setCommandName("ADD_BOOK");
        request.setSessionId(sessionId);
        request.setNewBook(newBook);

        return executeRequest(request);
    }

    private boolean findBooksByAuthor() {
        System.out.print("Author: ");
        String author = scanner.nextLine();

        FindBooksByAuthorRequest request = new FindBooksByAuthorRequest();
        request.setSessionId(sessionId);
        request.setCommandName("FIND_BOOKS_BY_AUTHOR");
        request.setAuthor(author);

        return executeGetRequest(request);
    }

    private boolean findBooksByIsbn() {
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        FindBookByIsbnRequest request = new FindBookByIsbnRequest();
        request.setSessionId(sessionId);
        request.setCommandName("FIND_BOOKS_BY_ISBN");
        request.setIsbn(isbn);

        return executeGetRequest(request);
    }

    private boolean findBooksByTitle() {
        System.out.print("Title: ");
        String title = scanner.nextLine();

        FindBooksByTitleRequest request = new FindBooksByTitleRequest();
        request.setSessionId(sessionId);
        request.setCommandName("FIND_BOOKS_BY_TITLE");
        request.setTitle(title);

        return executeGetRequest(request);
    }

    private boolean getBooks() {
        Request request = new Request();
        request.setCommandName("GET_BOOKS");
        request.setSessionId(sessionId);

        return executeGetRequest(request);
    }

    private boolean removeBooksByTitle() {
        System.out.print("Title: ");
        String title = scanner.nextLine();

        RemoveBooksByTitleRequest request = new RemoveBooksByTitleRequest();
        request.setSessionId(sessionId);
        request.setCommandName("REMOVE_BOOKS_BY_TITLE");
        request.setTitle(title);

        return executeRequest(request);
    }

    private boolean register() {
        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        RegisterRequest request = new RegisterRequest();
        request.setCommandName("REGISTER");
        request.setCredentials(new UserCredentials(email, password));

        return executeRequest(request);
    }

    private boolean signIn() {
        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        SignInRequest request = new SignInRequest();
        request.setCredentials(new UserCredentials(email, password));
        request.setCommandName("SIGN_IN");

        Response response = controller.executeRequest(request);
        if (!(response instanceof SignInResponse)) {
            System.out.println(response.getMessage());
            return false;
        }

        SignInResponse signInResponse = (SignInResponse) response;
        System.out.println(signInResponse.getMessage());
        if (signInResponse.getSessionInfo() != null) {
            sessionId = signInResponse.getSessionInfo().getSessionId();
            accessLevel = signInResponse.getSessionInfo().getAccessLevel();
        }

        return true;
    }

    private boolean signOut() {
        Request request = new Request();
        request.setCommandName("SIGN_OUT");
        request.setSessionId(sessionId);

        Response response = controller.executeRequest(request);
        System.out.println(response.getMessage());

        sessionId = null;
        accessLevel = AccessLevel.NONE;

        return true;
    }

    private boolean suggestNewBook() {
        Book newBook = readBook();

        SuggestNewBookRequest request = new SuggestNewBookRequest();
        request.setSessionId(sessionId);
        request.setNewBook(newBook);
        request.setCommandName("SUGGEST_NEW_BOOK");

        return executeRequest(request);
    }

    private boolean exit() {
        if (sessionId != null)
            signOut();

        return true;
    }

    private boolean executeRequest(Request request) {
        Response response = controller.executeRequest(request);
        System.out.println(response.getMessage());

        return true;
    }

    private boolean executeGetRequest(Request request) {
        Response response = controller.executeRequest(request);
        if (!(response instanceof GetBooksResponse)) {
            System.out.println(response.getMessage());
            return false;
        }

        GetBooksResponse gbResponse = (GetBooksResponse) response;
        System.out.println(gbResponse.getMessage());
        gbResponse.getBooks().forEach(book -> System.out.println(book.toString()));

        return true;
    }

    private Book readBook() {
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("E-book?: ");
        boolean physical = !Boolean.parseBoolean(scanner.nextLine());

        Book newBook = new Book(title, physical);

        String field;
        System.out.print("Author: ");
        field = scanner.nextLine();
        if (field.length() > 0)
            newBook.setAuthor(field);
        System.out.print("ISBN: ");
        field = scanner.nextLine();
        if (field.length() > 0)
            newBook.setIsbn(field);

        return newBook;
    }
}

package by.bsuir.lab01.view.console;

import by.bsuir.lab01.bean.UserCredentials;
import by.bsuir.lab01.bean.request.Request;
import by.bsuir.lab01.bean.request.impl.AddBookRequest;
import by.bsuir.lab01.bean.request.impl.RegisterRequest;
import by.bsuir.lab01.bean.request.impl.SignInRequest;
import by.bsuir.lab01.bean.response.Response;
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

    public void printMenuItems() {
        menuItems.entrySet().stream().filter(entry -> entry.getValue().getAccessLevel().ordinal() <= accessLevel.ordinal())
                .forEach(entry -> System.out.println(entry.getKey() + ":\t" + entry.getValue().getDescription()));
    }

    public boolean addBook() {
        AddBookRequest r = new AddBookRequest();
        r.setNewBook(new Book("a", "b", "c", true));
        r.setSessionId(null);
        controller.executeRequest(r);
        return true;
    }

    public boolean findBooksByAuthor() {
        return true;
    }

    public boolean findBooksByIsbn() {
        return true;
    }

    public boolean findBooksByTitle() {
        return true;
    }

    public boolean getBooks() {
        return true;
    }

    public boolean removeBooksByTitle() {
        return true;
    }

    public boolean register() {
        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        RegisterRequest request = new RegisterRequest();
        request.setCommandName("REGISTER");
        request.setCredentials(new UserCredentials(email, password));

        Response response = controller.executeRequest(request);
        System.out.println(response.getMessage());

        return true;
    }

    public boolean signIn() {
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

    public boolean signOut() {
        Request request = new Request();
        request.setCommandName("SIGN_OUT");
        request.setSessionId(sessionId);

        Response response = controller.executeRequest(request);
        System.out.println(response.getMessage());

        sessionId = null;
        accessLevel = AccessLevel.NONE;

        return true;
    }

    public boolean suggestNewBook() {
        return true;
    }

    public boolean exit() {
        if (sessionId != null)
            signOut();

        return true;
    }
}

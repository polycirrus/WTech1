package by.bsuir.lab01.view.console;

import by.bsuir.lab01.bean.request.impl.AddBookRequest;
import by.bsuir.lab01.entity.AccessLevel;
import by.bsuir.lab01.entity.Book;
import by.bsuir.lab01.view.View;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleView extends View {
    private Map<String, MenuItem> menuItems;

    public ConsoleView() {
        menuItems = new HashMap<>();

        menuItems.put("add", new MenuItem(this::AddBook, "Add book", AccessLevel.ADMINISTRATOR));
        menuItems.put("find_a", new MenuItem(this::FindBooksByAuthor, "Find books by author", AccessLevel.USER));
        menuItems.put("find_i", new MenuItem(this::FindBooksByIsbn, "Find books by ISBN", AccessLevel.USER));
        menuItems.put("find_t", new MenuItem(this::FindBooksByTitle, "Find books by title", AccessLevel.USER));
        menuItems.put("view", new MenuItem(this::GetBooks, "View all books", AccessLevel.USER));
        menuItems.put("remove_t", new MenuItem(this::RemoveBooksByTitle, "Remove books by title", AccessLevel.ADMINISTRATOR));

        menuItems.put("register", new MenuItem(this::Register, "Register", null));
        menuItems.put("signin", new MenuItem(this::SignIn, "Sign in", null));
        menuItems.put("signout", new MenuItem(this::SignOut, "Sign out", AccessLevel.USER));

        menuItems.put("suggest", new MenuItem(this::SuggestNewBook, "Suggest new book", AccessLevel.USER));

        menuItems.put("exit", new MenuItem(this::Exit, "Exit", null));
    }

    @Override
    public void view() {
        Scanner scanner = new Scanner(System.in);

        String input = null;
        while (input != "exit") {
            input = scanner.nextLine();
        }
    }

    public void PrintMenuItems() {
        menuItems.entrySet().stream().forEach(entry -> System.out.println(entry.getKey() + ":\t" + entry.getValue().getDescription()));
    }

    public boolean AddBook() {
        AddBookRequest r = new AddBookRequest();
        r.setNewBook(new Book("a", "b", "c", true));
        r.setSessionId(null);
        controller.executeRequest(r);
        return true;
    }

    public boolean FindBooksByAuthor() {
        return true;
    }

    public boolean FindBooksByIsbn() {
        return true;
    }

    public boolean FindBooksByTitle() {
        return true;
    }

    public boolean GetBooks() {
        return true;
    }

    public boolean RemoveBooksByTitle() {
        return true;
    }

    public boolean Register() {
        return true;
    }

    public boolean SignIn() {
        return true;
    }

    public boolean SignOut() {
        return true;
    }

    public boolean SuggestNewBook() {
        return true;
    }

    public boolean Exit() {
        return true;
    }
}

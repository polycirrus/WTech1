package by.bsuir.lab01.view;

import by.bsuir.lab01.controller.BookController;
import by.bsuir.lab01.entity.AccessLevel;

public abstract class View {
    protected BookController controller = new BookController();
    protected String sessionId = null;
    protected AccessLevel accessLevel = AccessLevel.NONE;

    public abstract void view();
}

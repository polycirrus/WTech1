package by.bsuir.lab01.bean.request.impl;

import by.bsuir.lab01.bean.request.Request;

public class RemoveBooksByTitleRequest extends Request {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

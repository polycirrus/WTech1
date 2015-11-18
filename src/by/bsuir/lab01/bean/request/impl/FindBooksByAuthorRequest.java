package by.bsuir.lab01.bean.request.impl;

import by.bsuir.lab01.bean.request.Request;

public class FindBooksByAuthorRequest extends Request {
    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

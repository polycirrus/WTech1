package by.bsuir.lab01.bean.request.impl;

import by.bsuir.lab01.bean.request.Request;

public class FindBookByIsbnRequest extends Request {
    private String isbn;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}

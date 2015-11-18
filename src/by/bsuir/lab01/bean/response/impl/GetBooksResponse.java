package by.bsuir.lab01.bean.response.impl;

import by.bsuir.lab01.bean.response.Response;
import by.bsuir.lab01.entity.Book;

import java.util.Collection;

public class GetBooksResponse extends Response {
    private Collection<Book> books;

    public Collection<Book> getBooks() {
        return books;
    }

    public void setBooks(Collection<Book> books) {
        this.books = books;
    }
}

package by.bsuir.lab01.bean.request.impl;

import by.bsuir.lab01.bean.request.Request;
import by.bsuir.lab01.entity.Book;

public class AddBookRequest extends Request {
	private Book newBook;

	public Book getNewBook() {
		return newBook;
	}

	public void setNewBook(Book newBook) {
		this.newBook = newBook;
	}
}

package by.bsuir.lab01.dao;

import by.bsuir.lab01.entity.Book;
import java.util.Collection;

public interface LibraryDao {
    public Collection<Book> getBooks() throws  DaoException;
    public Collection<Book> findBooksByAuthor(String author) throws DaoException;
    public Collection<Book> findBooksByIsbn(String isbn) throws DaoException;
    public Collection<Book> findBooksByTitle(String title) throws DaoException;
    public void addBook(Book newBook) throws DaoException;
    public boolean removeBook(String isbn) throws DaoException;
}

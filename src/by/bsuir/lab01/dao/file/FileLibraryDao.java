package by.bsuir.lab01.dao.file;

import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.dao.LibraryDao;
import by.bsuir.lab01.entity.Book;

import java.util.ArrayList;
import java.util.Collection;

public class FileLibraryDao implements LibraryDao {

    private final static FileLibraryDao instance = new FileLibraryDao();

    private static final String fileName = "books.txt"; //you must read it from property file

    private FileLibraryDao(){}

    public static FileLibraryDao getInstance(){
        return instance;
    }

    @Override
    public void addBook(Book newBook) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Book> findBooksByAuthor(String author) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Book> findBooksByIsbn(String isbn) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Book> findBooksByTitle(String title) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeBook(String isbn) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Book parseLine(String line) throws IllegalArgumentException {
        ArrayList<String> fields = new ArrayList<>(4);

        int parsePos = 0;
        while (parsePos < line.length()) {
            int tabIndex = line.indexOf('\0');
            String field = line.substring(parsePos, tabIndex);
            fields.add(field.length() > 0 ? field : null);
            parsePos = tabIndex + 1;
        }

        if (fields.size() != 4)
            throw new IllegalArgumentException(String.format("\"%s\": illegal format: each line must contain 4 null-separated elements.", line));
        if (fields.get(0).length() == 0)
            throw new IllegalArgumentException(String.format("\"%s\": illegal format: title must be present.", line));

        return new Book(fields.get(0), fields.get(1), fields.get(2), fields.get(3));
    }
    
}

package by.bsuir.lab01.dao.file;

import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.dao.LibraryDao;
import by.bsuir.lab01.entity.Book;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public class FileLibraryDao implements LibraryDao {

    private final static FileLibraryDao instance = new FileLibraryDao();

    private static final String fileName = "books.txt"; //you must read it from property file

    private FileLibraryDao(){}

    public static FileLibraryDao getInstance(){
        return instance;
    }

    @Override
    public void addBook(Book newBook) throws DaoException {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(fileName, true));

            writer.write(bookToString(newBook));
            writer.newLine();

            writer.close();
        } catch (IOException exception) {
            throw new DaoException(String.format("An error occurred while writing to file %s.", fileName), exception);
        }
    }

    @Override
    public Collection<Book> getBooks() throws DaoException {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(fileName));
        } catch (IOException exception) {
            throw new DaoException(String.format("An error occurred while reading from file %s.", fileName), exception);
        }

        List<Book> books = new ArrayList<>(lines.size());
        for (String line : lines) {
            try {
                books.add(parseLine(line));
            } catch (IllegalArgumentException exception) {
                //log
            }
        }

        return books;
    }

    @Override
    public Collection<Book> findBooksByAuthor(String author) throws DaoException {
        return findBooks(book -> author.equals(book.getAuthor()));
    }

    @Override
    public Collection<Book> findBooksByTitle(String title) throws DaoException {
        return findBooks(book -> title.equals(book.getTitle()));
    }

    @Override
    public Book findBookByIsbn(String isbn) throws DaoException {
        return findBooks(book -> isbn.equals(book.getIsbn())).stream().findFirst().orElse(null);
    }

    @Override
    public int removeBook(String title) throws DaoException {
        Collection<Book> books = getBooks();

        Book[] newBooks = books.stream().filter(book -> !book.getTitle().equals(title)).toArray(Book[]::new);
        int result = books.size() - newBooks.length;

        try {
            Files.delete(Paths.get(fileName));
        } catch (IOException exception) {
            throw new DaoException(String.format("An error occurred while writing to file %s.", fileName), exception);
        }

        for (Book book : newBooks)
            addBook(book);

        return result;
    }

    private Book parseLine(String line) throws IllegalArgumentException {
        ArrayList<String> fields = new ArrayList<>(4);

        int parsePos = 0;
        while (parsePos <= line.length()) {
            int tabIndex = line.indexOf('\0', parsePos);
            String field = line.substring(parsePos, tabIndex > 0 ? tabIndex : line.length());
            fields.add(field.length() > 0 ? field : null);
            parsePos = tabIndex >= 0 ? tabIndex + 1 : line.length() + 1;
        }

        if (fields.size() != 4)
            throw new IllegalArgumentException(String.format("\"%s\": illegal format: each line must contain 4 null-separated elements.", line));
        if (fields.get(0).length() == 0)
            throw new IllegalArgumentException(String.format("\"%s\": illegal format: title must be present.", line));

        return new Book(fields.get(0), fields.get(1), fields.get(2), fields.get(3) != null && Boolean.parseBoolean(fields.get(3)));
    }

    private String bookToString(Book book) {
        StringBuilder builder = new StringBuilder(book.getTitle());

        builder.append('\0');
        String nextField = book.getAuthor();
        if (nextField != null)
            builder.append(nextField);

        builder.append('\0');
        nextField = book.getIsbn();
        if (nextField != null)
            builder.append(nextField);

        builder.append('\0');
        builder.append(book.isPhysical());

        return builder.toString();
    }

    private Collection<Book> findBooks(Predicate<Book> filter) throws DaoException {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(fileName));
        }
        catch (IOException exception) {
            throw new DaoException(String.format("An error occurred while reading from file %s.", fileName), exception);
        }

        List<Book> books = new ArrayList<>(lines.size());
        for (String line : lines) {
            try {
                Book book = parseLine(line);
                if (filter.test(book))
                    books.add(book);
            }
            catch (IllegalArgumentException exception) {
                //log
            }
        }

        return books;
    }

}

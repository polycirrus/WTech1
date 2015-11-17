package by.bsuir.lab01.service;

import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.dao.DaoFactory;
import by.bsuir.lab01.dao.LibraryDao;
import by.bsuir.lab01.entity.AccessLevel;
import by.bsuir.lab01.entity.Book;

import java.util.Collection;

public class LibraryService {

    private LibraryService() {}

    public static void addBook(String sessionId, Book newBook) throws ServiceException {
        if (!AuthorizationService.Authenticate(sessionId, AccessLevel.ADMINISTRATOR))
            throw new ServiceException("Unable to add book: insufficient access level.");

        try {
            LibraryDao dao = getDao();
            dao.addBook(newBook);
        } catch (DaoException e) {
            throw new ServiceException("Unable to add book: data access failed.");
        } catch (ServiceException e) {
            throw new ServiceException("Unable to add book: " + e.getMessage(), e);
        }

        try {
            CommunicationService.SendNewsletter(sessionId, "A new book has been added to the library: " + newBook.toString());
        } catch (ServiceException exception) {
            //log
        }
    }

    public static Collection<Book> getBooks(String sessionId) throws ServiceException {
        if (!AuthorizationService.Authenticate(sessionId, AccessLevel.USER))
            throw new ServiceException("Unable to get books: insufficient access level.");

        Collection<Book> result;
        try {
            LibraryDao dao = getDao();
            result = dao.getBooks();
        } catch (DaoException e) {
            throw new ServiceException("Unable to add book: data access failed.");
        } catch (ServiceException e) {
            throw new ServiceException("Unable to add book: " + e.getMessage(), e);
        }

        return result;
    }

    public static Collection<Book> findBooksByTitle(String sessionId, String title) throws ServiceException {
        if (!AuthorizationService.Authenticate(sessionId, AccessLevel.USER))
            throw new ServiceException("Unable to add book: insufficient access level.");

        Collection<Book> result;
        try {
            LibraryDao dao = getDao();
            result = dao.findBooksByTitle(title);
        } catch (DaoException e) {
            throw new ServiceException("Unable to add book: data access failed.");
        } catch (ServiceException e) {
            throw new ServiceException("Unable to add book: " + e.getMessage(), e);
        }

        return result;
    }

    public static Collection<Book> findBooksByAuthor(String sessionId, String author) throws ServiceException {
        if (!AuthorizationService.Authenticate(sessionId, AccessLevel.USER))
            throw new ServiceException("Unable to add book: insufficient access level.");

        Collection<Book> result;
        try {
            LibraryDao dao = getDao();
            result = dao.findBooksByAuthor(author);
        } catch (DaoException e) {
            throw new ServiceException("Unable to add book: data access failed.");
        } catch (ServiceException e) {
            throw new ServiceException("Unable to add book: " + e.getMessage(), e);
        }

        return result;
    }

    public static Book findBooksByIsbn(String sessionId, String isbn) throws ServiceException {
        if (!AuthorizationService.Authenticate(sessionId, AccessLevel.USER))
            throw new ServiceException("Unable to add book: insufficient access level.");

        Book result;
        try {
            LibraryDao dao = getDao();
            result = dao.findBookByIsbn(isbn);
        } catch (DaoException e) {
            throw new ServiceException("Unable to add book: data access failed.");
        } catch (ServiceException e) {
            throw new ServiceException("Unable to add book: " + e.getMessage(), e);
        }

        return result;
    }

    public static boolean removeBooksByTitle(String sessionId, String title) throws ServiceException {
        if (!AuthorizationService.Authenticate(sessionId, AccessLevel.ADMINISTRATOR))
            throw new ServiceException("Unable to add book: insufficient access level.");

        boolean result;
        try {
            LibraryDao dao = getDao();
            result = dao.removeBook(title);
        } catch (DaoException e) {
            throw new ServiceException("Unable to add book: data access failed.");
        } catch (ServiceException e) {
            throw new ServiceException("Unable to add book: " + e.getMessage(), e);
        }

        return result;
    }

    private static LibraryDao getDao() throws ServiceException {
        LibraryDao dao;
        try {
            dao = DaoFactory.getDaoFactory().getLibraryDao();
        } catch (DaoException e) {
            throw new ServiceException("Could not get a data access object.");
        }

        return dao;
    }

}

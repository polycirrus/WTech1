package by.bsuir.lab01.service;

import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.dao.DaoFactory;
import by.bsuir.lab01.dao.LibraryDao;
import by.bsuir.lab01.entity.AccessLevel;
import by.bsuir.lab01.entity.Book;

public class LibraryService {

    private LibraryService(){}

    public static void addBook(String sessionId, Book newBook) throws ServiceException {
        if (!AuthorizationService.Authenticate(sessionId, AccessLevel.USER))
            throw new ServiceException("Unable to add book: insufficient access level.");

        try {
            LibraryDao dao = getDao();
            dao.addBook(newBook);
        } catch (DaoException e) {
            throw new ServiceException("Unable to add book: data access failed.");
        }
    }

    private static LibraryDao getDao() throws ServiceException {
        LibraryDao dao;
        try {
            dao = DaoFactory.getDaoFactory().getLibraryDao();
        } catch (DaoException e) {
            throw new ServiceException("Could not get a data access object: " + e.getMessage(), e);
        }

        return dao;
    }

}

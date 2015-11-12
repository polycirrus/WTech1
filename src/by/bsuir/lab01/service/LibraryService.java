package by.bsuir.lab01.service;

import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.dao.DaoFactory;
import by.bsuir.lab01.dao.LibraryDao;
import by.bsuir.lab01.entity.Book;

public class LibraryService {

    private LibraryService(){}

    private static LibraryDao getDao() throws ServiceException {
        LibraryDao dao;
        try {
            dao = DaoFactory.getDaoFactory().getLibraryDao();
        } catch (DaoException e) {
            throw new ServiceException("Could not get a DAO object: " + e.getMessage(), e);
        }

        return dao;
    }

    public static void addBook(int sessionId, Book newBook) throws ServiceException {
        //authenticate

        LibraryDao dao = getDao();

        try {
            dao.addBook(newBook);
        } catch (DaoException e) {

        }
    }

}

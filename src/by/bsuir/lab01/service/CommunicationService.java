package by.bsuir.lab01.service;

import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.dao.DaoFactory;
import by.bsuir.lab01.entity.AccessLevel;
import by.bsuir.lab01.entity.User;

import java.util.Collection;

public class CommunicationService {
    public static void SendNewsletter(String sessionId, String message) throws ServiceException {
        if (!AuthorizationService.Authenticate(sessionId, AccessLevel.ADMINISTRATOR))
            throw new ServiceException("Unable to send newsletter: insufficient access level.");

        Collection<User> users;
        try {
            users = DaoFactory.getDaoFactory().getAuthorizationDao().getUsers();
        } catch (DaoException exception) {
            throw new ServiceException("Unable to send messages: data access failed.");
        }

        SendMessage(users, message);
    }

    public static void SuggestNewBook(String sessionId, String message) throws ServiceException {
        if (!AuthorizationService.Authenticate(sessionId, AccessLevel.ADMINISTRATOR))
            throw new ServiceException("Unable to send newsletter: insufficient access level.");

        Collection<User> administrators;
        try {
            administrators = DaoFactory.getDaoFactory().getAuthorizationDao().getUsers(AccessLevel.ADMINISTRATOR);
        } catch (DaoException exception) {
            throw new ServiceException("Unable to send messages: data access failed.");
        }

        SendMessage(administrators, "New book suggestion: " + message);
    }

    private static void SendMessage(Collection<User> users, String message) {
        for (User user : users) {
            try {
                SendMessage(user.getEmailAddress(), message);
            } catch (ServiceException exception) {
                //log
            }
        }
    }

    private static void SendMessage(String address, String message) throws ServiceException {
        throw new ServiceException("Not implemented.");
    }
}

package by.bsuir.lab01.service;

import by.bsuir.lab01.dao.AuthorizationDao;
import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.dao.DaoFactory;
import by.bsuir.lab01.entity.AccessLevel;
import by.bsuir.lab01.entity.Session;
import by.bsuir.lab01.entity.User;
import by.bsuir.lab01.entity.UserCredentials;

import java.util.UUID;

public class AuthorizationService {

    public static String SignIn(UserCredentials credentials) throws ServiceException {
        try {
            AuthorizationDao dao = getDao();

            User target = dao.getUser(credentials.login);

            if (target == null || !target.getPasswordHash().equals(User.getHashString(credentials.password)))
                throw new ServiceException("Invalid login and password combination.");

            String sessionId = UUID.randomUUID().toString();
            dao.addSession(new Session(sessionId, target.getAccessLevel()));

            return sessionId;
        } catch (DaoException e) {
            throw new ServiceException("Could not sign in: data access failed.", e);
        }
    }

    public static void SignOut(String sessionId) throws ServiceException {
        try {
            getDao().removeSession(sessionId);
        } catch (DaoException e) {
            throw new ServiceException("Could not sign out: data access failed.", e);
        }
    }

    public static void Register(UserCredentials credentials) throws ServiceException {
        try {
            getDao().addUser(new User(credentials, AccessLevel.USER));
        } catch (DaoException e) {
            throw new ServiceException("Could not register: data access failed.", e);
        }
    }

    public static boolean Authenticate(String sessionId, AccessLevel requestedAccess) throws ServiceException {
        try {
            Session session = getDao().getSession(sessionId);

            if (session == null)
                return false;

            return session.accessLevel.ordinal() >= requestedAccess.ordinal();
        } catch (DaoException e) {
            throw new ServiceException("Could not sign in: data access failed.", e);
        }
    }

    private static AuthorizationDao getDao() throws DaoException {
        return DaoFactory.getDaoFactory().getAuthorizationDao();
    }

}

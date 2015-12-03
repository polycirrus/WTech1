package by.bsuir.lab01.service;

import by.bsuir.lab01.bean.SessionInfo;
import by.bsuir.lab01.bean.UserCredentials;
import by.bsuir.lab01.dao.AuthorizationDao;
import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.dao.DaoFactory;
import by.bsuir.lab01.entity.AccessLevel;
import by.bsuir.lab01.entity.Session;
import by.bsuir.lab01.entity.User;

import java.util.UUID;

/**
 * Provides authorization and authentication functionality.
 */
public class AuthorizationService {
    /**
     * Creates a new session using the specified user credentials.
     *
     * @param credentials a {@link by.bsuir.lab01.bean.UserCredentials} object containing the user's login and password
     * @return the id of the new session if one was created; otherwise, null
     * @throws ServiceException
     */
    public static SessionInfo SignIn(UserCredentials credentials) throws ServiceException {
        try {
            AuthorizationDao dao = getDao();

            User target = dao.getUser(credentials.getEmailAddress());

            if (target == null || !target.getPasswordHash().equals(User.getHashString(credentials.getPassword())))
                throw new ServiceException("Invalid login and password combination.");

            String sessionId = UUID.randomUUID().toString();
            dao.addSession(new Session(sessionId, target.getAccessLevel()));

            SessionInfo sessionInfo = new SessionInfo();
            sessionInfo.setAccessLevel(target.getAccessLevel());
            sessionInfo.setSessionId(sessionId);

            return sessionInfo;
        } catch (DaoException e) {
            throw new ServiceException("Could not sign in: data access failed.", e);
        }
    }

    /**
     * Ends the specified session.
     *
     * @param sessionId the id of the session to be ended
     * @throws ServiceException
     */
    public static void SignOut(String sessionId) throws ServiceException {
        try {
            getDao().removeSession(sessionId);
        } catch (DaoException e) {
            throw new ServiceException("Could not sign out: data access failed.", e);
        }
    }

    /**
     * Registers a new user.
     *
     * @param credentials a {@link by.bsuir.lab01.bean.UserCredentials} object containing the new user's login and password
     * @throws ServiceException
     */
    public static void Register(UserCredentials credentials) throws ServiceException {
        try {
            getDao().addUser(new User(credentials, AccessLevel.USER));
        } catch (DaoException e) {
            throw new ServiceException("Could not register: data access failed.", e);
        }
    }

    /**
     * Determines whether a user can access resources that require the specified access level.
     *
     * @param sessionId       the id of the requesting user's sessions
     * @param requestedAccess the access level of the requested resource
     * @return true if the user has access to the resource; otherwise, false
     * @throws ServiceException
     */
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

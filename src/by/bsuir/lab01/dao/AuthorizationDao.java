package by.bsuir.lab01.dao;

import by.bsuir.lab01.entity.Session;
import by.bsuir.lab01.entity.User;
import java.util.Collection;

public interface AuthorizationDao {
    public Collection<User> getUsers() throws DaoException;
    public User getUser(String login) throws DaoException;
    public void addUser(User newUser) throws DaoException;

    public Collection<Session> getSessions() throws DaoException;
    public Session getSession(String sessionId) throws DaoException;
    public void addSession(Session newSession) throws DaoException;
    public void removeSession(String sessionId) throws DaoException;
}

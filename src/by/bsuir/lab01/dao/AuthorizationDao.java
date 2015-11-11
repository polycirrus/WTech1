package by.bsuir.lab01.dao;

import by.bsuir.lab01.entity.User;
import java.util.Collection;

public interface AuthorizationDao {
    public Collection<User> getUsers() throws DaoException;
    public void addUser(User newUser) throws DaoException;
}

package by.bsuir.lab01.dao.file;

import by.bsuir.lab01.dao.AuthorizationDao;
import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.entity.AccessLevel;
import by.bsuir.lab01.entity.Session;
import by.bsuir.lab01.entity.User;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

import static by.bsuir.lab01.entity.AccessLevel.valueOf;

public class FileAuthorizationDao implements AuthorizationDao {

    private final static FileAuthorizationDao instance = new FileAuthorizationDao();

    private static final String usersFileName = "users.txt";
    private static final String sessionsFileName = "sessions.txt";

    private FileAuthorizationDao(){}

    public static FileAuthorizationDao getInstance(){
        return instance;
    }

    @Override
    public Collection<User> getUsers() throws DaoException {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(usersFileName), Charset.defaultCharset());
        }
        catch (IOException exception) {
            throw new DaoException(String.format("An error occurred while reading from file %s.", usersFileName), exception);
        }

        List<User> users = new ArrayList<>(lines.size());
        for (String line : lines) {
            try {
                users.add(parseUserLine(line));
            }
            catch (IllegalArgumentException exception) {
                //log
            }
        }

        return users;
    }

    @Override
    public User getUser(String login) throws DaoException {
        Collection<User> users = getUsers();
        return users.stream().filter(user -> user.getLogin().equals(login)).findFirst().orElse(null);
    }

    @Override
    public void addUser(User newUser) throws DaoException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(usersFileName, true));

            writer.write(userToString(newUser));
            writer.newLine();
        }
        catch (IOException exception) {
            throw new DaoException(String.format("An error occurred while writing to file %s.", usersFileName), exception);
        }
        finally {
            try {
                if (writer != null)
                    writer.close();
            }
            catch (IOException exception) {}
        }
    }

    @Override
    public Collection<Session> getSessions() throws DaoException {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(sessionsFileName), Charset.defaultCharset());
        }
        catch (IOException exception) {
            throw new DaoException(String.format("An error occurred while reading from file %s.", sessionsFileName), exception);
        }

        List<Session> sessions = new ArrayList<>(lines.size());
        for (String line : lines) {
            try {
                sessions.add(parseSessionLine(line));
            }
            catch (IllegalArgumentException exception) {
                //log
            }
        }

        return sessions;
    }

    @Override
    public Session getSession(String sessionId) throws DaoException {
        Collection<Session> sessions = getSessions();
        return sessions.stream().filter(session -> session.sessionId.equals(sessionId)).findFirst().orElse(null);
    }

    @Override
    public void addSession(Session newSession) throws DaoException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(sessionsFileName, true));

            writer.write(sessionToString(newSession));
            writer.newLine();
        }
        catch (IOException exception) {
            throw new DaoException(String.format("An error occurred while writing to file %s.", usersFileName), exception);
        }
        finally {
            try {
                if (writer != null)
                    writer.close();
            }
            catch (IOException exception) {}
        }
    }

    @Override
    public void removeSession(String sessionId) throws DaoException {
        Session[] newSessions = getSessions().stream().filter(session -> !session.sessionId.equals(sessionId)).toArray(Session[]::new);

        try {
            Files.delete(Paths.get(sessionsFileName));
        } catch (IOException exception) {
            throw new DaoException(String.format("An error occurred while writing to file %s.", sessionsFileName), exception);
        }

        for (Session session : newSessions) {
            addSession(session);
        }
    }

    private static User parseUserLine(String line) throws IllegalArgumentException {
        String[] fields = line.split("\0");
        if (fields.length != 3)
            throw new IllegalArgumentException(String.format("\"%s\": illegal format: each line must contain 3 null-separated elements.", line));
        if (fields[0].length() == 0 || fields[1].length() == 0 || fields[2].length() == 0)
            throw new IllegalArgumentException(String.format("\"%s\": illegal format: all 3 fields must be present.", line));

        AccessLevel accessLevel;
        try {
            accessLevel = AccessLevel.valueOf(fields[2]);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("\"%s\": %s is not a valid access modifier.", line, fields[2]));
        }

        return new User(fields[0], fields[1], accessLevel);
    }

    private static String userToString(User user) {
        StringBuilder builder = new StringBuilder(user.getLogin());
        builder.append('\0');
        builder.append(user.getPasswordHash());
        builder.append('\0');
        builder.append(user.getAccessLevel().toString());
        return builder.toString();
    }

    private static Session parseSessionLine(String line) throws IllegalArgumentException {
        String[] fields = line.split("\0");
        if (fields.length != 2)
            throw new IllegalArgumentException(String.format("\"%s\": illegal format: each line must contain 2 null-separated elements.", line));
        if (fields[0].length() == 0 || fields[0].length() == 0)
            throw new IllegalArgumentException(String.format("\"%s\": illegal format: both fields must be present.", line));

        AccessLevel accessLevel;
        try {
            accessLevel = valueOf(fields[1]);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("\"%s\": illegal format: %s is not a valid access level.", line, fields[1]));
        }

        return new Session(fields[0], accessLevel);
    }

    private static String sessionToString(Session session) {
        StringBuilder builder = new StringBuilder(session.sessionId);
        builder.append('\0');
        builder.append(session.accessLevel.toString());
        return builder.toString();
    }

}

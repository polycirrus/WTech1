package by.bsuir.lab01.dao.file;

import by.bsuir.lab01.dao.AuthorizationDao;
import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.entity.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FileAuthorizationDao implements AuthorizationDao {

    private final static FileAuthorizationDao instance = new FileAuthorizationDao();

    private static final String fileName = "users.txt";

    private FileAuthorizationDao(){}

    public static FileAuthorizationDao getInstance(){
        return instance;
    }

    @Override
    public Collection<User> getUsers() throws DaoException {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(fileName));
        }
        catch (IOException exception) {
            throw new DaoException(String.format("An error occurred while reading from file %s.", fileName), exception);
        }

        List<User> users = new ArrayList<>(lines.size());
        for (String line : lines) {
            try {
                users.add(parseLine(line));
            }
            catch (IllegalArgumentException exception) {
                //log
            }
        }

        return users;
    }

    @Override
    public void addUser(User newUser) throws DaoException {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(fileName, true));

            writer.write(userToString(newUser));
            writer.newLine();

            writer.close();
        }
        catch (IOException exception) {
            throw new DaoException(String.format("An error occurred while writing to file %s.", fileName), exception);
        }
    }

    private User parseLine(String line) throws IllegalArgumentException {
        String[] fields = line.split("\0");
        if (fields.length != 2)
            throw new IllegalArgumentException(String.format("\"%s\": illegal format: each line must contain 2 null-separated elements.", line));
        if (fields[0].length() == 0 || fields[0].length() == 0)
            throw new IllegalArgumentException(String.format("\"%s\": illegal format: both fields must be present.", line));

        return new User(fields[0], fields[1]);
    }

    private String userToString(User user) {
        StringBuilder builder = new StringBuilder(user.getLogin());
        builder.append('\0');
        builder.append(user.getPasswordHash());
        return builder.toString();
    }
}

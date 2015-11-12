package by.bsuir.lab01.start;

import by.bsuir.lab01.dao.AuthorizationDao;
import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.dao.DaoFactory;
import by.bsuir.lab01.dao.LibraryDao;
import by.bsuir.lab01.entity.AccessLevel;
import by.bsuir.lab01.entity.Book;
import by.bsuir.lab01.entity.User;

import java.util.ArrayList;
import java.util.Collection;

public class Main {

	public static void main(String[] args) {
		Book book1 = new Book("Book1", null, null, null);
		Book book2 = new Book("Book2", "Author2", null, null);
		Book book3 = new Book("Book3", "Author3", "0003", null);
		Book book4 = new Book("Book4", "Author4", "0004", "2004");
        Book book5 = new Book("Book5", null, "0005", "2005");
        Book book6 = new Book("Book6", "Author6", null, "2006");
        Book book7 = new Book("Book7", null, null, "2007");
        Book book8 = new Book("Book8", null, "0008", null);

        User user1 = new User("user1", "fgsfds1", AccessLevel.USER, true);

        Collection<Book> books;
        ArrayList<User> users;
        try {
            LibraryDao dao = DaoFactory.getDaoFactory().getLibraryDao();
            AuthorizationDao aDao = DaoFactory.getDaoFactory().getAuthorizationDao();

//            dao.addBook(book1);
//            dao.addBook(book2);
//            dao.addBook(book3);
//            dao.addBook(book4);
//            dao.addBook(book5);
//            dao.addBook(book6);
//            dao.addBook(book7);
//            dao.addBook(book8);
//
//            books = dao.getBooks();

            aDao.addUser(user1);

            users = (ArrayList<User>)aDao.getUsers();
            System.out.println(user1.getPasswordHash());
            System.out.println(users.get(0).getPasswordHash());
            System.out.println(user1.getPasswordHash().equals(users.get(0).getPasswordHash()));
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            return;
        }

//        for (Book book : books) {
//            System.out.println(book);
//        }
	}

}

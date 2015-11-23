package by.bsuir.lab01.start;

import by.bsuir.lab01.dao.DaoException;
import by.bsuir.lab01.dao.DaoFactory;
import by.bsuir.lab01.dao.LibraryDao;
import by.bsuir.lab01.entity.AccessLevel;
import by.bsuir.lab01.entity.Book;
import by.bsuir.lab01.entity.User;
import by.bsuir.lab01.bean.UserCredentials;

import java.util.ArrayList;
import java.util.Collection;

public class Main {

	public static void main(String[] args) {
        Book book1 = new Book("Book1", null, null, true);
        Book book2 = new Book("Book2", "Author2", null, false);
        Book book3 = new Book("Book3", "Author2", "0003", true);
        Book book4 = new Book("Book4", "Author4", "0004", false);
        Book book5 = new Book("Book5", null, "0005", true);
        Book book6 = new Book("Book6", "Author6", null, false);
        Book book7 = new Book("Book7", null, null, true);
        Book book8 = new Book("Book8", null, "0008", false);

        User user1 = new User("user1", "fgsfds1", , AccessLevel.USER, true);
        UserCredentials cred1 = new UserCredentials("user1", "fgsfds1");

        Collection<Book> books;
        ArrayList<User> users;
        try {
//            AuthorizationService.Register(cred1);
//
//            String sid = AuthorizationService.SignIn(cred1);
            LibraryDao dao = DaoFactory.getDaoFactory().getLibraryDao();
//            AuthorizationDao aDao = DaoFactory.getDaoFactory().getAuthorizationDao();

//            dao.addBook(book1);
//            dao.addBook(book2);
//            dao.addBook(book3);
//            dao.addBook(book4);
//            dao.addBook(book5);
//            dao.addBook(book6);
//            dao.addBook(book7);
//            dao.addBook(book8);

            for (Book book : dao.getBooks()) {
                System.out.println(book);
            }
//
//            books = dao.getBooks();

//            aDao.addUser(user1);
//
//            users = (ArrayList<User>)aDao.getUsers();
//            System.out.println(user1.getPasswordHash());
//            System.out.println(users.get(0).getPasswordHash());
//            System.out.println(user1.getPasswordHash().equals(users.get(0).getPasswordHash()));

//            String sid = AuthorizationService.SignIn(cred1);
//            AuthorizationService.SignOut(sid);
//            LibraryService.addBook(sid, book6);
        } catch (DaoException/* | ServiceException*/ e) {
            System.out.println(e.getMessage());
            return;
        }

//        for (Book book : books) {
//            System.out.println(book);
//        }
	}

}

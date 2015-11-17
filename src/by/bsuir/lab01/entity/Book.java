package by.bsuir.lab01.entity;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean physical;

    public Book(String title, boolean physical) throws IllegalArgumentException {
        this(title, null, null, physical);
    }

    public Book(String title, String author, String isbn, boolean physical) throws IllegalArgumentException {
        this.setTitle(title);
        this.setAuthor(author);
        this.setIsbn(isbn);
        this.setPhysical(physical);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws IllegalArgumentException {
        if (title == null)
            throw new IllegalArgumentException("Title field cannot be null.");

        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isPhysical() {
        return physical;
    }

    public void setPhysical(boolean physical) {
        this.physical = physical;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder(title);
        if (getAuthor() != null) {
            sb.insert(0, " - ");
            sb.insert(0, author);
        }
        if (getIsbn() != null) {
            sb.append(", ISBN ");
            sb.append(isbn);
        }
        sb.insert(0, physical ? "Book: " : "E-book: ");

        return sb.toString();
    }
}

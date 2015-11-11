package by.bsuir.lab01.entity;

import java.io.Serializable;
import java.util.Date;

public class Book {
    protected String title;
    protected String author;
    protected String isbn;
    protected String publicationDate;
    
    public Book(String title, String author, String isbn, String publicationDate){
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationDate = publicationDate;
    }
    
    public String getTitle(){
        return title;
    }
    
    public String getAuthor(){
        return author;
    }
    
    public String getIsbn(){
        return title;
    }
    
    public String getPublicationDate(){
        return publicationDate;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder(title);
        if (author != null){
            sb.insert(0, " - ");
            sb.insert(0, author);
        }
        if (publicationDate != null){
            sb.append(", ");
            sb.append(publicationDate);
        }
        if (isbn != null){
            sb.append(", ISBN ");
            sb.append(isbn);
        }
        
        return sb.toString();
    }
}

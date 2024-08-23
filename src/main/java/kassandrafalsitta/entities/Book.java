package kassandrafalsitta.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book extends Catalog {
    //attributi
    private String author;
    private String genre;

    //costruttore
    public Book() {

    }

    public Book(String title, int yearOfPublication, int numberOfPages, String author, String genre) {
        super(title, yearOfPublication, numberOfPages);
        this.author = author;
        this.genre = genre;
    }

    //getter e setter

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    //to string

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}

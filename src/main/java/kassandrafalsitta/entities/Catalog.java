package kassandrafalsitta.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "catalog")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type_catalog")
public abstract class Catalog {
    //attributi
    @Id
    @GeneratedValue
    @Column(name = "code_isbn")
    private UUID codeIsbn;
    private String title;
    @Column(name = "year_of_publication")
    private int yearOfPublication;
    @Column(name = "number_of_pages")
    private int numberOfPages;

    @OneToMany(mappedBy = "loanedElement")
    private List<Loan> loanList;

    //costruttore

    public Catalog() {

    }

    public Catalog(String title, int yearOfPublication, int numberOfPages) {
        this.title = title;
        this.yearOfPublication = yearOfPublication;
        this.numberOfPages = numberOfPages;
    }

    // getter e setter


    public List<Loan> getLoanList() {
        return loanList;
    }

    public void setLoanList(List<Loan> loanList) {
        this.loanList = loanList;
    }

    public UUID getCodeIsbn() {
        return codeIsbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "codeIsbn=" + codeIsbn +
                ", title='" + title + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                ", numberOfPages=" + numberOfPages +
                ", loanList=" + loanList +
                '}';
    }
}
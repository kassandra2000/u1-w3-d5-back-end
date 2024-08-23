package kassandrafalsitta.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    // Attributi
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private UUID userId;

    private String name;
    private String surname;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth; // Corretto qui

    @Column(name = "card_number")
    private int cardNumber;

    @OneToMany(mappedBy = "user")
    private List<Loan> loanList;

    // Costruttori
    public User() {
    }

    public User(String name, String surname, LocalDate dateOfBirth, int cardNumber) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth; // Corretto qui
        this.cardNumber = cardNumber;
    }

    // Getter e Setter
    public UUID getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() { // Corretto qui
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) { // Corretto qui
        this.dateOfBirth = dateOfBirth;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public List<Loan> getLoanList() { // Riattivato
        return loanList;
    }

    public void setLoanList(List<Loan> loanList) { // Riattivato
        this.loanList = loanList;
    }

    // Metodo toString
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBirth=" + dateOfBirth + // Corretto qui
                ", cardNumber=" + cardNumber +
                '}';
    }
}

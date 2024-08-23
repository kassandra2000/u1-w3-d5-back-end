package kassandrafalsitta.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "loans")
public class Loan {
    //attributi
    @Id
    @GeneratedValue
    @Column(name = "loan_id")
    private UUID loanId;
    @ManyToOne
    @JoinColumn(name = "loaned_element_id", nullable = false)
    private Catalog loanedElement; //elemento prestato

    @Column(name = "loaned_start_date")
    private LocalDate loanStartDate; // data inizio prestito
    @Column(name = "expected_repayment_date")
    private LocalDate expectedRepaymentDate; // data restituzione prestito prevista
    @Column(name = "actual_repayment_date")
    private LocalDate actualRepaymentDate; // data restituzione prestito effettiva

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //costruttori

    public Loan() {

    }

    public Loan(User user, Catalog loanedElement, LocalDate loanStartDate, LocalDate expectedRepaymentDate, LocalDate actualRepaymentDate) {
        this.user = user;
        this.loanedElement = loanedElement;
        this.loanStartDate = loanStartDate;
        this.expectedRepaymentDate = expectedRepaymentDate;
        this.actualRepaymentDate = actualRepaymentDate;
    }

    //getter e setter


    public UUID getLoanId() {
        return loanId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Catalog getLoanedElement() {
        return loanedElement;
    }

    public void setLoanedElement(Catalog loanedElement) {
        this.loanedElement = loanedElement;
    }

    public LocalDate getLoanStartDate() {
        return loanStartDate;
    }

    public void setLoanStartDate(LocalDate loanStartDate) {
        this.loanStartDate = loanStartDate;
    }

    public LocalDate getExpectedRepaymentDate() {
        return expectedRepaymentDate;
    }

    public void setExpectedRepaymentDate(LocalDate expectedRepaymentDate) {
        this.expectedRepaymentDate = expectedRepaymentDate;
    }

    public LocalDate getActualRepaymentDate() {
        return actualRepaymentDate;
    }

    public void setActualRepaymentDate(LocalDate actualRepaymentDate) {
        this.actualRepaymentDate = actualRepaymentDate;
    }

    //to string

    @Override
    public String toString() {
        return "Loan{" +
                "loanId=" + loanId +
                ", user=" + user +
                ", loanedElement=" + loanedElement +
                ", loanStartDate=" + loanStartDate +
                ", expectedRepaymentDate=" + expectedRepaymentDate +
                ", actualRepaymentDate=" + actualRepaymentDate +
                '}';
    }
}


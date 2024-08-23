package kassandrafalsitta.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import kassandrafalsitta.entities.Loan;

import java.util.List;

public class LoanDAO {
    //attributi
    private final EntityManager em;

    //costruttore
    public LoanDAO(EntityManager em) {
        this.em = em;
    }

    //metodi
    public void save(List<Loan> loanList) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        for (Loan loan : loanList) {
            em.persist(loan);
        }
        transaction.commit();
        System.out.println("I prestiti sono stati aggiunti con successo");
    }

}

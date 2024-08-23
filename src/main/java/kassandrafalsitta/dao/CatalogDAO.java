package kassandrafalsitta.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import kassandrafalsitta.entities.Book;
import kassandrafalsitta.entities.Catalog;
import kassandrafalsitta.entities.Loan;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class CatalogDAO {
    //attributi
    private final EntityManager em;

    //costruttore
    public CatalogDAO(EntityManager em) {
        this.em = em;
    }

    //metodi
    public void save(List<Catalog> catalogList) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        for (Catalog catalog : catalogList) {
            em.persist(catalog);
        }
        transaction.commit();
        System.out.println("gli cataloghi sono stati aggiunti con successo");
    }

    public Catalog findById(UUID catalogId) {
        Catalog catalogFound = null;
        try {
            catalogFound = em.find(Catalog.class, catalogId);

            if (catalogFound == null) System.out.println("L'cataloghi con id: " + catalogId + " non è stato trovato");
            else {
                System.out.println("\nEcco l'cataloghi che hai cercato:");
                System.out.println(catalogFound);
            }

        } catch (Exception e) {
            System.out.println("Non è stato possibile cercare l'cataloghi con id: " + catalogId + "\n");
        }
        return catalogFound;
    }

    public void findByIdAndDelete(UUID catalogId) {
        try {
            Catalog catalogFound = this.findById(catalogId);
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(catalogFound);
            transaction.commit();
            System.out.println("Il'catalogo con id: " + catalogFound.getTitle() + "  è stato rimosso con successo");

        } catch (IllegalArgumentException e) {
            System.out.println("l'elemento da eliminare con id: " + catalogId + " non esiste");
        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    public List<Catalog> findByYearOfProduction(int year) {
        TypedQuery<Catalog> query = em.createQuery("SELECT c FROM Catalog c WHERE c.yearOfPublication=:year", Catalog.class);
        query.setParameter("year", year);
        return query.getResultList();
    }

    public List<Book> findByAuthor(String author) {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.author=:author", Book.class);
        query.setParameter("author", author);
        return query.getResultList();
    }

    public List<Catalog> findByPartialTitle(String title) {
        TypedQuery<Catalog> query = em.createQuery("SELECT c FROM Catalog c WHERE LOWER(c.title) LIKE LOWER(:title)", Catalog.class);
        query.setParameter("title", title + "%");
        return query.getResultList();
    }

    public List<Loan> findByLoansFromCardNumber(Integer cardNumber) {
        LocalDate today = LocalDate.now();
        TypedQuery<Loan> query = em.createQuery(
                "SELECT l FROM Loan l WHERE l.user.cardNumber = :cardNumber " +
                        "AND (l.expectedRepaymentDate IS NULL OR l.expectedRepaymentDate >= :today) " +
                        "AND (l.actualRepaymentDate IS NULL OR l.actualRepaymentDate >= :today)",
                Loan.class);
        query.setParameter("cardNumber", cardNumber);
        query.setParameter("today", today);
        return query.getResultList();
    }

    public List<Loan> findExpiredAndUnreturnedLoans() {
        LocalDate today = LocalDate.now();
        TypedQuery<Loan> query = em.createQuery(
                "SELECT l FROM Loan l WHERE l.expectedRepaymentDate < :today " +
                        "AND l.actualRepaymentDate IS NULL",
                Loan.class);
        query.setParameter("today", today);
        return query.getResultList();
    }
}

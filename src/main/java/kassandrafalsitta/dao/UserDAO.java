package kassandrafalsitta.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import kassandrafalsitta.entities.User;

import java.util.List;

public class UserDAO {
    //attributi
    private final EntityManager em;

    //costruttore
    public UserDAO(EntityManager em) {
        this.em = em;
    }

    //metodi
    public void save(List<User> userList) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        for (User user : userList) {
            em.persist(user);
        }
        transaction.commit();
        System.out.println("gli utenti sono stati aggiunti con successo");
    }

}

package kassandrafalsitta;
//        LocalDate.of(r.nextInt(2023, 2025), r.nextInt(1, 13), r.nextInt(1, 29))

import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import kassandrafalsitta.dao.CatalogDAO;
import kassandrafalsitta.dao.LoanDAO;
import kassandrafalsitta.dao.UserDAO;
import kassandrafalsitta.entities.*;
import kassandrafalsitta.enums.Periodicity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Supplier;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u1w3d5");
    public static List<Catalog> catalog = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static Faker fk = new Faker();
    static Random r = new Random();
    public static Supplier<Book> bookCreateOne = () -> new Book(fk.book().title(), r.nextInt(2023, 2025), r.nextInt(20, 100), fk.book().author(), fk.book().genre());
    public static Supplier<Magazine> magazineCreateOne = () -> {
        Periodicity[] periodicityList = Periodicity.values();

        return new Magazine(fk.book().title(), r.nextInt(2023, 2025), r.nextInt(20, 100), periodicityList[r.nextInt(0, periodicityList.length)]);
    };
    public static Supplier<User> userCreateOne = () -> {
        LocalDate date = fk.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return new User(fk.name().firstName(), fk.name().lastName(), date, r.nextInt(1, 10000));
    };

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        EntityManager em = emf.createEntityManager();
        //dao
        CatalogDAO ct = new CatalogDAO(em);
        LoanDAO ld = new LoanDAO(em);
        UserDAO us = new UserDAO(em);
        //liste
        List<Catalog> catalogs = new ArrayList<>();
        List<User> users = new ArrayList<>();
        List<Loan> loans = new ArrayList<>();
        users = em.createQuery("SELECT u FROM User u", User.class).getResultList();
        catalogs = em.createQuery("SELECT c FROM Catalog c", Catalog.class).getResultList();
        loans = em.createQuery("SELECT l FROM Loan l", Loan.class).getResultList();

        System.out.println("----------------es 4--------------------");
        System.out.println(ct.findByYearOfProduction(2023));
        System.out.println("----------------es 5--------------------");
        System.out.println(ct.findByAuthor("Debra Hermiston"));
        System.out.println("----------------es 6--------------------");
        System.out.println(ct.findByPartialTitle("t"));
        System.out.println("----------------es 7--------------------");
        System.out.println(ct.findByLoansFromCardNumber(16));
        System.out.println("----------------es 8--------------------");
        System.out.println(ct.findExpiredAndUnreturnedLoans());


//        while (true) {
//            try {
//                System.out.println("vuoi creare un catalogo?");
//                System.out.println("1. Crea catalogo \n2. Salva catalogo  \n3. Cerca catalogo \n4. Elimina catalogo\n5. Crea persona\n6. Salva persona\n7. Crea prestiti\n8. Salva prestiti\n9. Esci");
//                String choice = sc.nextLine();
//                switch (choice) {
//
//                    case "1":
//                        catalogs = createCatalog();
//                        System.out.println(catalogs);
//                        break;
//                    case "2":
//                        ct.save(catalogs);
//                        break;
//                    case "3":
//                        try {
//                            System.out.println("Quale catalogo vuoi cercare tramite id?");
//                            UUID findId = UUID.fromString(sc.nextLine());
//                            ct.findById(findId);
//
//                        } catch (NumberFormatException e) {
//                            System.out.println("Inserisci il formato corretto\n");
//
//                        } catch (Exception e) {
//                            System.out.println(e.getMessage());
//
//                        }
//                        break;
//                    case "4":
//                        try {
//                            System.out.println("Quale catalogo vuoi eliminare tramite id?");
//                            UUID findByIdAndDelete = UUID.fromString(sc.nextLine());
//                            ct.findByIdAndDelete(findByIdAndDelete);
//
//                        } catch (NumberFormatException e) {
//                            System.out.println("Inserisci il formato corretto");
//
//                        } catch (Exception e) {
//                            System.out.println(e.getMessage());
//
//                        }
//                        break;
//                    case "5":
//                        users = createUser();
//                        System.out.println(users);
//                        break;
//                    case "6":
//                        us.save(users);
//                        break;
//                    case "7":
//                        users = em.createQuery("SELECT u FROM User u", User.class).getResultList();
//                        catalogs = em.createQuery("SELECT u FROM Catalog u", Catalog.class).getResultList();
//                        loans = createLoan(users.get(r.nextInt(users.size())), catalogs.get(r.nextInt(catalogs.size())));
//                        System.out.println(loans);
//                        break;
//                    case "8":
//                        ld.save(loans);
//                        break;
//
//                    case "9":
//                        break;
//                    default:
//                        System.out.println("il valore non è valido");
//                        break;
//                }
//                if (choice.equals("9")) {
//                    System.out.println("Uscita dal programma...");
//                    break;
//                }
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }


        em.close();
        emf.close();
    }

    public static Loan loanCreateOne(User user, Catalog catalog) {
        LocalDate date = LocalDate.of(2024, r.nextInt(1, 13), r.nextInt(1, 29));
        return new Loan(user, catalog, date, date.plusDays(30), date.plusDays(r.nextInt(1, 30)));
    }

    public static List<Catalog> createCatalog() {
        List<Catalog> catalogList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int numOfCatalogs;
        while (true) {
            System.out.println("quanti cataloghi vuoi creare?");
            try {
                numOfCatalogs = Integer.parseInt(sc.nextLine());
                break;
            } catch (InputMismatchException e) {
                System.out.println("inserisci un numero valido");

            } catch (Exception e) {
                System.out.println("Errore: " + e.getMessage());
            }

        }

        System.out.println("1. Crea libro \n 2. Crea Rivista");
        int choice;
        try {
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    for (int i = 0; i < numOfCatalogs; i++) {
                        catalogList.add(bookCreateOne.get());
                    }
                    System.out.println("Libro creato con successo");
                    break;
                case 2:
                    for (int i = 0; i < numOfCatalogs; i++) {
                        catalogList.add(magazineCreateOne.get());
                    }
                    System.out.println("Rivista creata con successo");
                    break;
                default:
                    System.out.println("scelta non valida riprova!");
                    break;
            }

        } catch (InputMismatchException e) {
            System.out.println("inserisci un numero valido");

        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }

        return catalogList;
    }

    public static List<User> createUser() {
        List<User> userList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int numOfUsers;
        while (true) {
            System.out.println("quanti persone vuoi creare?");
            try {
                numOfUsers = Integer.parseInt(sc.nextLine());
                break;
            } catch (InputMismatchException e) {
                System.out.println("inserisci un numero valido");

            } catch (Exception e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
        try {
            for (int i = 0; i < numOfUsers; i++) {
                userList.add(userCreateOne.get());
            }
            System.out.println("persona creati con successo");
        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }

        return userList;
    }

    public static List<Loan> createLoan(User user, Catalog catalog) {
        List<Loan> loanList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int numOfLoans;
        while (true) {
            System.out.println("quanti prestiti vuoi creare?");
            try {
                numOfLoans = Integer.parseInt(sc.nextLine());
                break;
            } catch (InputMismatchException e) {
                System.out.println("inserisci un numero valido");

            } catch (Exception e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
        try {
            for (int i = 0; i < numOfLoans; i++) {
                loanList.add(loanCreateOne(user, catalog));
            }
            System.out.println("prestiti creati con successo");
        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }

        return loanList;
    }

}

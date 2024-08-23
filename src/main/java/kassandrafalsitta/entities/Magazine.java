package kassandrafalsitta.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import kassandrafalsitta.enums.Periodicity;

@Entity
@Table(name = "magazines")
public class Magazine extends Catalog {
    //attributi
    @Enumerated(EnumType.STRING)
    private Periodicity periodicity;

    //costruttore
    public Magazine() {
    }

    public Magazine(String title, int yearOfPublication, int numberOfPages, Periodicity periodicity) {
        super(title, yearOfPublication, numberOfPages);
        this.periodicity = periodicity;
    }

    //getter e setter

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

    //to string
    @Override
    public String toString() {
        return "Magazine{" +
                "periodicity=" + periodicity +
                '}';
    }
}

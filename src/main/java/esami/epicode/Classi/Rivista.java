package esami.epicode.Classi;

import esami.epicode.Classi.Enum.Periodicità;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Rivista extends Pubblicazione {
    @Enumerated(EnumType.STRING)
    protected Periodicità periodicità;

    public Rivista(String isbn, String titolo, int anno, int numPagine, Periodicità periodicità) {
        super(isbn, titolo, anno, numPagine);
        this.periodicità = periodicità;
    }

    public Rivista() {
    }

    public Periodicità getPeriodicità() {
        return periodicità;
    }

    public void setPeriodicità(Periodicità periodicità) {
        this.periodicità = periodicità;
    }

    @Override
    public String toString() {
        return "\n" + "Rivista:" + "\n" +
                "isbn= " + isbn + ',' + "\n" +
                "titolo= " + titolo + ',' + "\n" +
                "anno= " + anno + ',' + "\n" +
                "numPagine= " + numPagine + ',' + "\n" +
                "periodicità= " + periodicità + ',' + "\n" +
                "----------";
    }
}

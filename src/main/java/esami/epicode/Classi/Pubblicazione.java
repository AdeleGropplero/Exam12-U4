package esami.epicode.Classi;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pubblicazioni")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_pubblicazione", discriminatorType = DiscriminatorType.STRING)

public abstract class Pubblicazione {
    @Id
    protected String isbn;
    protected String titolo;
    protected int anno;
    protected int numPagine;

    @OneToMany(mappedBy = "pubblicazione")
    private List<Prestito> prestiti;

    public Pubblicazione(String isbn, String titolo, int anno, int numPagine) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.anno = anno;
        this.numPagine = numPagine;
    }

    public Pubblicazione() {
    }

    public String getIsbn() {
        return this.isbn;
    }

    public String getTitolo() {
        return this.titolo;
    }

    public int getAnno() {
        return this.anno;
    }

    public int getNumPagine() {
        return this.numPagine;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public void setNumPagine(int numPagine) {
        this.numPagine = numPagine;
    }

}

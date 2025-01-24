package esami.epicode.Classi;

import esami.epicode.Classi.Enum.Genere;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Libro extends Pubblicazione{
    protected String autore;
    @Enumerated(EnumType.STRING)
    protected Genere genere;

    public Libro(String autore, Genere genere, String isbn, String titolo, int anno, int numPagine){
        super(isbn, titolo, anno, numPagine);
        this.autore=autore;
        this.genere=genere;

    }

    public Libro() {
    }

    public String getAutore() {
        return autore;
    }

    public Genere getGenere() {
        return genere;
    }


    public void setGenere(Genere genere) {
        this.genere = genere;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    @Override
    public String toString() {
        return  "\n" + "Libro: " + "\n" +
                "autore= " + autore + ',' + "\n" +
                "genere= " + genere + ',' + "\n" +
                "isbn= " + isbn + ',' + "\n" +
                "titolo= " + titolo  + ',' + "\n" +
                "anno= " + anno + ',' + "\n" +
                "numPagine= " + numPagine + "\n" +
                "----------";
    }

}

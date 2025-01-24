package esami.epicode.Classi;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
public class Prestito {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_utente") // Nome corretto della colonna FK
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "id_pubblicazione") // Nome corretto della colonna FK
    private Pubblicazione pubblicazione;

    @Column(name = "inizio_prestito")
    private LocalDate inizioPrestito;

    @Column(name = "restituzione_prevista")
    private LocalDate restituzionePrevista;

    @Column(name = "restituzione_effettiva")
    private LocalDate restituzioneEffettiva;

    public Prestito() {
    }

    public Prestito(Utente utente, Pubblicazione pubblicazione, LocalDate inizioPrestito) {
        this.utente = utente;
        this.pubblicazione = pubblicazione;
        this.inizioPrestito = inizioPrestito;
        this.restituzionePrevista = inizioPrestito.plusDays(30);
        this.restituzioneEffettiva = null;
    }

    // Getter e setter aggiornati
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Pubblicazione getPubblicazione() {
        return pubblicazione;
    }

    public void setPubblicazione(Pubblicazione pubblicazione) {
        this.pubblicazione = pubblicazione;
    }

    public LocalDate getInizioPrestito() {
        return inizioPrestito;
    }

    public void setInizioPrestito(LocalDate inizioPrestito) {
        this.inizioPrestito = inizioPrestito;
    }

    public LocalDate getRestituzionePrevista() {
        return restituzionePrevista;
    }

    public void setRestituzionePrevista(LocalDate restituzionePrevista) {
        this.restituzionePrevista = restituzionePrevista;
    }

    public LocalDate getRestituzioneEffettiva() {
        return restituzioneEffettiva;
    }

    public void setRestituzioneEffettiva(LocalDate restituzioneEffettiva) {
        this.restituzioneEffettiva = restituzioneEffettiva;
    }

    @Override
    public String toString() {
        return "Prestito{" +
                "id=" + id +
                ", utente=" + utente +
                ", pubblicazione=" + pubblicazione +
                ", inizioPrestito=" + inizioPrestito +
                ", restituzionePrevista=" + restituzionePrevista +
                ", restituzioneEffettiva=" + restituzioneEffettiva +
                '}';
    }
}

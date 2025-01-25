package esami.epicode;

import com.github.javafaker.Faker;
import esami.epicode.Classi.Enum.Genere;
import esami.epicode.Classi.Enum.Periodicità;
import esami.epicode.Classi.GenerazioneDati.GenerazioneDatiTabelle;
import esami.epicode.Classi.Libro;
import esami.epicode.Classi.Prestito;
import esami.epicode.Classi.Pubblicazione;
import esami.epicode.Classi.Rivista;
import esami.epicode.DAO.PrestitoDAO;
import esami.epicode.DAO.PubblicazioneDAO;
import esami.epicode.DAO.UtenteDAO;
import esami.epicode.Exceptions.PrestitPerTesseraException;
import esami.epicode.Exceptions.PubblicazioneNonDisponibileException;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Random;


public class Main {
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Exam12");
    public static EntityManager em = emf.createEntityManager();

    static Faker faker = new Faker(Locale.ITALY);


    public static void main(String[] args) {
        PubblicazioneDAO pubblicazioneDAO = new PubblicazioneDAO(em);
        UtenteDAO utenteDAO = new UtenteDAO(em);
        PrestitoDAO prestitoDAO = new PrestitoDAO(em);


        GenerazioneDatiTabelle generator = new GenerazioneDatiTabelle();

        /*🟢--------ES.1--------aggiunta di un elemento a catalogo----------------------🟢 */
             /*
              I due generator se lanciati salveranno nel db automaticamente,
              10 libri e 6 riviste con ISBN diversi generati da faker.
              Ho usato un for loop e faker per generare in automatico e
              più velocemente Pubblicazioni e Utenti.
              La logica è da ricercare nella classe GenerazioneDatiTabelle,
              all'interno del package GenerazioneDati.
              */

        // generator.generateBooks(pubblicazioneDAO, faker);
        // generator.generateRiviste(pubblicazioneDAO, faker);

        /*Metodo Classico:*/

        Libro libro1 = new Libro(
                "Tolkien",
                Genere.FANTASY,
                "978-0261103573",
                "Il Signore degli Anelli",
                1954, 1178
        );
        // pubblicazioneDAO.save(libro1);

              /*
              Intanto mi sono generata anche 3 utenti e salvati automaticamente
              con il metodo nel db. In preparazione alle richieste successive.
              */

        // generator.generateUtenti(utenteDAO, faker);
        /*--------------------------------------------------------------------------------*/

        /*Creo un po' di oggetti Prestito e li salvo nel db*/
        Prestito p1 = new Prestito(utenteDAO.getByID(24), pubblicazioneDAO.getByID(14), LocalDate.of(2025, 1, 24));
        //prestitoDAO.save(p1);
        Prestito p2 = new Prestito(utenteDAO.getByID(23), pubblicazioneDAO.getByID(19), LocalDate.of(2025, 2, 14));
        //prestitoDAO.save(p2);
        Prestito p3 = new Prestito(utenteDAO.getByID(25), pubblicazioneDAO.getByID(22), LocalDate.of(2024, 1, 30));
        //prestitoDAO.save(p3);
        p3.setRestituzioneEffettiva(LocalDate.of(2024, 2, 13));
        Prestito p4 = new Prestito(utenteDAO.getByID(25), pubblicazioneDAO.getByID(17), LocalDate.of(2024, 4, 12));
        //prestitoDAO.save(p4);
        p4.setRestituzioneEffettiva(LocalDate.of(2024, 6, 17));
        //prestitoDAO.update(p4);
        Prestito p5 = new Prestito(utenteDAO.getByID(24), pubblicazioneDAO.getByID(19), LocalDate.of(2025, 2, 16));
        prestitoDAO.save(p5);



        /*🟢--------ES.2--------rimozione di un elemento del catalogo dato un ISBN--------------🟢*/
              /*
              Mi prendo un elemento da catalogo con getById (che corrisponde all'ISBN)
              e lo cancello con delete, metodi definiti nella classe DAO.
              Inoltre in console mi stampo l'elemento eliminato.
              */

        Pubblicazione pubblicazione1 = pubblicazioneDAO.getByID(9);
        // pubblicazioneDAO.delete(pubblicazione1);
        System.out.println("Hai eliminato con successo:" + pubblicazione1);

        Pubblicazione pubblicazione2 = pubblicazioneDAO.getByID(11);
        //pubblicazioneDAO.delete(pubblicazione2);
        System.out.println("Hai eliminato con successo:" + pubblicazione2);

        Pubblicazione pubblicazione3 = pubblicazioneDAO.getByID(17);
                /*
                 Qui inserisco una gestione degli errori su questo caso specifico,
                 qui è presente la pubblicazione in un prestito. La console stamperà messaggi di errore
                 più precisi.
                 */
        try {
            pubblicazioneDAO.delete(pubblicazione3); // Tentativo di eliminare la pubblicazione
            System.out.println("Hai eliminato con successo:" + pubblicazione3);
        } catch (PersistenceException e) {
            if (e.getCause() instanceof ConstraintViolationException) {
                System.out.println("Errore: La pubblicazione è ancora referenziata in altri record.");
            } else {
                System.out.println("Errore durante l'eliminazione della pubblicazione: " + e.getMessage());
            }
        } catch (Exception e) {
            // Gestione di altre eccezioni generali
            System.out.println("Errore imprevisto: " + e.getMessage());
        }
        System.out.println("---------------------------------------------------");

        /*----------------------------------------------------------------------------------------*/

        /*🟢--------ES.3-------------ricerca per ISBN-------------------------------------------🟢*/
        pubblicazioneDAO.findByIsbnQuery(pubblicazione2.getIsbn());
        pubblicazioneDAO.findByIsbnQuery("0049726242");
             /*
             Qui ho usato due esempi di come puoi usare il metodo.
             */
        pubblicazioneDAO.findByIsbnQuery("8849726242"); //Qui se non trova l'id restituisce
        // un messaggio in console.
        System.out.println("---------------------------------------------------");
        /*----------------------------------------------------------------------------------------*/

        /*🟢--------ES.4-------------ricerca per ISBN-------------------------------------------🟢*/
        try {
            List<Pubblicazione> pubblicazioniPerAnno = pubblicazioneDAO.findByAnno(2000);
            //Cambiare il valore per effettuare ricerche diverse.
            System.out.println("Le pubblicazioni in archivio per l'anno cercato sono:");
            pubblicazioniPerAnno.forEach(System.out::println);
        } catch (PubblicazioneNonDisponibileException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("---------------------------------------------------");
        /*----------------------------------------------------------------------------------------*/

        /*🟢--------ES.5-------------ricerca per autore------------------------------------------🟢*/
        try {
            List<Pubblicazione> pubblicazioniPerAutore = pubblicazioneDAO.findByAutore("Tolkien");
            //Cambiare il valore per effettuare ricerche diverse.
            System.out.println("Le pubblicazioni in archivio per l'autore cercato sono:");
            pubblicazioniPerAutore.forEach(System.out::println);
        } catch (PubblicazioneNonDisponibileException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("---------------------------------------------------");
        /*----------------------------------------------------------------------------------------*/

        /*🟢--------ES.5----------ricerca per Titolo o parte di esso-----------------------------🟢*/
        try {
            List<Pubblicazione> pubblicazioniPerTitolo = pubblicazioneDAO.findByTitoloLike("ring");
            //Cambiare il valore per effettuare ricerche diverse.
            System.out.println("Le pubblicazioni in archivio per il titolo cercato sono:");
            pubblicazioniPerTitolo.forEach(System.out::println);
        } catch (PubblicazioneNonDisponibileException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("---------------------------------------------------");
        /*----------------------------------------------------------------------------------------*/

        /*🟢--------ES.6----------ricerca Prestiti attivi per Tessera-----------------------------🟢*/
        try {
            List<Prestito> prestitiAttiviPerTessera = prestitoDAO.findByNTessera(25);
            //Cambiare il valore per effettuare ricerche diverse.
            System.out.println("I prestiti in attivo per la tessera richiesta sono :");
            prestitiAttiviPerTessera.forEach(System.out::println);
        } catch (PrestitPerTesseraException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("---------------------------------------------------");
        /*----------------------------------------------------------------------------------------*/

    }
}

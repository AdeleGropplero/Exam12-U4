package esami.epicode;

import com.github.javafaker.Faker;
import esami.epicode.Classi.Enum.Genere;
import esami.epicode.Classi.Enum.Periodicità;
import esami.epicode.Classi.GenerazioneDati.GenerazioneDatiTabelle;
import esami.epicode.Classi.Libro;
import esami.epicode.Classi.Rivista;
import esami.epicode.DAO.PubblicazioneDAO;
import esami.epicode.DAO.UtenteDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Locale;
import java.util.Random;


public class Main {
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Exam12");
    public static EntityManager em = emf.createEntityManager();

    static Faker faker = new Faker(Locale.ITALY);


    public static void main(String[] args) {
        PubblicazioneDAO pubblicazioneDAO = new PubblicazioneDAO(em);
        UtenteDAO utenteDAO = new UtenteDAO(em);

        GenerazioneDatiTabelle generator = new GenerazioneDatiTabelle();
        generator.generateBooks(pubblicazioneDAO, faker); //ho usato un for loop e faker per generare in automatico e più velocemente i libri
        generator.generateRiviste(pubblicazioneDAO, faker);//la logica è da ricercare nella classe GenerazioneDatiTabelle.
        generator.generateUtenti(utenteDAO, faker);
    }


}
